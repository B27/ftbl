package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.user.adapter.RVUserClubInvAdapter;
import com.example.user.secondfootballapp.user.adapter.RVUserCommandAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserClubs extends Fragment{
    Logger log = LoggerFactory.getLogger(UserClubs.class);
    boolean scrollStatus;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
//        final FloatingActionButton fab;
        RecyclerView recyclerViewCommand;
        RecyclerView recyclerViewClubInv;
        TextView textCommandListStatus;
        TextView textClubListStatus;
        TextView textClubTitle;
//        TextView textClub;
        TextView textClubInv;
        NestedScrollView scroller;
        ImageButton buttonShowClubInfo;
        ImageView imageClub;
        RelativeLayout relativeLayout;
        boolean check = true;
        view = inflater.inflate(R.layout.user_clubs, container, false);
//        fab = (FloatingActionButton) view.findViewById(R.id.addCommandButton);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.userClubRelativeLayout);
        scroller = (NestedScrollView) view.findViewById(R.id.userClubScroll);
        buttonShowClubInfo = (ImageButton) view.findViewById(R.id.userClubButtonShow);
        imageClub = (ImageView) view.findViewById(R.id.userClubLogo);
        textCommandListStatus = (TextView) view.findViewById(R.id.userCommandListStatus);
        textClubListStatus = (TextView) view.findViewById(R.id.userClubListStatus);
        textClubTitle = (TextView) view.findViewById(R.id.userClubTitle);
        recyclerViewClubInv = (RecyclerView) view.findViewById(R.id.recyclerViewClubInv);
        RVUserClubInvAdapter adapterClub = new RVUserClubInvAdapter(this);
        recyclerViewClubInv.setAdapter(adapterClub);
        recyclerViewClubInv.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCommand = (RecyclerView) view.findViewById(R.id.recyclerViewUserCommand);
        RVUserCommandAdapter adapterCommand = new RVUserCommandAdapter(getActivity(),this);
        recyclerViewCommand.setAdapter(adapterCommand);
        recyclerViewCommand.setLayoutManager(new LinearLayoutManager(getActivity()));
        scrollStatus = false;

        scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {}
                if (scrollY < oldScrollY) {
                    scrollStatus = false;
                }
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    scrollStatus = true;
                    AuthoUser.fab.hide();
                }
            }
        });



        //if club.members != null
        if (!check){
            textClubListStatus.setVisibility(View.VISIBLE);
            textCommandListStatus.setVisibility(View.VISIBLE);
        }else {
            buttonShowClubInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    log.info("INFO: ======================================================================");
                    Intent intent = new Intent(getActivity(), UserClubInfo.class);
                    String title = "Some title";
                    Bundle bundle = new Bundle();
                    bundle.putString("NEWSTITLE", title);
                    intent.putExtra("NEWSTITLE", bundle);
                    startActivity(intent);
                }
            });
            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.ic_football)
                    .apply(new RequestOptions()
                            .circleCropTransform()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH))
                    .into(imageClub);
//            textClubTitle.setText();
            recyclerViewClubInv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//nothing to do
                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        AuthoUser.fab.show();
                    } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        AuthoUser.fab.hide();
                    }
                    if (scrollStatus) {
                        AuthoUser.fab.hide();
                    }
                    super.onScrollStateChanged(recyclerView, newState);
                }
            });
//            textClub = (TextView) view.findViewById(R.id.currentClub);
//            textClub.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
        }

        //есть приглашения
        if (check){
            textClubInv = (TextView) view.findViewById(R.id.clubInv) ;
            textClubInv.setVisibility(View.VISIBLE);
        }

        recyclerViewCommand.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//nothing to do
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    AuthoUser.fab.show();
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    AuthoUser.fab.hide();
                }
                if (scrollStatus) {
                    AuthoUser.fab.hide();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        AuthoUser.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add command
                Intent intent = new Intent(getActivity(), NewCommand.class);
                startActivity(intent);
                //сделать видимым, если тренер
            }
        });
        return view;
    }
}
