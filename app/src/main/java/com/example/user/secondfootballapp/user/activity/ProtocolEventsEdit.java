package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.Event;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.Player;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.user.adapter.RVEventsIconAdapter;
import com.example.user.secondfootballapp.user.adapter.RVProtocolEditAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProtocolEventsEdit extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(ProtocolEventsEdit.class);
    List<Integer> items;
    NestedScrollView scroller;
    CoordinatorLayout coordinatorLayout;
    AppBarLayout appBarLayout;
    AppBarLayout.Behavior behavior;
    final int REQUEST_CODE_ADDEVENT = 246;
    RVProtocolEditAdapter adapter;
    List<Event> events;
    List<PlayerEvent> playerEvents;
    Match match;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocol_events_edit);
        items = new ArrayList<>();
        items.add(1);
        FloatingActionButton buttonAddEvent;
        ImageButton imageClose;
        ImageButton imageSave;
        final RecyclerView recyclerView;
        coordinatorLayout = findViewById(R.id.editProtocolCoordinatorLayout);
        appBarLayout = findViewById(R.id.editProtocolAppbar);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        behavior = (AppBarLayout.Behavior) params.getBehavior();
        imageClose = findViewById(R.id.editProtocolEventsClose);
        imageSave = findViewById(R.id.editProtocolEventsSave);
        buttonAddEvent = findViewById(R.id.editProtocolAddEvent);
        scroller = findViewById(R.id.scrollProtocolEdit);
        imageClose.setOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.recyclerViewEditProtocol);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            Intent arguments = getIntent();
            playerEvents = (List<PlayerEvent>) Objects.requireNonNull(arguments.getExtras()).getSerializable("PROTOCOLEVENTS");
            final List<String> countPlayers = Objects.requireNonNull(arguments.getExtras()).getStringArrayList("PROTOCOLTEAMMATCH");
            match = (Match) Objects.requireNonNull(arguments.getExtras()).getSerializable("PROTOCOLMATCH");
            events = new ArrayList<>(match.getEvents());
            if (playerEvents.size() != 0) {
                LinearLayout layout = findViewById(R.id.emptyEvent);
                layout.setVisibility(View.GONE);
            }

            adapter = new RVProtocolEditAdapter(this, playerEvents, position -> {
                if (playerEvents.size() == 1) {
                    LinearLayout layout = findViewById(R.id.emptyEvent);
                    layout.setVisibility(View.VISIBLE);
                }
                playerEvents.remove(position);
                List<PlayerEvent> list = new ArrayList<>(playerEvents);
                adapter.dataChanged(list);
            });
            recyclerView.setAdapter(adapter);
            buttonAddEvent.setOnClickListener(v -> {
                if (countPlayers.size() == 0) {
                    Toast.makeText(ProtocolEventsEdit.this, "Выберите игроков обеих команд!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ProtocolEventsEdit.this, AddEvent.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("PROTOCOLCOUNTPLAYERS", (ArrayList<String>) countPlayers);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_CODE_ADDEVENT);
                }

            });
            imageSave.setOnClickListener(v -> {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("EDITEVENTRESULT", (Serializable) playerEvents);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);finish(); //post
            });
        } catch (Exception e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADDEVENT) {
                Event event = (Event) data.getExtras().getSerializable("ADDEVENT");
                Person person = (Person) data.getExtras().getSerializable("ADDEVENTPERSON");
                PlayerEvent playerEvent = new PlayerEvent();
                Team team1 = null;
                Club club = null;
                for (League league : PersonalActivity.tournaments) {
                    if (team1 == null && league.getId().equals(match.getLeague())) {
                        for (Team team : league.getTeams()) {
                            if (team.getId().equals(match.getTeamTwo())
                                    || team.getId().equals(match.getTeamOne())) {
                                for (Player player : team.getPlayers()) {
                                    if (player.getPlayerId().equals(person.getId())) {
                                        team1 = team;
                                        for (Club club1 : PersonalActivity.allClubs) {
                                            if (club1.getId().equals(team.getClub())) {
                                                club = club1;
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
                playerEvent.setNameTeam(team1.getName());
                try {
                    playerEvent.setClubLogo(club.getLogo());
                } catch (NullPointerException e) {
                    playerEvent.setClubLogo(null);
                }
                playerEvent.setPerson(person);
                playerEvent.setEvent(event);
//                List<PlayerEvent> playerEventList = new ArrayList<>(playerEvents);
                playerEvents.add(playerEvent);
                List<PlayerEvent> playerEventList = new ArrayList<>();
                playerEventList.addAll(playerEvents);
                if (playerEventList.size() == 1) {
//                    finish();
//                    startActivity(getIntent());
                    LinearLayout layout = findViewById(R.id.emptyEvent);
                    layout.setVisibility(View.GONE);
                }

                adapter.dataChanged(playerEventList);

            }
        } else {
            log.error("ERROR: onActivityResult");
        }
    }
}
