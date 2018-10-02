package com.example.user.secondfootballapp.user.adapter;

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
import com.example.user.secondfootballapp.user.activity.RefereesMatches;

public class RVRefereesMatchesAdapter extends RecyclerView.Adapter<RVRefereesMatchesAdapter.ViewHolder> {
    RefereesMatches context;
    public RVRefereesMatchesAdapter(RefereesMatches context){
        this.context =  context;
    }
    @NonNull
    @Override
    public RVRefereesMatchesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.referees_match, parent, false);
        RVRefereesMatchesAdapter.ViewHolder holder = new RVRefereesMatchesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVRefereesMatchesAdapter.ViewHolder holder, int position) {
        String str = "1 судья";
        holder.refereeType.setText(str);
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_cl)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image1);
        Glide.with(context)
                .asBitmap()
                .load(R.drawable.ic_cl)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image2);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView refereeType;
        TextView textTournamentTitle;
        TextView textDate;
        TextView textTime;
        TextView textStadium;
        TextView textComandTitle1;
        TextView textComandTitle2;
        ImageView image1;
        ImageView image2;
        public ViewHolder(View item) {
            super(item);
            textTournamentTitle = (TextView) item.findViewById(R.id.refereesMatchLeagueTitle);
            textDate = (TextView) item.findViewById(R.id.refereesMatchDate);
            textTime = (TextView) item.findViewById(R.id.refereesMatchTime);
            textStadium = (TextView) item.findViewById(R.id.refereesMatchStadium);
            textComandTitle1 = (TextView) item.findViewById(R.id.refereesMatchCommandTitle1);
            textComandTitle2 = (TextView) item.findViewById(R.id.refereesMatchCommandTitle2);
            image1 = (ImageView) item.findViewById(R.id.refereesMatchCommandLogo1);
            image2 = (ImageView) item.findViewById(R.id.refereesMatchCommandLogo2);
            refereeType = (TextView) item.findViewById(R.id.refereesMatchType);
        }
    }
}
