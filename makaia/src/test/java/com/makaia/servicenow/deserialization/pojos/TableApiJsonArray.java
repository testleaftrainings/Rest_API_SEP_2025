package com.makaia.servicenow.deserialization.pojos;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TableApiJsonArray {
	
	@SerializedName("result")
	private List<Result> resultJsonArray;

	public List<Result> getResultJsonArray() {
		return resultJsonArray;
	}	

}