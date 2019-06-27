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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.tournament.adapter.RVCommandMatchAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CommandMatchFragment extends Fragment {
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        RecyclerView recyclerView;
        LinearLayout layout;
        view = inflater.inflate(R.layout.command_info_match, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewCommandMatch);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setVisibility(View.VISIBLE);
        layout = view.findViewById(R.id.teamMatchEmpty);
        try {
        Bundle bundle = getArguments();
        List<Match> matches = (List<Match>) bundle.getSerializable("TEAMSTRUCTUREMATCHES");
        if (matches.size()!=0){
            layout.setVisibility(View.GONE);
        }
        RVCommandMatchAdapter adapter = new RVCommandMatchAdapter(getActivity(), this, matches);
        recyclerView.setAdapter(adapter);
        }catch (Exception e){}
        return view;
    }
}