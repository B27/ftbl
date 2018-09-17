package com.example.user.secondfootballapp;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.widget.TextView;

import android.support.v4.app.DialogFragment;


import com.example.user.secondfootballapp.club.activity.ClubPage;
import com.example.user.secondfootballapp.controller.BottomNavigationViewHelper;
import com.example.user.secondfootballapp.controller.CustomTypefaceSpan;
import com.example.user.secondfootballapp.home.activity.MainPage;
import com.example.user.secondfootballapp.players.activity.PlayersPage;
import com.example.user.secondfootballapp.tournament.activity.Tournament;
import com.example.user.secondfootballapp.tournament.activity.TournamentPage;
import com.example.user.secondfootballapp.user.activity.UserPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class PersonalActivity extends AppCompatActivity {

    private TextView mTextMessage;

    public static BottomNavigationView navigation;
    //    public static AHBottomNavigation navigation;
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    AdvertisingFragment dialogFragment;
    Fragment fragmentMain = new MainPage();
    Fragment fragmentTournament;
    Fragment fragmentClub = new ClubPage();
    Fragment fragmentPlayers = new PlayersPage();
    Fragment fragmentUser = new UserPage();
    Fragment fragment = (Tournament) getSupportFragmentManager().findFragmentByTag("tornamentTAG");

    android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
    //    Fragment active = fragmentHome;
    Fragment active = fragmentMain;
    public PersonalActivity() {
        fragmentTournament = new TournamentPage(fragmentManager);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            if (!CheckVisible() && active != (Tornament) getSupportFragmentManager().findFragmentByTag("tornamentTAG")){
////                getSupportFragmentManager().beginTransaction().remove(active).commit();
//                fragmentManager.beginTransaction().replace(R.id.pageContainer, fragmentTournament).show(fragmentTournament).commit();
//            }

            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    replaceFragment(fragmentMain);
                    fragmentManager.beginTransaction().replace(R.id.pageContainer, fragmentMain).show(fragmentMain).commit();
                    active = fragmentMain;
                    return true;
                case R.id.navigation_tournament:
//                    replaceFragment(fragmentTournament);
                    fragmentManager.beginTransaction().replace(R.id.pageContainer, fragmentTournament).show(fragmentTournament).commit();
                    active = fragmentTournament;
                    return true;
                case R.id.navigation_club:
//                    replaceFragment(fragmentClub);
                    fragmentManager.beginTransaction().replace(R.id.pageContainer, fragmentClub).show(fragmentClub).commit();
                    active = fragmentClub;
                    return true;
                case R.id.navigation_players:
//                    replaceFragment(fragmentPlayers);
                    fragmentManager.beginTransaction().replace(R.id.pageContainer, fragmentPlayers).show(fragmentPlayers).commit();
                    active = fragmentPlayers;
                    return true;
                case R.id.navigation_user:
//                    replaceFragment(fragmentUser);
                    fragmentManager.beginTransaction().replace(R.id.pageContainer, fragmentUser).show(fragmentUser).commit();
                    active = fragmentUser;
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        dialogFragment = new AdvertisingFragment().newInstance();
        dialogFragment.show(this.getSupportFragmentManager(), "dialogFragment");

        mTextMessage = (TextView) findViewById(R.id.message);

        try{
            navigation = (BottomNavigationView) findViewById(R.id.navigation);
            //            BottomNavigationViewHelper helper = new BottomNavigationViewHelper();
//            BottomNavigationViewHelper.disableShiftMode(navigation);
            disableShiftMode(navigation);

        }
        catch (Exception e){
            log.error("ERROR: ", e);
        }
//        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentHome, "1").commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentMain, "1").commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentTournament, "2").hide(fragmentTournament).commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentClub, "3").hide(fragmentClub).commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentPlayers, "4").hide(fragmentPlayers).commit();
        fragmentManager.beginTransaction().add(R.id.pageContainer, fragmentUser, "5").hide(fragmentUser).commit();
        navigation.getChildAt(0);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        //set font
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/manrope_regular.otf");
        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", tf);
        for (int i = 0; i <navigation.getMenu().size(); i++) {
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

    public boolean CheckVisible(){
        Tournament fr = (Tournament) getSupportFragmentManager().findFragmentByTag("tornamentTAG");
//        View fragmentRootView = fragment.getView();
        if (fr != null && fr.isVisible()){
//        if (fragmentRootView != null && fragmentRootView.getGlobalVisibleRect(new Rect())) {
            // fragment is visible
//            fragmentManager.beginTransaction().remove(fr).commit();
            log.info("INFO: fragment is visible");
            return true;
        } else {
            // fragment is not visible
            log.info("INFO: fragment is not visible");
            return false;
        }

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


}

