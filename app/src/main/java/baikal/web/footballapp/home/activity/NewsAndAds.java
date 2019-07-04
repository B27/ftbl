package baikal.web.footballapp.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import baikal.web.footballapp.CheckError;
import baikal.web.footballapp.Controller;
import baikal.web.footballapp.PersonalActivity;
import baikal.web.footballapp.R;
import baikal.web.footballapp.home.adapter.RecyclerViewMainAdsAdapter;
import baikal.web.footballapp.home.adapter.RecyclerViewMainNewsAdapter;
import baikal.web.footballapp.model.Announce;
import baikal.web.footballapp.model.Announces;
import baikal.web.footballapp.model.News;
import baikal.web.footballapp.model.News_;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsAndAds extends Fragment {
    private final Logger log = LoggerFactory.getLogger(MainPage.class);
    private final HomePage homePage = new HomePage();
    private final AdsPage adsPage = new AdsPage();
    private RecyclerView recyclerViewNews;
    private RecyclerView recyclerViewAds;
    private List<News_> allNews = new ArrayList<>();
    private final List<Announce> announces = new ArrayList<>();
    private RelativeLayout relativeLayout;
    private RelativeLayout relativeLayoutAds;
    private RecyclerViewMainNewsAdapter adapter;
    private RecyclerViewMainAdsAdapter adapterAds;

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
                .subscribe(this::saveAds
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
    private void GetAllNews() {
        allNews = new ArrayList<>();
        Controller.getApi().getAllNews("2", "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(this::saveData
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