package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVProtocolEditAdapter;


import java.util.ArrayList;
import java.util.List;

public class ProtocolEdit extends AppCompatActivity {
    public static int toolbarHeight;
    public static List<Integer> items;
    public static NestedScrollView scroller;
    public static CoordinatorLayout coordinatorLayout;
    public static AppBarLayout appBarLayout;
    public static AppBarLayout.Behavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbar;
        items = new ArrayList<>();
        items.add(1);
        ImageView imageLogo1;
        ImageView imageLogo2;
        TextView textTitle1;
        TextView textTitle2;
        ImageButton buttonReferees;
        ImageButton buttonShowCommand1;
        ImageButton buttonShowCommand2;
        ImageButton buttonEvents;
        ImageButton imageClose;
        ImageButton imageSave;
        FloatingActionButton fab;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocol_edit);
        imageLogo1 = (ImageView) findViewById(R.id.editProtocolCommand1Logo);
        imageLogo2 = (ImageView) findViewById(R.id.editProtocolCommand2Logo);
        textTitle1 = (TextView) findViewById(R.id.editProtocolCommand1Title);
        textTitle2 = (TextView) findViewById(R.id.editProtocolCommand2Title);
        buttonShowCommand1 = (ImageButton) findViewById(R.id.editProtocolCommand1ButtonShow);
        buttonShowCommand2 = (ImageButton) findViewById(R.id.editProtocolCommand2ButtonShow);
        buttonReferees = (ImageButton) findViewById(R.id.editProtocolRefereesButtonShow);
        buttonEvents = (ImageButton) findViewById(R.id.editProtocolEventsButtonShow);
        fab = (FloatingActionButton) findViewById(R.id.editProtocolButtonShowScore);
        imageClose = (ImageButton) findViewById(R.id.editProtocolClose);
        imageSave = (ImageButton) findViewById(R.id.editProtocolSave);
        scroller = (NestedScrollView) findViewById(R.id.scrollProtocolEdit);
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
        buttonShowCommand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProtocolEdit.this, ProtocolCommand2.class);
                startActivity(intent);
            }
        });
        buttonShowCommand1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProtocolEdit.this, ProtocolCommand1.class);
                startActivity(intent);
            }
        });

        buttonEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProtocolEdit.this, ProtocolEventsEdit.class);
                startActivity(intent);
            }
        });
        buttonReferees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProtocolEdit.this, ResponsiblePersons.class);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProtocolEdit.this, ProtocolMatchScore.class);
                startActivity(intent);
            }
        });
    }
}
