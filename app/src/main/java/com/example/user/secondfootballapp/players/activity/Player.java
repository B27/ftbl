package com.example.user.secondfootballapp.players.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.FullScreenImage;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.players.adapter.RVPlayersTournamentAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player extends Fragment {
    boolean scrollStatus;
    Logger log = LoggerFactory.getLogger(Player.class);

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        ImageView imageLogo;
        TextView textName;
        TextView textDOB;
        TextView textTournaments;
        RecyclerView recyclerView;
        NestedScrollView scroller;
        final FloatingActionButton fab;
        boolean check = true;
        view = inflater.inflate(R.layout.player_info, container, false);
        imageLogo = (ImageView) view.findViewById(R.id.playerImage);
        textName = (TextView) view.findViewById(R.id.playerName);
        textDOB = (TextView) view.findViewById(R.id.playerDOB);
        textTournaments = (TextView) view.findViewById(R.id.playerInfoTournamentsStatus);
        fab = (FloatingActionButton) view.findViewById(R.id.addPlayerButton);
        scroller = (NestedScrollView) view.findViewById(R.id.playerInfoScroll);
        scrollStatus = false;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //добавить игрока в команду (только тренер) сделать видимым, если тренер
                Intent intent = new Intent(getActivity(), PlayerInv.class);
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
                    log.info("INFO: scroll");
                    fab.hide();
                }
            }
        });

        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(imageLogo);
        imageLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FullScreenImage.class);
                String title = "Some title";
                Bundle bundle = new Bundle();
                bundle.putString("NEWSTITLE", title);
                intent.putExtra("NEWSTITLE", bundle);
                startActivity(intent);
            }
        });
        textName.setText("Иванов В.В.");
        textDOB.setText("12.12.81");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPlayerTournaments);
        if (check) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            textTournaments.setVisibility(View.VISIBLE);
        }
        RVPlayersTournamentAdapter adapter = new RVPlayersTournamentAdapter(this);
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
