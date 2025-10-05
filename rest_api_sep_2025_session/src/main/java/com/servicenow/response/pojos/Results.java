package com.servicenow.response.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("sys_id")
    @Expose
    private String sysId;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("impact")
    @Expose
    private String impact;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("category")
    @Expose
    private String category;

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
