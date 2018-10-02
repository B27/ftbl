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
import com.example.user.secondfootballapp.user.activity.TimeTableFragment;

public class RVTimeTableAdapter extends RecyclerView.Adapter<RVTimeTableAdapter.ViewHolder>{
    TimeTableFragment context;
    PersonalActivity activity;
    public RVTimeTableAdapter(Activity activity, TimeTableFragment context){
        this.context =  context;
        this.activity = (PersonalActivity) activity;
    }
    @NonNull
    @Override
    public RVTimeTableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable, parent, false);
        RVTimeTableAdapter.ViewHolder holder = new RVTimeTableAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVTimeTableAdapter.ViewHolder holder, int position) {
        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EditTimeTable.class);
                String title = "Some title";
                Bundle bundle = new Bundle();
                bundle.putString("NEWSTITLE", title);
                intent.putExtra("NEWSTITLE", bundle);
                context.startActivity(intent);
            }
        });
        if (position==9){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTime;
        TextView textLeague;
        TextView textStadium;
        TextView textCommandTitle1;
        TextView textCommandTitle2;
        TextView textScore;
        ImageView image1;
        ImageView image2;
        TextView textReferee1;
        TextView textReferee2;
        TextView textReferee3;
        TextView textReferee4;
        ImageButton buttonEdit;
        View line;
        public ViewHolder(View item) {
            super(item);
            textDate = (TextView) item.findViewById(R.id.timetableMatchDate);
            textTime = (TextView) item.findViewById(R.id.timetableMatchTime);
            textLeague = (TextView) item.findViewById(R.id.timetableMatchLeague);
            textStadium = (TextView) item.findViewById(R.id.timetableMatchStadium);
            textCommandTitle1 = (TextView) item.findViewById(R.id.timetableMatchCommandTitle1);
            textCommandTitle2 = (TextView) item.findViewById(R.id.timetableMatchCommandTitle2);
            image1 = (ImageView) item.findViewById(R.id.timetableMatchCommandLogo1);
            image2 = (ImageView) item.findViewById(R.id.timetableMatchCommandLogo2);
            textReferee1 = (TextView) item.findViewById(R.id.timetableMatchReferee1);
            textReferee2 = (TextView) item.findViewById(R.id.timetableMatchReferee2);
            textReferee3 = (TextView) item.findViewById(R.id.timetableMatchReferee3);
            textReferee4 = (TextView) item.findViewById(R.id.timetableMatchReferee4);
            buttonEdit = (ImageButton) item.findViewById(R.id.timetableMatchEdit);
            //may be null
            textScore = (TextView) item.findViewById(R.id.timetableMatchScore);
            line = (View) item.findViewById(R.id.timetableMatchLine);
        }
    }
}
