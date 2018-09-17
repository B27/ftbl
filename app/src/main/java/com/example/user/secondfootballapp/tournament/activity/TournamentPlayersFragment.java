package com.example.user.secondfootballapp.tournament.activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.AbbreviationDialogFragment;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;
import com.example.user.secondfootballapp.tournament.adapter.RVTournamentPlayersAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TournamentPlayersFragment extends Fragment {
    Logger log = LoggerFactory.getLogger(TournamentTimeTableFragment.class);
    boolean scrollStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        Spinner spinner;
        final AbbreviationDialogFragment dialogFragment;
        final FloatingActionButton fab;
        NestedScrollView scroller;
        List<String> categories = new ArrayList<String>();
        categories.add("по проведенным мячам");
        categories.add("по забитым мячам");
        categories.add("по количеству ЖК");
        categories.add("по количеству КК");
        categories.add("по количеству дисквалификаций");

        RecyclerView recyclerView;
        log.info("INFO: TournamentPlayersFragment onCreateView 3");
        view = inflater.inflate(R.layout.tournament_info_tab_players, container, false);
        dialogFragment = new AbbreviationDialogFragment();
        fab = (FloatingActionButton) view.findViewById(R.id.playersInfoButton);
        spinner = (Spinner) view.findViewById(R.id.playersSpinner);
        scroller = (NestedScrollView) view.findViewById(R.id.tournamentInfoPlayersScroll);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewTournamentPlayers);
        scrollStatus = false;
//        ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.spinnerItem, android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, R.id.playersSort, categories);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getFragmentManager(), "abbrev");
            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.spinnerItem, R.layout.spinner_item);
        try {
//            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter1.setDropDownViewResource(R.layout.spinner_dropdown);
        } catch (Exception t) {
            log.error("ERROR: from TournamentPlayersFragment", t);
        }

        try {
            spinner.setAdapter(adapter1);
        } catch (Exception t) {
            log.error("ERROR: from TournamentPlayersFragment set Adapter", t);
        }


        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.spinnerItem);
                Toast.makeText(getActivity(), choose[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RVTournamentPlayersAdapter adapter = new RVTournamentPlayersAdapter();
        recyclerView.setAdapter(adapter);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


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

//                if (scrollY == 0) {
//                    Log.i(TAG, "TOP SCROLL");
//                }
//
                if (scrollY == ( v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight() )) {
                    log.info("INFO: RecyclerView scrolled: bottom scroll!");
                    scrollStatus = true;
//                    fab.hide();
                }
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//nothing to do
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                int currentFirstVisible = myLayoutManager.findFirstVisibleItemPosition();
//
//                if (currentFirstVisible > firstVisibleInListview) {
////                    log.info("INFO: RecyclerView scrolled: scroll up!");
//                    PersonalActivity.navigation.animate().translationY(PersonalActivity.navigation.getHeight());
//
//                } else {
////                    log.info("INFO: RecyclerView scrolled: scroll down!");
//                    PersonalActivity.navigation.animate().translationY(0);
//                }
//                firstVisibleInListview = currentFirstVisible;
                if (newState == RecyclerView.SCROLL_STATE_IDLE ) {
                    fab.show();
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    fab.hide();
                }
                if (scrollStatus){
                    fab.hide();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        return view;
    }

    public void SrollListener() {

    }

    @Override
    public void onPause() {
        log.info("INFO: TournamentPlayersFragment onPause 3");

        super.onPause();
    }

    @Override
    public void onDestroy() {
        log.info("INFO: TournamentPlayersFragment onDestroy 3");
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        super.onDestroy();
    }
}
