package com.example.user.secondfootballapp.tournament.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.DateToString;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SetImage;
import com.example.user.secondfootballapp.TimeToString;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.Event;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.LeagueInfo;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.Player;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.tournament.activity.ShowProtocol;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import q.rorbin.badgeview.QBadgeView;

import static com.example.user.secondfootballapp.Controller.BASE_URL;

public class RecyclerViewTournamentTimeTableAdapter extends RecyclerView.Adapter<RecyclerViewTournamentTimeTableAdapter.ViewHolder> {
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    private PersonalActivity activity;
    private List<Match> matches;
    private LeagueInfo league;

    public RecyclerViewTournamentTimeTableAdapter(Activity activity, List<Match> matches, LeagueInfo league) {
        this.activity = (PersonalActivity) activity;
        this.matches = matches;
        this.league = league;
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
//        new QBadgeView(activity).bindTarget(holder.imgCommandLogo1).setBadgeBackground(activity.getDrawable(R.drawable.ic_circle)).setBadgeNumber(3);
        Match match = matches.get(position);
        String str;
        str = match.getDate();
        DateToString dateToString = new DateToString();
        holder.textDate.setText(dateToString.ChangeDate(str));
        try {
            TimeToString timeToString = new TimeToString();
            holder.textTime.setText(timeToString.ChangeTime(str));
        } catch (NullPointerException e) {
            holder.textTime.setText(str);
        }
        str = match.getPlace();
        try {
            String[] stadium;
            stadium = str.split(":", 1);
            holder.textStadium.setText(stadium[0]);
        } catch (NullPointerException e) {
            holder.textStadium.setText(str);
        }

        str = match.getTour();
        holder.textTour.setText(str);
//        int score1 = 0;
//        int score2 = 0;
        Team team1 = null;
        Team team2 = null;
        List<String> teamPlayers1 = new ArrayList<>();
        List<String> teamPlayers2 = new ArrayList<>();

        SetImage setImage = new SetImage();
        for (Team team : league.getTeams()) {
            if (team.getId().equals(match.getTeamOne())) {
                str = team.getName();
                team1 = team;
                holder.textCommandTitle1.setText(str);
                for (Club club : PersonalActivity.allClubs) {
                    if (club.getId().equals(team.getClub())) {
                        setImage.setImage(activity, holder.imgCommandLogo1 ,club.getLogo());
                    }
                }
                for (Player player : team.getPlayers()) {
                    teamPlayers1.add(player.getId());
                    if (player.getActiveDisquals() != 0) {
                        new QBadgeView(activity)
                                .bindTarget(holder.imgCommandLogo1)
                                .setBadgeBackground(activity.getDrawable(R.drawable.ic_circle))
                                .setBadgeTextColor(activity.getResources().getColor(R.color.colorBadge))
                                .setBadgeTextSize(5, true)
                                .setBadgePadding(5, true)
                                .setBadgeGravity(Gravity.END | Gravity.BOTTOM)
                                .setGravityOffset(-3, 1, true)
                                .setBadgeNumber(3);
                    }
                }
            }
            if (team.getId().equals(match.getTeamTwo())) {
                str = team.getName();
                team2 = team;
                holder.textCommandTitle2.setText(str);
                for (Club club : PersonalActivity.allClubs) {
                    if (club.getId().equals(team.getClub())) {
                        setImage.setImage(activity, holder.imgCommandLogo2 ,club.getLogo());
                    }
                }
                for (Player player : team.getPlayers()) {
                    teamPlayers2.add(player.getId());
                    if (player.getActiveDisquals() != 0) {
                        new QBadgeView(activity)
                                .bindTarget(holder.imgCommandLogo2)
                                .setBadgeBackground(activity.getDrawable(R.drawable.ic_circle))
                                .setBadgeTextColor(activity.getResources().getColor(R.color.colorBadge))
                                .setBadgeTextSize(5, true)
                                .setBadgePadding(5, true)
                                .setBadgeGravity(Gravity.END | Gravity.BOTTOM)
                                .setGravityOffset(-3, 1, true)
                                .setBadgeNumber(3);
//                        break;
                    }
//                    if (player.getGoals()!=0){
//                        score2+=player.getGoals();
//                    }
                }
//                break;
            }
        }
        try {
            str = match.getScore();
            if (str.equals("")) {
                str = "-";
            }
        } catch (NullPointerException e) {
            str = "-";
        }
        holder.textScore.setText(str);

        if (!str.equals("-")) {
            List<Match> list = new ArrayList<>(matches);
            list.remove(match);
            for (Match match1 : list) {
                try {
                    str = match1.getScore();
                    if (!str.equals("")
                            && match1.getTeamOne().equals(match.getTeamOne())
                            && match1.getTeamOne().equals(match.getTeamTwo())) {
                        str = match1.getScore();
                        holder.textLastScore.setVisibility(View.VISIBLE);
                        holder.textLastScore.setText(str);
                    }
                    if (!str.equals("")
                            && match1.getTeamOne().equals(match.getTeamTwo())
                            && match1.getTeamOne().equals(match.getTeamOne())) {
                        str = match1.getScore();
                        String[] strArray = str.split(":");
                        str = strArray[1] + ":" + strArray[0];
                        holder.textLastScore.setVisibility(View.VISIBLE);
                        holder.textLastScore.setText(str);
                    }
                } catch (Exception e) {
                }

            }
        }
        if (match.getPlayed()){
            holder.layout.setBackgroundResource(R.color.colorBadgeScale);
            holder.layout.setOnClickListener(v -> {
                Intent intent = new Intent(activity, ShowProtocol.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SHOWPROTOCOL", match);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            });
        }
        try {
            str = match.getPenalty();
            if (!str.equals("")) {
                holder.textPenalty.setText(str);
                holder.textPenalty.setVisibility(View.VISIBLE);
            }
        } catch (NullPointerException e) {
        }
        if (position == (matches.size() - 1)) {
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCommandTitle1;
        TextView textCommandTitle2;
        ImageView imgCommandLogo1;
        ImageView imgCommandLogo2;
        TextView textStadium;
        TextView textDate;
        TextView textTime;
        TextView textTour;
        TextView textLastScore;
        TextView textPenalty;
        //        TextView textTournamentTitle;
        TextView textScore;
        RelativeLayout layout;
        View line;

        public ViewHolder(View item) {
            super(item);
            textCommandTitle1 = item.findViewById(R.id.timetableCommandTitle1);
            textCommandTitle2 = item.findViewById(R.id.timetableCommandTitle2);
            imgCommandLogo1 = item.findViewById(R.id.timetableCommandLogo1);
            imgCommandLogo2 = item.findViewById(R.id.timetableCommandLogo2);
            textStadium = item.findViewById(R.id.timetableStadium);
            textDate = item.findViewById(R.id.timetableDate);
            textTime = item.findViewById(R.id.timetableTime);
            textTour = item.findViewById(R.id.timetableTour);
            textLastScore = item.findViewById(R.id.timetableLastScore);
//            textTournamentTitle = (TextView) item.findViewById(R.id.timetableLeagueTitle);
            textScore = item.findViewById(R.id.timetableGameScore);
            textPenalty = item.findViewById(R.id.timetablePenalty);
            layout = item.findViewById(R.id.timetableLayout);
            line = item.findViewById(R.id.timetableLine);
        }
    }
}
