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
import android.widget.LinearLayout;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.LeagueInfo;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.tournament.adapter.RecyclerViewTournamentTimeTableAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TournamentTimeTableFragment extends Fragment {
    Logger log = LoggerFactory.getLogger(TournamentTimeTableFragment.class);
    boolean scrollStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        RecyclerView recyclerView;
        NestedScrollView scroller;
        LinearLayout layout;
        Bundle arguments = getArguments();
        LeagueInfo league = (LeagueInfo) arguments.getSerializable("TOURNAMENTINFOMATCHESLEAGUE");
        List<Match> matches = league.getMatches();
        view = inflater.inflate(R.layout.tournament_info_tab_timetable, container, false);
        recyclerView = view.findViewById(R.id.tournamentInfoTabTimetable);
        recyclerView.setNestedScrollingEnabled(false);
        scroller = view.findViewById(R.id.tournamentInfoTimetableScroll);
        layout = view.findViewById(R.id.tournamentInfoTabTimetableEmpty);
        if (matches.size()!=0){
            layout.setVisibility(View.GONE);
        }
        scrollStatus = false;
        RecyclerViewTournamentTimeTableAdapter adapter = new RecyclerViewTournamentTimeTableAdapter(getActivity(), matches, league);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (scrollY > oldScrollY) {
                log.info("INFO: RecyclerView scrolled: scroll down!");
//                    PersonalActivity.navigation.animate().translationY(PersonalActivity.navigation.getHeight());

            }
            if (scrollY < oldScrollY) {
                log.info("INFO: RecyclerView scrolled: scroll up!");
//                    PersonalActivity.navigation.animate().translationY(0);
                scrollStatus = false;
            }
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                log.info("INFO: RecyclerView scrolled: bottom scroll!");
                scrollStatus = true;
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
