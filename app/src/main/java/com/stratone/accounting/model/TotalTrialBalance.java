package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalTrialBalance {
    @SerializedName("InitialDebet")
    @Expose
    private String initialDebet;
    @SerializedName("InitialCredit")
    @Expose
    private String initialCredit;
    @SerializedName("MutationDebet")
    @Expose
    private String mutationDebet;
    @SerializedName("MutationCredit")
    @Expose
    private String mutationCredit;
    @SerializedName("EndingDebet")
    @Expose
    private String endingDebet;
    @SerializedName("EndingCredit")
    @Expose
    private String endingCredit;

    public String getInitialDebet() {
        return initialDebet;
    }

    public void setInitialDebet(String initialDebet) {
        this.initialDebet = initialDebet;
    }

    public String getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(String initialCredit) {
        this.initialCredit = initialCredit;
    }

    public String getMutationDebet() {
        return mutationDebet;
    }

    public void setMutationDebet(String mutationDebet) {
        this.mutationDebet = mutationDebet;
    }

    public String getMutationCredit() {
        return mutationCredit;
    }

    public void setMutationCredit(String mutationCredit) {
        this.mutationCredit = mutationCredit;
    }

    public String getEndingDebet() {
        return endingDebet;
    }

    public void setEndingDebet(String endingDebet) {
        this.endingDebet = endingDebet;
    }

    public String getEndingCredit() {
        return endingCredit;
    }

    public void setEndingCredit(String endingCredit) {
        this.endingCredit = endingCredit;
    }
}
