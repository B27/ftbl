package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.user.secondfootballapp.SaveSharedPreference;
import com.example.user.secondfootballapp.SetImage;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.Event;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.Player;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.model.Referee;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.user.activity.AuthoUser;
import com.example.user.secondfootballapp.user.activity.EditTimeTable;
import com.example.user.secondfootballapp.user.activity.MyMatches;
import com.example.user.secondfootballapp.user.activity.PlayerAddToTeam;
import com.example.user.secondfootballapp.user.activity.ProtocolEdit;

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

public class RVMyMatchesAdapter extends RecyclerView.Adapter<RVMyMatchesAdapter.ViewHolder>{
    MyMatches context;
    private List<Match> matches;
    Logger log = LoggerFactory.getLogger(PlayerAddToTeam.class);
    PersonalActivity activity;
    public RVMyMatchesAdapter(Activity activity, MyMatches context, List<Match> matches){
        this.context =  context;
        this.activity = (PersonalActivity) activity;
        this.matches = matches;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match, parent, false);
        RVMyMatchesAdapter.ViewHolder holder = new RVMyMatchesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{
        final Match match = matches.get(position);

        Boolean check = false;

        String str;
        str = match.getDate();
        DateToString dateToString = new DateToString();
        holder.textDate.setText(dateToString.ChangeDate(str));
        try{
            holder.textTime.setText(TimeToString(str));
        }catch (NullPointerException e){
            holder.textTime.setText(str);
        }
        str = match.getPlace();
        try{
            String[] stadium;
            stadium = str.split(":", 1);
            holder.textStadium.setText(stadium[0]);
        }catch (NullPointerException e){
            holder.textStadium.setText(str);
        }

        str = match.getTour();
        holder.textTour.setText(str);
        int score1 = 0;
        int score2 = 0;
        List<String> teamPlayers1 = new ArrayList<>();
        List<String> teamPlayers2 = new ArrayList<>();

        League league = null;
        for (League league1 : PersonalActivity.tournaments){
            if (league1.getId().equals(match.getLeague())){
                league = league1;
                break;
            }
        }

            SetImage setImage = new SetImage();
        Team team1 = null;
        Team team2 = null;
        String club1 = "";
        String club2 = "";
        for (Team team: league.getTeams()){
            if (team.getId().equals(match.getTeamOne())){
                team1 = team;
                str = team.getName();
                holder.textCommand1.setText(str);
                for (Club club : PersonalActivity.allClubs){
                    if (club.getId().equals(team.getClub())){
                        setImage.setImage(activity, holder.image1, club.getLogo());
                    }
                }
                for (Player player: team.getPlayers()){
                    teamPlayers1.add(player.getId());
                    if (player.getActiveDisquals()!=0 ){
                        new QBadgeView(activity)
                                .bindTarget(holder.image1)
                                .setBadgeBackground(activity.getDrawable(R.drawable.ic_circle))
                                .setBadgeTextColor(activity.getResources().getColor(R.color.colorBadge))
                                .setBadgeTextSize(5, true)
                                .setBadgePadding(5,true)
                                .setBadgeGravity(Gravity.END|Gravity.BOTTOM)
                                .setGravityOffset(-3, 1, true)
                                .setBadgeNumber(3);
                    }
//                    score1+=player.getGoals();
                }
            }
            if (team.getId().equals(match.getTeamTwo())){
                team2 = team;
                str = team.getName();
                holder.textCommand2.setText(str);
                for (Club club : PersonalActivity.allClubs){
                    if (club.getId().equals(team.getClub())){
                        setImage.setImage(activity, holder.image2, club.getLogo());
                    }
                }
                for (Player player: team.getPlayers()){
                    teamPlayers2.add(player.getId());
                    if (player.getActiveDisquals()!=0 ){
                        new QBadgeView(activity)
                                .bindTarget(holder.image2)
                                .setBadgeBackground(activity.getDrawable(R.drawable.ic_circle))
                                .setBadgeTextColor(activity.getResources().getColor(R.color.colorBadge))
                                .setBadgeTextSize(5, true)
                                .setBadgePadding(5,true)
                                .setBadgeGravity(Gravity.END|Gravity.BOTTOM)
                                .setGravityOffset(-3, 1, true)
                                .setBadgeNumber(3);
                    }
                }
            }
        }
        try{
            if (!match.getScore().equals("")){
                str = match.getScore();
            }
            else {
                str = "-";
            }
        }catch (NullPointerException e){
            str = "-";
        }

        holder.textScore.setText(str);
        try{
            str = match.getPenalty();
            if (!str.equals("")){
                holder.textPenalty.setText(str);
                holder.textPenalty.setVisibility(View.VISIBLE);
            }
        }catch (NullPointerException e){}

        try{
            List<Referee> referees = new ArrayList<>(match.getReferees());
            for (Referee referee : referees){
                if (referee.getType().equals("3 судья") && referee.getPerson().equals(SaveSharedPreference.getObject().getUser().getId())){
                    check = true;
                    break;
                }
            }
        }catch (NullPointerException e){
            check=false;
        }

        if (check){
            holder.button.setVisibility(View.VISIBLE);
            final Team finalTeam = team1;
            final Team finalTeam1 = team2;
            final String finalClub = club1;
            final String finalClub1 = club2;
            holder.button.setOnClickListener(v -> {
                Intent intent = new Intent(activity, ProtocolEdit.class);
                Bundle bundle = new Bundle();
                int count = MyMatches.matches.indexOf(match);
                bundle.putSerializable("PROTOCOLMATCH", match);
                bundle.putSerializable("PROTOCOLTEAM1", finalTeam);
                bundle.putSerializable("PROTOCOLTEAM2", finalTeam1);
                bundle.putString("PROTOCOLCLUB1", finalClub);
                bundle.putString("PROTOCOLCLUB2", finalClub1);
                bundle.putInt("MATCHPOSITION", count);
                intent.putExtras(bundle);
                context.startActivity(intent);
            });
        }
        if (position==(matches.size()-1)){
            holder.line.setVisibility(View.INVISIBLE);
        }

        }catch (NullPointerException r){}
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTime;
        TextView textTour;
        TextView textStadium;
        TextView textScore;
        TextView textCommand1;
        TextView textCommand2;
        ImageView image1;
        ImageView image2;
        Button button;
        RelativeLayout layout;
        View line;
        TextView textPenalty;
        public ViewHolder(View item) {
            super(item);
            button = item.findViewById(R.id.myMatchEdit);
            textDate = item.findViewById(R.id.myMatchDate);
            textTime = item.findViewById(R.id.myMatchTime);
            textTour = item.findViewById(R.id.myMatchLeague);
            textStadium = item.findViewById(R.id.myMatchStadium);
            textScore = item.findViewById(R.id.myMatchScore);
            textCommand1 = item.findViewById(R.id.myMatchCommandTitle1);
            textCommand2 = item.findViewById(R.id.myMatchCommandTitle2);
            image1 = item.findViewById(R.id.myMatchCommandLogo1);
            image2 = item.findViewById(R.id.myMatchCommandLogo2);
            layout = item.findViewById(R.id.myMatchShowProtocol);
            line = item.findViewById(R.id.myMatchLine);
            textPenalty = item.findViewById(R.id.myMatchPenalty);
        }
    }
    private String TimeToString(String str)  {
        String dateDOB = "";
        try {
            SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            Date date1;
            date1 = mdformat.parse(str);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            if (String.valueOf(cal.get(Calendar.HOUR)).length()==1){
                dateDOB += "0" + String.valueOf(cal.get(Calendar.HOUR)) + ":";
            }
            else {
                dateDOB += String.valueOf(cal.get(Calendar.HOUR)) + ":";
            }
            if ((String.valueOf(cal.get(Calendar.MINUTE) + 1).length()==1)){
                dateDOB += "0" + String.valueOf(cal.get(Calendar.MINUTE) + 1);
            }
            else{
                dateDOB += String.valueOf(cal.get(Calendar.MINUTE) + 1);
            }
        } catch (ParseException e) {
            try{
                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US);
                Date date1;
                date1 = mdformat.parse(str);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date1);
                if (String.valueOf(cal.get(Calendar.HOUR)).length()==1){
                    dateDOB += "0" + String.valueOf(cal.get(Calendar.HOUR)) + ":";
                }
                else {
                    dateDOB += String.valueOf(cal.get(Calendar.HOUR)) + ":";
                }
                if ((String.valueOf(cal.get(Calendar.MINUTE) + 1).length()==1)){
                    dateDOB += "0" + String.valueOf(cal.get(Calendar.MINUTE) + 1);
                }
                else{
                    dateDOB += String.valueOf(cal.get(Calendar.MINUTE) + 1);
                }
            }
            catch (ParseException t) {t.printStackTrace();}
        }
        return dateDOB;
    }

    public void dataChanged(List<Match> allPlayers1) {
        matches.clear();
        matches.addAll(allPlayers1);
        notifyDataSetChanged();
    }
}
