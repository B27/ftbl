package com.example.user.secondfootballapp.user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVRefereesMatchesAdapter;

public class RefereesMatches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        ImageButton buttonBack;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referees_matches);
        buttonBack = (ImageButton) findViewById(R.id.refereesMatchesBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRefereesMatches);
        RVRefereesMatchesAdapter adapter = new RVRefereesMatchesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
