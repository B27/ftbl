package com.example.user.secondfootballapp.home.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.secondfootballapp.DateToString;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.AdsPage;
import com.example.user.secondfootballapp.model.Announce;

import java.util.List;

public class RecyclerViewAdsAdapter extends RecyclerView.Adapter<RecyclerViewAdsAdapter.ViewHolder> {
    private List<Announce> ads;
    private AdsPage context;
    private PersonalActivity activity;
    public RecyclerViewAdsAdapter(Activity activity, AdsPage context, List<Announce> ads){
        this.ads = ads;
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads, parent, false);
        RecyclerViewAdsAdapter.ViewHolder holder = new RecyclerViewAdsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String str = ads.get(position).getContent();
        holder.textTitle.setText(str);
        DateToString dateToString = new DateToString();
        str = ads.get(position).getDate();
        holder.textDate.setText(dateToString.ChangeDate(str));
        if (position==(ads.size()-1)){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return ads.size();
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
        ads.clear();
        ads.addAll(allPlayers1);
        notifyDataSetChanged();
    }
}
