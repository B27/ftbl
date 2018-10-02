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

public class ProtocolCommand1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        TextView textCommandTitle;
        ImageButton imageSave;
        ImageButton imageClose;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protocol_command1);
        textCommandTitle = (TextView) findViewById(R.id.command1);
        textCommandTitle.setText("Лара");
        imageClose = (ImageButton) findViewById(R.id.editProtocolCommand1Close);
        imageSave = (ImageButton) findViewById(R.id.editProtocolCommand1Save);
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProtocolCommand1);
        RVProtocolCommand1Adapter adapter = new RVProtocolCommand1Adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
