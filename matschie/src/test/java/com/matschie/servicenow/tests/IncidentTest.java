package com.matschie.servicenow.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.api.client.RestAssuredApiClientImpl;
import com.matschie.servicenow.deserialization.pojos.TableApi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

import static com.matschie.general.utils.PropertiesHandlers.config;
import static com.matschie.general.utils.PropertiesHandlers.secret;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class IncidentTest {
	
	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	RestAssuredApiClientImpl apiClient = new RestAssuredApiClientImpl();
	Gson gson = new Gson();
	ResponseAPI response;
	String sysId;
	
	@BeforeClass
	public void beforeClass() {
		requestBuilder.setBaseUri(config("service.now.base.uri"));
		requestBuilder.setBasePath(config("service.now.base.path"));
		requestBuilder.setAuth(RestAssured.basic(config("sevice.now.instance.username"), secret("service.now.instance.password")));
		requestBuilder.addPathParam("tableName", "incident");
	}
	
	@Test(priority = 1)
	public void userShouldCreateANewIncident() {		
		requestBuilder.addHeader("Content-Type", "application/json");
		
		response = apiClient.post(requestBuilder, "/{tableName}");
		
		assertThat(response.getStatusCode(), equalTo(201));
		assertThat(response.getStatusMessage(), equalTo("Created"));
		assertThat(response.getContentType(), equalTo("application/json"));
		
		TableApi tableApi = gson.fromJson(response.getBody(), TableApi.class);
		
		assertThat(tableApi.getResult().getSysId(), not(emptyOrNullString()));
		
		sysId = tableApi.getResult().getSysId();
	}
	
	@Test(priority = 2)
	public void userShouldAbleToGetIncidents() {

		response = apiClient.get(requestBuilder, "/{tableName}");

		assertThat(response.getStatusCode(), equalTo(200));
		assertThat(response.getStatusMessage(), equalTo("OK"));
		assertThat(response.getContentType(), equalTo("application/json"));
	}
	
	@Test(priority = 3)
	public void userShouldAbleToGetASingleIncident() {

		response = apiClient.get(requestBuilder, "/{tableName}/"+sysId);

		assertThat(response.getStatusCode(), equalTo(200));
		assertThat(response.getStatusMessage(), equalTo("OK"));
		assertThat(response.getContentType(), equalTo("application/json"));
		
		TableApi tableApi = gson.fromJson(response.getBody(), TableApi.class);
		
		assertThat(tableApi.getResult().getSysId(), equalTo(sysId));
	}

}