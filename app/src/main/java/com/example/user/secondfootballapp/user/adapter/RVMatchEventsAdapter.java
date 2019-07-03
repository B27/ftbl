package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.user.activity.MatchEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RVMatchEventsAdapter extends RecyclerView.Adapter<RVMatchEventsAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(MatchEvents.class);
    MatchEvents context;
    private HashMap<Integer, String> halves;
    private List<PlayerEvent> playerAllEvents;
    public RVMatchEventsAdapter(Activity context, HashMap<Integer, String> halves, List<PlayerEvent> playerAllEvents){
        this.context = (MatchEvents) context;
        this.halves = halves;
        this.playerAllEvents = playerAllEvents;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.half_events, parent, false);
        RVMatchEventsAdapter.ViewHolder holder = new RVMatchEventsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String half = halves.get(position);
        holder.textHalf.setText(half);
        List<PlayerEvent> playerEvents = new ArrayList<>();
        for (PlayerEvent playerEvent : playerAllEvents){
            if (playerEvent.getEvent().getTime().equals(half)){
                playerEvents.add(playerEvent);
            }
        }
        RVEventsAdapter adapter = new RVEventsAdapter(context, playerEvents);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public int getItemCount() {
        return halves.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textHalf;
        RecyclerView recyclerView;
        public ViewHolder(View item) {
            super(item);
            textHalf = item.findViewById(R.id.matchEventsHalf);
            recyclerView = item.findViewById(R.id.recyclerViewHalfEvents);
        }
    }
}
