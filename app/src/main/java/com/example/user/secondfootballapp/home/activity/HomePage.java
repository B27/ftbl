package com.example.user.secondfootballapp.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewHomeAdapter;
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

public class HomePage extends Fragment {
    Logger log = LoggerFactory.getLogger(HomePage.class);
    List<News_> allNews = new ArrayList<>();
    RecyclerView recyclerView;
    NestedScrollView scroller;
    RecyclerViewHomeAdapter adapter;
    int count = 0;
    int limit = 5;
    int offset = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        view = inflater.inflate(R.layout.page_home, container, false);
        scroller = view.findViewById(R.id.scrollerNews);
        checkConnection();
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        try {
            adapter = new RecyclerViewHomeAdapter(getActivity(),HomePage.this , allNews);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){}
        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                offset++;
                int temp = limit*offset;
                if (temp<=count) {
                    String str = String.valueOf(temp);
                    GetAllNews("5", str);
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
                        GetAllNews("5", "0");
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void GetAllNews(String limit, String offset){
        Controller.getApi().getAllNews(limit, offset)
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
        count = matches.getCount();
        allNews.addAll(allNews.size(), matches.getNews());
        List<News_> list = new ArrayList<>(allNews);
        adapter.dataChanged(list);
    }
}
