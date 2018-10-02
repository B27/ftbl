package com.example.user.secondfootballapp.user.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;

public class Abbrev extends DialogFragment {
    public static Abbrev newInstance() {
        Abbrev f = new Abbrev();
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.abbrev, null);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.abbrevCloseButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}