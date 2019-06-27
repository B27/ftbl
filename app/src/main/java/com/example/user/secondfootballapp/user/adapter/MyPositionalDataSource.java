package com.example.user.secondfootballapp.user.adapter;

import android.arch.paging.PositionalDataSource;

import com.example.user.secondfootballapp.model.Match;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class MyPositionalDataSource extends PositionalDataSource<Match> {

    private final List<Match> matches;

    public MyPositionalDataSource(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Match> callback) {
//        List<Match> result = matches.getData(params.requestedStartPosition, params.requestedLoadSize);
//        callback.onResult(result, 0);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Match> callback) {
//        List<Match> result = matches.getData(params.startPosition, params.loadSize);
//        callback.onResult(result);
    }

}
