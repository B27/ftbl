package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SetImage;
import com.example.user.secondfootballapp.model.ActiveMatch;
import com.example.user.secondfootballapp.model.Event;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.model.TeamTitleClubLogoMatchEvents;
import com.example.user.secondfootballapp.user.adapter.RVProtocolEditAdapter;
import com.example.user.secondfootballapp.user.adapter.RVScoreFoulsAdapter;
import com.example.user.secondfootballapp.user.adapter.RVScoreHalfAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.example.user.secondfootballapp.Controller.BASE_URL;

public class ProtocolScore extends AppCompatActivity{
    Logger log = LoggerFactory.getLogger(ProtocolMatchScore.class);
    HashMap<Integer, String> halves;
    SetImage setImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerViewFouls;
        RecyclerView recyclerViewScore;
        ImageButton buttonBack;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocol_match_score);
        halves = new HashMap<>();
        buttonBack = findViewById(R.id.protocolScoreBack);
        buttonBack.setOnClickListener(v -> finish());
        recyclerViewFouls = findViewById(R.id.recyclerViewScoreFouls);

        recyclerViewFouls.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewScore = findViewById(R.id.recyclerViewScore);
        recyclerViewScore.setLayoutManager(new LinearLayoutManager(this));
        try{

            setImage = new SetImage();
            Intent arguments = getIntent();
            TeamTitleClubLogoMatchEvents playerEvents = (TeamTitleClubLogoMatchEvents) Objects.requireNonNull(arguments.getExtras()).getSerializable("PROTOCOLEVENTS");
//            ActiveMatch match = (ActiveMatch) arguments.getExtras().getSerializable("PROTOCOLMATCH");
            Match match = (Match) arguments.getExtras().getSerializable("PROTOCOLMATCH");
            int i = 0;
            if (playerEvents.getPlayerEvents()!=null) {
                for (PlayerEvent playerEvent : playerEvents.getPlayerEvents()) {
                    if (!halves.containsValue(playerEvent.getEvent().getTime())) {
                        halves.put(i, playerEvent.getEvent().getTime());
                        i++;
                    }
                }
            }

            RVScoreHalfAdapter adapter1 = new RVScoreHalfAdapter(this, halves, playerEvents);
            recyclerViewScore.setAdapter(adapter1);

            Boolean containsFouls = false;
            try {
                for (PlayerEvent playerEv1 : playerEvents.getPlayerEvents()){
                    if (playerEv1.getEvent().getEventType().equals("foul")){
                        containsFouls = true;
                        break;
                    }
                }
            }catch (NullPointerException e){
                containsFouls = false;
            }


            if (containsFouls){
                RVScoreFoulsAdapter adapter = new RVScoreFoulsAdapter(this, halves, playerEvents);
                recyclerViewFouls.setAdapter(adapter);
            }else {
                LinearLayout textFouls = findViewById(R.id.protocolScoreFouls);
                textFouls.setVisibility(View.GONE);
            }


            setScore(playerEvents, match);

            if (playerEvents.getPlayerEvents()==null){
                TextView textScore = findViewById(R.id.protocolScore);
                textScore.setVisibility(View.GONE);
                View line = findViewById(R.id.protocolScoreLine);
                line.setVisibility(View.GONE);
                LinearLayout textFouls = findViewById(R.id.protocolScoreFouls);
                textFouls.setVisibility(View.GONE);
            }
            if (match.getPenalty() == null ){
                LinearLayout layout = findViewById(R.id.penalty);
                layout.setVisibility(View.GONE);
            }else {
                setPenalty(playerEvents, match);
            }
            if (match.getAutoGoal() == null ){
//            if (match.getAutoGoal() == null && !match.getAutoGoal().equals("")){
                LinearLayout layout = findViewById(R.id.protocolScoreOwnGoals);
                layout.setVisibility(View.GONE);
            }else {
                setAutoGoal(playerEvents, match);
            }

        }catch (Exception e){
            log.error("ERROR: ", e);
        }
    }

        private  void setScore(TeamTitleClubLogoMatchEvents playerEvents, Match match){
//    private  void setScore(TeamTitleClubLogoMatchEvents playerEvents, ActiveMatch match){
        ImageView image1 = findViewById(R.id.scoreCommand1Logo);
        ImageView image2 = findViewById(R.id.scoreCommand2Logo);
        TextView text1 = findViewById(R.id.scoreCommand1Title);
        TextView text2 = findViewById(R.id.scoreCommand2Title);
        TextView score = findViewById(R.id.protocolEditMatchScore);
        String str;
        str = playerEvents.getNameTeam1();
        text1.setText(str);
        str = playerEvents.getNameTeam2();
        text2.setText(str);
        try {
            str = match.getScore();
            if (str.equals("")) {
                str = "-";
            }
        } catch (Exception e) {
            str = "-";
        }
        score.setText(str);
        setImage.setImage(image1.getContext(), image1, playerEvents.getClubLogo1());
        setImage.setImage(image2.getContext(),image2, playerEvents.getClubLogo2());


    }
//    private  void setPenalty(TeamTitleClubLogoMatchEvents playerEvents, ActiveMatch match){
    private  void setPenalty(TeamTitleClubLogoMatchEvents playerEvents, Match match){
        ImageView image1 = findViewById(R.id.penaltyCommand1Logo);
        ImageView image2 = findViewById(R.id.penaltyCommand2Logo);
        TextView text1 = findViewById(R.id.penaltyCommand1Title);
        TextView text2 = findViewById(R.id.penaltyCommand2Title);
        TextView penalty = findViewById(R.id.protocolEditMatchPenalty);
        penalty.setText(match.getPenalty());
        String str;
        str = playerEvents.getNameTeam1();
        text1.setText(str);
        str = playerEvents.getNameTeam2();
        text2.setText(str);
        setImage.setImage(image1.getContext(), image1, playerEvents.getClubLogo1());
        setImage.setImage(image2.getContext(), image2, playerEvents.getClubLogo2());
    }
//    private  void setAutoGoal(TeamTitleClubLogoMatchEvents playerEvents, ActiveMatch match){
    private  void setAutoGoal(TeamTitleClubLogoMatchEvents playerEvents, Match match){
        ImageView image1 = findViewById(R.id.ownGoalsFoulsCommand1Logo);
        ImageView image2 = findViewById(R.id.ownGoalsFoulsCommand2Logo);
        TextView text1 = findViewById(R.id.ownGoalsFoulsCommand1Title);
        TextView text2 = findViewById(R.id.ownGoalsFoulsCommand2Title);
        TextView autoGoal = findViewById(R.id.protocolEditMatchAuthoGoul);
        autoGoal.setText(match.getAutoGoal());
        String str;
        str = playerEvents.getNameTeam1();
        text1.setText(str);
        str = playerEvents.getNameTeam2();
        text2.setText(str);
        setImage.setImage(image1.getContext(), image1, playerEvents.getClubLogo1());
        setImage.setImage(image2.getContext(), image2, playerEvents.getClubLogo2());
    }

}
