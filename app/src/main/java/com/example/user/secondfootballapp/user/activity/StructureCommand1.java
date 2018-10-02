package com.example.user.secondfootballapp.user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVProtocolFirstCommandAdapter;

public class StructureCommand1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        TextView textCommandTitle;
        ImageButton buttonBack;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.structure_command1);
        buttonBack = (ImageButton)findViewById(R.id.editProtocolCommand1Back);
        textCommandTitle = (TextView) findViewById(R.id.command1Title);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewConfirmProtocolCommand1);
        RVProtocolFirstCommandAdapter adapter = new RVProtocolFirstCommandAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textCommandTitle.setText(getResources().getText(R.string.commandTitle));
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
