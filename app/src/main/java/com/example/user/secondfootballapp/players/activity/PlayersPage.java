package com.example.user.secondfootballapp.players.activity;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.players.adapter.RecyclerViewPlayersAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class PlayersPage extends Fragment {
    Logger log = LoggerFactory.getLogger(PlayersPage.class);
//    MaterialSearchView searchView;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        RecyclerView recyclerView;
        AppBarLayout appBarLayout;
        final CollapsingToolbarLayout collapsingToolbar;
        Toolbar toolbar;
        view = inflater.inflate(R.layout.page_players, container, false);
//        setHasOptionsMenu(true);
//        searchView = (MaterialSearchView) view.findViewById(R.id.searchView);
        searchView = (SearchView) view.findViewById(R.id.searchView);
//        searchView.setVoiceSearch(false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPlayers);
//        getActivity().getSupportActionBar().hide();
        toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbarPlayers);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbarPlayers);

        collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbarPlayers);
        collapsingToolbar.setTitle("Игроки");
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/manrope_regular.otf");
        collapsingToolbar.setExpandedTitleTypeface(tf);
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        SearchView.SearchAutoComplete theTextArea = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
        theTextArea.setTextColor(getResources().getColor(R.color.colorBottomNavigationUnChecked));
        theTextArea.setTypeface(tf);
        theTextArea.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        searchView.setQueryHint(Html.fromHtml("<font color = #63666F>" + getResources().getString(R.string.search) + "</font>"));
        ImageView searchIcon = searchView
                .findViewById(android.support.v7.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_search2));

        ImageView searchViewClose = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchViewClose.setImageResource(R.drawable.ic_clear);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    //  Collapsed
                    collapsingToolbar.setCollapsedTitleTextColor(Color.TRANSPARENT);
//                    searchView.setVisibility(View.VISIBLE);

                    searchView.animate().translationY(0);


//                    int mShortAnimationDuration = getResources().getInteger(
//                            android.R.integer.config_shortAnimTime);
//
//                    searchView.animate()
//                            .alpha(0f)
//                            .setDuration(mShortAnimationDuration)
//                            .setListener(new AnimatorListenerAdapter() {
//                                @Override
//                                public void onAnimationEnd(Animator animation) {
//                                    searchView.setVisibility(View.GONE);
//                                }
//                            });
//                    searchView.animate()
//                            .alpha(1f)
//                            .setDuration(mShortAnimationDuration)
//                            .setListener(null);

                }
                else
                {
                    //Expanded
                    collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
//                    searchView.setVisibility(View.INVISIBLE);

                    searchView.animate().translationY(0 - searchView.getHeight());

//                    searchView.setVisibility(View.VISIBLE);

                    int mShortAnimationDuration = getResources().getInteger(
                            android.R.integer.config_shortAnimTime);


                }

            }
        });
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                log.info("INFO: submit");
//                if (query.length()==0){
//                    log.info("INFO: empty query");
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                log.info("INFO: change");
//                return false;
//            }
//        });



        RecyclerViewPlayersAdapter adapter = new RecyclerViewPlayersAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        log.info("000000000000000000000000000000000000000000000000000000000000000000000000000");
        menuInflater.inflate(R.menu.player_search, menu);

        searchView.setIconified(true);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
//        searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView = (MaterialSearchView) MenuItemCompat.getActionView(item);
//        searchView.setMenuItem(item);

    }



    }



