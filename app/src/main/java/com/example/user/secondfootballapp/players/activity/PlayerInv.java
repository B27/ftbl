package com.example.user.secondfootballapp.players.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SaveSharedPreference;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.People;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.PersonTeams;
import com.example.user.secondfootballapp.model.ServerResponse;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.players.adapter.RVPlayerInvAdapter;
import com.example.user.secondfootballapp.players.adapter.SpinnerCommandAdapter;
import com.example.user.secondfootballapp.user.activity.AuthoUser;
import com.example.user.secondfootballapp.user.activity.NewCommand;
import com.example.user.secondfootballapp.user.activity.UserCommands;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerInv extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(PlayerInv.class);
    Team itemTeam;
    List<PersonTeams> teams;
    public static String personId;
    Person person;
    public static RVPlayerInvAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        teams = new ArrayList<>();
        ImageButton imageClose;
        ImageButton imageSave;
        RecyclerView recyclerView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_inv);
        imageClose = findViewById(R.id.playerInvClose);
        recyclerView = findViewById(R.id.recyclerViewPlayerInv);
//        imageSave = (ImageButton) findViewById(R.id.playerInvSave);
//        spinnerCommands = (Spinner) findViewById(R.id.playerInvSpinner);
        try{
            Intent intent = getIntent();
            person = (Person) intent.getExtras().getSerializable("PLAYERINV");
            personId = person.getId();
//            Person creator = AuthoUser.user.getUser();
//            for (PersonTeams personTeams : AuthoUser.personOngoingLeagues){
            for (PersonTeams personTeams : AuthoUser.personOwnCommand){
                League league = null;
                for (League league1 : PersonalActivity.tournaments){
                    if (league1.getId().equals(personTeams.getLeague())){
                        league = league1;
                        break;
                    }
                }
//                League league = personTeams.getLeague();
                for (Team team: league.getTeams()){
//                    if (team.getCreator().equals(creator.getId()) && team.getStatus().equals("Pending")
                    if (team.getStatus().equals("Pending")
                            && team.getCreator().equals(SaveSharedPreference.getObject().getUser().getId())){
                        PersonTeams personTeams1 = new PersonTeams();
                        personTeams1.setTeam(team.getId());
                        personTeams1.setLeague(league.getId());
//                        personTeams1.setId("000");
                        teams.add(personTeams1);
                    }
                }
            }
            adapter = new RVPlayerInvAdapter(this, teams);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            SpinnerCommandAdapter adapter = new SpinnerCommandAdapter(this,R.layout.spinner_item, teams);
//            spinnerCommands.setAdapter(adapter);
//            spinnerCommands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                    itemTeam = (Team) parent.getItemAtPosition(pos);
//                }
//                public void onNothingSelected(AdapterView<?> parent) {
//                }
//            });

        }catch (Exception e){ log.error("ERROR: ", e);}

        imageClose.setOnClickListener(v -> finish());
//        imageSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try{
//
//                        addPlayer(person.getId());
//
//
//                }catch (Exception e){ log.error("ERROR: ", e);}
//
//            }
//        });


    }


}
