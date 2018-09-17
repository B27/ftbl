package com.example.user.secondfootballapp.tournament.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.CommandInfoActivity;
import com.example.user.secondfootballapp.tournament.activity.TournamentCommandFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RVTournamentCommandCardAdapter extends RecyclerView.Adapter<RVTournamentCommandCardAdapter.ViewHolder> {
    TournamentCommandFragment context;
    PersonalActivity activity;
//    CommandInfoFragment commandInfoFragment = new CommandInfoFragment();
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    public RVTournamentCommandCardAdapter(Activity activity, TournamentCommandFragment context){
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.tournament_info_command, parent, false);
        RVTournamentCommandCardAdapter.ViewHolder holder = new RVTournamentCommandCardAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textNum.setText("1");
//        holder.textTitle.setText("SomeTitle");
        holder.btnTitle.setText("SomeTitle");
        holder.btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(activity, CommandInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("COMMANDTITLE1", "SomeText");
                intent.putExtra("COMMANDTITLE", bundle);
//                intent.putExtra("COMMANDTITLE", "SomeText");
                activity.startActivity(intent);
//                FragmentManager fragmentManager = activity.getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.tournamentInfoFragment, commandInfoFragment).commit();


//                fragmentManager.beginTransaction().replace(R.id.tournamentInfoFragment, commandInfoFragment).commit();

            }
        });

        holder.textGame.setText("3");
        holder.textDifference.setText("4");
        holder.textPoint.setText("5");

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNum;
//        TextView textTitle;
        Button btnTitle;
        TextView textGame;
        TextView textDifference;
        TextView textPoint;
        public ViewHolder(View item) {
            super(item);
            textNum = (TextView) item.findViewById(R.id.commandNum);
            btnTitle = (Button) item.findViewById(R.id.commandTitle);
//            textTitle = (TextView) item.findViewById(R.id.commandTitle);
            textGame = (TextView) item.findViewById(R.id.commandScoreGame);
            textDifference = (TextView) item.findViewById(R.id.commandScoreDifference);
            textPoint = (TextView) item.findViewById(R.id.commandScorePoint);
        }
    }
}
