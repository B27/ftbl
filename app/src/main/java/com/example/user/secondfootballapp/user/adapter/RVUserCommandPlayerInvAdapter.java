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
import com.example.user.secondfootballapp.user.activity.UserCommandInfo;

public class RVUserCommandPlayerInvAdapter extends RecyclerView.Adapter<RVUserCommandPlayerInvAdapter.ViewHolder>{
    UserCommandInfo context;
    public RVUserCommandPlayerInvAdapter (UserCommandInfo context){
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.command_player_inv
                , parent, false);
        RVUserCommandPlayerInvAdapter.ViewHolder holder = new RVUserCommandPlayerInvAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        int count = position + 1;
        String str = Integer.toString(count);
        holder.textNum.setText(str);
        holder.textName.setText("Иванов В.В.");
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);
        if (position==4){
            holder.line.setVisibility(View.INVISIBLE);}
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //post
                int newPosition = holder.getAdapterPosition();
                notifyItemRemoved(newPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNum;
        ImageView image;
        TextView textName;
        ImageButton buttonDelete;
        View line;
        public ViewHolder(View item) {
            super(item);
            textNum = (TextView) item.findViewById(R.id.userCommandPlayerInvTextNum);
            image = (ImageView) item.findViewById(R.id.userCommandPlayerInvLogo);
            textName = (TextView) item.findViewById(R.id.userCommandPlayerInvName);
            buttonDelete = (ImageButton) item.findViewById(R.id.userCommandInvPlayerDelete);
            line = (View) item.findViewById(R.id.userCommandPlayerInvLine);
        }
    }
}
