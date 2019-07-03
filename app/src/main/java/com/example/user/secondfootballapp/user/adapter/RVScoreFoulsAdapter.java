package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SetImage;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.model.TeamTitleClubLogoMatchEvents;
import com.example.user.secondfootballapp.user.activity.ProtocolScore;

import java.util.HashMap;

public class RVScoreFoulsAdapter extends RecyclerView.Adapter<RVScoreFoulsAdapter.ViewHolder>{
    ProtocolScore context;
    private HashMap<Integer, String> halves;
    private TeamTitleClubLogoMatchEvents playerEvents;
    public RVScoreFoulsAdapter(Activity context, HashMap<Integer, String> halves,
                               TeamTitleClubLogoMatchEvents playerEvents){
        this.context = (ProtocolScore) context;
        this.halves = halves;
        this.playerEvents = playerEvents;
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
        String half = halves.get(position);
        holder.textHalf.setText(half);
        String str;
        str = playerEvents.getNameTeam1();
        holder.textTitle1.setText(str);
        str = playerEvents.getNameTeam2();
        holder.textTitle2.setText(str);

        int count1=0;
        int count2=0;

        for (PlayerEvent playerEvent : playerEvents.getPlayerEvents()){
            if (playerEvent.getNameTeam().equals(playerEvents.getNameTeam1())
                    && playerEvent.getEvent().getEventType().equals("foul")
                    && playerEvent.getEvent().getTime().equals(half))
            {
                count1++;
            }
            if (playerEvent.getNameTeam().equals(playerEvents.getNameTeam2())
                    && playerEvent.getEvent().getEventType().equals("foul")
                    && playerEvent.getEvent().getTime().equals(half))
            {
                count2++;
            }
        }

        str = count1 + ":" + count2;

        holder.textScore.setText(str);
        SetImage setImage = new SetImage();
        setImage.setImage(context, holder.image1, playerEvents.getClubLogo1());
        setImage.setImage(context, holder.image2, playerEvents.getClubLogo2());
    }

    @Override
    public int getItemCount() {
        return halves.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textHalf;
        TextView textTitle1;
        TextView textTitle2;
        TextView textScore;
        ImageView image1;
        ImageView image2;
        public ViewHolder(View item) {
            super(item);
            textHalf = item.findViewById(R.id.foulsHalf);
            image1 = item.findViewById(R.id.foulsCommand1Logo);
            image2 = item.findViewById(R.id.foulsCommand2Logo);
            textTitle1 = item.findViewById(R.id.foulsCommand1Title);
            textTitle2 = item.findViewById(R.id.foulsCommand2Title);
            textScore = item.findViewById(R.id.foulsCommand1FoulsNum);
        }
    }

}
