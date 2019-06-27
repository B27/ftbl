package com.example.user.secondfootballapp.user.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SaveSharedPreference;
import com.example.user.secondfootballapp.model.GetLeagueInfo;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.PersonTeams;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.model.User;
import com.example.user.secondfootballapp.players.activity.PlayerInv;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;
import com.example.user.secondfootballapp.user.adapter.RVUserCommandAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


public class UserCommands extends Fragment {
    Logger log = LoggerFactory.getLogger(UserClubs.class);
    final int REQUEST_CODE_NEWCOMMAND = 286;
    int num;
    boolean scrollStatus;
    View line;
    TextView textView;
    TextView textView2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.info("INFO: UserCommands onCreate");
    }

    public static UserCommands newInstance() {
        return new UserCommands();
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        log.info("INFO: onCreateView onCreate");
//        final FloatingActionButton fab;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        NestedScrollView scroller;
        LinearLayout linearLayout;
        LinearLayout linear;
        view = inflater.inflate(R.layout.user_commands, container, false);
        LinearLayout linearOwnCommand = view.findViewById(R.id.ownCommands);
        LinearLayout linearUserCommand = view.findViewById(R.id.userCommands);
        line = view.findViewById(R.id.userCommandsLine);
        textView = view.findViewById(R.id.userCommandsText);
        textView2 = view.findViewById(R.id.userCommandsText2);


        try{

        if (AuthoUser.personOwnCommand.size()==0){
            linearOwnCommand.setVisibility(View.GONE);
        }
        if (AuthoUser.personCommand.size()==0){
            linearUserCommand.setVisibility(View.GONE);
        }
        if (AuthoUser.personOwnCommand.size() != 0
                && AuthoUser.personCommand.size() != 0) {
            line.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
        }
        linear = view.findViewById(R.id.emptyCommand);
        linearLayout = view.findViewById(R.id.notEmptyCommand);
        scroller = view.findViewById(R.id.userCommandScroll);
        recyclerView = view.findViewById(R.id.recyclerViewUserCommand);
        recyclerView.setAdapter(AuthoUser.adapterCommand);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView2 = view.findViewById(R.id.recyclerViewOwnCommand);
        recyclerView2.setAdapter(AuthoUser.adapterOwnCommand);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        scrollStatus = false;

        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > oldScrollY) {}
            if (scrollY < oldScrollY) {
                scrollStatus = false;
            }
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                scrollStatus = true;
                AuthoUser.fab.hide();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        AuthoUser.fab.setOnClickListener(v -> {
            //add command
//                num = AuthoUser.pendingTeamInvitesList.size();
            num = AuthoUser.personOwnCommand.size();
            Intent intent = new Intent(getActivity(), NewCommand.class);
            startActivityForResult(intent, REQUEST_CODE_NEWCOMMAND);
        });

        if (AuthoUser.personOngoingLeagues.size()!=0){
            linear.setVisibility(View.GONE);
        }
        else {
            linearLayout.setVisibility(View.GONE);
        }
        }catch (NullPointerException e){}
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_NEWCOMMAND) {
                if (num==0){
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                    FragmentTransaction ft = this.getChildFragmentManager().beginTransaction();
                    ft.detach(this).attach(this).commit();
                }
                else{
                    AuthoUser.adapterCommand.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Команда добавлена", Toast.LENGTH_LONG).show();
                }

                if (AuthoUser.personOwnCommand.size()!=0
                        && AuthoUser.personCommand.size()!=0){
                    line.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                }
            }
        } else {
            log.error("ERROR: onActivityResult");
        }
    }

    @Override
    public void onDestroy() {
        log.info("INFO: UserCommands onDestroy");
        super.onDestroy();
    }



}
