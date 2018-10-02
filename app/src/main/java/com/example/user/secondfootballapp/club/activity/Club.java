package com.example.user.secondfootballapp.club.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.club.adapter.RVClubMembersAdapter;

public class Club extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        AdvertisingFragment dialog;
        RecyclerView recyclerView;
        TextView textMembers;
        ImageView imageLogo;
        Boolean check = true;
        LinearLayoutManager layoutManager;
        view = inflater.inflate(R.layout.club_info, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewClubMembers);
        textMembers = (TextView) view.findViewById(R.id.clubInfoMembersStatus);
        imageLogo = (ImageView) view.findViewById(R.id.clubInfoLogo);
        dialog = new AdvertisingFragment();
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.ic_member)
                .apply(new RequestOptions()
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(imageLogo);
        dialog.show(getFragmentManager(), "adver2");
        //if club.members != null
        if (check){
            recyclerView.setVisibility(View.VISIBLE);
        }
        else {textMembers.setVisibility(View.VISIBLE);}
        RVClubMembersAdapter adapter = new RVClubMembersAdapter(this);
        recyclerView.setAdapter(adapter);
        layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
