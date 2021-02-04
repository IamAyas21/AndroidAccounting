package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LedgerModel {
    @SerializedName("Ref")
    @Expose
    private String ref;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Voucher")
    @Expose
    private String voucher;
    @SerializedName("Desc")
    @Expose
    private String desc;
    @SerializedName("Debet")
    @Expose
    private String debet;
    @SerializedName("Credit")
    @Expose
    private String credit;
    @SerializedName("Balance")
    @Expose
    private String balance;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDebet() {
        return debet;
    }

    public void setDebet(String debet) {
        this.debet = debet;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
