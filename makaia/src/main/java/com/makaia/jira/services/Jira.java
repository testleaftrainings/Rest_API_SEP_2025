package com.makaia.jira.services;

import static com.makaia.general.utils.PropertiesHandler.config;
import static com.makaia.general.utils.PropertiesHandler.secret;

import com.google.gson.Gson;
import com.makaia.api.design.ResponseAPI;
import com.makaia.api.rest.assured.base.RequestSpecBuilder;
import com.makaia.api.rest.assured.base.RestAssuredBase;

import io.restassured.http.ContentType;

public class Jira {
	
	protected ResponseAPI response;
	protected RestAssuredBase restAssured = new RestAssuredBase();	
	protected RequestSpecBuilder requestBuilder;	
	protected Gson gson = new Gson();
	
	protected RequestSpecBuilder globalRequest() {
		return new RequestSpecBuilder()
				   .setBaseUri(config("jira.base.uri"))	
				   .setBasePath(config("jira.base.path"))
				   .setUsername(config("jira.username"))
			       .setPassword(secret("jira.api.token"))
			       .setPreemptive(true)
				   .setAccept(ContentType.JSON);
	}
	
	protected <T> T deSerializeResponse(String response, Class<T> classType) {
		return gson.fromJson(response, classType);
	}

}