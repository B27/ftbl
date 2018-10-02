package com.example.user.secondfootballapp.user.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVProtocolEditAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProtocolEventsEdit extends AppCompatActivity {
    public static List<Integer> items;
    public static NestedScrollView scroller;
    public static CoordinatorLayout coordinatorLayout;
    public static AppBarLayout appBarLayout;
    public static AppBarLayout.Behavior behavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocol_events_edit);
        items = new ArrayList<>();
        items.add(1);
        FloatingActionButton buttonAddEvent;
        ImageButton imageClose;
        ImageButton imageSave;
//        ImageButton buttonAddEvent;
        final RecyclerView recyclerView;
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.editProtocolCoordinatorLayout);
        appBarLayout = (AppBarLayout) findViewById(R.id.editProtocolAppbar);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        behavior = (AppBarLayout.Behavior) params.getBehavior();
        imageClose = (ImageButton) findViewById(R.id.editProtocolEventsClose);
        imageSave = (ImageButton) findViewById(R.id.editProtocolEventsSave);
//        buttonAddEvent = (ImageButton) findViewById(R.id.editProtocolAddEvent);
        buttonAddEvent = (FloatingActionButton) findViewById(R.id.editProtocolAddEvent);
        scroller = (NestedScrollView) findViewById(R.id.scrollProtocolEdit);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //post
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEditProtocol);
        final RVProtocolEditAdapter adapter = new RVProtocolEditAdapter(this);
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(2);
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
//                adapter.notifyItemRangeInserted(0, items.size());

                if (items.size()<=1){
//                    scroller.scrollTo(0, 0);
//                    scroller.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
//                    scroller.dispatchNestedPreScroll(0, toolbarHeight, null, null);
//                    scroller.dispatchNestedScroll(0, 0, 0, 0, new int[]{0, -toolbarHeight});
//                    scroller.scrollTo(0, scroller.getBottom());
                    if (behavior != null) {
                        behavior.onNestedFling(coordinatorLayout, appBarLayout, null, 0, 10000, true);
                    }
                }
            }
        });

        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
