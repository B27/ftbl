package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVUserCommandPlayerAdapter;
import com.example.user.secondfootballapp.user.adapter.RVUserCommandPlayerInvAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCommandInfo extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(UserCommandInfo.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerViewPlayer;
        RecyclerView recyclerViewPlayerInv;
        Toolbar toolbar;
        ImageButton buttonClose;
        ImageButton buttonSave;
        super.onCreate(savedInstanceState);
        try {
            Intent intent = getIntent();
            String title = intent.getStringExtra("NEWSTITLE");
            setContentView(R.layout.user_command_info);
            toolbar = (Toolbar) findViewById(R.id.toolbarUserCommandInfo);
            setSupportActionBar(toolbar);
            buttonSave = (ImageButton) findViewById(R.id.userCommandSave);
            buttonClose = (ImageButton) findViewById(R.id.userCommandClose);
            recyclerViewPlayer = (RecyclerView) findViewById(R.id.recyclerViewUserCommandPlayers);
            RVUserCommandPlayerAdapter adapter = new RVUserCommandPlayerAdapter(this);
            recyclerViewPlayer.setAdapter(adapter);
            recyclerViewPlayer.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewPlayerInv = (RecyclerView) findViewById(R.id.recyclerViewUserCommandPlayersInv);
            RVUserCommandPlayerInvAdapter adapterInv = new RVUserCommandPlayerInvAdapter(this);
            recyclerViewPlayerInv.setAdapter(adapterInv);
            recyclerViewPlayerInv.setLayoutManager(new LinearLayoutManager(this));
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //post
                    finish();
                }
            });
            buttonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            log.error("ERROR: ", e);
        }
    }
}
