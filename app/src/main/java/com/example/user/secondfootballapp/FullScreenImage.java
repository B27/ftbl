package com.example.user.secondfootballapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.user.activity.AuthoUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class FullScreenImage extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(FullScreenImage.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
//        ImageButton button;
        Button button;
        ImageView image;
        try{
            Intent intent = getIntent();
            String uri = intent.getStringExtra("player_photo");
//            button = (ImageButton) findViewById(R.id.fullScreenImgBtn);
            button = (Button) findViewById(R.id.fullScreenImgBtn);
            image = (ImageView) findViewById(R.id.fullScreenImg);
            URL url = new URL(uri);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
            requestOptions.errorOf(R.drawable.ic_logo2);
            requestOptions.priority(Priority.HIGH);
            requestOptions.fitCenter();
            Glide.with(this)
                    .asBitmap()
                    .load(url)
                    .apply(requestOptions)
                    .into(image);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();//??????
                }
            });

        }
        catch (Exception t){
            log.error("ERROR: ", t);
        }
    }
}
