package com.example.user.secondfootballapp.tournament.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class RVLeaguePlayoffCommandAdapter extends RecyclerView.Adapter<RVLeaguePlayoffCommandAdapter.ViewHolder> {
    private TournamentCommandFragment context;
    private PersonalActivity activity;
    //    CommandInfoFragment commandInfoFragment = new CommandInfoFragment();
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    private List<Team> teams;
    private LeagueInfo leagueInfo;

    public RVLeaguePlayoffCommandAdapter(Activity activity, TournamentCommandFragment context, List<Team> teams,
                                         LeagueInfo leagueInfo) {
        this.activity = (PersonalActivity) activity;
        this.context = context;
        this.teams = teams;
        this.leagueInfo = leagueInfo;
    }

    @NonNull
    @Override
    public RVLeaguePlayoffCommandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.tournament_info_command, parent, false);
        RVLeaguePlayoffCommandAdapter.ViewHolder holder = new RVLeaguePlayoffCommandAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVLeaguePlayoffCommandAdapter.ViewHolder holder, final int position) {
//        String str = String.valueOf(position+1);
        String str;
//        str = String.valueOf(teams.get(position).getGroupScore());
        try {
//            str = String.valueOf(teams.get(position).getPlace());
            str = String.valueOf(teams.get(position).getPlayoffPlace());
            if (str.equals("null")) {
                str = "-";
                log.error("3333333333333333333333333333333333333");
                try{
                    Boolean check = teams.get(position).getMadeToPlayoff();
                    if (!check ){
                        holder.layout.setBackgroundResource(R.color.colorBadgeScale);
                    }
                }catch (NullPointerException e){}



            }
        } catch (NullPointerException e) {
            str = "-";
//            holder.layout.setBackgroundResource(R.color.colorBadgeScale);
            try{
                Boolean check = teams.get(position).getMadeToPlayoff();
                if (!check){
                    holder.layout.setBackgroundResource(R.color.colorBadgeScale);
                }
            }catch (NullPointerException r){}
        }
        holder.textNum.setText(str);
//        holder.textTitle.setText("SomeTitle");
        str = teams.get(position).getName();
        holder.btnTitle.setText(str);
        holder.btnTitle.setOnClickListener(v -> {
            Intent intent = new Intent(activity, CommandInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TOURNAMENTMATCHCOMMANDINFO", teams.get(position));
            bundle.putSerializable("TOURNAMENTMATCHCOMMANDINFOMATCHES", (Serializable) leagueInfo.getMatches());
            intent.putExtras(bundle);
            activity.startActivity(intent);

        });

        int count;
        count = teams.get(position).getDraws() + teams.get(position).getWins() + teams.get(position).getLosses();
        str = String.valueOf(count);
        holder.textGame.setText(str);
        count = teams.get(position).getGoals() - teams.get(position).getGoalsReceived();
        str = String.valueOf(count);
        holder.textDifference.setText(str);
//        str = String.valueOf(teams.get(position).getGoals());
        str = String.valueOf(teams.get(position).getGroupScore());
        holder.textPoint.setText(str);


    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textNum;
        //        TextView textTitle;
        TextView btnTitle;
        TextView textGame;
        TextView textDifference;
        TextView textPoint;

        public ViewHolder(View item) {
            super(item);
            layout = item.findViewById(R.id.playoffLayout);
            textNum = item.findViewById(R.id.commandNum);
            btnTitle = item.findViewById(R.id.commandTitle);
//            textTitle = (TextView) item.findViewById(R.id.commandTitle);
            textGame = item.findViewById(R.id.commandScoreGame);
            textDifference = item.findViewById(R.id.commandScoreDifference);
            textPoint = item.findViewById(R.id.commandScorePoint);
        }
    }
}
