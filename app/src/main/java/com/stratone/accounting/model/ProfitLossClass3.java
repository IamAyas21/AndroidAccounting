package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfitLossClass3 {
    @SerializedName("TitleId")
    @Expose
    private String titleId;
    @SerializedName("TitleEg")
    @Expose
    private String titleEg;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("Data")
    @Expose
    private List<ProfitLossClass2> data;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitleEg() {
        return titleEg;
    }

    public void setTitleEg(String titleEg) {
        this.titleEg = titleEg;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<ProfitLossClass2> getData() {
        return data;
    }

    public void setData(List<ProfitLossClass2> data) {
        this.data = data;
    }
}
