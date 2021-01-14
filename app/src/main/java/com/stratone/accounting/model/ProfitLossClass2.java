package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfitLossClass2 {
    @SerializedName("TitleId")
    @Expose
    private String titleId;
    @SerializedName("TitleEg")
    @Expose
    private String titleEg;
    @SerializedName("Amount")
    @Expose
    private String amount;

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
}
