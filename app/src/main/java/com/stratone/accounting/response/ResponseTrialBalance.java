package com.stratone.accounting.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stratone.accounting.model.TotalTrialBalance;
import com.stratone.accounting.model.TrialBalance;

import java.util.List;

public class ResponseTrialBalance {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Total")
    @Expose
    private TotalTrialBalance total;
    @SerializedName("Data")
    @Expose
    private List<TrialBalance> data = null;

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

    public TotalTrialBalance getTotal() {
        return total;
    }

    public void setTotal(TotalTrialBalance total) {
        this.total = total;
    }

    public List<TrialBalance> getData() {
        return data;
    }

    public void setData(List<TrialBalance> data) {
        this.data = data;
    }
}
