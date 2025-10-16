package com.matschie.servicenow.services;

import com.google.gson.Gson;
import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.api.client.RestAssuredApiClientImpl;
import static com.matschie.general.utils.PropertiesHandlers.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class ServiceNow {
	
	protected RestAssuredApiClientImpl apiClient = new RestAssuredApiClientImpl();
	protected RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	protected ResponseAPI response;
	protected Gson gson = new Gson();	
	
	protected RequestSpecBuilder globalPreRequest() {
		return requestBuilder.setBaseUri(config("service.now.base.uri"))
				             .setBasePath(config("service.now.base.path"))
				             .setAuth(RestAssured.basic(config("sevice.now.instance.username"), secret("service.now.instance.password")));
	}
	
	protected <T> T deSerializeResponse(String response, Class<T> classType) {
		return gson.fromJson(response, classType);
	}

}