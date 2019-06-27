package com.example.user.secondfootballapp.user.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.People;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.user.adapter.RVPlayerAddToTeamAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PlayerAddToTeam extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(PlayerAddToTeam.class);
    RVPlayerAddToTeamAdapter adapter;
    ProgressDialog mProgressDialog;
    ProgressBar progressBar;
    NestedScrollView scroller;
    List<Person> people = new ArrayList<>();
    List<Person> allPeople = new ArrayList<>();
    List<Person> result = new ArrayList<>();
    int count = 0;
    int offset = 0;
    int limit = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mProgressDialog = new ProgressDialog(this, R.style.MyProgressDialogTheme);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Загрузка...");
        RecyclerView recyclerView;
        SearchView searchView;
        super.onCreate(savedInstanceState);
        getAllPlayers("10", "0");
        setContentView(R.layout.player_add_to_team);
        recyclerView = findViewById(R.id.recyclerViewUserCommandAddPlayers);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progressSearchPlayerToTeam);
        scroller = findViewById(R.id.scrollerPlayerAddToTeam);
        try {
            Team team = (Team) getIntent().getExtras().getSerializable("ADDPLAYERTOUSERTEAM");
            League league = (League) getIntent().getExtras().getSerializable("ADDPLAYERTOUSERTEAMLEAGUE");
//            adapter = new RVPlayerAddToTeamAdapter(this, PersonalActivity.people, team, league);
            adapter = new RVPlayerAddToTeamAdapter(this, people, team, league);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
        }
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/manrope_regular.otf");
        searchView = findViewById(R.id.userCommandSearchView);
        SearchView.SearchAutoComplete theTextArea = searchView.findViewById(R.id.search_src_text);
        theTextArea.setTextColor(getResources().getColor(R.color.colorBottomNavigationUnChecked));
        theTextArea.setTypeface(tf);
        theTextArea.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        searchView.setQueryHint(Html.fromHtml("<font color = #63666F>" + getResources().getString(R.string.search) + "</font>"));
        ImageView icon = searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        icon.setColorFilter(getResources().getColor(R.color.colorLightGrayForText), PorterDuff.Mode.SRC_ATOP);
        ImageView searchViewClose = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchViewClose.setColorFilter(getResources().getColor(R.color.colorLightGrayForText), PorterDuff.Mode.SRC_ATOP);
        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                offset++;
                int temp = limit*offset;
                if (temp<=count && result.size()==0) {
                    String str = String.valueOf(temp);
                    getAllPlayers("10", str);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchUsers(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(() -> {
            result.clear();
            people.clear();
            people.addAll(allPeople);
            List<Person> list = new ArrayList<>(people);
            adapter.dataChanged(list);
            return false;
        });

    }

    @SuppressLint("CheckResult")
    public void SearchUsers(String search) {
        String type = "player";
        Controller.getApi().getAllUsers(type, search, "32575", "0")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(__ -> showDialog())
                .doOnTerminate(()->hideDialog())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(people -> savePlayers(people),
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(this, error);
                        }
                );
    }

    private void savePlayers(People people) {
        adapter.dataChanged(people.getPeople());
        result.clear();
        result.addAll(people.getPeople());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UserCommandInfo.hideDialog();
        finish();
    }

    @Override
    protected void onDestroy() {
        PersonalActivity.people.clear();
        PersonalActivity.people.addAll(PersonalActivity.AllPeople);
        super.onDestroy();
    }

    @SuppressLint("CheckResult")
    public void getAllPlayers(String limit, String offset) {
        CheckError checkError = new CheckError();
        String type = "player";
        Controller.getApi().getAllUsers(type, null, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(people -> saveAllPlayers(people),
                        error -> checkError.checkError(this, error)
                );
    }

    private void saveAllPlayers(People peopleList) {
        count = peopleList.getCount();
        people.addAll(people.size(), peopleList.getPeople());
        List<Person> list = new ArrayList<>(people);
        allPeople.clear();
        allPeople.addAll(people);
        adapter.dataChanged(list);
    }

    public void showDialog() {

        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

}
