package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LedgerAccountModel {

    @SerializedName("No")
    @Expose
    private String no;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DebetMutation")
    @Expose
    private String debetMutation;
    @SerializedName("CreditMutation")
    @Expose
    private String creditMutation;
    @SerializedName("InitialBalance")
    @Expose
    private String initialBalance;
    @SerializedName("EndingBalance")
    @Expose
    private String endingBalance;
    @SerializedName("Mutations")
    @Expose
    private List<LedgerModel> mutations = null;

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

    public String getDebetMutation() {
        return debetMutation;
    }

    public void setDebetMutation(String debetMutation) {
        this.debetMutation = debetMutation;
    }

    public String getCreditMutation() {
        return creditMutation;
    }

    public void setCreditMutation(String creditMutation) {
        this.creditMutation = creditMutation;
    }

    public String getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(String initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(String endingBalance) {
        this.endingBalance = endingBalance;
    }

    public List<LedgerModel> getMutations() {
        return mutations;
    }

    public void setMutations(List<LedgerModel> mutations) {
        this.mutations = mutations;
    }
}
