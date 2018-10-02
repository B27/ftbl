package com.example.user.secondfootballapp.user.adapter;

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
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.UserEditClub;

public class RVEditClubMembersAdapter extends RecyclerView.Adapter<RVEditClubMembersAdapter.ViewHolder>{
    UserEditClub context;
    public RVEditClubMembersAdapter(UserEditClub context){
        this.context =  context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_club_member, parent, false);
        RVEditClubMembersAdapter.ViewHolder holder = new RVEditClubMembersAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        int count = position + 1;
        holder.textNum.setText(String.valueOf(count));
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //post
                int newPosition = holder.getAdapterPosition();
                notifyItemRemoved(newPosition);
            }
        });
        if (position==9) {
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNum;
        ImageView image;
        TextView textName;
        ImageButton buttonDelete;
        View line;
        public ViewHolder(View item) {
            super(item);
            textNum = (TextView) item.findViewById(R.id.userEditClubPlayerTextNum);
            image = (ImageView) item.findViewById(R.id.userEditClubPlayerLogo);
            textName = (TextView) item.findViewById(R.id.userEditClubPlayerName);
            buttonDelete = (ImageButton) item.findViewById(R.id.userEditClubPlayerDelete);
            line = (View) item.findViewById(R.id.userEditClubPlayerLine);
        }
    }
}
