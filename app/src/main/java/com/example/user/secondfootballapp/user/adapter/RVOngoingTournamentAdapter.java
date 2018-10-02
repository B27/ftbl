package com.example.user.secondfootballapp.user.adapter;

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
import com.example.user.secondfootballapp.user.activity.OngoingTournamentFragment;


public class RVOngoingTournamentAdapter extends RecyclerView.Adapter<RVOngoingTournamentAdapter.ViewHolder> {
    OngoingTournamentFragment context;
    public RVOngoingTournamentAdapter(OngoingTournamentFragment context){
        this.context =  context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_tournament, parent, false);
        RVOngoingTournamentAdapter.ViewHolder holder = new RVOngoingTournamentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_logo)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textTitle;
        TextView textDate;
        TextView textCommand;
        public ViewHolder(View item) {
            super(item);
            image = (ImageView) item.findViewById(R.id.userTournamentLogo);
            textTitle = (TextView) item.findViewById(R.id.userTournamentTitle);
            textDate = (TextView) item.findViewById(R.id.userTournamentDate);
            textCommand = (TextView) item.findViewById(R.id.userTournamentCommandTitle);
        }
    }
}
