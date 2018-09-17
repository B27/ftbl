package com.example.user.secondfootballapp.tournament.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.adapter.RVTournamentCommandAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TournamentCommandFragment extends Fragment{
    Logger log = LoggerFactory.getLogger(TournamentTimeTableFragment.class);
    boolean scrollStatus;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        NestedScrollView scroller;
        RecyclerView recyclerView;
        log.info("INFO: TournamentCommandFragment onCreate 2");
        view = inflater.inflate(R.layout.tournament_info_tab_command, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.tournamentInfoTabCommand);
        scroller = (NestedScrollView) view.findViewById(R.id.tournamentInfoCommandScroll);
        scrollStatus = false;
        RVTournamentCommandAdapter adaptet = new RVTournamentCommandAdapter(getActivity(),this);
        recyclerView.setAdapter(adaptet);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    log.info("INFO: RecyclerView scrolled: scroll down!");
                    PersonalActivity.navigation.animate().translationY(PersonalActivity.navigation.getHeight());

                }
                if (scrollY < oldScrollY) {
                    log.info("INFO: RecyclerView scrolled: scroll up!");
                    PersonalActivity.navigation.animate().translationY(0);
                    scrollStatus = false;
                }
                if (scrollY == ( v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight() )) {
                    log.info("INFO: RecyclerView scrolled: bottom scroll!");
                    scrollStatus = true;
                }
            }
        });
        return view;
    }


    @Override
    public void onPause() {
        log.info("INFO: TournamentCommandFragment onPause 2");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        log.info("INFO: TournamentCommandFragment onDestroy 2");
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        super.onDestroy();
    }
}
