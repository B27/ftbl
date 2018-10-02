package com.example.user.secondfootballapp.user.adapter;

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
import com.example.user.secondfootballapp.user.activity.MatchEvents;

public class RVEventsAdapter extends RecyclerView.Adapter<RVEventsAdapter.ViewHolder>{
    MatchEvents context;
    public RVEventsAdapter(Activity context){
        this.context = (MatchEvents) context;
    }
    @NonNull
    @Override
    public RVEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events, parent, false);
        RVEventsAdapter.ViewHolder holder = new RVEventsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVEventsAdapter.ViewHolder holder, int position) {
        String str = "16:30";
        holder.textTime.setText(str);
        holder.textEvent.setText("K");
        str = "Иванов В.В.";
        holder.textName.setText(str);
        Glide.with(context)
            .asBitmap()
            .load(R.drawable.ic_fin)
            .apply(new RequestOptions()
                    .circleCropTransform()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .priority(Priority.HIGH))
            .into(holder.image);
        if (position==4){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTime;
        TextView textName;
        TextView textEvent;
        ImageView image;
        View line;
        public ViewHolder(View item) {
            super(item);
            textTime = (TextView) item.findViewById(R.id.eventTime);
            textName = (TextView) item.findViewById(R.id.eventPlayerName);
            textEvent = (TextView) item.findViewById(R.id.eventPlayerEvent);
            image = (ImageView) item.findViewById(R.id.eventCommandLogo);
            line = (View) item.findViewById(R.id.eventLine);
        }
    }
}
