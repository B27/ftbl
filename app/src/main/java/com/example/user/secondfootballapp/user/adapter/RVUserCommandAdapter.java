package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.UserClubInfo;
import com.example.user.secondfootballapp.user.activity.UserClubs;
import com.example.user.secondfootballapp.user.activity.UserCommandInfo;

public class RVUserCommandAdapter extends RecyclerView.Adapter<RVUserCommandAdapter.ViewHolder>{
    UserClubs context;
    PersonalActivity activity;
    public RVUserCommandAdapter (Activity activity, UserClubs context){
        this.activity = (PersonalActivity) activity;
        this.context = context;
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
//        holder.textTournamentTitle.setText();
        String title = "Команда: ";
        String date = "Начало: ";
        String transfer = "Трансферные периоды: ";
        String playersNum = "Количество игроков: ";
        String str = title + "Лара";
        holder.textCommandTitle.setText(str);
        str = date + "12.07.2018";
        holder.textTournamentDate.setText(str);
        str = transfer + "12.07.2018 - 12.07.2018";
        holder.textTransfer.setText(str);
        str = playersNum + "24";
        holder.textPlayersNum.setText(str);
//        if (status)
//        Glide.with(context)
//                .asBitmap()
//                .load(R.drawable.ic_con)
//                .apply(new RequestOptions()
//                        .format(DecodeFormat.PREFER_ARGB_8888)
//                        .priority(Priority.HIGH))
//                .into(holder.image);
        if (position==4){
            holder.line.setVisibility(View.INVISIBLE);
        }
        holder.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UserCommandInfo.class);
                String title = "Some title";
                Bundle bundle = new Bundle();
                bundle.putString("NEWSTITLE", title);
                intent.putExtra("NEWSTITLE", bundle);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        ImageButton buttonShow;
        TextView textTournamentTitle;
        TextView textCommandTitle;
        TextView textTournamentDate;
        TextView textTransfer;
        TextView textPlayersNum;
        View line;
        public ViewHolder(View item) {
            super(item);
            buttonShow = (ImageButton) item.findViewById(R.id.userCommandShow);
//            image = (ImageView) item.findViewById(R.id.userCommandTournamentStatusImg);
            textTournamentTitle = (TextView) item.findViewById(R.id.userCommandTournamentTitle);
            textCommandTitle = (TextView) item.findViewById(R.id.userCommandInfoTitle);
            textTournamentDate = (TextView) item.findViewById(R.id.userCommandTournamentDate);
            textTransfer = (TextView) item.findViewById(R.id.userCommandTournamentTransfer);
            textPlayersNum = (TextView) item.findViewById(R.id.userCommandInfoPlayersNum);
           line = (View) item.findViewById(R.id.userCommandLine);
        }
    }
}
