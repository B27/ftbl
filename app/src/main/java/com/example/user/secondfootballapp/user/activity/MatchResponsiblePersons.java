package com.example.user.secondfootballapp.user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;

public class MatchResponsiblePersons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton buttonBack;
        ImageView imageInspector;
        ImageView imageReferee1;
        ImageView imageReferee2;
        ImageView imageReferee3;
        ImageView imageReferee4;
        TextView textInspector;
        TextView textReferee1;
        TextView textReferee2;
        TextView textReferee3;
        TextView textReferee4;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_responsible_persons);
        buttonBack = (ImageButton) findViewById(R.id.matchResponsiblePersonsBack);
        imageInspector = (ImageView) findViewById(R.id.inspectorLogo);
        imageReferee1 = (ImageView) findViewById(R.id.referee1Logo);
        imageReferee2 = (ImageView) findViewById(R.id.referee2Logo);
        imageReferee3 = (ImageView) findViewById(R.id.referee3Logo);
        imageReferee4 = (ImageView) findViewById(R.id.referee4Logo);
        textInspector = (TextView) findViewById(R.id.inspectorName);
        textReferee1 = (TextView) findViewById(R.id.referee1Name);
        textReferee2 = (TextView) findViewById(R.id.referee2Name);
        textReferee3 = (TextView) findViewById(R.id.referee3Name);
        textReferee4 = (TextView) findViewById(R.id.referee4Name);
        String str = "Иванов В.В.";
        textInspector.setText(str);
        textReferee1.setText(str);
        textReferee2.setText(str);
        textReferee3.setText(str);
        textReferee4.setText(str);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(imageInspector);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(imageReferee1);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(imageReferee2);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(imageReferee3);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(imageReferee4);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
