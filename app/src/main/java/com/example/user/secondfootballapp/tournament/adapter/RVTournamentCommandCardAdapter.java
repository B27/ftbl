package com.example.user.secondfootballapp.tournament.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.LeagueInfo;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.tournament.activity.CommandInfoActivity;
import com.example.user.secondfootballapp.tournament.activity.TournamentCommandFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

public class RVTournamentCommandCardAdapter extends RecyclerView.Adapter<RVTournamentCommandCardAdapter.ViewHolder> {
    private TournamentCommandFragment context;
    private PersonalActivity activity;
    //    CommandInfoFragment commandInfoFragment = new CommandInfoFragment();
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    private List<Team> teams;
    private String group;
    private LeagueInfo leagueInfo;
    public RVTournamentCommandCardAdapter(Activity activity, TournamentCommandFragment context, List<Team> teams, String group,
                                          LeagueInfo leagueInfo) {
        this.activity = (PersonalActivity) activity;
        this.context = context;
        this.teams = teams;
        this.group = group;
        this.leagueInfo = leagueInfo;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String str = String.valueOf(position+1);
        holder.textNum.setText(str);
        str = teams.get(position).getName();
        holder.btnTitle.setText(str);
        holder.btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CommandInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("TOURNAMENTMATCHCOMMANDINFO", teams.get(position));
                bundle.putSerializable("TOURNAMENTMATCHCOMMANDINFOMATCHES", (Serializable) leagueInfo.getMatches());
                intent.putExtras( bundle);
                activity.startActivity(intent);

            }
        });
        int count;
        count = teams.get(position).getDraws() + teams.get(position).getWins() + teams.get(position).getLosses();
        str = String.valueOf(count);
        holder.textGame.setText(str);
        count = teams.get(position).getGoals() - teams.get(position).getGoalsReceived();
        str = String.valueOf(count);
        holder.textDifference.setText(str);
        str = String.valueOf(teams.get(position).getGroupScore());
        holder.textPoint.setText(str);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNum;
        //        TextView textTitle;
        TextView btnTitle;
        TextView textGame;
        TextView textDifference;
        TextView textPoint;
        public ViewHolder(View item) {
            super(item);
            textNum = item.findViewById(R.id.commandNum);
            btnTitle = item.findViewById(R.id.commandTitle);
//            textTitle = (TextView) item.findViewById(R.id.commandTitle);
            textGame = item.findViewById(R.id.commandScoreGame);
            textDifference = item.findViewById(R.id.commandScoreDifference);
            textPoint = item.findViewById(R.id.commandScorePoint);
        }
    }
}
