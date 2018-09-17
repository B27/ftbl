package com.example.user.secondfootballapp.tournament.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.adapter.ViewPagerTournamentInfoAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tournament extends Fragment {

    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    TabLayout tabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.info("INFO: tournament onCreate");
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.tournamentInfoViewPager, new TournamentTimeTableFragment(), "fragmentTimeTable")
                .show(new TournamentTimeTableFragment())
                .commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        ViewPager viewPager;
        view = inflater.inflate(R.layout.tournament_info_two, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tournamentInfoTab);
        viewPager = (ViewPager) view.findViewById(R.id.tournamentInfoViewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
        setCustomFont();
        log.info("INFO: tournament onCreateView");
        return view;
    }


    private void setupViewPager(ViewPager viewPager){

        ViewPagerTournamentInfoAdapter adapter = new ViewPagerTournamentInfoAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new TournamentTimeTableFragment(), "Расписание");
        adapter.addFragment(new TournamentCommandFragment(), "Команды");
        adapter.addFragment(new TournamentPlayersFragment(), "Игроки");
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.tournamentInfoViewPager, new TournamentTimeTableFragment(), "fragmentTimeTable")
//                .addToBackStack(null)
//                .show(new TournamentTimeTableFragment())
                .commit();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.tournamentInfoViewPager, new TournamentCommandFragment(), "fragmentCommand")
//                .addToBackStack(null)
//                .hide(new TournamentCommandFragment())
                .commit();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.tournamentInfoViewPager, new TournamentPlayersFragment(), "fragmentPlayers")
//                .addToBackStack(null)
//                .hide(new TournamentCommandFragment())
                .commit();
        viewPager.setAdapter(adapter);
    }


    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf"));
                }
            }
        }
    }


    @Override
    public void onPause() {
        log.info("INFO: tournament onPause");
        Fragment fragment = (TournamentTimeTableFragment) getActivity().getSupportFragmentManager().findFragmentByTag("fragmentTimeTable");
//        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        fragment.onDestroy();

        Fragment fragment1 = (TournamentCommandFragment) getActivity().getSupportFragmentManager().findFragmentByTag("fragmentCommand");
//        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
        fragment1.onDestroy();
        Fragment fragment2 = (TournamentPlayersFragment) getActivity().getSupportFragmentManager().findFragmentByTag("fragmentPlayers");
//        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
        fragment2.onDestroy();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        log.info("INFO: tournament onDestroy");
        super.onDestroy();
    }


}