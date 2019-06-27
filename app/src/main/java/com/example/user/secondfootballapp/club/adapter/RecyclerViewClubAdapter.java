package com.example.user.secondfootballapp.club.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.FullScreenImage;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.club.activity.Club;
import com.example.user.secondfootballapp.club.activity.ClubPage;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.players.activity.Player;

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

public class RecyclerViewClubAdapter extends RecyclerView.Adapter<RecyclerViewClubAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    List<com.example.user.secondfootballapp.model.Club> allClubs;
    ClubPage context;
    PersonalActivity activity;
    public RecyclerViewClubAdapter(Activity activity, ClubPage context,  List<com.example.user.secondfootballapp.model.Club> allClubs){
        this.allClubs = allClubs;
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club, parent, false);
        RecyclerViewClubAdapter.ViewHolder holder = new RecyclerViewClubAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        try {
            String uriPic = BASE_URL;
            String str = allClubs.get(position).getName();
            holder.textTitle.setText(str);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.optionalCircleCrop();
            requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
            requestOptions.error(R.drawable.ic_logo2);
            requestOptions.override(500, 500);
            requestOptions.priority(Priority.HIGH);
            try {
                uriPic += "/" + allClubs.get(position).getLogo();
                log.info("INFO: url " + uriPic);
                URL url = new URL(uriPic);
                Glide.with(activity)
                        .asBitmap()
                        .load(url)
                        .apply(requestOptions)
                        .into(holder.imageLogo);
                final String finalUriPic = uriPic;
                holder.imageLogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (finalUriPic.contains(".jpg") || finalUriPic.contains(".jpeg") || finalUriPic.contains(".png")) {
                            Intent intent = new Intent(activity, FullScreenImage.class);
                            intent.putExtra("player_photo", finalUriPic);
                            context.startActivity(intent);
                        }

                    }
                });
            } catch (MalformedURLException e) {
                Glide.with(activity)
                        .asBitmap()
                        .load(R.drawable.ic_logo2)
                        .apply(requestOptions)
                        .into(holder.imageLogo);
            }

            holder.buttonShow.setOnClickListener(v -> {
                Club club = new Club();
                Bundle bundle = new Bundle();
                com.example.user.secondfootballapp.model.Club club1 = allClubs.get(position);
                bundle.putSerializable("CLUBINFO", club1);
                club.setArguments(bundle);
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.pageContainer, club).hide(PersonalActivity.active).show(club).commit();
                PersonalActivity.active = club;
            });
            if (position == (allClubs.size() - 1)){
                holder.line.setVisibility(View.INVISIBLE);
            }


        } catch (Exception e) {}

    }

    @Override
    public int getItemCount() {
        return allClubs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View line;
        ImageView imageLogo;
        TextView textTitle;
        LinearLayout buttonShow;
        public ViewHolder(View item) {
            super(item);
            line = item.findViewById(R.id.clubLine);
            imageLogo = item.findViewById(R.id.clubLogo);
            textTitle = item.findViewById(R.id.clubTitle);
            buttonShow = item.findViewById(R.id.clubButtonShow);
        }
    }

    public void dataChanged(List<com.example.user.secondfootballapp.model.Club> allPlayers1){
        allClubs.clear();
        allClubs.addAll(allPlayers1);
        notifyDataSetChanged();
    }
}
