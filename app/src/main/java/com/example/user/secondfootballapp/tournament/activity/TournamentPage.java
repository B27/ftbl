package com.example.user.secondfootballapp.tournament.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.tournament.adapter.RecyclerViewTournamentAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressLint("ValidFragment")
public class TournamentPage extends Fragment {
    Logger log = LoggerFactory.getLogger(NewsPage.class);

    android.support.v4.app.FragmentManager fragmentManager;
    @SuppressLint("ValidFragment")
    public TournamentPage(android.support.v4.app.FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        view = inflater.inflate(R.layout.page_tournament, container, false);
//        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.tournamentFilterButton);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                log.info("INFO: hello from FloatingActionButton");
//                //filter
//            }
//        });
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.tournamentCollapsingToolbar);
        collapsingToolbar.setTitle("Турниры");
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf");
        collapsingToolbar.setExpandedTitleTypeface(tf);
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewTournament);
        RecyclerViewTournamentAdapter adapter = new RecyclerViewTournamentAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
