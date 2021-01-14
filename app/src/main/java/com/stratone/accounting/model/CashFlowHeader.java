package com.stratone.accounting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CashFlowHeader {
    @SerializedName("Group")
    @Expose
    private String group;
    @SerializedName("Group2")
    @Expose
    private String group2;
    @SerializedName("ACC")
    @Expose
    private String aCC;
    @SerializedName("Sum")
    @Expose
    private String sum;
    @SerializedName("SortId")
    @Expose
    private Integer sortId;
    @SerializedName("Data")
    @Expose
    private List<CashFlowDetail> data = null;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup2() {
        return group2;
    }

    public void setGroup2(String group2) {
        this.group2 = group2;
    }

    public String getACC() {
        return aCC;
    }

    public void setACC(String aCC) {
        this.aCC = aCC;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public List<CashFlowDetail> getData() {
        return data;
    }

    public void setData(List<CashFlowDetail> data) {
        this.data = data;
    }
}
