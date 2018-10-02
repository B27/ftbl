package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.StructureCommand2;

public class RVProtocolSecondCommandAdapter extends RecyclerView.Adapter<RVProtocolSecondCommandAdapter.ViewHolder>{
    StructureCommand2 context;
    public RVProtocolSecondCommandAdapter(Activity context){
        this.context = (StructureCommand2) context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_second_command, parent, false);
        RVProtocolSecondCommandAdapter.ViewHolder holder = new RVProtocolSecondCommandAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);
        if (position==4){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNum;
        TextView textName;
        ImageView image;
        View line;
        public ViewHolder(View item) {
            super(item);
            textNum = (TextView) item.findViewById(R.id.playerCommandSecondNum);
            textName = (TextView) item.findViewById(R.id.playerCommandSecondName);
            image = (ImageView) item.findViewById(R.id.playerCommandSecondLogo);
            line = (View) item.findViewById(R.id.playerCommandSecondLine);
        }
    }
}
