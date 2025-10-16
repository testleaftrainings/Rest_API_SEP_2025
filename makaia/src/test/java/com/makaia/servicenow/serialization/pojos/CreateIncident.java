package com.makaia.servicenow.serialization.pojos;

import com.google.gson.annotations.SerializedName;

public class CreateIncident {

	@SerializedName("short_description")
	private String shortDescription;
	private String description;

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

}