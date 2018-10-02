package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.EditTimeTable;
import com.example.user.secondfootballapp.user.activity.MyMatches;
import com.example.user.secondfootballapp.user.activity.ProtocolEdit;

public class RVMyMatchesAdapter extends RecyclerView.Adapter<RVMyMatchesAdapter.ViewHolder>{
    MyMatches context;
    PersonalActivity activity;
    public RVMyMatchesAdapter(Activity activity, MyMatches context){
        this.context =  context;
        this.activity = (PersonalActivity) activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match, parent, false);
        RVMyMatchesAdapter.ViewHolder holder = new RVMyMatchesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ProtocolEdit.class);
                String title = "Some title";
                Bundle bundle = new Bundle();
                bundle.putString("NEWSTITLE", title);
                intent.putExtra("NEWSTITLE", bundle);
                context.startActivity(intent);
            }
        });
        if (position==4){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTime;
        TextView textLeague;
        TextView textStadium;
        TextView textScore;
        TextView textCommand1;
        TextView textCommand2;
        ImageView image1;
        ImageView image2;
        ImageButton buttonShow;
        View line;
        public ViewHolder(View item) {
            super(item);
            textDate = (TextView) item.findViewById(R.id.myMatchDate);
            textTime = (TextView) item.findViewById(R.id.myMatchTime);
            textLeague = (TextView) item.findViewById(R.id.myMatchLeague);
            textStadium = (TextView) item.findViewById(R.id.myMatchStadium);
            textScore = (TextView) item.findViewById(R.id.myMatchScore);
            textCommand1 = (TextView) item.findViewById(R.id.myMatchCommandTitle1);
            textCommand2 = (TextView) item.findViewById(R.id.myMatchCommandTitle2);
            image1 = (ImageView) item.findViewById(R.id.myMatchCommandLogo1);
            image2 = (ImageView) item.findViewById(R.id.myMatchCommandLogo2);
            buttonShow = (ImageButton) item.findViewById(R.id.myMatchShowProtocol);
            line = (View) item.findViewById(R.id.myMatchLine);
        }
    }
}
