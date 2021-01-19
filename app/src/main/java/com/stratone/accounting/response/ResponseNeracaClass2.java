package com.stratone.accounting.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stratone.accounting.model.NeracaClass3;
import com.stratone.accounting.model.ProfitLossClass3;

import java.util.List;

public class ResponseNeracaClass2 {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<NeracaClass3> data = null;

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

    public List<NeracaClass3> getData() {
        return data;
    }

    public void setData(List<NeracaClass3> data) {
        this.data = data;
    }
}
