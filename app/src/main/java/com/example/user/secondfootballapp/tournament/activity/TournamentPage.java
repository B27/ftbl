package com.example.user.secondfootballapp.tournament.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.home.activity.HomePage;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.home.adapter.RecyclerViewHomeAdapter;
import com.example.user.secondfootballapp.model.GetLeagueInfo;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.LeagueInfo;
import com.example.user.secondfootballapp.model.News;
import com.example.user.secondfootballapp.model.People;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.model.Tournaments;
import com.example.user.secondfootballapp.tournament.adapter.RecyclerViewTournamentAdapter;
import com.example.user.secondfootballapp.user.adapter.RVProtocolEditAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class TournamentPage extends Fragment {
    public static RecyclerViewTournamentAdapter adapter;
    public static List<Person> referees = new ArrayList<>();
    Logger log = LoggerFactory.getLogger(NewsPage.class);
    RecyclerView recyclerView;
    android.support.v4.app.FragmentManager fragmentManager;
    ProgressBar progressBar;
    NestedScrollView scroller;
    List<League> tournaments= new ArrayList<>();
    int count = 0;
    int offset = 0;
    int limit = 5;
    @SuppressLint("ValidFragment")
    public TournamentPage(android.support.v4.app.FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.page_tournament, container, false);
        scroller = view.findViewById(R.id.scrollerLeague);
        progressBar =view.findViewById(R.id.progresLeague);
        GetAllReferees();
        GetAllTournaments("5", "0");
        try {
            recyclerView = view.findViewById(R.id.recyclerViewTournament);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new RecyclerViewTournamentAdapter(getActivity(), TournamentPage.this, PersonalActivity.tournaments, new RecyclerViewTournamentAdapter.ListAdapterListener() {
                @Override
                public void onClickSwitch(String  leagueId) {
                    showTournamentInfo(leagueId);
                }
            });
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            log.error("ERROR: ", e);
        }
        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                offset++;
                int temp = limit*offset;
                if (temp<=count) {
                    String str = String.valueOf(temp);
                    GetAllTournaments("10", str);
                }
            }
        });
        return view;
    }

    @SuppressLint("CheckResult")
    private void GetAllTournaments(String limit, String offset) {
        Controller.getApi().getAllTournaments(limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tournaments1 -> saveAllData(tournaments1)
                        ,
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(getActivity(), error);
                        }
                );
    }

    private void saveAllData(Tournaments tournaments1) {
        count = tournaments1.getCount();
        tournaments.addAll(tournaments.size(), tournaments1.getLeagues());
        List<League> list = new ArrayList<>(tournaments);
        adapter.dataChanged(list);
    }

    @SuppressLint("CheckResult")
    public void showTournamentInfo(String leagueId){
        Controller.getApi().getLeagueInfo(leagueId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(__ -> progressBarShow())
//                .doOnTerminate(()->progressBarHide())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getLeagueInfo -> saveData(getLeagueInfo)
                        ,
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(getActivity(), error);
                        }
                );
    }

    private void saveData(GetLeagueInfo getLeagueInfo) {
        LeagueInfo tournament1 = getLeagueInfo.getLeagueInfo();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TOURNAMENTINFO", tournament1);
        Tournament tournament = new Tournament();
        tournament.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        try{
            fragmentManager.beginTransaction().add(R.id.pageContainer, tournament, "LEAGUEINFO").commit();
        }catch (Exception e){
            log.error("ERROR: ", e);
        }
        fragmentManager.beginTransaction().hide(PersonalActivity.active).show(tournament).commit();
        PersonalActivity.active = tournament;
        progressBarHide();
    }

    @SuppressLint("CheckResult")
    private void GetAllReferees() {
        String type = "referee";
        Controller.getApi().getAllUsers(type, null, "32575", "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(people -> saveReferees(people),
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(getActivity(), error);
                        }
                );
    }

    private void saveReferees(People people) {
        referees.clear();
        referees.addAll(people.getPeople());
    }

    private void progressBarShow(){
        progressBar.setVisibility(View.VISIBLE);
    }
    protected void progressBarHide(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
