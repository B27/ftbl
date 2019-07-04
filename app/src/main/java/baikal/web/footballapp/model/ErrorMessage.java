package baikal.web.footballapp.model;

import com.google.gson.annotations.SerializedName;

class ErrorMessage {
    @SerializedName("message")
    private
    String message;

    public String getMessage() {
        return message;
    }
}
