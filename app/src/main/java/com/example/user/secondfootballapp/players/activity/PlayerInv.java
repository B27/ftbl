package com.example.user.secondfootballapp.players.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.user.secondfootballapp.R;

public class PlayerInv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton imageClose;
        ImageButton imageSave;
        Spinner spinnerCommands;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_inv);
        imageClose = (ImageButton) findViewById(R.id.playerInvClose);
        imageSave = (ImageButton) findViewById(R.id.playerInvSave);
        spinnerCommands = (Spinner) findViewById(R.id.playerInvSpinner);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //post
            }
        });
    }
}
