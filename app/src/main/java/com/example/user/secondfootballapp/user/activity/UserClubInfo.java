package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.club.adapter.RVClubMembersAdapter;
import com.example.user.secondfootballapp.home.activity.MainPage;
import com.example.user.secondfootballapp.user.adapter.RVUserClubInfoMemberAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserClubInfo extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(UserClubInfo.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        Button buttonBack;
        TextView textMembers;
        TextView textCoach;
        TextView textDesc;
        TextView textTitle;
        ImageView imageLogo;
        Button buttonEdit;
        Button buttonEscape;
        Boolean check = true;
        LinearLayoutManager layoutManager;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_club_info);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUserClubInfoMembers);
        textCoach = (TextView) findViewById(R.id.userClubInfoCoach);
        textDesc = (TextView) findViewById(R.id.userClubInfoDesc);
        textTitle = (TextView) findViewById(R.id.userClubInfoTitle);
        textMembers = (TextView) findViewById(R.id.userClubInfoMembersStatus);
        imageLogo = (ImageView) findViewById(R.id.userClubInfoLogo);
        buttonBack = (Button) findViewById(R.id.userClubInfoButtonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try{
            final Intent intent = getIntent();
            String title = intent.getStringExtra("NEWSTITLE");
            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.ic_member)
                    .apply(new RequestOptions()
                            .circleCropTransform()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH))
                    .into(imageLogo);
            if (check){
                recyclerView.setVisibility(View.VISIBLE);
            }
            else {textMembers.setVisibility(View.VISIBLE);}
            RVUserClubInfoMemberAdapter adapter = new RVUserClubInfoMemberAdapter(this);
            recyclerView.setAdapter(adapter);
            layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            //если тренер
            boolean coach = true;
            if (coach){
                buttonEdit = (Button) findViewById(R.id.editClub);
                buttonEdit.setVisibility(View.VISIBLE);
                buttonEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(UserClubInfo.this, UserEditClub.class);
                        startActivity(intent1);
                    }
                });
            }
            else{
                buttonEscape = (Button) findViewById(R.id.userEscapeClub);
                buttonEscape.setVisibility(View.VISIBLE);
                buttonEscape.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //post
                    }
                });
            }

        }catch (Exception e){
            log.error("ERROR: ", e);
        }

    }
}
