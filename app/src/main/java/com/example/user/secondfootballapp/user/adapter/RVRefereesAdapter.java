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

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.user.activity.RefereeFragment;
import com.example.user.secondfootballapp.user.activity.RefereesMatches;

public class RVRefereesAdapter extends RecyclerView.Adapter<RVRefereesAdapter.ViewHolder>{
    RefereeFragment context;
    PersonalActivity activity;
    public RVRefereesAdapter(Activity activity, RefereeFragment context){
        this.context =  context;
        this.activity = (PersonalActivity) activity;
    }
    @NonNull
    @Override
    public RVRefereesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.referee, parent, false);
        RVRefereesAdapter.ViewHolder holder = new RVRefereesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVRefereesAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);
        holder.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, RefereesMatches.class);
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
        ImageView image;
        TextView textName;
        ImageButton buttonShow;
        View line;
        public ViewHolder(View item) {
            super(item);
            image = (ImageView) item.findViewById(R.id.refereePhoto);
            textName = (TextView) item.findViewById(R.id.refereeName);
            buttonShow = (ImageButton)  item.findViewById(R.id.refereeButtonShow);
            line = (View) item.findViewById(R.id.refereeLine);
        }
    }
}
