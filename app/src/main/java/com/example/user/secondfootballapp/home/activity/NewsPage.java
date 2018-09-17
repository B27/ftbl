package com.example.user.secondfootballapp.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsPage extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(NewsPage.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);
        Button button;
        ImageView imageNews;
        TextView textTitle;
        TextView textDate;
        TextView textDesc;
        try{
            Intent intent = getIntent();
            String title = intent.getStringExtra("NEWSTITLE");
            imageNews = (ImageView) findViewById(R.id.newsInfoImg);
            button = (Button) findViewById(R.id.newsButtonBack);
            textTitle = (TextView) findViewById(R.id.newsInfoTitle);
            textDate = (TextView) findViewById(R.id.newsInfoDate);
            textDesc = (TextView) findViewById(R.id.newsInfoText);
            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.ic_some_news)
                    .apply(new RequestOptions()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH)
                            .centerCrop())
                    .into(imageNews);
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

