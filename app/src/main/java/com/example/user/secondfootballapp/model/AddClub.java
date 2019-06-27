package com.example.user.secondfootballapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddClub implements Serializable {
    @SerializedName("dataClub")
    @Expose
    private DataClub dataClub;
//    private List<DataClub> dataList;

    public DataClub getData() {
        return dataClub;
    }
    public void setDataList(DataClub dataClub) {
        this.dataClub = dataClub;
    }
}
