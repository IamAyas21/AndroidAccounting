package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountModel {
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("CompanyId")
    @Expose
    private String companyId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
