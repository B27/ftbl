package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;
import com.example.user.secondfootballapp.user.activity.ProtocolEdit;
import com.example.user.secondfootballapp.user.activity.ProtocolEventsEdit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RVProtocolEditAdapter extends RecyclerView.Adapter<RVProtocolEditAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(ProtocolEdit.class);
    ProtocolEventsEdit context;
    public RVProtocolEditAdapter(Activity context){
        this.context = (ProtocolEventsEdit) context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.protocol, parent, false);
        RVProtocolEditAdapter.ViewHolder holder = new RVProtocolEditAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (ProtocolEventsEdit.items.size() == 1){
//            holder.buttonDelete.setVisibility(View.INVISIBLE);
        }
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPosition = holder.getAdapterPosition();
                notifyItemRemoved(newPosition);
                ProtocolEventsEdit.items.remove(newPosition);
                if (ProtocolEventsEdit.items.size()<=1){
                    if (ProtocolEventsEdit.behavior != null) {
                        ProtocolEventsEdit.behavior.onNestedFling(ProtocolEdit.coordinatorLayout, ProtocolEdit.appBarLayout, null, 0, 10000, true);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProtocolEventsEdit.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Spinner spinnerEvent;
        EditText editMinutes;
        EditText editSeconds;
        Spinner spinnerHalf;
        Spinner spinnerPlayers;
        Button buttonDelete;
        View line;
        public ViewHolder(View item) {
            super(item);
            spinnerEvent = (Spinner) item.findViewById(R.id.editProtocolSpinnerEvent);
            spinnerHalf = (Spinner) item.findViewById(R.id.editProtocolSpinnerHalf);
            spinnerPlayers = (Spinner) item.findViewById(R.id.editProtocolSpinnerPlayers);
            editMinutes = (EditText) item.findViewById(R.id.editProtocolEventMinutes);
            editSeconds = (EditText) item.findViewById(R.id.editProtocolEventSeconds);
            buttonDelete = (Button) item.findViewById(R.id.editProtocolDeleteEvent);
            line = (View) item.findViewById(R.id.editProtocolLine);
        }
    }
}
