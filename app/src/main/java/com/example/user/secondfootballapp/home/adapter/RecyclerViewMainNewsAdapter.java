package com.example.user.secondfootballapp.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.MainPage;
import com.example.user.secondfootballapp.home.activity.NewsPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecyclerViewMainNewsAdapter extends RecyclerView.Adapter<RecyclerViewMainNewsAdapter.ViewHolder> {
    MainPage context;
    PersonalActivity activity;
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    public RecyclerViewMainNewsAdapter(Activity activity, MainPage context){
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news, parent, false);
        RecyclerViewMainNewsAdapter.ViewHolder holder = new RecyclerViewMainNewsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textTitle.setText("В мире футбола чето там изменилось. Вау. Как неожиданно.");
//        holder.textDate.setText("27.01.18");
        holder.imageButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.info("INFO: hello from RecyclerViewHomeAdapter");
                Intent intent = new Intent(activity, NewsPage.class);
                String title = "Some title";
                Bundle bundle = new Bundle();
                bundle.putString("NEWSTITLE", title);
                intent.putExtra("NEWSTITLE", bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTitle;
        ImageButton imageButtonShow;
        public ViewHolder(View itemView) {
            super(itemView);
            textDate = (TextView) itemView.findViewById(R.id.newsDate);
            textTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            imageButtonShow = (ImageButton) itemView.findViewById(R.id.newsButtonShow);
        }
    }
}

