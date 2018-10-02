package com.example.user.secondfootballapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("place")
    @Expose
    private Object place;
    @SerializedName("group")
    @Expose
    private Object group;
    @SerializedName("goals")
    @Expose
    private Object goals;
    @SerializedName("goalsReceived")
    @Expose
    private Object goalsReceived;
    @SerializedName("groupScore")
    @Expose
    private Object groupScore;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("team")
    @Expose
    private String team;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPlace() {
        return place;
    }

    public void setPlace(Object place) {
        this.place = place;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public Object getGoals() {
        return goals;
    }

    public void setGoals(Object goals) {
        this.goals = goals;
    }

    public Object getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(Object goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public Object getGroupScore() {
        return groupScore;
    }

    public void setGroupScore(Object groupScore) {
        this.groupScore = groupScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

}
