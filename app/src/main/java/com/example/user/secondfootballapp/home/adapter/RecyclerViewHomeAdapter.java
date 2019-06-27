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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.secondfootballapp.DateToString;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.HomePage;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.model.ActiveMatch;
import com.example.user.secondfootballapp.model.News_;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.ViewHolder> {
    private List<News_> news;
    private HomePage context;
    private PersonalActivity activity;
    public RecyclerViewHomeAdapter(Activity activity, HomePage context, List<News_> news){
        this.news = news;
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String str = news.get(position).getCaption();
        holder.textTitle.setText(str);
        DateToString dateToString = new DateToString();
        str = news.get(position).getCreatedAt();
        holder.textDate.setText(dateToString.ChangeDate(str));
        holder.imageButtonShow.setOnClickListener(v -> {
            Intent intent = new Intent(activity, NewsPage.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("NEWS" ,news.get(position));
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
        if (position==(news.size()-1)){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTitle;
        LinearLayout imageButtonShow;
        View line;
        public ViewHolder(View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.newsDate);
            textTitle = itemView.findViewById(R.id.newsTitle);
            imageButtonShow = itemView.findViewById(R.id.newsButtonShow);
            line = itemView.findViewById(R.id.newsLine);
        }
    }

    public void dataChanged(List<News_> allPlayers1) {
        news.clear();
        news.addAll(allPlayers1);
        notifyDataSetChanged();
    }
}

