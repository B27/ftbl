package com.example.user.secondfootballapp.home.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.secondfootballapp.home.adapter.RecyclerViewAdsAdapter;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewMainAdsAdapter;
import com.example.user.secondfootballapp.R;

public class AdsPage extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        CollapsingToolbarLayout collapsingToolbar;
        view = inflater.inflate(R.layout.ads_page, container, false);
        collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbarAdsPage);
        collapsingToolbar.setTitle("Объявления");
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf");
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        collapsingToolbar.setExpandedTitleTypeface(tf);

//        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewAds);
        RecyclerViewAdsAdapter adapter = new RecyclerViewAdsAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}

