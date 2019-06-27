package com.example.user.secondfootballapp.user.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.PendingTeamInvite;
import com.example.user.secondfootballapp.user.adapter.RVInvitationAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InvitationFragment extends Fragment {
    Logger log = LoggerFactory.getLogger(InvitationFragment.class);
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        RecyclerView recyclerView;
        LinearLayout linearEmpty;
        LinearLayout linearNotEmpty;
        view = inflater.inflate(R.layout.user_invitations, container, false);
        linearEmpty = view.findViewById(R.id.emptyInv);
        linearNotEmpty = view.findViewById(R.id.notEmptyInv);
        recyclerView = view.findViewById(R.id.recyclerViewUserInvitation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        try{
        recyclerView.setAdapter(AuthoUser.adapterInv);
        if (AuthoUser.pendingTeamInvitesList.size()!=0){
            linearEmpty.setVisibility(View.GONE);
        }
        else {
            linearNotEmpty.setVisibility(View.GONE);
        }
        }catch (NullPointerException e){}
        return view;
    }

    @Override
    public void onDestroy() {
        log.info("INFO: InvitationFragment onDestroy");
        super.onDestroy();
    }
}
