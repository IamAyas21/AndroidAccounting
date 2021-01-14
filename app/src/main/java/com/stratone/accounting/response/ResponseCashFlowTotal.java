package com.stratone.accounting.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;

import java.util.List;

public class ResponseCashFlowTotal {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<CashFlowDetail> data = null;

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

    public List<CashFlowDetail> getData() {
        return data;
    }

    public void setData(List<CashFlowDetail> data) {
        this.data = data;
    }
}
