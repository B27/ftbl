package com.example.user.secondfootballapp.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewAdsAdapter;
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

public class AdsPage extends Fragment {
    Logger log = LoggerFactory.getLogger(AdsPage.class);
    List<Announce> allNews = new ArrayList<>();
    RecyclerView recyclerView;
    NestedScrollView scroller;
    RecyclerViewAdsAdapter adapter;
    int count = 0;
    int limit = 5;
    int offset = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.ads_page, container, false);
        scroller = view.findViewById(R.id.scrollerAds);
        checkConnection();
        recyclerView = view.findViewById(R.id.recyclerViewAds);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        try{
            adapter = new RecyclerViewAdsAdapter(getActivity(),AdsPage.this , allNews);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){log.error("ERROR: " , e);}
        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                offset++;
                int temp = limit*offset;
                if (temp<=count) {
                    String str = String.valueOf(temp);
                    GetAllAds("5", str);
                }
            }
        });
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
                        GetAllAds("5", "0");
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void GetAllAds(String limit, String offset){
        Controller.getApi().getAllAnnounce(limit, offset)
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

    private void saveData(Announces matches) {
        count = matches.getCount();
        allNews.addAll(allNews.size(), matches.getAnnounces());
        List<Announce> list = new ArrayList<>(allNews);
        adapter.dataChanged(list);
    }
}

