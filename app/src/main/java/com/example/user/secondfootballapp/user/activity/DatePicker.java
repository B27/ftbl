package com.example.user.secondfootballapp.user.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.secondfootballapp.AdvertisingFragment;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.ycuwq.datepicker.date.DatePickerDialogFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DatePicker extends DatePickerDialogFragment {
//public class DatePicker extends DialogFragment {

    Logger log = LoggerFactory.getLogger(PersonalActivity.class);
    public static DatePicker newInstance() {
        DatePicker f = new DatePicker();
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.date_picker, null);
        final com.ycuwq.datepicker.date.DatePicker datePicker = (com.ycuwq.datepicker.date.DatePicker) view.findViewById(R.id.datePicker);
        HashMap<String, Integer> months = new HashMap<String, Integer>();
        months.put("янв.", 1);
        months.put("февр.", 2);
        months.put("марта", 3);
        months.put("апр.", 4);
        months.put("мая", 5);
        months.put("июня", 6);
        months.put("июля", 7);
        months.put("авг.", 8);
        months.put("сент.", 9);
        months.put("окт.", 10);
        months.put("нояб.", 11);
        months.put("дек.", 12);

        String dateDOB;
        try{
            dateDOB = UserInfo.buttonDOB.getText().toString();
        }catch (Exception e){
            dateDOB = PersonalInfo.textDOB.getText().toString();
        }
        String[] date = dateDOB.split(" ");
        try {
            int month =  months.get(date[1]);
            datePicker.setDate(Integer.parseInt(date[2]), month,Integer.parseInt(date[0]));
        }
        catch (Exception e){
        }

//        datePicker.setDate(Integer.parseInt(date[2]), months.get("дек."),Integer.parseInt(date[0]));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

//        datePicker.setMaxDate(new Date().getTime());
//        Date maxDate = new Date();
        Date maxDate = cal.getTime();
//        datePicker.setMaxDate(new Date().getTime());
        datePicker.setMaxDate(maxDate.getTime());
        cal.set(Calendar.YEAR, 1940);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date minDate = cal.getTime();
        datePicker.setMinDate(minDate.getTime());

        datePicker.setOnDateSelectedListener(new com.ycuwq.datepicker.date.DatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day) {
                int e = year;
                String str = Integer.toString(e);
//                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });


        mCancelButton = (Button) view.findViewById(R.id.datePickerClose);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mDecideButton = (Button) view.findViewById(R.id.datePickerOk);
        mDecideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = datePicker.getDate();
//                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                try{
                    UserInfo.buttonDOB.setText(str);
                }catch (Exception e){
                    PersonalInfo.textDOB.setText(str);
                }

                dismiss();
            }
        });



        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }

//    @Override
//    protected void initChild() {
//        super.initChild();
//        mDatePicker.setMaxDate(new Date().getTime());
////        mCancelButton.setTextSize(mCancelButton.getTextSize() + 5);
//        mCancelButton.setText("закрыть");
////        mDecideButton.setTextSize(mDecideButton.getTextSize() + 5);
//        mDecideButton.setText("ок");
//        mDatePicker.setShowCurtain(false);
//    }
}
