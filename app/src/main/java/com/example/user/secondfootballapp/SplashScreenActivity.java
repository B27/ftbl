package com.example.user.secondfootballapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashScreenActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.SplashScreenTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        Intent intent = new Intent(this, PersonalActivity.class);
        startActivity(intent);
        finish();

    }


}
