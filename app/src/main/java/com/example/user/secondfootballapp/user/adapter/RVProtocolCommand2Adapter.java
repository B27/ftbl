package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.activity.ProtocolCommand1;
import com.example.user.secondfootballapp.user.activity.ProtocolCommand2;

public class RVProtocolCommand2Adapter extends RecyclerView.Adapter<RVProtocolCommand2Adapter.ViewHolder>{
    ProtocolCommand2 context;
    public RVProtocolCommand2Adapter(Activity context){
        this.context = (ProtocolCommand2) context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_command2, parent, false);
        RVProtocolCommand2Adapter.ViewHolder holder = new RVProtocolCommand2Adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
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
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);
        if (position==7){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText editNum;
        ImageView image;
        TextView textName;
        SwitchCompat switchCompat;
        View line;
        public ViewHolder(View item) {
            super(item);
            editNum = (EditText) item.findViewById(R.id.playerCommand2Num);
            image = (ImageView) item.findViewById(R.id.playerCommand2Logo);
            textName = (TextView) item.findViewById(R.id.playerCommand2Name);
            switchCompat = (SwitchCompat) item.findViewById(R.id.playerCommand2Switch);
            line = (View) item.findViewById(R.id.playerCommand2Line);
        }
    }
}
