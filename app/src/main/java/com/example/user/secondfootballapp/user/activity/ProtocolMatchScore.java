package com.example.user.secondfootballapp.user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVScoreFoulsAdapter;
import com.example.user.secondfootballapp.user.adapter.RVScoreHalfAdapter;

import java.util.HashMap;

public class ProtocolMatchScore extends AppCompatActivity {

    public static HashMap<Integer, String> halves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerViewFouls;
        RecyclerView recyclerViewScore;
        ImageButton buttonBack;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocol_match_score);
        halves = new HashMap<Integer, String>();
        halves.put(0, "Тайм 1");
        halves.put(1, "Тайм 2");
        halves.put(2, "Тайм 3");
        halves.put(3, "Тайм 4");
        buttonBack = (ImageButton) findViewById(R.id.protocolScoreBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerViewFouls = (RecyclerView) findViewById(R.id.recyclerViewScoreFouls);
        RVScoreFoulsAdapter adapter = new RVScoreFoulsAdapter(this);
        recyclerViewFouls.setAdapter(adapter);
        recyclerViewFouls.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewScore = (RecyclerView) findViewById(R.id.recyclerViewScore);
        RVScoreHalfAdapter adapter1 = new RVScoreHalfAdapter(this);
        recyclerViewScore.setAdapter(adapter1);
        recyclerViewScore.setLayoutManager(new LinearLayoutManager(this));
    }
}
