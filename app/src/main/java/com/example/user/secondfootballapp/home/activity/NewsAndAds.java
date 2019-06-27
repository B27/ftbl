package com.example.user.secondfootballapp.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewMainAdsAdapter;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewMainNewsAdapter;
import com.example.user.secondfootballapp.model.Announce;
import com.example.user.secondfootballapp.model.Announces;
import com.example.user.secondfootballapp.model.News;
import com.example.user.secondfootballapp.model.News_;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsAndAds extends Fragment {
    Logger log = LoggerFactory.getLogger(MainPage.class);
    HomePage homePage = new HomePage();
    AdsPage adsPage = new AdsPage();
    RecyclerView recyclerViewNews;
    RecyclerView recyclerViewAds;
    List<News_> allNews = new ArrayList<>();
    List<Announce> announces = new ArrayList<>();
    RelativeLayout relativeLayout;
    RelativeLayout relativeLayoutAds;
    RecyclerViewMainNewsAdapter adapter;
    RecyclerViewMainAdsAdapter adapterAds;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        view = inflater.inflate(R.layout.activity_main_page, container, false);
        checkConnection();
        relativeLayout  = view.findViewById(R.id.mainPageNewsLayout);
        relativeLayoutAds  = view.findViewById(R.id.mainPageAdsLayout);
        final Button btnNews = view.findViewById(R.id.showAllNews);
        final Button btnAds = view.findViewById(R.id.showAllAds);
        fragmentManager.beginTransaction().add(R.id.pageContainer, homePage).add(R.id.pageContainer, adsPage).hide(homePage).hide(adsPage).commit();
        btnNews.setOnClickListener(v -> {
            //show all news
            fragmentManager.beginTransaction().hide(PersonalActivity.active).show( homePage).commit();
            PersonalActivity.active = homePage;
        });
        btnAds.setOnClickListener(v -> {
            //show all ads
            fragmentManager.beginTransaction().hide(PersonalActivity.active).show( adsPage).commit();
            PersonalActivity.active = adsPage;
        });
        try{
            recyclerViewNews = view.findViewById(R.id.recyclerViewMainNews);
            recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new RecyclerViewMainNewsAdapter(getActivity(), NewsAndAds.this, allNews);
            relativeLayout.setVisibility(View.GONE);
            recyclerViewNews.setAdapter(adapter);
        }catch (Exception e){log.error("ERROR: ", e);}


        try{
            recyclerViewAds = view.findViewById(R.id.recyclerViewMainAds);
            recyclerViewAds.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapterAds = new RecyclerViewMainAdsAdapter(getActivity(), NewsAndAds.this, announces);
            relativeLayoutAds.setVisibility(View.GONE);
            recyclerViewAds.setAdapter(adapterAds);
        }catch (Exception e){log.error("ERROR: ", e);}


        return view;
    }

    @SuppressLint("CheckResult")
    private void checkConnection() {
        ReactiveNetwork
                .observeNetworkConnectivity(getActivity().getApplicationContext())
                .flatMapSingle(connectivity -> ReactiveNetwork.checkInternetConnectivity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> {
                    // isConnected can be true or false
                    if (isConnected){
                        GetAllNews();
                        GetAllAds();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void GetAllAds() {
        Controller.getApi().getAllAnnounce("2", "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(matches -> saveAds(matches)
                        ,
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(getActivity(), error);
                        }
                );
    }

    private void saveAds(Announces matches) {
        announces.addAll(announces.size(), matches.getAnnounces());
        List<Announce> list = new ArrayList<>(announces);
        adapterAds.dataChanged(list);
    }

    @SuppressLint("CheckResult")
    public void GetAllNews() {
        allNews = new ArrayList<>();
        Controller.getApi().getAllNews("2", "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(matches -> saveData(matches)
                        ,
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(getActivity(), error);
                        }
                );

    }

    private void saveData(News matches) {
        allNews.addAll(allNews.size(), matches.getNews());
        List<News_> list = new ArrayList<>(allNews);
        adapter.dataChanged(list);
    }
}