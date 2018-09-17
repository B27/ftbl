package com.example.user.secondfootballapp.tournament.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.Tournament;
import com.example.user.secondfootballapp.tournament.activity.TournamentPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RecyclerViewTournamentAdapter extends RecyclerView.Adapter<RecyclerViewTournamentAdapter.ViewHolder>{
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    TournamentPage context;
    //    Activity activity;
    PersonalActivity activity;
    //    public RecyclerViewTournamentAdapter(Context context){
    public RecyclerViewTournamentAdapter(Activity activity, TournamentPage context){
//        this.activity = activity;
        this.activity = (PersonalActivity) activity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tournament, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        boolean status = true;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.info("INFO: hello from RecyclerViewTournamentAdapter");
                Bundle bundle = new Bundle();
                bundle.putString("TOURNAMENT", holder.textTitle.toString());
                Tournament tornament = new Tournament();
                tornament.setArguments(bundle);
//                fragmentManager = PersonalActivity.getSupportFragmentManager();
//                fragmentManager.beginTransaction().add(R.id.pageContainer, new Tornament(), "1").commit();

                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.pageContainer, tornament, "tornamentTAG")
                        .addToBackStack(null)
//                        .show(tornament)
                        .commit();

            }
        });
        if (status){
            holder.textStatusFinish.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_fin)
                    .apply(new RequestOptions()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH))
//                    .load(R.drawable.ic_finish)
//                    .apply(new RequestOptions()
//                    .fitCenter()
//                    .override(Target.SIZE_ORIGINAL)
//                    .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(holder.imageView);
        }
        else {
            holder.textStatusFinish.setVisibility(View.INVISIBLE);
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_con)
                    .apply(new RequestOptions()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH))
                    .into(holder.imageView);}

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.info("INFO: hello from RecyclerViewTournamentAdapter");
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(context.getId(), new Tournament()).commit();

//                            Intent intent = new Intent(activity, Tornament.class);
//                            String title = "Some title";
//                            Bundle bundle = new Bundle();
//                            bundle.putString("TOURNAMENT", title);
//                            intent.putExtra("TOURNAMENT", bundle);
//                            context.startActivity(intent);
            }
        });
        if (position==4){
            holder.view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        TextView textDate;
        TextView textCommandNum;
        TextView textStatusFinish;
        ImageView imageView;
        View view;
        ImageButton imageButton;
        public ViewHolder(View itemView) {
            super(itemView);
            view = (View) itemView.findViewById(R.id.tournamentLine);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    // item clicked
//                }
//            });
            textCommandNum = (TextView) itemView.findViewById(R.id.tournamentCommandNum);
            textDate = (TextView) itemView.findViewById(R.id.tournamentDate);
            textTitle = (TextView) itemView.findViewById(R.id.tournamentTitle);
            textStatusFinish = (TextView) itemView.findViewById(R.id.tournamentStatusFinish);
            imageView = (ImageView) itemView.findViewById(R.id.tournamentStatusImg);
            imageButton = (ImageButton) itemView.findViewById(R.id.tournamentButtonShow);
        }
    }
}

