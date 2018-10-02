package com.example.user.secondfootballapp.user.activity;

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

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVMatchEventsAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchEvents extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(MatchEvents.class);
public static FloatingActionButton fab;
public static boolean scrollStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Abbrev dialogFragment;
        NestedScrollView scroller;
        ImageButton buttonBack;
        RecyclerView recyclerView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_events);
        dialogFragment = new Abbrev();
        scroller = (NestedScrollView) findViewById(R.id.scrollMatchEvents);
        fab = (FloatingActionButton) findViewById(R.id.buttonAbbreviation);
        buttonBack = (ImageButton) findViewById(R.id.matchEventsBack);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMatchEvents);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RVMatchEventsAdapter adapter = new RVMatchEventsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scrollStatus = false;
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogFragment.show(getSupportFragmentManager(), "abbrevMatch");
            }
        });


        scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
//                    PersonalActivity.navigation.animate().translationY(PersonalActivity.navigation.getHeight());
                    fab.show();
                }
                if (scrollY < oldScrollY) {
//                    PersonalActivity.navigation.animate().translationY(0);
                    scrollStatus = false;
                    fab.show();
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    scrollStatus = true;
                    fab.hide();
                }
            }
        });


//        scroller.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_MOVE){
//                    fab.hide();
//                }
//                return false;
//            }
//        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //nothing to do
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    log.info("INFO: ====================");
//                    fab.show();
//                } else {
//                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    fab.hide();
//                    log.info("INFO: +++++++++++++++++++++");
//                }
//            }
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    fab.hide();
                }else{fab.show();}
                if (scrollStatus) {
                    fab.hide();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
