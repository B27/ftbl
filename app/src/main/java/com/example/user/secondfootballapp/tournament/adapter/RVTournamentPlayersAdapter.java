package com.example.user.secondfootballapp.tournament.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;


public class RVTournamentPlayersAdapter extends RecyclerView.Adapter<RVTournamentPlayersAdapter.ViewHolder>{
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tournament_player, parent, false);
        RVTournamentPlayersAdapter.ViewHolder holder = new RVTournamentPlayersAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText("Иванов В.В.");
        holder.textPoint1.setText("1");
        holder.textPoint2.setText("2");
        holder.textPoint3.setText("3");
        holder.textPoint4.setText("4");
        holder.textPoint5.setText("5");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textName;
        TextView textPoint1;
        TextView textPoint2;
        TextView textPoint3;
        TextView textPoint4;
        TextView textPoint5;
        public ViewHolder(View item) {
            super(item);
            textName = (TextView) item.findViewById(R.id.tournamentPlayer);
            textPoint1 = (TextView) item.findViewById(R.id.tournamentPlayerPoint1);
            textPoint2 = (TextView) item.findViewById(R.id.tournamentPlayerPoint2);
            textPoint3 = (TextView) item.findViewById(R.id.tournamentPlayerPoint3);
            textPoint4 = (TextView) item.findViewById(R.id.tournamentPlayerPoint4);
            textPoint5 = (TextView) item.findViewById(R.id.tournamentPlayerPoint5);
        }
    }
}
