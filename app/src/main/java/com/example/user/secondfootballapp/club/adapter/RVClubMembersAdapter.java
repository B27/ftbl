package com.example.user.secondfootballapp.club.adapter;

import android.support.annotation.NonNull;
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
import com.example.user.secondfootballapp.club.activity.Club;

public class RVClubMembersAdapter extends RecyclerView.Adapter<RVClubMembersAdapter.ViewHolder>{
    Club context;
    public RVClubMembersAdapter(Club context){
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_member, parent, false);
        RVClubMembersAdapter.ViewHolder holder = new RVClubMembersAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText("Иванов В.В.");
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textName;
        public ViewHolder(View item) {
            super(item);
            img = (ImageView) item.findViewById(R.id.memberImg);
            textName = (TextView) item.findViewById(R.id.memberName);
        }
    }
}
