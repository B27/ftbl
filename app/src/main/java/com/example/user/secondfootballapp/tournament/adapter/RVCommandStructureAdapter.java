package com.example.user.secondfootballapp.tournament.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.user.secondfootballapp.CheckName;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.LeagueInfo;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.Player;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.tournament.activity.CommandInfoActivity;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RVCommandStructureAdapter extends RecyclerView.Adapter<RVCommandStructureAdapter.ViewHolder> {
    Logger log = LoggerFactory.getLogger(CommandInfoActivity.class);
    private List<Player> players;
    public RVCommandStructureAdapter(List<Player> players){
        this.players = players;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.command_structure, parent, false);
        RVCommandStructureAdapter.ViewHolder holder = new RVCommandStructureAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person player = null;
        for (Person person : PersonalActivity.AllPeople){
            if (person.getId().equals(players.get(position).getPlayerId())){
                player = person;
                break;
            }
        }
        try {
            String str;
            CheckName checkName = new CheckName();
            str = checkName.check(player.getSurname(), player.getName(), player.getLastname());
            holder.textName.setText(str);
            int count = players.get(position).getMatches();
            str = String.valueOf(count);
            holder.textPoint2.setText(str);
            count = players.get(position).getGoals();
            str = String.valueOf(count);
            holder.textPoint3.setText(str);
            count = players.get(position).getYellowCards();
//            count = players.get(position).getActiveYellowCards();
            str = String.valueOf(count);
            holder.textPoint4.setText(str);
//            count = players.get(position).getActiveDisquals();
            count = players.get(position).getDisquals();
            str = String.valueOf(count);
            holder.textPoint5.setText(str);
            if (!str.equals("0")){
                holder.linearLayout.setBackgroundResource(R.color.colorBadgeScale);
            }
        }catch (NullPointerException e){
            String str = "Удален";
            holder.textName.setText(str);
            str="";
            holder.textPoint2.setText(str);
            holder.textPoint3.setText(str);
            holder.textPoint4.setText(str);
            holder.textPoint5.setText(str);
        }


    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName;
//        TextView textPoint1;
        TextView textPoint2;
        TextView textPoint3;
        TextView textPoint4;
        TextView textPoint5;
        LinearLayout linearLayout;
        public ViewHolder(View item) {
            super(item);
            textName = item.findViewById(R.id.commandPlayer);
//            textPoint1 = item.findViewById(R.id.commandPlayerPoint1);
            textPoint2 = item.findViewById(R.id.commandPlayerPoint2);
            textPoint3 = item.findViewById(R.id.commandPlayerPoint3);
            textPoint4 = item.findViewById(R.id.commandPlayerPoint4);
            textPoint5 = item.findViewById(R.id.commandPlayerPoint5);
            linearLayout = item.findViewById(R.id.commandPlayerLayout);
        }
    }
}
