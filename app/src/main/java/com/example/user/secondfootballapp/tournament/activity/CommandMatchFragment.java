package com.example.user.secondfootballapp.tournament.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.adapter.RVCommandMatchAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandMatchFragment extends Fragment {
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        boolean check = true;
        TextView textMatchSrarus;
        RecyclerView recyclerView;
        view = inflater.inflate(R.layout.command_info_match, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewCommandMatch);
        textMatchSrarus = (TextView) view.findViewById(R.id.comandMatchStatus);
        RVCommandMatchAdapter adapter = new RVCommandMatchAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //if club.members != null
        if (!check){
            recyclerView.setVisibility(View.VISIBLE);
        }
        else {textMatchSrarus.setVisibility(View.VISIBLE);}
        return view;
    }
}