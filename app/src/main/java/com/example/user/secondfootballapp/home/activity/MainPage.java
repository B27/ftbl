package com.example.user.secondfootballapp.home.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewMainAdsAdapter;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewMainNewsAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends Fragment {
    Logger log = LoggerFactory.getLogger(MainPage.class);
    HomePage homePage = new HomePage();
    AdsPage adsPage = new AdsPage();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        ImageView imageNews;
        ImageView imageAds;
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        view = inflater.inflate(R.layout.activity_main_page, container, false);
        imageAds = (ImageView) view.findViewById(R.id.mainPageAds);
        imageNews = (ImageView) view.findViewById(R.id.mainPageNews);
        Button btnNews = view.findViewById(R.id.showAllNews);
        Button btnAds = view.findViewById(R.id.showAllAds);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_ads_background)
                .apply(new RequestOptions()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH)
                        .centerCrop())
                .into(imageAds);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_news_background)
                .apply(new RequestOptions()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH)
                        .centerCrop())
                .into(imageNews);

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show all news
                log.info("INFO: click all news");
                fragmentManager.beginTransaction().replace(((ViewGroup)getView().getParent()).getId(), homePage).commit();
            }
        });
        btnAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show all ads
                log.info("INFO: click all ads");
                fragmentManager.beginTransaction().replace(((ViewGroup)getView().getParent()).getId(), adsPage).commit();
            }
        });
        RecyclerView recyclerViewNews = (RecyclerView) view.findViewById(R.id.recyclerViewMainNews);
        RecyclerViewMainNewsAdapter adapterNews = new RecyclerViewMainNewsAdapter(getActivity(),this);
        recyclerViewNews.setAdapter(adapterNews);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView recyclerViewAds = (RecyclerView) view.findViewById(R.id.recyclerViewMainAds);
        RecyclerViewMainAdsAdapter adapterAds = new RecyclerViewMainAdsAdapter(getActivity(),this);
        recyclerViewAds.setAdapter(adapterAds);
//        recyclerViewAds.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAds.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}