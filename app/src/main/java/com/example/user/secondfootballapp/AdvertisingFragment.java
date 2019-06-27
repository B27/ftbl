package com.example.user.secondfootballapp;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.model.Ad;
import com.example.user.secondfootballapp.model.News;
import com.example.user.secondfootballapp.model.News_;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;

import static com.example.user.secondfootballapp.Controller.BASE_URL;

public class AdvertisingFragment extends DialogFragment{
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView textTitle;
        ImageView image;
        View view = getActivity().getLayoutInflater().inflate(R.layout.adver, null);
        textTitle = view.findViewById(R.id.advertisingText);
        image = view.findViewById(R.id.advertisingImg);
        List<Ad> list = (List<Ad>) getArguments().getSerializable("ADVERTISING");
        Ad news = list.get(0);
        textTitle.setText(news.getCaption());
        String uriPic = BASE_URL;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
        RequestOptions.errorOf(R.drawable.ic_logo);
        requestOptions.fitCenter();
        requestOptions.priority(Priority.HIGH);
        try {
            if (news.getImg() != null) {
                uriPic += "/" + news.getImg();
                URL url = new URL(uriPic);
                Glide.with(getActivity())
                        .asBitmap()
                        .load(url)
                        .apply(requestOptions)
                        .into(image);
            } else {
                Glide.with(getActivity())
                        .asBitmap()
                        .load(R.drawable.ic_logo)
                        .apply(requestOptions)
                        .into(image);
            }
        } catch (Exception e) {
            Glide.with(getActivity())
                    .asBitmap()
                    .load(R.drawable.ic_logo)
                    .apply(requestOptions)
                    .into(image);
        }
        ImageButton imageButton = view.findViewById(R.id.advertisingCloseButton);
        imageButton.setOnClickListener(v -> {
            getDialog().dismiss();
        });
        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }


}
