package com.example.user.secondfootballapp.club.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
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
import com.example.user.secondfootballapp.club.activity.Club;
import com.example.user.secondfootballapp.club.activity.ClubPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecyclerViewClubAdapter extends RecyclerView.Adapter<RecyclerViewClubAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    ClubPage context;
    PersonalActivity activity;
    public RecyclerViewClubAdapter(Activity activity, ClubPage context){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textTitle.setText("Спартак");
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_football)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.imageLogo);
        holder.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.info("INFO: hello from RecyclerViewClubAdapterr");
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(context.getId(), new Club()).addToBackStack(null).commit();
            }
        });
        if (position == 7){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View line;
        ImageView imageLogo;
        TextView textTitle;
        ImageButton buttonShow;
        public ViewHolder(View item) {
            super(item);
            line = (View) item.findViewById(R.id.clubLine);
            imageLogo = (ImageView) item.findViewById(R.id.clubLogo);
            textTitle = (TextView) item.findViewById(R.id.clubTitle);
            buttonShow = (ImageButton) item.findViewById(R.id.clubButtonShow);
        }
    }
}
