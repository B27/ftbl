package com.example.user.secondfootballapp.model;

import com.google.gson.annotations.SerializedName;

public class ErrorMessage {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
