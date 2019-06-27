package com.example.user.secondfootballapp.user.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.Match;

import io.reactivex.annotations.NonNull;

public class RVTimeTablePagAdapter extends PagedListAdapter<Match, MatchViewHolder> {

    protected RVTimeTablePagAdapter(DiffUtil.ItemCallback<Match> diffUtilCallback) {
        super(diffUtilCallback);
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable, parent, false);
        MatchViewHolder holder = new MatchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
//        holder.bind(getItem(position));
    }

}