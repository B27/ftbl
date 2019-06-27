package com.example.user.secondfootballapp.tournament.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.tournament.adapter.ViewPagerCommandInfoAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CommandInfoActivity extends AppCompatActivity {
    Team team;
    List<Match>  matchList;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_command_info);
        final TabLayout tabLayout;
        ImageButton buttonBack;
        ViewPager viewPager;
        TextView textView;
        final AbbreviationDialogFragment dialogFragment;
        Intent intent;
        intent = getIntent();
        team = (Team) intent.getExtras().getSerializable("TOURNAMENTMATCHCOMMANDINFO");
        List<Match> matches = (List<Match>) intent.getExtras().getSerializable("TOURNAMENTMATCHCOMMANDINFOMATCHES");
        matchList = new ArrayList<>();
        for (Match match : matches){
            if (match.getTeamOne().equals(team.getId())
                    || match.getTeamTwo().equals(team.getId())){
                matchList.add(match);
            }
        }
//        String title = intent.getExtras().getString("COMMANDTITLE");
        buttonBack = findViewById(R.id.commandInfoBack);
        tabLayout = findViewById(R.id.commandInfoTab);
        viewPager = findViewById(R.id.commandInfoViewPager);
        textView = findViewById(R.id.commandInfoTitle);
        fab = findViewById(R.id.commandInfoButton);
        dialogFragment = new AbbreviationDialogFragment();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getSupportFragmentManager(), "abbrev2");
            }
        });
        buttonBack.setOnClickListener(v -> finish());
        String str;
        str = team.getName();
        viewPager.addOnPageChangeListener(onPageChangeListener);
        tabLayout.addOnTabSelectedListener(onTabSelectedListener);
        textView.setText(str);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
//        setCustomFont();
        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/manrope_regular.otf");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_text,null);
            tv.setTypeface(tf);
            tabLayout.getTabAt(i).setCustomView(tv);
        }
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        TextView selectedText = (TextView) tab.getCustomView();
        selectedText.setTextColor(getResources().getColor(R.color.colorBottomNavigationUnChecked));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView selectedText = (TextView) tab.getCustomView();
                selectedText.setTextColor(getResources().getColor(R.color.colorBottomNavigationUnChecked));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView selectedText = (TextView) tab.getCustomView();
                selectedText.setTextColor(getResources().getColor(R.color.colorLightGrayForText));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
    private void setupViewPager(ViewPager viewPager){
        CommandStructureFragment commandStructureFragment = new CommandStructureFragment();
        CommandMatchFragment commandMatchFragment = new CommandMatchFragment();
        Bundle teams = new Bundle();
        teams.putSerializable("TEAMSTRUCTURE", team);
        teams.putSerializable("TEAMSTRUCTUREMATCHES", (Serializable) matchList);
        commandStructureFragment.setArguments(teams);
        commandMatchFragment.setArguments(teams);
        ViewPagerCommandInfoAdapter adapter = new ViewPagerCommandInfoAdapter(getSupportFragmentManager());
        adapter.addFragment(commandStructureFragment, "СОСТАВ");
        adapter.addFragment(commandMatchFragment, "МАТЧИ");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }


    private void animateFab(int position) {
        switch (position) {
            case 0:
                fab.show();
                break;
            case 1:
                fab.hide();
                break;
            default:
                fab.show();
                break;
        }
    }

    TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            animateFab(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            animateFab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public FloatingActionButton getFab() {
        return fab;
    }
}
