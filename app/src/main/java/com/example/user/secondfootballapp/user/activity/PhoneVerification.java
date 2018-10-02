package com.example.user.secondfootballapp.user.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.user.secondfootballapp.R;

public class PhoneVerification extends Fragment {
    PersonalInfo personalInfo = new PersonalInfo();
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        EditText textPhone;
        EditText textCode;
        final Button buttonSend;
        Button buttonCheck;
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final LinearLayout linearLayout;
        view = inflater.inflate(R.layout.phone_verification, container, false);
        textPhone = (EditText) view.findViewById(R.id.registrationPhone);
        textCode = (EditText) view.findViewById(R.id.registrationCode);
        buttonSend = (Button) view.findViewById(R.id.registrationButtonSendCode);
        buttonCheck = (Button) view.findViewById(R.id.registrationButtonCheckCode);
        linearLayout = (LinearLayout) view.findViewById(R.id.registrationLayout);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                buttonSend.setVisibility(View.GONE);
            }
        });
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.registrationViewPager, personalInfo).show(personalInfo).commit();
                RegistrationUser.imageSave.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}
