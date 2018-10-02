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
import com.example.user.secondfootballapp.user.activity.StructureCommand1;

public class RVProtocolFirstCommandAdapter extends RecyclerView.Adapter<RVProtocolFirstCommandAdapter.ViewHolder>{
    StructureCommand1 context;
    public RVProtocolFirstCommandAdapter(Activity context){
        this.context = (StructureCommand1) context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_first_command, parent, false);
        RVProtocolFirstCommandAdapter.ViewHolder holder = new RVProtocolFirstCommandAdapter.ViewHolder(view);
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
            textNum = (TextView) item.findViewById(R.id.playerCommandFirstNum);
            textName = (TextView) item.findViewById(R.id.playerCommandFirstName);
            image = (ImageView) item.findViewById(R.id.playerCommandFirstLogo);
            line = (View) item.findViewById(R.id.playerCommandFirstLine);
        }
    }
}
