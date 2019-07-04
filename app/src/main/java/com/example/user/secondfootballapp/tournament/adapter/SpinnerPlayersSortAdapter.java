package com.example.user.secondfootballapp.tournament.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.user.secondfootballapp.tournament.activity.TournamentPlayersFragment;

import java.util.List;

class SpinnerPlayersSortAdapter extends BaseAdapter{
    private final List<String> item;
    private final TournamentPlayersFragment context;
    public SpinnerPlayersSortAdapter(TournamentPlayersFragment context, List<String> item){
        this.context = context;
        this.item = item;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
