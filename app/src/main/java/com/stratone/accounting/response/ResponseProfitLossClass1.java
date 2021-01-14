package com.stratone.accounting.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stratone.accounting.model.ProfitLossClass1;

import java.util.List;

public class ResponseProfitLossClass1 {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<ProfitLossClass1> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProfitLossClass1> getData() {
        return data;
    }

    public void setData(List<ProfitLossClass1> data) {
        this.data = data;
    }
}
