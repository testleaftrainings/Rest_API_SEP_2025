package com.matschie.servicenow.deserialization.pojos;

import com.google.gson.annotations.SerializedName;

public class Result {
	
	@SerializedName("sys_id")
	private String sysId;
	private String category;

	public String getSysId() {
		return sysId;
	}

	public String getCategory() {
		return category;
	}
	
}