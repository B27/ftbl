package com.example.user.secondfootballapp.user.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVTimeTableAdapter;

public class TimeTableFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
//        final FloatingActionButton fab;
        RecyclerView recyclerView;
        view = inflater.inflate(R.layout.user_timetable, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewTimeTable);
        RVTimeTableAdapter adapter = new RVTimeTableAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
