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
import com.example.user.secondfootballapp.home.activity.HomePage;
import com.example.user.secondfootballapp.home.activity.NewsPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.ViewHolder> {
    //    List<News> news;
    HomePage context;
    PersonalActivity activity;
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    public RecyclerViewHomeAdapter(Activity activity, HomePage context){
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
//        holder.textTitle.setText("SomeTitle");
//        holder.textDate.setText("SomeDate");
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
//                holder.textNews.setVisibility(View.VISIBLE);
//                holder.imageView.setVisibility(View.VISIBLE);
//                Toast.makeText(PersonalActivity.this, "WOW", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTitle;
        ImageButton imageButtonShow;
        //        ImageButton imageButtonHide;
//        TextView textNews;
//        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textDate = (TextView) itemView.findViewById(R.id.newsDate);
            textTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            imageButtonShow = (ImageButton) itemView.findViewById(R.id.newsButtonShow);
//            imageButtonHide = (ImageButton) itemView.findViewById(R.id.newsButtonHide);
//            imageView = (ImageView) itemView.findViewById(R.id.newsImg);
//            textNews = (TextView) itemView.findViewById(R.id.newsText);
        }
    }
}

