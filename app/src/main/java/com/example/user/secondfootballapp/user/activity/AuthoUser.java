package com.example.user.secondfootballapp.user.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.controller.CustomTypefaceSpan;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;
import com.example.user.secondfootballapp.user.adapter.RVOngoingTournamentAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthoUser extends Fragment{
    Logger log = LoggerFactory.getLogger(AuthoUser.class);
    DrawerLayout drawer;
    public static FloatingActionButton fab;
    Toolbar toolbar;
    public static TextView categoryTitle;
    InvitationFragment firstFragment = new InvitationFragment();
    OngoingTournamentFragment defaultFragment = new OngoingTournamentFragment();
    UserClubs secondFragment = new UserClubs();
    TimeTableFragment timeTableFragment = new TimeTableFragment();
    RefereeFragment refereeFragment = new RefereeFragment();
    MyMatches myMatches = new  MyMatches();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        ImageButton buttonOpenProfile;
        ActionBarDrawerToggle mDrawerToggle;
        NavigationView nvDrawer;
        ImageButton button;
//        RecyclerView recyclerView;
        view = inflater.inflate(R.layout.user_autho, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.addCommandButton);
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        categoryTitle = (TextView) view.findViewById(R.id.categoryType);
        button = (ImageButton) view.findViewById(R.id.drawerBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END);
            }
        });

        // Find our drawer view
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewUserTournament);
//        RVOngoingTournamentAdapter adapter = new RVOngoingTournamentAdapter();
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        nvDrawer = (NavigationView) view.findViewById(R.id.nvView);
        nvDrawer.setItemIconTintList(null);
//        LayoutInflater inflater1 = getLayoutInflater();
//        View view1 = inflater1.inflate(R.layout.navigation_header, null);
        View view1 = nvDrawer.getHeaderView(0);
        buttonOpenProfile = (ImageButton) view1.findViewById(R.id.userProfileOpen);
        buttonOpenProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInfo.class);
                String title = "Some title";
                Bundle bundle = new Bundle();
                bundle.putString("NEWSTITLE", title);
                intent.putExtra("NEWSTITLE", bundle);
                getContext().startActivity(intent);
            }
        });
        Menu m = nvDrawer.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
//        nvDrawer.setNavigationItemSelectedListener(this);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, defaultFragment).show(defaultFragment).commit();
        fab.setVisibility(View.INVISIBLE);
        categoryTitle.setText(getActivity().getText(R.string.title_tournament));
        return view;
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;

        switch(menuItem.getItemId()) {
            case R.id.nav_default_fragment:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, defaultFragment).show(defaultFragment).commit();
                categoryTitle.setText(getActivity().getText(R.string.title_tournament));
                fab.setVisibility(View.INVISIBLE);
                break;
            case R.id.nav_first_fragment:
//                fragmentClass = InvitationFragment.class;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, firstFragment).show(firstFragment).commit();
                categoryTitle.setText(getActivity().getText(R.string.invitation));
                fab.setVisibility(View.INVISIBLE);
                break;
            case R.id.nav_second_fragment:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, secondFragment).show(secondFragment).commit();
                categoryTitle.setText(getActivity().getText(R.string.title_club));
                fab.setVisibility(View.VISIBLE);
//                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, myMatches).show(myMatches).commit();
                categoryTitle.setText(getActivity().getText(R.string.matches));
                fab.setVisibility(View.INVISIBLE);
//                fragmentClass = ThirdFragment.class;
                break;
            case R.id.nav_referee_fragment:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, refereeFragment).show(refereeFragment).commit();
                categoryTitle.setText(getActivity().getText(R.string.referees));
                fab.setVisibility(View.INVISIBLE);
                break;
            case R.id.nav_timetable_fragment:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, timeTableFragment).show(timeTableFragment).commit();
                categoryTitle.setText(getActivity().getText(R.string.tournamentInfoTimetable));
                fab.setVisibility(View.INVISIBLE);
                break;
            default:
                fab.setVisibility(View.INVISIBLE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, defaultFragment).show(defaultFragment).commit();
                categoryTitle.setText(getActivity().getText(R.string.title_tournament));
// fragmentClass = FirstFragment.class;
        }

        try {
//            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
//        toolbar.setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
    }

}
