package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;

public class ConfirmProtocol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton imageClose;
        ImageButton imageSave;
        ImageButton buttonCommand1;
        ImageButton buttonCommand2;
        ImageButton buttonReferees;
        ImageButton buttonEvents;
        FloatingActionButton fab;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_protocol);
        fab = (FloatingActionButton) findViewById(R.id.confirmProtocolButtonShowScore);
        buttonCommand1 = (ImageButton) findViewById(R.id.confirmProtocolCommand1ButtonShow);
        buttonCommand2 = (ImageButton) findViewById(R.id.confirmProtocolCommand2ButtonShow);
        buttonReferees = (ImageButton) findViewById(R.id.confirmProtocolRefereesButtonShow);
        buttonEvents = (ImageButton) findViewById(R.id.confirmProtocolEventsButtonShow);
        imageClose = (ImageButton) findViewById(R.id.confirmProtocolClose);
        imageSave = (ImageButton) findViewById(R.id.confirmProtocolSave);
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmProtocol.this, ProtocolMatchScore.class);
                startActivity(intent);
            }
        });
        buttonCommand1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmProtocol.this, StructureCommand1.class);
                startActivity(intent);
            }
        });
        buttonCommand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmProtocol.this, StructureCommand2.class);
                startActivity(intent);
            }
        });
        buttonReferees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmProtocol.this, MatchResponsiblePersons.class);
                startActivity(intent);
            }
        });

        buttonEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmProtocol.this, MatchEvents.class);
                startActivity(intent);
            }
        });
    }
}
