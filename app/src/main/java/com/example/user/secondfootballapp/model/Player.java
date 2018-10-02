package com.example.user.secondfootballapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("cards")
    @Expose
    private List<Object> cards = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("playerId")
    @Expose
    private Object playerId;

    public List<Object> getCards() {
        return cards;
    }

    public void setCards(List<Object> cards) {
        this.cards = cards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Object playerId) {
        this.playerId = playerId;
    }

}