package com.example.user.secondfootballapp.tournament.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.CommandInfoActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RVCommandStructureAdapter extends RecyclerView.Adapter<RVCommandStructureAdapter.ViewHolder> {
    Logger log = LoggerFactory.getLogger(CommandInfoActivity.class);
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.command_structure, parent, false);
        RVCommandStructureAdapter.ViewHolder holder = new RVCommandStructureAdapter.ViewHolder(view);
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
        return 12;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName;
        TextView textPoint1;
        TextView textPoint2;
        TextView textPoint3;
        TextView textPoint4;
        TextView textPoint5;
        public ViewHolder(View item) {
            super(item);
            textName = (TextView) item.findViewById(R.id.commandPlayer);
            textPoint1 = (TextView) item.findViewById(R.id.commandPlayerPoint1);
            textPoint2 = (TextView) item.findViewById(R.id.commandPlayerPoint2);
            textPoint3 = (TextView) item.findViewById(R.id.commandPlayerPoint3);
            textPoint4 = (TextView) item.findViewById(R.id.commandPlayerPoint4);
            textPoint5 = (TextView) item.findViewById(R.id.commandPlayerPoint5);
        }
    }
}
