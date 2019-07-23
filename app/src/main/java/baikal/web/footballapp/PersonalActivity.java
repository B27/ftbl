package baikal.web.footballapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import baikal.web.footballapp.club.activity.ClubPage;
import baikal.web.footballapp.controller.CustomTypefaceSpan;
import baikal.web.footballapp.home.activity.MainPage;
import baikal.web.footballapp.model.Advertisings;
import baikal.web.footballapp.model.Club;
import baikal.web.footballapp.model.Clubs;
import baikal.web.footballapp.model.League;
import baikal.web.footballapp.model.People;
import baikal.web.footballapp.model.Person;
import baikal.web.footballapp.model.Tournaments;
import baikal.web.footballapp.players.activity.PlayersPage;
import baikal.web.footballapp.tournament.activity.Tournament;
import baikal.web.footballapp.tournament.activity.TournamentPage;
import baikal.web.footballapp.user.activity.AuthoUser;
import baikal.web.footballapp.user.activity.UserPage;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PersonalActivity extends AppCompatActivity {

    public static final List<Person> allPlayers = new ArrayList<>();
    public static final List<Person> people = new ArrayList<>();
    public static final List<Person> AllPeople = new ArrayList<>();
    public static final Fragment fragmentUser = new UserPage();
    private static final AuthoUser authoUser = new AuthoUser();
    private static final Fragment fragmentMain = new MainPage();
    public static List<League> tournaments = new ArrayList<>();
    public static List<Club> allClubs = new ArrayList<>();
    //    Fragment active = fragmentHome;
    public static Fragment active = fragmentMain;
    private static Context contextBase;
    private static BottomNavigationView bottomNavigationView;
    private final Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    private final Fragment fragmentTournament;
    private final Fragment fragmentClub = new ClubPage();
    private final Fragment fragmentPlayers = new PlayersPage();
    private final FragmentManager fragmentManager = this.getSupportFragmentManager();

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    Fragment fragment = (Tournament) getSupportFragmentManager().findFragmentByTag("tornamentTAG");
    private ProgressDialog mProgressDialog;
    private AdvertisingFragment dialogFragment;

    public PersonalActivity() {
        fragmentTournament = new TournamentPage(fragmentManager);
    }

    public static void saveData(Tournaments tournaments1) {
        tournaments.clear();
        tournaments.addAll(tournaments1.getLeagues());
//        TournamentPage.adapter.dataChanged(tournaments1.getLeagues());
    }

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
            bottomNavigationView = findViewById(R.id.bottom_navigation_view);
            //            BottomNavigationViewHelper helper = new BottomNavigationViewHelper();
//            BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

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
        bottomNavigationView.getChildAt(0);
        //set font
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/manrope_regular.otf");
        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", tf);
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(menuItem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            menuItem.setTitle(spannableTitle);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                .subscribe(isConnectedToInternet -> GetAdvertising("1", "0"));
    }

    @SuppressLint("CheckResult")
    private void GetAdvertising(String limit, String offset) {
        Controller.getApi().getAdvertising(limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showAds
                        ,
                        error -> {
                            CheckError checkError = new CheckError();
                            checkError.checkError(this, error);
                        }
                );
    }

    private void showAds(Advertisings news) {
        if (news.getAds().size() != 0) {
            dialogFragment = AdvertisingFragment.newInstance();
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
                    if (isConnected) {
                        showSnack();
                    } else {
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
                .subscribe(SaveSharedPreference::editObject
                        ,
                        this::getError
                );
    }

    @SuppressLint("CheckResult")
    private void GetAllTournaments() {
        tournaments = new ArrayList<>();
        Controller.getApi().getAllTournaments("32575", "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(PersonalActivity::saveData
                        ,
                        this::getError
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
    private void GetAllPlayers() {
        String type = "player";
        Controller.getApi().getAllUsers(type, null, "32575", "0")
                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(__ -> showDialog())
//                .doOnTerminate(__ ->hideDialog())
//                .retryWhen(retryHandler -> retryHandler.flatMap(nothing -> retrySubject.asObservable()))
                .retryWhen(throwableObservable -> throwableObservable.take(3).delay(30, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(this::savePlayers,
                        this::getError
                );
    }


    @SuppressLint("CheckResult")
    private void GetAllClubs() {
        allClubs = new ArrayList<>();
        Controller.getApi().getAllClubs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(this::saveClubs,
                        this::getError
                );
    }

    private void saveClubs(Clubs clubs) {
        allClubs.clear();
        allClubs.addAll(clubs.getClubs());
        ClubPage.adapter.dataChanged(clubs.getClubs());
//        ClubPage.adapter.notifyDataSetChanged();
    }

    private void showToast(String str) {
        this.runOnUiThread(() -> Toast.makeText(PersonalActivity.this, str, Toast.LENGTH_SHORT).show());
    }
}
