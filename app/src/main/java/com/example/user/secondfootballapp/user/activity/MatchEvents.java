package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.model.TeamTitleClubLogoMatchEvents;
import com.example.user.secondfootballapp.user.HalvesComparator;
import com.example.user.secondfootballapp.user.adapter.RVMatchEventsAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class MatchEvents extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(MatchEvents.class);
    HashMap<Integer, String> halves;
    FloatingActionButton fab;
    boolean scrollStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Abbrev dialogFragment;
        NestedScrollView scroller;
        ImageButton buttonBack;
        RecyclerView recyclerView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_events);
        dialogFragment = new Abbrev();
        scroller = findViewById(R.id.scrollMatchEvents);
        fab = findViewById(R.id.buttonAbbreviation);
        buttonBack = findViewById(R.id.matchEventsBack);
        recyclerView = findViewById(R.id.recyclerViewMatchEvents);
        buttonBack.setOnClickListener(v -> finish());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scrollStatus = false;
        fab.setOnClickListener(v -> dialogFragment.show(getSupportFragmentManager(), "abbrevMatch"));
        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > oldScrollY) {
                fab.show();
            }
            if (scrollY < oldScrollY) {
                scrollStatus = false;
                fab.show();
            }

            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                scrollStatus = true;
                fab.hide();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //nothing to do
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    fab.hide();
                } else {
                    fab.show();
                }
                if (scrollStatus) {
                    fab.hide();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        try {
            Intent arguments = getIntent();
            TeamTitleClubLogoMatchEvents playerEvents = (TeamTitleClubLogoMatchEvents) Objects.requireNonNull(arguments.getExtras()).getSerializable("PROTOCOLEVENTS");
            halves = new HashMap<>();
            int i = 0;
            if (playerEvents.getPlayerEvents() != null) {
                for (PlayerEvent playerEvent : playerEvents.getPlayerEvents()) {
                    if (!halves.containsValue(playerEvent.getEvent().getTime())) {
                        halves.put(i, playerEvent.getEvent().getTime());
                        i++;
                    }
                }
            }
//            Collections.sort(halves, new HalvesComparator());
            RVMatchEventsAdapter adapter = new RVMatchEventsAdapter(this, halves, playerEvents.getPlayerEvents());
            recyclerView.setAdapter(adapter);
        } catch (NullPointerException e) {
            LinearLayout layout = findViewById(R.id.emptyEvents);
            layout.setVisibility(View.VISIBLE);
            fab.hide();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
