package com.stratone.accounting.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stratone.accounting.model.ChartBankBalance;

import java.util.List;

public class ResponseChartBankBalance {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<ChartBankBalance> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String success) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChartBankBalance> getData() {
        return data;
    }

    public void setData(List<ChartBankBalance> data) {
        this.data = data;
    }
}
