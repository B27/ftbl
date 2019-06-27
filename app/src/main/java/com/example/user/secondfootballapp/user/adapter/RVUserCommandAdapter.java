package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.secondfootballapp.DateToString;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.PersonTeams;
import com.example.user.secondfootballapp.model.Player;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.user.activity.UserCommands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RVUserCommandAdapter extends RecyclerView.Adapter<RVUserCommandAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(UserCommands.class);
    UserCommands context;
    PersonalActivity activity;
    List<PersonTeams> list;
    public RVUserCommandAdapter (Activity activity, List<PersonTeams> list){
        this.activity = (PersonalActivity) activity;
//        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_command, parent, false);
        RVUserCommandAdapter.ViewHolder holder = new RVUserCommandAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonTeams personTeams = list.get(position);
//        League league = personTeams.getLeague();
        League league = null;
        for (League league1 : PersonalActivity.tournaments){
            if (league1.getId().equals(personTeams.getLeague())){
                league = league1;
                break;
            }
        }
        String teamId = personTeams.getTeam();
        Team teamLeague = null;
        for (Team team: league.getTeams()){
            if (team.getId().equals(teamId)){
                teamLeague = team;
            }
        }
//        Team team = personTeams.getTeam();
//        holder.textTournamentTitle.setText();
        String title = "Команда: ";
        String date = "Начало: ";
        String transfer = "Трансферные периоды: ";
        String playersNum = "Количество игроков: ";
        String str = title + teamLeague.getName();
        holder.textCommandTitle.setText(str);
        if (teamLeague.getStatus().equals("Rejected")){
            holder.textStatus.setText("Отклонена");
            holder.textStatus.setTextColor(ContextCompat.getColor(activity, R.color.colorBadge));
        }
        if (teamLeague.getStatus().equals("Pending")){
            holder.textStatus.setText("Ожидание");
        }
        if (teamLeague.getStatus().equals("Approved")){
            holder.textStatus.setText("Утверждена");
            holder.textStatus.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        }

        str = league.getTourney() + ". " + league.getName();
        holder.textTournamentTitle.setText(str);

        DateToString dateToString = new DateToString();
        str = date + dateToString.ChangeDate(league.getBeginDate());
        holder.textTournamentDate.setText(str);
        log.error(str);
        str = transfer + dateToString.ChangeDate(league.getTransferBegin()) + "-" + dateToString.ChangeDate(league.getTransferEnd());
        holder.textTransfer.setText(str);
        List<Player> players = teamLeague.getPlayers();
        List<Player> playerList = new ArrayList<>();
        for (Player player: players){
            if (player.getInviteStatus().equals("Approved") || player.getInviteStatus().equals("Accepted")){
                playerList.add(player);
            }
        }
        str = playersNum + playerList.size();
        holder.textPlayersNum.setText(str);
        if (position== (list.size() - 1)){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textTournamentTitle;
        TextView textCommandTitle;
        TextView textTournamentDate;
        TextView textTransfer;
        TextView textPlayersNum;
        TextView textStatus;
        View line;
        public ViewHolder(View item) {
            super(item);
            textTournamentTitle = item.findViewById(R.id.userCommandTournamentTitle);
            textCommandTitle = item.findViewById(R.id.userCommandInfoTitle);
            textTournamentDate = item.findViewById(R.id.userCommandTournamentDate);
            textTransfer = item.findViewById(R.id.userCommandTournamentTransfer);
            textPlayersNum = item.findViewById(R.id.userCommandInfoPlayersNum);
            textStatus = item.findViewById(R.id.teamStatus);
           line = item.findViewById(R.id.userCommandLine);
        }
    }





}
