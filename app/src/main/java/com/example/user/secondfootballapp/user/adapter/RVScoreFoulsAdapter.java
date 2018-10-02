package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
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
import com.example.user.secondfootballapp.user.activity.ProtocolMatchScore;

public class RVScoreFoulsAdapter extends RecyclerView.Adapter<RVScoreFoulsAdapter.ViewHolder>{
    ProtocolMatchScore context;
    public RVScoreFoulsAdapter(Activity context){
        this.context = (ProtocolMatchScore) context;
    }
    @NonNull
    @Override
    public RVScoreFoulsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fouls, parent, false);
        RVScoreFoulsAdapter.ViewHolder holder = new RVScoreFoulsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVScoreFoulsAdapter.ViewHolder holder, int position) {
        String half = ProtocolMatchScore.halves.get(0);
        holder.textHalf.setText(half);
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_fin)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image1);
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_fin)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image2);
        if (position == 2){
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textHalf;
        TextView textTitle1;
        TextView textTitle2;
        TextView textFoulsNum1;
        TextView textFoulsNum2;
        ImageView image1;
        ImageView image2;
        View line;
        public ViewHolder(View item) {
            super(item);
            textHalf = (TextView) item.findViewById(R.id.foulsHalf);
            image1 = (ImageView) item.findViewById(R.id.foulsCommand1Logo);
            image2 = (ImageView) item.findViewById(R.id.foulsCommand2Logo);
            textTitle1 = (TextView) item.findViewById(R.id.foulsCommand1Title);
            textTitle2 = (TextView) item.findViewById(R.id.foulsCommand2Title);
            textFoulsNum1 = (TextView) item.findViewById(R.id.foulsCommand1FoulsNum);
            textFoulsNum2 = (TextView) item.findViewById(R.id.foulsCommand2FoulsNum);
            line = (View) item.findViewById(R.id.foulsLine);
        }
    }
}
