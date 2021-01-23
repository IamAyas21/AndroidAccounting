package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrialBalance {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("No")
    @Expose
    private String no;
    @SerializedName("Name")
    @Expose
    private String name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
