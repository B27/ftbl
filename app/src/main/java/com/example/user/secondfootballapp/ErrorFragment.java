package com.example.user.secondfootballapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorFragment extends DialogFragment {
    Logger log = LoggerFactory.getLogger(AdvertisingFragment.class);
    public static ErrorFragment newInstance() {
        ErrorFragment f = new ErrorFragment();
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder

        View view = getActivity().getLayoutInflater().inflate(R.layout.error, null);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.errorCloseButton);
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
