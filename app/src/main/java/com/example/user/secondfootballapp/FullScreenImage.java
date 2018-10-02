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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            String title = intent.getStringExtra("NEWSTITLE");
//            button = (ImageButton) findViewById(R.id.fullScreenImgBtn);
            button = (Button) findViewById(R.id.fullScreenImgBtn);
            image = (ImageView) findViewById(R.id.fullScreenImg);
            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.ic_some_news)
                    .apply(new RequestOptions()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH)
                            .fitCenter())
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
