package com.example.user.secondfootballapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tournaments {

    @SerializedName("leagues")
    @Expose
    private List<League> leagues = null;

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

}