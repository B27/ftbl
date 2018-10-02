package com.example.user.secondfootballapp.user.activity;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.user.secondfootballapp.R;

public class NewCommand extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageButton imageClose;
        ImageButton imageSave;
        Spinner spinnerTournament;
        Spinner spinnerLeague;
        final EditText textTitle;
        setContentView(R.layout.user_new_command);
        imageClose = (ImageButton) findViewById(R.id.newCommandClose);
        imageSave = (ImageButton) findViewById(R.id.newCommandSave);
        textTitle = (EditText) findViewById(R.id.newCommandTitle);
        spinnerTournament = (Spinner) findViewById(R.id.newCommandTournamentSpinner);
        spinnerLeague = (Spinner) findViewById(R.id.newCommandLeagueSpinner);
        textTitle.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        textTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textTitle.getBackground().clearColorFilter();
                }
                else {
                    textTitle.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                }
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
