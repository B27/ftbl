package com.example.user.secondfootballapp.user.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.PersonTeams;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.user.adapter.RVInvitationAdapter;
import com.example.user.secondfootballapp.user.adapter.RVOngoingTournamentAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OngoingTournamentFragment extends Fragment{
    Logger log = LoggerFactory.getLogger(OngoingTournamentFragment.class);
    public static RVOngoingTournamentAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        RecyclerView recyclerView;
        LinearLayout linearLayout;
        LinearLayout linear;
        List<PersonTeams> list = new ArrayList<>();
        view = inflater.inflate(R.layout.user_tournaments, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewUserTournament);
        linearLayout = view.findViewById(R.id.emptyTournament);
        linear = view.findViewById(R.id.notEmptyTournament);

        try{
        for (PersonTeams personTeams: AuthoUser.personOngoingLeagues){
            String teamId = personTeams.getTeam();
//            League league = personTeams.getLeague();
            League league = null;
            for (League league1 : PersonalActivity.tournaments){
                if (league1.getId().equals(personTeams.getLeague())){
                    league = league1;
                    break;
                }
            }
            List<Team> teams = league.getTeams();
            for (Team team: teams){
//                if (team.getId().equals(teamId) && team.getStatus().equals("Pending")){
                if (team.getId().equals(teamId) && team.getStatus().equals("Approved")){
                    list.add(personTeams);
                }
            }
        }

        if (list.size()!=0){
            linearLayout.setVisibility(View.GONE);
            adapter = new RVOngoingTournamentAdapter(this, list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        else {
            linear.setVisibility(View.GONE);
        }
        }catch (NullPointerException e){}
        log.info("INFO: OngoingTournament onCreateView");
        return view;
    }

    @Override
    public void onDestroy() {
        log.info("INFO: OngoingTournament onDestroy");
        super.onDestroy();
    }

}
