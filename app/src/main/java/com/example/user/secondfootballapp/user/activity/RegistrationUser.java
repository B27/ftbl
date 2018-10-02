package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;

public class RegistrationUser extends AppCompatActivity {
    Fragment authoUser = new AuthoUser();
    public static ImageButton imageSave;
    PhoneVerification phoneVerification = new PhoneVerification();
    PersonalInfo personalInfo = new PersonalInfo();
    FragmentManager fragmentManager = this.getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton imageClose;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_user);
        imageClose = (ImageButton) findViewById(R.id.registrationClose);
        imageSave = (ImageButton) findViewById(R.id.registrationSave);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.pageContainer, authoUser).addToBackStack(null).commit();

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("STARTACTIVITY", "forResult");
                intent.putExtra("REGISTRATION", bundle);
                setResult(RESULT_OK, intent);
//                FragmentManager fragmentManager = getSupportFragmentManager();
//
//                fragmentManager.beginTransaction().replace(R.id.pageContainer, authoUser).addToBackStack(null).commit();


//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.pageContainer, authoUser)
//                        .show(authoUser)
//                        .commit();
                finish();
                //post
            }
        });
        fragmentManager.beginTransaction().add(R.id.registrationViewPager, phoneVerification, "phoneVerification").commit();
        fragmentManager.beginTransaction().add(R.id.registrationViewPager, personalInfo, "personalInfo").hide(personalInfo).commit();
    }
}
