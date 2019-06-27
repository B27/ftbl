package com.example.user.secondfootballapp.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SaveSharedPreference;
import com.example.user.secondfootballapp.home.activity.NewsPage;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.user.adapter.RVUserClubInvAdapter;
import com.example.user.secondfootballapp.user.adapter.RVUserCommandAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static com.example.user.secondfootballapp.Controller.BASE_URL;

public class UserClubs extends Fragment {
    Logger log = LoggerFactory.getLogger(UserClubs.class);
    String uriPic;
    FloatingActionButton fab;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fab = getActivity().findViewById(R.id.buttonEditClub);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        TextView textDesc;
        TextView textTitle;
        ImageView imageLogo;
        Club club;
        LinearLayout linearLayout;
        LinearLayout linearLayoutClubInfo;
        view = inflater.inflate(R.layout.user_clubs, container, false);
        linearLayout = view.findViewById(R.id.emptyClub);
        linearLayoutClubInfo = view.findViewById(R.id.userClub);
        textDesc = view.findViewById(R.id.userClubInfoDesc);
        textTitle = view.findViewById(R.id.userClubInfoTitle);
        imageLogo = view.findViewById(R.id.userClubInfoLogo);
        club = null;
        try {
            Person person = SaveSharedPreference.getObject().getUser();
            String str = person.getClub();
            for (Club club1 : PersonalActivity.allClubs) {
                if (club1.getId().equals(str)) {
                    club = club1;
                }
            }

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.optionalCircleCrop();
            requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
            RequestOptions.errorOf(R.drawable.ic_logo2);
            requestOptions.override(500, 500); // resizing
            requestOptions.priority(Priority.HIGH);

            if (club != null) {
                linearLayout.setVisibility(View.GONE);
                str = club.getName();
                textTitle.setText(str);
                str = club.getInfo();
                textDesc.setText(str);
                uriPic = BASE_URL;

                try {
                    uriPic += "/" + club.getLogo();
                    URL url = new URL(uriPic);
                    Glide.with(this)
                            .asBitmap()
                            .load(url)
                            .apply(requestOptions)
                            .into(imageLogo);
                } catch (Exception e) {
                    Glide.with(this)
                            .asBitmap()
                            .load(R.drawable.ic_logo2)
                            .apply(requestOptions)
                            .into(imageLogo);
                }
            } else {
                linearLayoutClubInfo.setVisibility(View.GONE);
            }
        } catch (NullPointerException e) {
        }

        return view;
    }

    @Override
    public void onDestroy() {
        log.info("INFO: UserClubs onDestroy");
        fab.setVisibility(View.INVISIBLE);
        super.onDestroy();
    }
}
