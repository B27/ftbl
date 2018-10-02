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
import com.example.user.secondfootballapp.user.adapter.RVOngoingTournamentAdapter;
import com.example.user.secondfootballapp.user.adapter.RVRefereesAdapter;

public class RefereeFragment extends Fragment{
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        RecyclerView recyclerView;
        boolean check = true;
        view = inflater.inflate(R.layout.user_referees, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewReferees);
        RVRefereesAdapter adapter = new RVRefereesAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
