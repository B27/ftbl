package com.example.user.secondfootballapp.tournament.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.TournamentCommandFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RVTournamentCommandAdapter extends RecyclerView.Adapter<RVTournamentCommandAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    TournamentCommandFragment context;
    PersonalActivity activity;

    public RVTournamentCommandAdapter(Activity activity, TournamentCommandFragment context){
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.tournament_info_tab_command_card, parent, false);
        RVTournamentCommandAdapter.ViewHolder holder = new RVTournamentCommandAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textGroupTitle.setText("Группа А");
        //add onClick in adapter
        //getChildFragmentManager()
        RVTournamentCommandCardAdapter adapter = new RVTournamentCommandCardAdapter(activity, context);
        holder.recyclerView.setAdapter(adapter);
//        holder.recyclerView.setLayoutManager(new CustomLinearLayoutManager(activity));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textGroupTitle;
        View cardView;
        RecyclerView recyclerView;
        public ViewHolder(View item) {
            super(item);
            textGroupTitle = (TextView) item.findViewById(R.id.groupTitle);
            cardView = (View) item.findViewById(R.id.cv);
            recyclerView = (RecyclerView) item.findViewById(R.id.tournamentInfoTabCommandCard);
        }
    }
}
