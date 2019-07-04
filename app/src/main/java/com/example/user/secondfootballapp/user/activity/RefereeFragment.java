package com.example.user.secondfootballapp.user.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVRefereesAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RefereeFragment extends Fragment{
    private final Logger log = LoggerFactory.getLogger(RefereeFragment.class);
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        RecyclerView recyclerView;
        view = inflater.inflate(R.layout.user_referees, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewReferees);
        recyclerView.setNestedScrollingEnabled(false);
        RVRefereesAdapter adapter = new RVRefereesAdapter(getActivity(),this, AuthoUser.allReferees);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    @Override
    public void onDestroy() {
        log.info("INFO: RefereeFragment onDestroy");
        super.onDestroy();
    }
}
