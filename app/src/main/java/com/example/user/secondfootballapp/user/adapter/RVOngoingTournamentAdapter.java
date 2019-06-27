package com.example.user.secondfootballapp.user.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.DateToString;
import com.example.user.secondfootballapp.FullScreenImage;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SetImage;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.PersonTeams;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.user.activity.OngoingTournamentFragment;
import com.example.user.secondfootballapp.user.activity.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.user.secondfootballapp.Controller.BASE_URL;


public class RVOngoingTournamentAdapter extends RecyclerView.Adapter<RVOngoingTournamentAdapter.ViewHolder> {
    Logger log = LoggerFactory.getLogger(OngoingTournamentFragment.class);
    OngoingTournamentFragment context;
    List<PersonTeams> leagues;
    String uriPic;
    public RVOngoingTournamentAdapter(OngoingTournamentFragment context, List<PersonTeams> leagues){
        this.context =  context;
        this.leagues = leagues;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_tournament, parent, false);
        RVOngoingTournamentAdapter.ViewHolder holder = new RVOngoingTournamentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonTeams personTeams = leagues.get(position);
        League league = null;
        for (League league1 : PersonalActivity.tournaments){
            if (league1.getId().equals(personTeams.getLeague())){
                league = league1;
                break;
            }
        }
        uriPic = BASE_URL;
        String teamId = personTeams.getTeam();
        Team teamLeague = null;
        for (Team team: league.getTeams()){
            if (team.getId().equals(teamId)){
                teamLeague = team;
            }
        }
        Club clubLeague = null;
        for (Club club : PersonalActivity.allClubs){
            if (club.getId().equals(teamLeague.getClub())){
                clubLeague = club;
            }
        }
        SetImage setImage = new SetImage();
        setImage.setImage(context.getActivity(), holder.image, clubLeague.getLogo());

        String str = league.getTourney() +". "+ league.getName();
        holder.textTitle.setText(str);
        DateToString dateToString = new DateToString();
        str = dateToString.ChangeDate(league.getBeginDate()) + "-" + dateToString.ChangeDate(league.getEndDate());
        holder.textDate.setText(str);
        str = teamLeague.getName();
        holder.textCommand.setText(str);
        if (position == (leagues.size() - 1)){
            holder.line
                    .setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textTitle;
        TextView textDate;
        TextView textCommand;
        View line;
        public ViewHolder(View item) {
            super(item);
            image = item.findViewById(R.id.userTournamentLogo);
            textTitle = item.findViewById(R.id.userTournamentTitle);
            textDate = item.findViewById(R.id.userTournamentDate);
            textCommand = item.findViewById(R.id.userTournamentCommandTitle);
            line = item.findViewById(R.id.userTournamentLine);
        }
    }

}
