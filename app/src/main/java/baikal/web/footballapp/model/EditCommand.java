package baikal.web.footballapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditCommand {
    @SerializedName("_id")
    private
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("teamId")
    private
    String teamId;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @SerializedName("players")
    private
    List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}