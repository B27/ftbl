package com.example.user.secondfootballapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User_ {

    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("club")
    @Expose
    private Object club;
    @SerializedName("pendingClubInvites")
    @Expose
    private List<Object> pendingClubInvites = null;
    @SerializedName("pendingTeamInvites")
    @Expose
    private List<Object> pendingTeamInvites = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getClub() {
        return club;
    }

    public void setClub(Object club) {
        this.club = club;
    }

    public List<Object> getPendingClubInvites() {
        return pendingClubInvites;
    }

    public void setPendingClubInvites(List<Object> pendingClubInvites) {
        this.pendingClubInvites = pendingClubInvites;
    }

    public List<Object> getPendingTeamInvites() {
        return pendingTeamInvites;
    }

    public void setPendingTeamInvites(List<Object> pendingTeamInvites) {
        this.pendingTeamInvites = pendingTeamInvites;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}