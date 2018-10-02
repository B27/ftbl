package com.example.user.secondfootballapp.user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVProtocolCommand1Adapter;
import com.example.user.secondfootballapp.user.adapter.RVProtocolCommand2Adapter;

public class ProtocolCommand2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        TextView textCommandTitle;
        ImageButton imageSave;
        ImageButton imageClose;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocol_command2);
        textCommandTitle = (TextView) findViewById(R.id.command2);
        textCommandTitle.setText("Лара");
        imageClose = (ImageButton) findViewById(R.id.editProtocolCommand2Close);
        imageSave = (ImageButton) findViewById(R.id.editProtocolCommand2Save);
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProtocolCommand2);
        RVProtocolCommand2Adapter adapter = new RVProtocolCommand2Adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
