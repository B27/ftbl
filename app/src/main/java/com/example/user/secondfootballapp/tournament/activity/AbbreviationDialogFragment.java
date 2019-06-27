package com.example.user.secondfootballapp.tournament.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.user.secondfootballapp.R;


public class AbbreviationDialogFragment extends DialogFragment{
    public static AbbreviationDialogFragment newInstance() {
        AbbreviationDialogFragment f = new AbbreviationDialogFragment();
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.abbreviation_dialog_fragment, null);
        Button button = view.findViewById(R.id.abbreviationCloseButton);
        button.setOnClickListener(v -> getDialog().dismiss());
        builder.setView(view);
        return builder.create();
    }
}
