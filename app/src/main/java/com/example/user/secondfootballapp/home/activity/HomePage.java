package com.example.user.secondfootballapp.home.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewHomeAdapter;

public class HomePage extends Fragment {
//    DialogFragment dialogFragment;
//AdvertisingFragment dialogFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        Toolbar toolbar;
        view = inflater.inflate(R.layout.page_home, container, false);
//        TextView textView = (TextView) view.findViewById(R.id.textView);

//        dialogFragment = new AdvertisingFragment().newInstance();

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Новости");
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf");
        collapsingToolbar.setExpandedTitleTypeface(tf);
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarHome);
//        toolbar.setTitle("Новости");
//        collapsingToolbar.setTitleEnabled(false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewHome);
        RecyclerViewHomeAdapter adapter = new RecyclerViewHomeAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



//        dialogFragment.show(fragmentManager, "AdvertisingFragment");




//        dialogFragment.show(getFragmentManager(), "dialogFragment");

        return view;
    }
}
