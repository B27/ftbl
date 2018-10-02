package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.UserCommandInfo;

public class RVUserCommandPlayerAdapter extends RecyclerView.Adapter<RVUserCommandPlayerAdapter.ViewHolder> {

    UserCommandInfo context;
    public RVUserCommandPlayerAdapter (Activity context){
        this.context = (UserCommandInfo) context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.command_player, parent, false);
        RVUserCommandPlayerAdapter.ViewHolder holder = new RVUserCommandPlayerAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        int count = position + 1;
//        String str = Integer.toString(count);
        String str = String.valueOf(count);
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
        holder.editNum.getBackground().setColorFilter(context.getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        holder.editNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    holder.editNum.getBackground().clearColorFilter();
                }
                else {
                    holder.editNum.getBackground().setColorFilter(context.getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                }
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
        EditText editNum;
        ImageButton buttonDelete;
        View line;
        public ViewHolder(View item) {
            super(item);
            textNum = (TextView) item.findViewById(R.id.userCommandPlayerTextNum);
            image = (ImageView) item.findViewById(R.id.userCommandPlayerLogo);
            textName = (TextView) item.findViewById(R.id.userCommandPlayerName);
            editNum = (EditText) item.findViewById(R.id.userCommandPlayerNum);
            buttonDelete = (ImageButton) item.findViewById(R.id.userCommandPlayerDelete);
            line = (View) item.findViewById(R.id.userCommandPlayerLine);
        }
    }
}
