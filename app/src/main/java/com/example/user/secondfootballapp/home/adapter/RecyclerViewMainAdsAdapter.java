package com.example.user.secondfootballapp.home.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.MainPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecyclerViewMainAdsAdapter extends RecyclerView.Adapter<RecyclerViewMainAdsAdapter.ViewHolder> {
    MainPage context;
    PersonalActivity activity;
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    public RecyclerViewMainAdsAdapter(Activity activity, MainPage context){
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads, parent, false);
        RecyclerViewMainAdsAdapter.ViewHolder holder = new RecyclerViewMainAdsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textTitle.setText("В мире футбола чето там изменилось. Вау. Как неожиданно.");
        holder.textDate.setText("27.01.18");

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        TextView textDate;
        public ViewHolder(View item) {
            super(item);
            textDate = (TextView) item.findViewById(R.id.adsDate);
            textTitle = (TextView) item.findViewById(R.id.adsTitle);
        }
    }
}
