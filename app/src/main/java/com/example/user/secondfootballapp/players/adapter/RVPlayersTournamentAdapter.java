package com.example.user.secondfootballapp.players.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.players.activity.Player;


public class RVPlayersTournamentAdapter extends RecyclerView.Adapter<RVPlayersTournamentAdapter.ViewHolder>{
    Player context;
    public RVPlayersTournamentAdapter(Player context){
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_tournament, parent, false);
        RVPlayersTournamentAdapter.ViewHolder holder = new RVPlayersTournamentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textTournament.setText("");
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTournament;
        public ViewHolder(View item) {
            super(item);
            textTournament = (TextView) item.findViewById(R.id.tournamentPlayersTitle);
        }
    }
}
