package com.example.user.secondfootballapp.players.activity;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.People;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.players.adapter.RecyclerViewPlayersAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PlayersPage extends Fragment {
    RecyclerView recyclerView;
    int count = 0;
    int offset = 0;
    int limit = 10;
    public static RecyclerViewPlayersAdapter adapter;
//    RecyclerViewPlayersAdapter adapter;
    Logger log = LoggerFactory.getLogger(PlayersPage.class);
    SearchView searchView;
    ProgressBar progressBar;
    List<Person> result = new ArrayList<>();
    ProgressDialog mProgressDialog;
    List<Person> people = new ArrayList<>();
    List<Person> allPeople = new ArrayList<>();
    NestedScrollView scroller;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        mProgressDialog = new ProgressDialog(getActivity(), R.style.MyProgressDialogTheme);
        mProgressDialog.setIndeterminate(true);
//        mProgressDialog.setMessage("Загрузка...");
        final View view;
        view = inflater.inflate(R.layout.page_players, container, false);
        getAllPlayers("10", "0");
        scroller = view.findViewById(R.id.scrollerPlayersPage);
        searchView = view.findViewById(R.id.searchView);
        progressBar = view.findViewById(R.id.progressSearch);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf");
        SearchView.SearchAutoComplete theTextArea = searchView.findViewById(R.id.search_src_text);
        theTextArea.setTextColor(getResources().getColor(R.color.colorBottomNavigationUnChecked));
        theTextArea.setTypeface(tf);
        theTextArea.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
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

        try{
            recyclerView = view.findViewById(R.id.recyclerViewPlayers);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new RecyclerViewPlayersAdapter(getActivity(), PlayersPage.this, people);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){log.error("ERROR: ", e);}

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.player_search, menu);
        searchView.setIconified(true);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();

    }

    @SuppressLint("CheckResult")
    public void SearchUsers(String search){
//        PersonalActivity.people.clear();
        String type = "player";
        Controller.getApi().getAllUsers(type, search, "32575", "0")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(__ -> showDialog())
                .doOnTerminate(()->hideDialog())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(people -> savePlayers(people),
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(getActivity(), error);
                        }
                );

    }

    private void savePlayers(People people) {
        result.clear();
        result.addAll(people.getPeople());
        adapter.dataChanged(result);
    }

    @Override
    public void onPause() {
        PersonalActivity.people.clear();
        PersonalActivity.people.addAll(PersonalActivity.AllPeople);
        super.onPause();
    }

    public void showDialog() {

        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }


    @SuppressLint("CheckResult")
    public void getAllPlayers(String limit, String offset) {
        CheckError checkError = new CheckError();
        String type = "player";
        Controller.getApi().getAllUsers(type, null, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(people -> saveAllPlayers(people),
                        error -> checkError.checkError(getActivity(), error)
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
}



