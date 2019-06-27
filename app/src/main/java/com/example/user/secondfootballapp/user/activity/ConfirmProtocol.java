package com.example.user.secondfootballapp.user.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SaveSharedPreference;
import com.example.user.secondfootballapp.SetImage;
import com.example.user.secondfootballapp.model.ActiveMatch;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.Event;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.Player;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.model.TeamTitleClubLogoMatchEvents;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ConfirmProtocol extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(ConfirmProtocol.class);
    List<PlayerEvent> playerEvents;
    String clubOne;
    String clubTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton imageClose;
        ImageButton imageSave;
        ImageButton buttonCommand1;
        ImageButton buttonCommand2;
        ImageButton buttonReferees;
        ImageButton buttonEvents;
        FloatingActionButton fab;
        TextView textTitle1;
        TextView textTitle2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_protocol);
        fab = findViewById(R.id.confirmProtocolButtonShowScore);
        buttonCommand1 = findViewById(R.id.confirmProtocolCommand1ButtonShow);
        buttonCommand2 = findViewById(R.id.confirmProtocolCommand2ButtonShow);
        buttonReferees = findViewById(R.id.confirmProtocolRefereesButtonShow);
        buttonEvents = findViewById(R.id.confirmProtocolEventsButtonShow);
        imageClose = findViewById(R.id.confirmProtocolClose);
        imageSave = findViewById(R.id.confirmProtocolSave);
        textTitle1 = findViewById(R.id.confirmProtocolCommand1Title);
        textTitle2 = findViewById(R.id.confirmProtocolCommand2Title);
        imageClose.setOnClickListener(v -> finish());
        try {
            String str;
            ActiveMatch match = (ActiveMatch) getIntent().getExtras().getSerializable("CONFIRMPROTOCOL");
            HashMap<String, Team> teams = getTeams(match);
            str = teams.get("TeamOne").getName();
            textTitle1.setText(str);
            str = teams.get("TeamTwo").getName();
            textTitle2.setText(str);
            TeamTitleClubLogoMatchEvents entry = getPlayerEvent(match.getEvents(), match, teams.get("TeamOne"), teams.get("TeamTwo"));
            try {
                playerEvents = new ArrayList<>(entry.getPlayerEvents());
            } catch (NullPointerException e) {
                playerEvents = new ArrayList<>();
            }
            imageSave.setOnClickListener(v -> {
                confirmProtocol(match.getId());
            });
            fab.setOnClickListener(v -> {
                List<Event> list = new ArrayList<>();
                for (PlayerEvent playerEvent : playerEvents) {
                    list.add(playerEvent.getEvent());
                }
                TeamTitleClubLogoMatchEvents playerEv = getPlayerEvent(list, match, teams.get("TeamOne"), teams.get("TeamTwo"));
                Intent intent = new Intent(ConfirmProtocol.this, ProtocolScore.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PROTOCOLMATCH", match);
                bundle.putSerializable("PROTOCOLEVENTS", playerEv);
                intent.putExtras(bundle);
                startActivity(intent);
            });
            buttonCommand1.setOnClickListener(v -> {
                Intent intent = new Intent(ConfirmProtocol.this, StructureCommand1.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CONFIRMPROTOCOLMATCH", match);
                bundle.putSerializable("CONFIRMPROTOCOLCOMMAND", teams.get("TeamOne"));
                intent.putExtras(bundle);
                startActivity(intent);
            });
            buttonCommand2.setOnClickListener(v -> {
                Intent intent = new Intent(ConfirmProtocol.this, StructureCommand1.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CONFIRMPROTOCOLMATCH", match);
                bundle.putSerializable("CONFIRMPROTOCOLCOMMAND", teams.get("TeamTwo"));
                intent.putExtras(bundle);
                startActivity(intent);
            });
            buttonReferees.setOnClickListener(v -> {
                Intent intent = new Intent(ConfirmProtocol.this, MatchResponsiblePersons.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CONFIRMPROTOCOLREFEREES", (Serializable) match.getReferees());
                intent.putExtras(bundle);
                startActivity(intent);
            });

            buttonEvents.setOnClickListener(v -> {
                Intent intent = new Intent(ConfirmProtocol.this, MatchEvents.class);
                startActivity(intent);
            });
        } catch (NullPointerException e) {
        }
    }

    @SuppressLint("CheckResult")
    private void confirmProtocol(String id) {
        CheckError checkError = new CheckError();
        Controller.getApi().confirmProtocol(SaveSharedPreference.getObject().getToken(), id)
                .map(responseBody -> {
                    if (!responseBody.isSuccessful()) {
                        String srt = responseBody.errorBody().string();
                        log.error(srt);
                        showToast(srt);
                    }
                    if (responseBody.errorBody() != null) {
                        checkError.checkHttpError(this, responseBody.errorBody().string());
                    }
                    return responseBody.body();
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                            String str = "Изменения сохранены";
                            showToastResult(str);
                            finish();
                        },
                        error -> checkError.checkError(this, error));

    }

    private HashMap<String, Team> getTeams(ActiveMatch match) {
        HashMap<String, Team> teams = new HashMap<>();
        ImageView image1 = findViewById(R.id.confirmProtocolCommand1Logo);
        ImageView image2 = findViewById(R.id.confirmProtocolCommand2Logo);
        SetImage setImage = new SetImage();
                            teams.put("TeamOne", match.getTeamOne());
                            teams.put("TeamTwo", match.getTeamTwo());
                        for (Club club : PersonalActivity.allClubs) {
                            if (match.getTeamOne().getClub().equals(club.getId())) {
                                try {
                                    clubOne = club.getLogo();
                                } catch (NullPointerException e) {
                                    clubOne = "";
                                }
                                setImage.setImage(image1.getContext(), image1, club.getLogo());
                            }
                            if (match.getTeamTwo().getClub().equals(club.getId())) {
                                try {
                                    clubTwo = club.getLogo();
                                } catch (NullPointerException e) {
                                    clubTwo = "";
                                }
                                setImage.setImage(image2.getContext(), image2, club.getLogo());
                            }
                        }
        return teams;
    }

    private TeamTitleClubLogoMatchEvents getPlayerEvent(List<Event> events, ActiveMatch match, Team team1, Team team2) {

        log.error(match.getId());
        List<PlayerEvent> playerEvents1 = new ArrayList<>();
        String teamOne = team1.getName();
        String teamTwo = team2.getName();
        String clubEvent = "";
        String teamName = "";
        for (Event event : events) {
            Person person = null;
            for (Person person1 : PersonalActivity.AllPeople) {
                if (person1.getId().equals(event.getPlayer())) {
                    person = person1;
                }
            }
            for (Player player : team1.getPlayers()) {
                if (player.getPlayerId().equals(person.getId())) {
                    clubEvent = clubOne;
                    teamName = team1.getName();
                }
            }
            for (Player player : team2.getPlayers()) {
                if (player.getPlayerId().equals(person.getId())) {
                    clubEvent = clubTwo;
                    teamName = team2.getName();
                }
            }

            PlayerEvent playerEvent = new PlayerEvent();
            playerEvent.setPerson(person);
            try {
                playerEvent.setClubLogo(clubEvent);
            } catch (Exception e) {
                playerEvent.setClubLogo(null);
            }
            playerEvent.setEvent(event);
            playerEvent.setNameTeam(teamName);
            playerEvents1.add(playerEvent);
        }
        if (match.getEvents().isEmpty()) {
            playerEvents1 = null;
        }

        TeamTitleClubLogoMatchEvents entry = new TeamTitleClubLogoMatchEvents();
        entry.setPlayerEvents(playerEvents1);
        entry.setClubLogo1(clubOne);
        entry.setClubLogo2(clubTwo);
        entry.setNameTeam1(teamOne);
        entry.setNameTeam2(teamTwo);
//        return playerEvents;
        return entry;
    }


    private void showToast(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            String str1 = jsonObject.getString("message");
            log.error(str);
            this.runOnUiThread(() -> Toast.makeText(ConfirmProtocol.this, str1, Toast.LENGTH_SHORT).show());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void showToastResult(String str) {
            this.runOnUiThread(() -> Toast.makeText(ConfirmProtocol.this, str, Toast.LENGTH_SHORT).show());
    }
}

