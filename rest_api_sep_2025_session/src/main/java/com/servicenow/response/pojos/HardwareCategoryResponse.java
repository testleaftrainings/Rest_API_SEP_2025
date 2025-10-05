package com.servicenow.response.pojos;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HardwareCategoryResponse {

    @SerializedName("result")
    @Expose
    private List<Results> results = new ArrayList<Results>();

    public List<Results> getResults() {
        return results;
    }

    public void setResult(List<Results> results) {
        this.results = results;
    }

}