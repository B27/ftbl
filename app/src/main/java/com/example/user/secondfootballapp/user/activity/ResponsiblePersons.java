package com.example.user.secondfootballapp.user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;

public class ResponsiblePersons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView image;
        ImageButton imageClose;
        ImageButton imageSave;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.responsible_persons);
        imageClose = (ImageButton) findViewById(R.id.responsiblePersonsClose);
        imageSave = (ImageButton) findViewById(R.id.responsiblePersonsSave);
        image = (ImageView) findViewById(R.id.responsiblePersonsPhoto);
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
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_fin)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(image);
    }
}
