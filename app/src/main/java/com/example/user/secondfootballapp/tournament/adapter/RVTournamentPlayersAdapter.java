package com.example.user.secondfootballapp.tournament.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.secondfootballapp.CheckName;
import com.example.user.secondfootballapp.PersonalActivity;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SetImage;
import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.Player;
import com.example.user.secondfootballapp.tournament.activity.TournamentPlayersFragment;

import java.util.List;


public class RVTournamentPlayersAdapter extends RecyclerView.Adapter<RVTournamentPlayersAdapter.ViewHolder>{
    private TournamentPlayersFragment context;
    private List<Player> players;
    private List<String> clubs;
    public RVTournamentPlayersAdapter(TournamentPlayersFragment context, List<Player> players, List<String> clubs){
        this.context = context;
        this.players = players;
        this.clubs = clubs;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tournament_player, parent, false);
        RVTournamentPlayersAdapter.ViewHolder holder = new RVTournamentPlayersAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person player = null;
        for (Person person : PersonalActivity.AllPeople){
            if (person.getId().equals(players.get(position).getPlayerId())){
                player = person;
                break;
            }
        }
        String str;
        CheckName checkName = new CheckName();

        try {
            if (player==null){
                player = new Person();
                player.setName("Удален");
                player.setSurname("");
                player.setLastname("");
            }
            str = checkName.check(player.getSurname(), player.getName(), player.getLastname());
            holder.textName.setText(str);
            int count = players.get(position).getMatches();
            str = String.valueOf(count);
            holder.textPoint1.setText(str);
            count = players.get(position).getGoals();
            str = String.valueOf(count);
            holder.textPoint2.setText(str);
            count = players.get(position).getYellowCards();
//            count = players.get(position).getActiveYellowCards();
            str = String.valueOf(count);
            holder.textPoint3.setText(str);
//            count = players.get(position).getActiveDisquals();
            count = players.get(position).getRedCards();
            str = String.valueOf(count);
            holder.textPoint4.setText(str);
        }catch (NullPointerException e){
            int count = position +1;
            str = String.valueOf(count);
            holder.textPoint1.setText(str);
            str = String.valueOf(count);
            holder.textPoint2.setText(str);
            str = String.valueOf(count);
            holder.textPoint3.setText(str);
            str = String.valueOf(count);
            holder.textPoint4.setText(str);
        }
        Club club = null;
        for (Club club1 : PersonalActivity.allClubs){
            try{
                if (club1.getId().equals(clubs.get(position))){
                    club = club1;
                }
            }catch (IndexOutOfBoundsException e){break;}
        }
        SetImage setImage = new SetImage();
        assert club != null;
        try {
            setImage.setImage(holder.image.getContext(), holder.image, club.getLogo());
        }catch (NullPointerException e){}


    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textName;
        TextView textPoint1;
        TextView textPoint2;
        TextView textPoint3;
        TextView textPoint4;
        ImageView image;
//        TextView textPoint5;
        public ViewHolder(View item) {
            super(item);
            textName = item.findViewById(R.id.tournamentPlayer);
            textPoint1 = item.findViewById(R.id.tournamentPlayerPoint1);
            textPoint2 = item.findViewById(R.id.tournamentPlayerPoint2);
            textPoint3 = item.findViewById(R.id.tournamentPlayerPoint3);
            textPoint4 = item.findViewById(R.id.tournamentPlayerPoint4);
            image = item.findViewById(R.id.tournamentPlayerCommandLogo);
//            textPoint5 = (TextView) item.findViewById(R.id.tournamentPlayerPoint5);
        }
    }
    public void dataChanged(List<Player> allPlayers1){
        players.clear();
        players.addAll(allPlayers1);
        notifyDataSetChanged();
    }
}
