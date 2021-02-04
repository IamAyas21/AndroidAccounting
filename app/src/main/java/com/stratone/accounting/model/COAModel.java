package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class COAModel {
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Acc")
    @Expose
    private String acc;
    @SerializedName("Data")
    @Expose
    private List<COAModel> data = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public List<COAModel> getData() {
        return data;
    }

    public void setData(List<COAModel> data) {
        data = data;
    }
}
