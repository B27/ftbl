package com.example.user.secondfootballapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Club {

    @SerializedName("teams")
    @Expose
    private List<ClubTeam> teams = null;
    @SerializedName("addLogo")
    @Expose
    private String addLogo;
    @SerializedName("addInfo")
    @Expose
    private String addInfo;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<ClubTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<ClubTeam> teams) {
        this.teams = teams;
    }

    public String getAddLogo() {
        return addLogo;
    }

    public void setAddLogo(String addLogo) {
        this.addLogo = addLogo;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
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

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}