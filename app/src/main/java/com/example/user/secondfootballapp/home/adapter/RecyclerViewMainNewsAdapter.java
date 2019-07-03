package com.example.user.secondfootballapp.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.secondfootballapp.DateToString;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.NewsAndAds;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.model.News_;

import java.util.List;

public class RecyclerViewMainNewsAdapter extends RecyclerView.Adapter<RecyclerViewMainNewsAdapter.ViewHolder> {
    private List<News_> news;
    private NewsAndAds context;
    private PersonalActivity activity;
    public RecyclerViewMainNewsAdapter(Activity activity, NewsAndAds context, List<News_> news){
        this.news = news;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
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
        if (news.size()>=2 && position==2){
            holder.line.setVisibility(View.INVISIBLE);
        }
        if (news.size()==1 && position==1){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (news.size()>=2){
            count=2;
        }
        if (news.size()==1){
            count=1;
        }
        return count;
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

