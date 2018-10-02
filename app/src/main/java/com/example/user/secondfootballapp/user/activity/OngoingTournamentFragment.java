package com.example.user.secondfootballapp.user.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVInvitationAdapter;
import com.example.user.secondfootballapp.user.adapter.RVOngoingTournamentAdapter;

public class OngoingTournamentFragment extends Fragment{
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        RecyclerView recyclerView;
        TextView textTournamentStatus;
        boolean check = true;
        view = inflater.inflate(R.layout.user_tournaments, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewUserTournament);
        textTournamentStatus = (TextView) view.findViewById(R.id.userTournamentListStatus);
        RVOngoingTournamentAdapter adapter = new RVOngoingTournamentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (!check){
            textTournamentStatus.setVisibility(View.VISIBLE);
        }
        return view;
    }
}
