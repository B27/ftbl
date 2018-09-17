package com.example.user.secondfootballapp.tournament.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.CommandInfoActivity;
import com.example.user.secondfootballapp.tournament.activity.CommandMatchFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RVCommandMatchAdapter extends RecyclerView.Adapter<RVCommandMatchAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(CommandInfoActivity.class);
    CommandMatchFragment context;
    CommandInfoActivity activity;
    public RVCommandMatchAdapter(Activity activity, CommandMatchFragment context){
        this.activity = (CommandInfoActivity) activity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_fragment, parent, false);
        RVCommandMatchAdapter.ViewHolder holder = new RVCommandMatchAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String str = "SomeText";
        if (str == "SomeText"){
            holder.textCommandTitle1.setText("Лара");
            holder.textCommandTitle2.setText("Спартак");
            holder.imgCard1.setVisibility(View.INVISIBLE);
            holder.imgCard2.setVisibility(View.INVISIBLE);
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_club)
                    .apply(new RequestOptions()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH))
                    .into(holder.imgCommandLogo1);
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_club2)
                    .apply(new RequestOptions()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH))
                    .into(holder.imgCommandLogo2);
            holder.textDate.setText("24.01.18");
            holder.textTime.setText("12:30");
            holder.textScore.setText("4:1");
            holder.textStadium.setText("ВСГУТУ");
            holder.textTournamentTitle.setText("финал какой-нибудь");
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCommandTitle1;
        TextView textCommandTitle2;
        ImageView imgCommandLogo1;
        ImageView imgCommandLogo2;
        TextView textStadium;
        TextView textDate;
        TextView textTime;
        TextView textTournamentTitle;
        ImageView imgCard1;
        ImageView imgCard2;
        TextView textScore;

        public ViewHolder(View item) {
            super(item);
            textCommandTitle1 = (TextView) item.findViewById(R.id.timetableCommandTitle1);
            textCommandTitle2 = (TextView) item.findViewById(R.id.timetableCommandTitle2);
            imgCommandLogo1 = (ImageView) item.findViewById(R.id.timetableCommandLogo1);
            imgCommandLogo2 = (ImageView) item.findViewById(R.id.timetableCommandLogo2);
            textStadium = (TextView) item.findViewById(R.id.timetableStadium);
            textDate = (TextView) item.findViewById(R.id.timetableDate);
            textTime = (TextView) item.findViewById(R.id.timetableTime);
            textTournamentTitle = (TextView) item.findViewById(R.id.timetableLeagueTitle);
            imgCard1 = (ImageView) item.findViewById(R.id.timetableCommandCard1);
            imgCard2 = (ImageView) item.findViewById(R.id.timetableCommandCard2);
            textScore = (TextView) item.findViewById(R.id.timetableGameScore);
        }
    }
}
