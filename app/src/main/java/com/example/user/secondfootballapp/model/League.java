package com.example.user.secondfootballapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class League {

    @SerializedName("matches")
    @Expose
    private List<Object> matches = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tourney")
    @Expose
    private String tourney;
    @SerializedName("beginDate")
    @Expose
    private String beginDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("maxTeams")
    @Expose
    private Integer maxTeams;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;
    @SerializedName("players")
    @Expose
    private List<Player> players = null;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("document")
    @Expose
    private String document;

    public List<Object> getMatches() {
        return matches;
    }

    public void setMatches(List<Object> matches) {
        this.matches = matches;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTourney() {
        return tourney;
    }

    public void setTourney(String tourney) {
        this.tourney = tourney;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}