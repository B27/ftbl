package com.example.user.secondfootballapp.user.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.players.activity.Player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static android.app.Activity.RESULT_OK;


public class UserPage extends Fragment {
    Logger log = LoggerFactory.getLogger(UserPage.class);
    final int REQUEST_CODE_REGISTRATION = 256;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        final EditText textLogin;
        final EditText textPass;
        TextView textReg;
        Button logIn;
        view = inflater.inflate(R.layout.page_user, container, false);
        textLogin = (EditText) view.findViewById(R.id.loginLog);
        textPass = (EditText) view.findViewById(R.id.passwordLog);
        logIn = (Button) view.findViewById(R.id.logInButton);
        textReg = (TextView) view.findViewById(R.id.registration);
        textLogin.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        textLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus){
//                    v.setBackground(getActivity().getDrawable(R.drawable.edittext_back_focus));
//                }
//                else {
//                    v.setBackground(getActivity().getDrawable(R.drawable.edittext_back));
//                }
                if (hasFocus){
                    textLogin.getBackground().clearColorFilter();
                }
                else {
                    textLogin.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                }
            }

        });
        textPass.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        textPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textPass.getBackground().clearColorFilter();
                }
                else {
                    textPass.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                }
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(((ViewGroup)getView().getParent()).getId(), new AuthoUser()).addToBackStack(null).commit();
            }
        });
        textReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), RegistrationUser.class);
//                getActivity().startActivity(intent);
                Intent intent = new Intent(getActivity(), RegistrationUser.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTRATION);
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // запишем в лог значения requestCode и resultCode
        // если пришло ОК
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_REGISTRATION){
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(((ViewGroup)getView().getParent()).getId(), new AuthoUser()).addToBackStack(null).commit();
//                int color = data.getIntExtra("color", Color.WHITE);
            }
        } else {
            log.error("ERROR: onActivityResult");
        }
    }

}
