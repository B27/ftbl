package com.example.user.secondfootballapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.support.v4.app.DialogFragment;
import android.widget.Toast;


import com.example.user.secondfootballapp.club.activity.ClubPage;
import com.example.user.secondfootballapp.club.adapter.RecyclerViewClubAdapter;
import com.example.user.secondfootballapp.controller.BottomNavigationViewHelper;
import com.example.user.secondfootballapp.controller.CustomTypefaceSpan;
import com.example.user.secondfootballapp.home.activity.ComingMatches;
import com.example.user.secondfootballapp.home.activity.MainPage;
import com.example.user.secondfootballapp.home.activity.NewsAndAds;
import com.example.user.secondfootballapp.model.Advertisings;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.Clubs;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.News;
import com.example.user.secondfootballapp.model.News_;
import com.example.user.secondfootballapp.model.People;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.Tournaments;
import com.example.user.secondfootballapp.model.User;
import com.example.user.secondfootballapp.players.activity.PlayersPage;
import com.example.user.secondfootballapp.players.adapter.RecyclerViewPlayersAdapter;
import com.example.user.secondfootballapp.tournament.activity.Tournament;
import com.example.user.secondfootballapp.tournament.activity.TournamentPage;
import com.example.user.secondfootballapp.tournament.adapter.RecyclerViewTournamentAdapter;
import com.example.user.secondfootballapp.tournament.adapter.ViewPagerTournamentInfoAdapter;
import com.example.user.secondfootballapp.user.activity.AuthoUser;
import com.example.user.secondfootballapp.user.activity.ConfirmProtocol;
import com.example.user.secondfootballapp.user.activity.NewCommand;
import com.example.user.secondfootballapp.user.activity.PersonalInfo;
import com.example.user.secondfootballapp.user.activity.UserPage;
import com.github.pwittchen.reactivenetwork.library.rx2.ConnectivityPredicate;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalActivity extends AppCompatActivity {

    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    public static AuthoUser authoUser = new AuthoUser();

    public static Context contextBase;
    public static List<League> tournaments = new ArrayList<>();
    public static List<Person> allPlayers = new ArrayList<>();
    public static List<Person> people = new ArrayList<>();
    public static List<Person> AllPeople = new ArrayList<>();
    public static List<Club> allClubs = new ArrayList<>();

    public static BottomNavigationView navigation;

    ProgressDialog mProgressDialog;
    AdvertisingFragment dialogFragment;
    static Fragment fragmentMain = new MainPage();
    Fragment fragmentTournament;
    Fragment fragmentClub = new ClubPage();
    Fragment fragmentPlayers = new PlayersPage();
    public static Fragment fragmentUser = new UserPage();
    Fragment fragment = (Tournament) getSupportFragmentManager().findFragmentByTag("tornamentTAG");

    android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
    //    Fragment active = fragmentHome;
    public static Fragment active = fragmentMain;

    public PersonalActivity() {
        fragmentTournament = new TournamentPage(fragmentManager);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().hide(active).show(fragmentMain).commit();
                    active = fragmentMain;
                    return true;
                case R.id.navigation_tournament:
                    fragmentManager.beginTransaction().hide(active).show(fragmentTournament).commit();
                    active = fragmentTournament;
                    return true;
                case R.id.navigation_club:
                    fragmentManager.beginTransaction().hide(active).show(fragmentClub).commit();
                    active = fragmentClub;
                    return true;
                case R.id.navigation_players:
                    fragmentManager.beginTransaction().hide(active).show(fragmentPlayers).commit();
                    active = fragmentPlayers;
                    return true;
                case R.id.navigation_user:
//                    if (!UserPage.auth){
//                        fragmentManager.beginTransaction().hide(active).show(fragmentUser).addToBackStack(null).commit();
//                        active = fragmentUser;
//                    }
//                    else{
//                        fragmentManager.beginTransaction().hide(active).show(UserPage.authoUser).addToBackStack(null).commit();
//                        active = UserPage.authoUser;
//                    }

                    if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                        log.error("ЗАРЕГАН");
                        log.error("-------------");
                        log.error(SaveSharedPreference.getObject().getToken());
                        log.error("-------------");
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.detach(authoUser).attach(authoUser).commit();
//                        fragmentManager.beginTransaction().hide(active).show(UserPage.authoUser).addToBackStack(null).commit();
                        fragmentManager.beginTransaction().hide(active).show(authoUser).addToBackStack(null).commit();
//                        active = UserPage.authoUser;
                        active = authoUser;
                    } else {
                        log.error("НЕ ЗАРЕГАН");
                        fragmentManager.beginTransaction().hide(active).show(fragmentUser).addToBackStack(null).commit();
                        active = fragmentUser;
                    }
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);


        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Загрузка...");

        contextBase = getApplicationContext();

        checkConnection();
        checkConnectionSingle();


        try {
            navigation = findViewById(R.id.navigation);
            //            BottomNavigationViewHelper helper = new BottomNavigationViewHelper();
//            BottomNavigationViewHelper.disableShiftMode(navigation);
            disableShiftMode(navigation);

        } catch (Exception e) {
            log.error("ERROR: ", e);
        }
//        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentHome, "1").commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentMain, "1").commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentTournament, "2").hide(fragmentTournament).commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentClub, "3").hide(fragmentClub).commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentPlayers, "4").hide(fragmentPlayers).commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentUser, "5").hide(fragmentUser).commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, authoUser, "6").hide(authoUser).commit();
        navigation.getChildAt(0);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        //set font
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/manrope_regular.otf");
        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", tf);
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = navigation.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(menuItem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            menuItem.setTitle(spannableTitle);
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }

    @SuppressLint("CheckResult")
    private void checkConnectionSingle() {
        Single<Boolean> single = ReactiveNetwork.checkInternetConnectivity();
        single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnectedToInternet -> {
                    GetAdvertising("1", "0");
                });
    }

    @SuppressLint("CheckResult")
    private void GetAdvertising(String limit, String offset) {
        Controller.getApi().getAdvertising(limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> showAds(news)
                        ,
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(this, error);
                        }
                );
    }

    private void showAds(Advertisings news) {
        if (news.getAds().size()!=0) {
            dialogFragment = new AdvertisingFragment().newInstance();
            Bundle args = new Bundle();
            args.putSerializable("ADVERTISING", (Serializable) news.getAds());
            dialogFragment.setArguments(args);
            dialogFragment.show(this.getSupportFragmentManager(), "dialogFragment");
        }
    }



    @SuppressLint("CheckResult")
    private void checkConnection() {
        ReactiveNetwork
                .observeNetworkConnectivity(getApplicationContext())
                .flatMapSingle(connectivity -> ReactiveNetwork.checkInternetConnectivity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> {
                    // isConnected can be true or false
                    if (isConnected){
                        showSnack();
                    }else {
                        String str = "Отсутствует интернет соединение";
                        showToast(str);
                    }
                });
    }




    private void showSnack() {
            //all tournaments
            GetAllTournaments();
            //all players
            GetAllPlayers();
            //all clubs
            GetAllClubs();
        if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            log.error("REFRESH USER");
            RefreshUser();
        }
    }

    @SuppressLint("CheckResult")
    private void RefreshUser() {
        Controller.getApi().refreshUser(SaveSharedPreference.getObject().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(result -> {
                            SaveSharedPreference.editObject(result);
                        }
                        ,
                        error -> getError(error)
                );
    }


    @SuppressLint("CheckResult")
    public void GetAllTournaments() {
        tournaments = new ArrayList<>();
        Controller.getApi().getAllTournaments("32575", "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(tournaments1 -> saveData(tournaments1)
                        ,
                        error -> getError(error)
                );
    }

    private void getError(Throwable error) {
        String str;
        try {

            if (error instanceof HttpException) {
                HttpException exception = (HttpException) error;
                switch (exception.code()) {
                    case 408:
                        str = "Истекло время ожидания, попробуйте позже";
                        break;
                    case 500:
                        str = "Неполадки на сервере. Попробуйте позже";
                        break;
                    case 522:
                        str = "Отсутствует соединение";
                    default:
                        break;
                }
            }
            if (error instanceof SocketTimeoutException) {
                str = "Неполадки на сервере. Попробуйте позже.";
                Toast.makeText(PersonalActivity.this, str, Toast.LENGTH_SHORT).show();
            }
            if (error instanceof ConnectException) {
                str = "Отсутствует соединение.";
                Toast.makeText(PersonalActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        } catch (ClassCastException n) {
            str = "Неполадки на сервере. Попробуйте позже.";
            Toast.makeText(PersonalActivity.this, str, Toast.LENGTH_SHORT).show();
        }


    }

    public static void saveData(Tournaments tournaments1) {
        tournaments.clear();
        tournaments.addAll(tournaments1.getLeagues());
//        TournamentPage.adapter.dataChanged(tournaments1.getLeagues());
    }

    private void savePlayers(People people1) {
        people.clear();
        AllPeople.clear();
        allPlayers.clear();
        allPlayers.addAll(people1.getPeople());
        people.addAll(allPlayers);
        AllPeople.addAll(allPlayers);
//        PlayersPage.adapter.notifyDataSetChanged();
//        PlayersPage.adapter.dataChanged(people1.getPeople());
    }

    @SuppressLint("CheckResult")
    public void GetAllPlayers() {
        String type = "player";
        Controller.getApi().getAllUsers(type, null, "32575", "0")
                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(__ -> showDialog())
//                .doOnTerminate(__ ->hideDialog())
//                .retryWhen(retryHandler -> retryHandler.flatMap(nothing -> retrySubject.asObservable()))
                .retryWhen(throwableObservable -> throwableObservable.take(3).delay(30, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(people -> savePlayers(people),
                        error -> getError(error)
                );
    }



    @SuppressLint("CheckResult")
    public void GetAllClubs() {
        allClubs = new ArrayList<>();
        Controller.getApi().getAllClubs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(clubs -> saveClubs(clubs),
                        error -> getError(error)
                );
    }

    private void saveClubs(Clubs clubs) {
        allClubs.clear();
        allClubs.addAll(clubs.getClubs());
        ClubPage.adapter.dataChanged(clubs.getClubs());
//        ClubPage.adapter.notifyDataSetChanged();
    }


    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                item.setPadding(0, 25, 0, 0);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            log.error("ERROR: BNVHelper. Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            log.error("ERROR: BNVHelper. Unable to change value of shift mode", e);
        }
    }

    private void showToast(String str) {
        this.runOnUiThread(() -> Toast.makeText(PersonalActivity.this, str, Toast.LENGTH_SHORT).show());
    }
}

