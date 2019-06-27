package com.example.user.secondfootballapp.user.adapter;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.Event;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.PlayerEvent;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;
import com.example.user.secondfootballapp.user.activity.AddEvent;
import com.example.user.secondfootballapp.user.activity.AuthoUser;
import com.example.user.secondfootballapp.user.activity.ProtocolEdit;
import com.example.user.secondfootballapp.user.activity.ProtocolEventsEdit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.user.secondfootballapp.Controller.BASE_URL;

public class RVProtocolEditAdapter extends RecyclerView.Adapter<RVProtocolEditAdapter.ViewHolder> {
    Logger log = LoggerFactory.getLogger(ProtocolEdit.class);
    private ProtocolEventsEdit context;
    private List<PlayerEvent> playerEvents;
    private ListAdapterListener mListener;

    //    private List<Event> events;
//    private Match match;
//    public RVProtocolEditAdapter(Activity context, List<Event> events, Match match){
    public RVProtocolEditAdapter(Activity context, List<PlayerEvent> playerEvents, ListAdapterListener mListener) {
        this.context = (ProtocolEventsEdit) context;
        this.playerEvents = playerEvents;
        this.mListener = mListener;
//        this.events = events;
//        this.match = match;
    }


    public interface ListAdapterListener {
        void onClickSwitch(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.protocol, parent, false);
        RVProtocolEditAdapter.ViewHolder holder = new RVProtocolEditAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.optionalCircleCrop();
        requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
        RequestOptions.errorOf(R.drawable.ic_logo2);
        requestOptions.override(500, 500);
        requestOptions.priority(Priority.HIGH);
        String uriPic = BASE_URL;
//        events.get(position).getPlayer();
//        Event event = events.get(position);
//        Person person = null;
//        for (Person person1 : PersonalActivity.AllPeople){
//            if (person1.getId().equals(event.getPlayer())){
//                person = person1;
//            }
//        }
//        Club club = null;
//        for (League league: PersonalActivity.tournaments){
//            if (league.getId().equals(match.getLeague())){
//                for (Team team :league.getTeams()){
//                    if (team.getId().equals(match.getTeamTwo())
//                            || team.getId().equals(match.getTeamOne())){
//                        if (team.getPlayers().contains(person)){
//                            for (Club club1 :PersonalActivity.allClubs){
//                                if (club.getId().equals(team.getClub())){
//                                    club = club1;
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

//        try {
//            if (club.getLogo() != null) {
//                uriPic += "/" + club.getLogo();
//                URL url = new URL(uriPic);
//                Glide.with(context)
//                        .asBitmap()
//                        .load(url)
////                    .load(R.drawable.ic_fin)
//                        .apply(requestOptions)
//                        .into(holder.image);
//            } else {
//                Glide.with(context)
//                        .asBitmap()
//                        .load(R.drawable.ic_logo2)
//                        .apply(requestOptions)
//                        .into(holder.image);
//            }
//        } catch (Exception e) {
//            Glide.with(context)
//                    .asBitmap()
//                    .load(R.drawable.ic_logo2)
//                    .apply(requestOptions)
//                    .into(holder.image);
//        }
        PlayerEvent playerEvent = playerEvents.get(position);
        try {
            if (playerEvent.getClubLogo() != null) {
                uriPic += "/" + playerEvent.getClubLogo();
                URL url = new URL(uriPic);
                Glide.with(context)
                        .asBitmap()
                        .load(url)
//                    .load(R.drawable.ic_fin)
                        .apply(requestOptions)
                        .into(holder.image);
            } else {
                Glide.with(context)
                        .asBitmap()
                        .load(R.drawable.ic_logo2)
                        .apply(requestOptions)
                        .into(holder.image);
            }
        } catch (Exception e) {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_logo2)
                    .apply(requestOptions)
                    .into(holder.image);
        }
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //post
//                int newPosition = holder.getAdapterPosition();
//                notifyItemRemoved(newPosition);
                mListener.onClickSwitch(position);
            }
        });
//        holder.text.setText(event.getEventType());
        String str;
        str = playerEvent.getEvent().getEventType();
        switch (str) {
            case "goal":
                str = "Гол";
                break;
            case "autoGoal":
                str = "Автогол";
                break;
            case "yellowCard":
                str = "ЖК";
                break;
            case "redCard":
                str = "КК";
                break;
            case "penalty":
                str = "Пенальти";
                break;
            case "foul":
                str = "Фол";
                break;
            default:
                break;
        }
        holder.text.setText(str);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        if (position==(events.size()-1)){
        if (position == (playerEvents.size() - 1)) {
            holder.line.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
//        return events.size();
        return playerEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        ImageButton buttonDelete;
        View line;
        LinearLayout layout;

        public ViewHolder(View item) {
            super(item);
            text = item.findViewById(R.id.protocolEvent);
            image = item.findViewById(R.id.protocolEventCommandLogo);
            buttonDelete = item.findViewById(R.id.protocolEventDelete);
            line = item.findViewById(R.id.protocolLine);
            layout = item.findViewById(R.id.protocolLayout);
        }
    }

    public void dataChanged(List<PlayerEvent> allPlayers1) {
        playerEvents.clear();
        playerEvents.addAll(allPlayers1);
        notifyDataSetChanged();
    }
}
