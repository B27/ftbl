package com.example.user.secondfootballapp.user.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.CheckName;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.DateToString;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SetImage;
import com.example.user.secondfootballapp.TimeToString;
import com.example.user.secondfootballapp.model.ActiveMatch;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.GetLeagueInfo;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.LeagueInfo;
import com.example.user.secondfootballapp.model.Match;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.Player;
import com.example.user.secondfootballapp.model.Referee;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.user.activity.AuthoUser;
import com.example.user.secondfootballapp.user.activity.EditTimeTable;
import com.example.user.secondfootballapp.user.activity.ResponsiblePersons;
import com.example.user.secondfootballapp.user.activity.TimeTableFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import q.rorbin.badgeview.QBadgeView;

import static com.example.user.secondfootballapp.Controller.BASE_URL;

public class RVTimeTableAdapter extends RecyclerView.Adapter<RVTimeTableAdapter.ViewHolder> {
    TimeTableFragment context;
    PersonalActivity activity;
    private List<ActiveMatch> matches;
    private LeagueInfo leagueInfo;

    Logger log = LoggerFactory.getLogger(TimeTableFragment.class);
    public RVTimeTableAdapter(Activity activity, TimeTableFragment context, List<ActiveMatch> matches) {
        this.matches = matches;
        this.context = context;
        this.activity = (PersonalActivity) activity;
    }

    @NonNull
    @Override
    public RVTimeTableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable, parent, false);
        RVTimeTableAdapter.ViewHolder holder = new RVTimeTableAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVTimeTableAdapter.ViewHolder holder, int position) {
        ActiveMatch match = matches.get(position);
        String str;
        str = match.getDate();
        DateToString dateToString = new DateToString();
        holder.textDate.setText(dateToString.ChangeDate(str));
        try {
            TimeToString timeToString = new TimeToString();
            holder.textTime.setText(timeToString.ChangeTime(str));
        } catch (NullPointerException e) {
            holder.textTime.setText(str);
        }
        str = match.getPlace();
        try {
            String[] stadium;
            stadium = str.split(":", 1);
            holder.textStadium.setText(stadium[0]);
        } catch (NullPointerException e) {
            holder.textStadium.setText(str);
        }

        str = match.getTour();
        holder.textLeague.setText(str);
        SetImage setImage = new SetImage();
        Team team1 = match.getTeamOne();
        Team team2 = match.getTeamTwo();
                str = team1.getName();
                holder.textCommandTitle1.setText(str);
                for (Club club : PersonalActivity.allClubs) {
                    if (club.getId().equals(team1.getClub())) {
                        setImage.setImage(activity, holder.image1, club.getLogo());
                    }
                }
                for (Player player : team1.getPlayers()) {
                    if (player.getActiveDisquals() != 0) {
                        new QBadgeView(activity)
                                .bindTarget(holder.image1)
                                .setBadgeBackground(activity.getDrawable(R.drawable.ic_circle))
                                .setBadgeTextColor(activity.getResources().getColor(R.color.colorBadge))
                                .setBadgeTextSize(5, true)
                                .setBadgePadding(5, true)
                                .setBadgeGravity(Gravity.END | Gravity.BOTTOM)
                                .setGravityOffset(-3, 1, true)
                                .setBadgeNumber(3);
                    }
                }

                str = team2.getName();
                holder.textCommandTitle2.setText(str);
                for (Club club : PersonalActivity.allClubs) {
                    if (club.getId().equals(team2.getClub())) {
                        setImage.setImage(activity, holder.image2, club.getLogo());
                    }
                }
                for (Player player : team2.getPlayers()) {
                    if (player.getActiveDisquals() != 0) {
                        new QBadgeView(activity)
                                .bindTarget(holder.image2)
                                .setBadgeBackground(activity.getDrawable(R.drawable.ic_circle))
                                .setBadgeTextColor(activity.getResources().getColor(R.color.colorBadge))
                                .setBadgeTextSize(5, true)
                                .setBadgePadding(5, true)
                                .setBadgeGravity(Gravity.END | Gravity.BOTTOM)
                                .setGravityOffset(-3, 1, true)
                                .setBadgeNumber(3);
                    }
                }


        try {
            str = match.getScore();
            if (str.equals("")) {
                str = "-";
            }
        } catch (NullPointerException e) {
            str = "-";
        }
        holder.textScore.setText(str);
        if (!str.equals("-")) {
            List<ActiveMatch> list = new ArrayList<>(matches);
            list.remove(match);
            for (ActiveMatch match1 : list) {
                try {
                    str = match1.getScore();
                    if (!str.equals("")
                            && match1.getTeamOne().equals(match.getTeamOne())
                            && match1.getTeamOne().equals(match.getTeamTwo())) {
                        str = match1.getScore();
                        holder.textLastScore.setVisibility(View.VISIBLE);
                        holder.textLastScore.setText(str);
                    }
                    if (!str.equals("")
                            && match1.getTeamOne().equals(match.getTeamTwo())
                            && match1.getTeamOne().equals(match.getTeamOne())) {
                        str = match1.getScore();
                        String[] strArray = str.split(":");
                        str = strArray[1] + ":" + strArray[0];
                        holder.textLastScore.setVisibility(View.VISIBLE);
                        holder.textLastScore.setText(str);
                    }
                } catch (Exception e) {
                }

            }
        }
        try {
            str = match.getPenalty();
            if (!str.equals("")) {
                holder.textPenalty.setText(str);
                holder.textPenalty.setVisibility(View.VISIBLE);
            }
        } catch (NullPointerException e) {
        }
        holder.buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(activity, EditTimeTable.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("MATCHCONFIRMPROTOCOLREFEREES", match);
//            log.error(String.valueOf(match.getReferees().size()));
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
        List<Referee> referees = match.getReferees();
        CheckName checkName = new CheckName();

//        if (referees.size()!=0){}else {
        str = "Не назначен";
            holder.textReferee1.setText(str);
            holder.textReferee1.setTextColor(ContextCompat.getColor(activity, R.color.colorBadge));
            holder.textReferee2.setText(str);
            holder.textReferee2.setTextColor(ContextCompat.getColor(activity, R.color.colorBadge));
            holder.textReferee3.setText(str);
            holder.textReferee3.setTextColor(ContextCompat.getColor(activity, R.color.colorBadge));
            holder.textReferee4.setText(str);
            holder.textReferee4.setTextColor(ContextCompat.getColor(activity, R.color.colorBadge));

        try{
            for (Referee referee : referees) {
                log.error(String.valueOf(match.getReferees().size()));
                for (Person person : AuthoUser.allReferees) {
                    if (referee.getPerson().equals(person.getId())) {
                        switch (referee.getType()) {
                            case "1 судья":
                                str = checkName.check(person.getSurname(), person.getName(), person.getLastname());
                                holder.textReferee1.setText(str);
                                holder.textReferee1.setTextColor(ContextCompat.getColor(activity, R.color.colorBottomNavigationUnChecked));
                                break;
                            case "2 судья":
                                str = checkName.check(person.getSurname(), person.getName(), person.getLastname());
                                holder.textReferee2.setText(str);
                                holder.textReferee2.setTextColor(ContextCompat.getColor(activity, R.color.colorBottomNavigationUnChecked));
                                break;
                            case "3 судья":
                                str = checkName.check(person.getSurname(), person.getName(), person.getLastname());
                                holder.textReferee3.setText(str);
                                holder.textReferee3.setTextColor(ContextCompat.getColor(activity, R.color.colorBottomNavigationUnChecked));
                                break;
                            case "Хронометрист":
                                str = checkName.check(person.getSurname(), person.getName(), person.getLastname());
                                holder.textReferee4.setText(str);
                                holder.textReferee4.setTextColor(ContextCompat.getColor(activity, R.color.colorBottomNavigationUnChecked));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }catch (Exception e){
            log.error("ERROR: ", e);
        }
        if (position == (matches.size() - 1)) {
            holder.line.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTime;
        TextView textLeague;
        TextView textLastScore;
        TextView textPenalty;
        TextView textStadium;
        TextView textCommandTitle1;
        TextView textCommandTitle2;
        TextView textScore;
        ImageView image1;
        ImageView image2;
        TextView textReferee1;
        TextView textReferee2;
        TextView textReferee3;
        TextView textReferee4;
        LinearLayout buttonEdit;
        View line;

        public ViewHolder(View item) {
            super(item);
            textDate = item.findViewById(R.id.timetableMatchDate);
            textTime = item.findViewById(R.id.timetableMatchTime);
            textLeague = item.findViewById(R.id.timetableMatchLeague);
            textStadium = item.findViewById(R.id.timetableMatchStadium);
            textLastScore = item.findViewById(R.id.timetableMatchLastScore);
            textPenalty = item.findViewById(R.id.timetableMatchPenalty);
            textCommandTitle1 = item.findViewById(R.id.timetableMatchCommandTitle1);
            textCommandTitle2 = item.findViewById(R.id.timetableMatchCommandTitle2);
            image1 = item.findViewById(R.id.timetableMatchCommandLogo1);
            image2 = item.findViewById(R.id.timetableMatchCommandLogo2);
            textReferee1 = item.findViewById(R.id.timetableMatchReferee1);
            textReferee2 = item.findViewById(R.id.timetableMatchReferee2);
            textReferee3 = item.findViewById(R.id.timetableMatchReferee3);
            textReferee4 = item.findViewById(R.id.timetableMatchReferee4);
            buttonEdit = item.findViewById(R.id.timetableMatchEdit);
            //may be null
            textScore = item.findViewById(R.id.timetableMatchScore);
            line = item.findViewById(R.id.timetableMatchLine);
        }
    }

    public void dataChanged(List<ActiveMatch> allPlayers1) {
        matches.clear();
        matches.addAll(allPlayers1);
        notifyDataSetChanged();
    }




}
