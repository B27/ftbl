package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SaveSharedPreference;
import com.example.user.secondfootballapp.model.AddTeam;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.PersonTeams;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.model.User;
import com.example.user.secondfootballapp.user.adapter.SpinnerClubAdapter;
import com.example.user.secondfootballapp.user.adapter.SpinnerTournamentAdapter;

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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCommand extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(NewCommand.class);
    List<Club> allClubs = new ArrayList<>();
    List<League> allTournaments = new ArrayList<>();
    Spinner spinnerTournament;
    Club itemClub;
    League itemTournament;
    Spinner spinnerClubs;
    Team team;
    EditText textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageButton imageClose;
        ImageButton imageSave;
        setContentView(R.layout.user_new_command);


        allClubs.addAll(PersonalActivity.allClubs);
        allTournaments.addAll(PersonalActivity.tournaments);
        log.error(String.valueOf(allClubs.size()));
        imageClose = findViewById(R.id.newCommandClose);
        imageSave = findViewById(R.id.newCommandSave);
        textTitle = findViewById(R.id.newCommandTitle);
        spinnerTournament = findViewById(R.id.newCommandTournamentSpinner);
//        spinnerLeague = (Spinner) findViewById(R.id.newCommandLeagueSpinner);
        spinnerClubs = findViewById(R.id.newCommandClubSpinner);

        spinnerTournament.setPopupBackgroundResource(R.color.colorWhite);


        Drawable spinnerDrawable = spinnerClubs.getBackground().getConstantState().newDrawable();


        spinnerDrawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);


        spinnerClubs.setBackground(spinnerDrawable);


        spinnerDrawable = spinnerTournament.getBackground().getConstantState().newDrawable();


        spinnerDrawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);


        spinnerTournament.setBackground(spinnerDrawable);
        SpinnerTournamentAdapter adapterTournament = new SpinnerTournamentAdapter(this, R.layout.spinner_item, allTournaments);
        spinnerTournament.setAdapter(adapterTournament);
//        spinnerTournament.setPopupBackgroundResource(R.color.colorWhite);
        spinnerTournament.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                itemTournament = (League) parent.getItemAtPosition(pos);
            }


            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//        adapterTournament.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        adapterTournament.setDropDownViewResource(R.layout.spinner_item);
        SpinnerClubAdapter adapterClub = new SpinnerClubAdapter(this, R.layout.spinner_item, allClubs);




        adapterClub.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerClubs.setAdapter(adapterClub);
        spinnerClubs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                itemClub = (Club) parent.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        textTitle.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        textTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    textTitle.getBackground().clearColorFilter();
                } else {
                    textTitle.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                }
            }
        });
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageSave.setOnClickListener(v -> CreateNewCommand());
    }

    public void CreateNewCommand() {
        League league = new League();
        String name = String.valueOf(textTitle.getText());
        Map<String, RequestBody> map = new HashMap<>();
        String token = SaveSharedPreference.getObject().getToken();
        String tournament = itemTournament.getId();
//        for (PersonTeams league1: AuthoUser.personOngoingLeagues){
        for (League league1 : PersonalActivity.tournaments) {
//            League tournament1 = league1.getLeague();
            if (league1.getId().equals(tournament)) {
                league = league1;
            }
        }
        String club = itemClub.getId();
        String creator = SaveSharedPreference.getObject().getUser().getId();
        RequestBody request = RequestBody.create(MediaType.parse("text/plain"), tournament);
        map.put("_id", request);
        request = RequestBody.create(MediaType.parse("text/plain"), club);
        map.put("club", request);
        request = RequestBody.create(MediaType.parse("text/plain"), creator);
        map.put("creator", request);
        request = RequestBody.create(MediaType.parse("text/plain"), name);
        map.put("name", request);
        Call<AddTeam> call = Controller.getApi().addTeam(token, map);
        log.info("INFO: load and parse json-file");
        final League finalLeague = league;
        call.enqueue(new Callback<AddTeam>() {
            @Override
            public void onResponse(Call<AddTeam> call, Response<AddTeam> response) {
                log.info("INFO: check response");
                if (response.isSuccessful()) {
                    log.info("INFO: response isSuccessful");
                    if (response.body() == null) {
                        log.error("ERROR: body is null");
                    } else {
                        team = response.body().getTeam();
                        log.info(team.getName());
                        PersonTeams personTeams = new PersonTeams();
                        personTeams.setTeam(team.getId());
                        finalLeague.getTeams().add(team);
                        personTeams.setLeague(finalLeague.getId());
//                        personTeams.setId("000");
                        AuthoUser.personOwnCommand.add(personTeams);
                        AuthoUser.personOngoingLeagues.add(personTeams);


                        User user = SaveSharedPreference.getObject();
                        Person person = user.getUser();
                        List<PersonTeams> list = new ArrayList<>(person.getParticipation());
                        list.add(personTeams);
                        person.setParticipation(list);
                        SaveSharedPreference.editObject(user);


//                        UserCommands.adapterCommand.notifyDataSetChanged();
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish(); //post
                    }
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String str = "Ошибка! ";
                        str += jsonObject.getString("message");
                        Toast.makeText(NewCommand.this, str, Toast.LENGTH_LONG).show();
                        finish();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddTeam> call, Throwable t) {
                log.error("ERROR: onResponse", t);
                Toast.makeText(NewCommand.this, "Ошибка сервера.", Toast.LENGTH_LONG).show();
//                finish();
            }
        });
    }
}
