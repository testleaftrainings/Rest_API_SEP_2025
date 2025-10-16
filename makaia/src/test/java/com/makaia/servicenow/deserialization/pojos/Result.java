package com.makaia.servicenow.deserialization.pojos;

import com.google.gson.annotations.SerializedName;

public class Result {
	
	@SerializedName("sys_id")
	private String sysId;
	private String category;
	private String number;

	public String getSysId() {
		return sysId;
	}

	public String getCategory() {
		return category;
	}

	public String getNumber() {
		return number;
	}
	
}