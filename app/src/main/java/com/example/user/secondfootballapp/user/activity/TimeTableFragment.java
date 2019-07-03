package com.example.user.secondfootballapp.user.activity;

import android.annotation.SuppressLint;
import androidx.paging.PagedList;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.ActiveMatch;
import com.example.user.secondfootballapp.model.ActiveMatches;
import com.example.user.secondfootballapp.user.adapter.RVTimeTableAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TimeTableFragment extends Fragment {
    RVTimeTableAdapter adapter;
    List<ActiveMatch> matches = new ArrayList<>();
    NestedScrollView scroller;
    int count = 0;
    int limit = 10;
    int offset = 0;
    Logger log = LoggerFactory.getLogger(TimeTableFragment.class);
    LinearLayout layout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        RecyclerView recyclerView;
        view = inflater.inflate(R.layout.user_timetable, container, false);
        scroller = view.findViewById(R.id.scrollerTimeTable);
        layout = view.findViewById(R.id.emptyTimetable);
        getActiveMatches("10", "0");
        recyclerView = view.findViewById(R.id.recyclerViewTimeTable);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        try {
            adapter = new RVTimeTableAdapter(getActivity(), this, matches);
            recyclerView.setAdapter(adapter);

        } catch (NullPointerException e) {
            log.error("ERROR: ", e);
        }
        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                offset++;
                int temp = limit*offset;
                if (temp<=count) {
                    String str = String.valueOf(temp);
                    getActiveMatches("10", str);
                }
            }
        });

        return view;
    }

    @SuppressLint("CheckResult")
    private void getActiveMatches(String limit, String offset) {
        Controller.getApi().getActiveMatches(limit, offset, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(matches -> saveData(matches)
                        ,
                        error -> {
                            layout.setVisibility(View.VISIBLE);
                            CheckError checkError = new CheckError();
                            checkError.checkError(getActivity(), error);
                        }
                );
    }

    private void saveData(ActiveMatches matches1) {
        count = matches1.getCount();
        List<ActiveMatch> result;
        result = matches1.getMatches();
        if (result.size() != 0) {
            matches.addAll(matches.size(), result);
            List<ActiveMatch> list = new ArrayList<>(matches);
            adapter.dataChanged(list);
            layout.setVisibility(View.GONE);

        }
        if (matches.size()==0){
            layout.setVisibility(View.VISIBLE);
        }
//        else {
//            layout.setVisibility(View.VISIBLE);
//        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
