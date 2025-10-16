package com.makaia.serivcenow.api.services;

import io.restassured.http.ContentType;

import static com.makaia.general.utils.PropertiesHandler.*;

import com.google.gson.Gson;
import com.makaia.api.design.ResponseAPI;
import com.makaia.api.rest.assured.base.RequestSpecBuilder;
import com.makaia.api.rest.assured.base.RestAssuredBase;

public class ServiceNow {
	
	protected RestAssuredBase apiClient = new RestAssuredBase();	
	protected RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	protected ResponseAPI response;
	protected Gson gson = new Gson();	
	
	protected RequestSpecBuilder globalRequest() {
		return new RequestSpecBuilder()
				   .setBaseUri(config("service.now.base.uri"))	
				   .setBasePath(config("service.now.base.path"))
				   .setUsername(config("sevice.now.instance.username"))
			       .setPassword(secret("service.now.instance.password"))
				   .setAccept(ContentType.JSON);
	}
	
	protected <T> T deSerializeResponse(String response, Class<T> classType) {
		return gson.fromJson(response, classType);
	}
	
}