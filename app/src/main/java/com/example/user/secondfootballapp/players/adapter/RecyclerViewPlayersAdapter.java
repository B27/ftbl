package com.example.user.secondfootballapp.players.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.players.activity.Player;
import com.example.user.secondfootballapp.players.activity.PlayersPage;

public class RecyclerViewPlayersAdapter extends RecyclerView.Adapter<RecyclerViewPlayersAdapter.ViewHolder>{
    PlayersPage context;
    PersonalActivity activity;
    public RecyclerViewPlayersAdapter(Activity activity,PlayersPage context){
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player, parent, false);
        RecyclerViewPlayersAdapter.ViewHolder holder = new RecyclerViewPlayersAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textDOB.setText("27.03.18");
        holder.textName.setText("Иванов В.В.");
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.imageLogo);
        holder.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(context.getId(), new Player()).addToBackStack(null).commit();
            }
        });
        if (position==19){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView imageLogo;
        ImageButton buttonShow;
        TextView textName;
        TextView textDOB;
        View line;
        public ViewHolder(View item) {
            super(item);
            line = (View) item.findViewById(R.id.playersLine);
            imageLogo = (ImageView) item.findViewById(R.id.playerInfoLogo);
            buttonShow = (ImageButton) item.findViewById(R.id.playerInfoButtonShow);
            textName = (TextView) item.findViewById(R.id.playerInfoName);
            textDOB = (TextView) item.findViewById(R.id.playerInfoDOB);
        }
    }
}
