package com.example.user.secondfootballapp.tournament.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;


import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.adapter.RVCommandStructureAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandStructureFragment extends Fragment {
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        final AbbreviationDialogFragment dialogFragment;
        final FloatingActionButton fab;
        RecyclerView recyclerView;
        view = inflater.inflate(R.layout.command_info_structure, container, false);
        dialogFragment = new AbbreviationDialogFragment();
        fab = (FloatingActionButton) view.findViewById(R.id.commandInfoButton);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewCommandStructure);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getFragmentManager(), "abbrev2");
            }
        });
        RVCommandStructureAdapter adapter = new RVCommandStructureAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
//                if (dy > 0 ||dy<0 && fab.isShown())
//                    fab.hide();
//                log.info("INFO: SCROLL=================");
//            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.show();
                }
                else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                    fab.hide();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        return view;
    }
}
