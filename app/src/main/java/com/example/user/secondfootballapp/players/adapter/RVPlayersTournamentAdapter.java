package com.example.user.secondfootballapp.players.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.PastLeague;
import com.example.user.secondfootballapp.players.activity.Player;

import java.util.List;


public class RVPlayersTournamentAdapter extends RecyclerView.Adapter<RVPlayersTournamentAdapter.ViewHolder>{
    private Player context;
    private List<PastLeague> pastLeagues;
    public RVPlayersTournamentAdapter(Player context, List<PastLeague> pastLeagues){
        this.context = context;
        this.pastLeagues = pastLeagues;
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
        String str;
        if (pastLeagues.get(position).getPlace().equals("0")){
            str = pastLeagues.get(position).getTourney() + ". " + pastLeagues.get(position).getName() + ". Команда: "
                    + pastLeagues.get(position).getTeamName();
        }
        else {
            str = pastLeagues.get(position).getTourney() + ". " + pastLeagues.get(position).getName() + ". Команда: "
                    + pastLeagues.get(position).getTeamName() + ". " + pastLeagues.get(position).getPlace() + " место";
        }

        holder.textTournament.setText(str);
        if (position==(pastLeagues.size()-1)){
            holder.view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return pastLeagues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView textTournament;
        public ViewHolder(View item) {
            super(item);
            textTournament = item.findViewById(R.id.tournamentPlayersTitle);
            view = item.findViewById(R.id.playerTournamentLine);
        }
    }
}
