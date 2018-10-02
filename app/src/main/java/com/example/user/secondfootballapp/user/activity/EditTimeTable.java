package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;

public class EditTimeTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton imageClose;
        ImageButton imageSave;
        Button button;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_timetable);
        button = (Button) findViewById(R.id.buttonMainRefereeShowProtocol);
        imageClose = (ImageButton) findViewById(R.id.refereeEditMatchClose);
        imageSave = (ImageButton) findViewById(R.id.refereeEditMatchSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTimeTable.this, ConfirmProtocol.class);
                startActivity(intent);
            }
        });
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
