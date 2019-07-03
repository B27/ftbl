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
import com.example.user.secondfootballapp.user.activity.ProtocolEdit;
import com.example.user.secondfootballapp.user.activity.ProtocolScore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class RVScoreHalfAdapter extends RecyclerView.Adapter<RVScoreHalfAdapter.ViewHolder>{
    ProtocolScore context;
    Logger log = LoggerFactory.getLogger(ProtocolEdit.class);
    private HashMap<Integer, String> halves;
    private TeamTitleClubLogoMatchEvents playerEvents;
    public RVScoreHalfAdapter(Activity context, HashMap<Integer, String> halves,
                              TeamTitleClubLogoMatchEvents playerEvents){
        this.context = (ProtocolScore) context;
        this.halves = halves;
        this.playerEvents = playerEvents;
    }
    @NonNull
    @Override
    public RVScoreHalfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score, parent, false);
        RVScoreHalfAdapter.ViewHolder holder = new RVScoreHalfAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVScoreHalfAdapter.ViewHolder holder, int position) {
        String half = halves.get(position);
        holder.textHalf.setText(half);
        String str;
        str = playerEvents.getNameTeam1();
        holder.textTitle1.setText(str);
        str = playerEvents.getNameTeam2();
        holder.textTitle2.setText(str);
//        try{
//            str = match.getScore();
//            if (str.equals("")){
//                str = "-";
//            }
//        }catch (Exception e){
//            str = "-";
//        }
        int count1=0;
        int count2=0;

        for (PlayerEvent playerEvent : playerEvents.getPlayerEvents()){
            if (playerEvent.getNameTeam().equals(playerEvents.getNameTeam1())
                    && playerEvent.getEvent().getEventType().equals("goal")
                    && playerEvent.getEvent().getTime().equals(half))
            {
                count1++;
            }
            if (playerEvent.getNameTeam().equals(playerEvents.getNameTeam2())
                    && playerEvent.getEvent().getEventType().equals("goal")
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
        View line;
        public ViewHolder(View item) {
            super(item);
            textHalf = item.findViewById(R.id.scoreHalf);
            image1 = item.findViewById(R.id.scoreHalfCommand1Logo);
            image2 = item.findViewById(R.id.scoreHalfCommand2Logo);
            textTitle1 = item.findViewById(R.id.scoreHalfCommand1Title);
            textTitle2 = item.findViewById(R.id.scoreHalfCommand2Title);
            textScore = item.findViewById(R.id.protocolMatchScoreHalf);
        }
    }

}
