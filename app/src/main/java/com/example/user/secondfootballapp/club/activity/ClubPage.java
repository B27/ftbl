package com.example.user.secondfootballapp.club.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.club.adapter.RecyclerViewClubAdapter;

public class ClubPage extends Fragment {
    boolean scrollStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        RecyclerView recyclerView;
        NestedScrollView scroller;
        final FloatingActionButton fab;
        CollapsingToolbarLayout collapsingToolbar;
        view = inflater.inflate(R.layout.page_club, container, false);
        scroller = (NestedScrollView) view.findViewById(R.id.scrollClubs);
        fab = (FloatingActionButton) view.findViewById(R.id.createClubButton);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewClubs);
        scrollStatus = false;
        collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbarClub);
        collapsingToolbar.setTitle("Клубы");
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf");
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbar.setExpandedTitleTypeface(tf);
        collapsingToolbar.setCollapsedTitleTypeface(tf);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewClub.class);
                getActivity().startActivity(intent);
            }
        });

        scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
//                    PersonalActivity.navigation.animate().translationY(PersonalActivity.navigation.getHeight());
                }
                if (scrollY < oldScrollY) {
//                    PersonalActivity.navigation.animate().translationY(0);
                    scrollStatus = false;
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    scrollStatus = true;
                    fab.hide();
                }
            }
        });

        RecyclerViewClubAdapter adapter = new RecyclerViewClubAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //nothing to do
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    fab.hide();
                }
                if (scrollStatus) {
                    fab.hide();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        return view;
    }
}
