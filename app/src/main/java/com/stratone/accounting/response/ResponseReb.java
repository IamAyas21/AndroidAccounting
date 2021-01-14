package com.stratone.accounting.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.model.RebModel;

import java.util.List;

public class ResponseReb {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private RebModel data = null;

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

    public RebModel getData() {
        return data;
    }

    public void setData(RebModel data) {
        this.data = data;
    }
}
