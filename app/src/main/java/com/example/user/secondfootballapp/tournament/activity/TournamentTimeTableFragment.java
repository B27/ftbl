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
import com.example.user.secondfootballapp.tournament.adapter.RecyclerViewTournamentTimeTableAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TournamentTimeTableFragment extends Fragment {
    Logger log = LoggerFactory.getLogger(TournamentTimeTableFragment.class);
    boolean scrollStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        RecyclerView recyclerView;
        NestedScrollView scroller;
        log.info("INFO: TournamentTimeTableFragment onCreateView 1");
        view = inflater.inflate(R.layout.tournament_info_tab_timetable, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.tournamentInfoTabTimetable);
        scroller = (NestedScrollView) view.findViewById(R.id.tournamentInfoTimetableScroll);
        scrollStatus = false;
        RecyclerViewTournamentTimeTableAdapter adaptet = new RecyclerViewTournamentTimeTableAdapter(getActivity(), this);
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
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    log.info("INFO: RecyclerView scrolled: bottom scroll!");
                    scrollStatus = true;
                }
            }
        });
        return view;
    }


    @Override
    public void onPause() {
        log.info("INFO: TournamentTimeTableFragment onPause  1");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        log.info("INFO: TournamentTimeTableFragment onDestroy 1");
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        super.onDestroy();
    }
}
