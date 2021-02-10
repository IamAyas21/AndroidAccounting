package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChartProfitLoss {
    @SerializedName("Month")
    @Expose
    private String month;
    @SerializedName("Cost")
    @Expose
    private String cost;
    @SerializedName("Income")
    @Expose
    private String income;
    @SerializedName("Total")
    @Expose
    private String total;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
