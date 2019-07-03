package com.example.user.secondfootballapp.tournament.activity;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.example.user.secondfootballapp.R;


public class CommandAbbrev extends DialogFragment{
    public static CommandAbbrev newInstance() {
        CommandAbbrev f = new CommandAbbrev();
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.abbrev_comand, null);
        Button button = view.findViewById(R.id.commandAbbrevCloseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}
