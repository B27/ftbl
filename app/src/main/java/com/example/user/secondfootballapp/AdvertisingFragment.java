package com.example.user.secondfootballapp;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvertisingFragment extends DialogFragment{
    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    public static AdvertisingFragment newInstance() {
        AdvertisingFragment f = new AdvertisingFragment();
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
//                .setMessage(R.string.dialog_fire_missiles)
//                .setNegativeButton(R.string.advertisingOk, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // cancelled the dialog
//                        if (dialog != null) {
//                            dialog.dismiss();
//                        }
//                    }
//                });

        View view = getActivity().getLayoutInflater().inflate(R.layout.adver, null);
//        View view = getActivity().getLayoutInflater().inflate(R.layout.advertising, null);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.advertisingCloseButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.info("INFO: hello from Advertising");
                getDialog().dismiss();
//                dialog.dismiss();
            }
        });
        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }


}
