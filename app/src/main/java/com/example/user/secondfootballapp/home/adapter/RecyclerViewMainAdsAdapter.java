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
import com.example.user.secondfootballapp.home.activity.NewsAndAds;
import com.example.user.secondfootballapp.model.Announce;
import com.example.user.secondfootballapp.model.News_;

import java.util.List;

public class RecyclerViewMainAdsAdapter extends RecyclerView.Adapter<RecyclerViewMainAdsAdapter.ViewHolder> {

    private List<Announce> announces;
    private NewsAndAds context;
    private PersonalActivity activity;
    public RecyclerViewMainAdsAdapter(Activity activity, NewsAndAds context, List<Announce> announces){
        this.announces = announces;
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
        holder.textDate.setVisibility(View.GONE);
        String str = announces.get(position).getContent();
        holder.textTitle.setText(str);
        if (announces.size()>=2 && position==2){
            holder.line.setVisibility(View.INVISIBLE);
        }
        if (announces.size()==1 && position==1){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (announces.size()>=2){
            count=2;
        }
        if (announces.size()==1){
            count=1;
        }
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        TextView textDate;
        View line;
        public ViewHolder(View item) {
            super(item);
            textDate = item.findViewById(R.id.adsDate);
            textTitle = item.findViewById(R.id.adsTitle);
            line = item.findViewById(R.id.adsLine);
        }
    }

    public void dataChanged(List<Announce> allPlayers1) {
        announces.clear();
        announces.addAll(allPlayers1);
        notifyDataSetChanged();
    }
}
