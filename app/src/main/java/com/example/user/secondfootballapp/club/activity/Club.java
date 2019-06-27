package com.example.user.secondfootballapp.club.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.AdvertisingFragment;
import com.example.user.secondfootballapp.CheckName;
import com.example.user.secondfootballapp.FullScreenImage;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.Owner;

import java.net.URL;

import static com.example.user.secondfootballapp.Controller.BASE_URL;

public class Club extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
//        AdvertisingFragment dialog;
        TextView textTitle;
        TextView textInfo;
        TextView textCoach;
        ImageView imageLogo;
        view = inflater.inflate(R.layout.club_info, container, false);
        textTitle = view.findViewById(R.id.clubInfoTitle);
        textInfo = view.findViewById(R.id.clubInfoDesc);
        textCoach = view.findViewById(R.id.clubInfoCoach);
        imageLogo = view.findViewById(R.id.clubInfoLogo);
//        dialog = new AdvertisingFragment();
        try{
            Bundle bundle = this.getArguments();
            com.example.user.secondfootballapp.model.Club club =
                    (com.example.user.secondfootballapp.model.Club) bundle.getSerializable("CLUBINFO");
            String uriPic = BASE_URL;

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.optionalCircleCrop();
            requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
            requestOptions.error(R.drawable.ic_logo2);
            requestOptions.override(500, 500);
            requestOptions.priority(Priority.HIGH);
            try {
                uriPic += "/" + club.getLogo();
                URL url = new URL(uriPic);
                Glide.with(this)
                        .asBitmap()
                        .load(url)
                        .apply(requestOptions)
                        .into(imageLogo);
                final String finalUriPic = uriPic;
                imageLogo.setOnClickListener(v -> {
                    if (finalUriPic.contains(".jpg") || finalUriPic.contains(".jpeg") || finalUriPic.contains(".png")) {
                        Intent intent = new Intent(getActivity(), FullScreenImage.class);
                        intent.putExtra("player_photo", finalUriPic);
                        getActivity().startActivity(intent);
                    }
                });
            } catch (Exception e) {
                Glide.with(this)
                        .asBitmap()
                        .load(R.drawable.ic_logo2)
                        .apply(requestOptions)
                        .into(imageLogo);
            }

            String str = club.getName();
            textTitle.setText(str);
            str = club.getInfo();
            textInfo.setText(str);

            Owner person = club.getOwner();

            CheckName checkName = new CheckName();
            str = checkName.check(person.getSurname(), person.getName(), person.getLastname());

            textCoach.setText(str);
        }catch (Exception e){}


//        dialog.show(getFragmentManager(), "adver2");

        return view;
    }
}
