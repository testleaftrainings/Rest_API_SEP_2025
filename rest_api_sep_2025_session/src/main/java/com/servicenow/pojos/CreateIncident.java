package com.servicenow.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateIncident {

    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    private String category;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}   

}