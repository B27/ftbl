package com.example.user.secondfootballapp.tournament.adapter;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecyclerViewTournamentTimeTableAdapter extends RecyclerView.Adapter<RecyclerViewTournamentTimeTableAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    TournamentTimeTableFragment context;
    PersonalActivity activity;

    public RecyclerViewTournamentTimeTableAdapter(Activity activity, TournamentTimeTableFragment context){
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_fragment, parent, false);
        RecyclerViewTournamentTimeTableAdapter.ViewHolder holder = new RecyclerViewTournamentTimeTableAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textCommandTitle1;
        TextView textCommandTitle2;
        ImageView imgCommandLogo1;
        ImageView imgCommandLogo2;
        TextView textStadium;
        TextView textDate;
        TextView textTime;
        TextView textTournamentTitle;
        ImageView imgCard1;
        ImageView imgCard2;
        TextView textScore;

        public ViewHolder(View item) {
            super(item);
            textCommandTitle1 = (TextView) item.findViewById(R.id.timetableCommandTitle1);
            textCommandTitle2 = (TextView) item.findViewById(R.id.timetableCommandTitle2);
            imgCommandLogo1 = (ImageView) item.findViewById(R.id.timetableCommandLogo1);
            imgCommandLogo2 = (ImageView) item.findViewById(R.id.timetableCommandLogo2);
            textStadium = (TextView) item.findViewById(R.id.timetableStadium);
            textDate = (TextView) item.findViewById(R.id.timetableDate);
            textTime = (TextView) item.findViewById(R.id.timetableTime);
            textTournamentTitle = (TextView) item.findViewById(R.id.timetableLeagueTitle);
            imgCard1 = (ImageView) item.findViewById(R.id.timetableCommandCard1);
            imgCard2 = (ImageView) item.findViewById(R.id.timetableCommandCard2);
            textScore = (TextView) item.findViewById(R.id.timetableGameScore);
        }
    }
}
