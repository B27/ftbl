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

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVMyMatchesAdapter;

public class MyMatches extends Fragment{
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        RecyclerView recyclerView;
        view = inflater.inflate(R.layout.user_matches, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMyMatches);
        RVMyMatchesAdapter adapter = new RVMyMatchesAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
