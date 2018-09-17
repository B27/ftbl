package com.example.user.secondfootballapp.club.activity;

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
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.club.adapter.RecyclerViewClubAdapter;

public class ClubPage extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        RecyclerView recyclerView;
        CollapsingToolbarLayout collapsingToolbar;
        view = inflater.inflate(R.layout.page_club, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewClubs);
        collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbarClub);
        collapsingToolbar.setTitle("Клубы");
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf");
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbar.setExpandedTitleTypeface(tf);
        collapsingToolbar.setCollapsedTitleTypeface(tf);

        RecyclerViewClubAdapter adapter = new RecyclerViewClubAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
