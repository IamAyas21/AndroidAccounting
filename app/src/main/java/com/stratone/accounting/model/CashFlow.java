package com.stratone.accounting.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashFlow {
    @SerializedName("Group")
    @Expose
    private String group;
    @SerializedName("Group2")
    @Expose
    private String group2;
    @SerializedName("ACC")
    @Expose
    private String aCC;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Name2")
    @Expose
    private String name2;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("Sum")
    @Expose
    private String sum;
    @SerializedName("SortId")
    @Expose
    private Integer sortId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
}
