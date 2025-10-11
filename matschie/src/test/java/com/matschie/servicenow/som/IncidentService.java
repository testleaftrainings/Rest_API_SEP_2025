package com.matschie.servicenow.som;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import com.google.gson.Gson;
import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.api.client.RestAssuredApiClientImpl;
import com.matschie.servicenow.deserialization.pojos.TableApi;

import io.restassured.builder.RequestSpecBuilder;

public class IncidentService extends RestAssuredApiClientImpl {
	
	private String endpoint;
	private ResponseAPI response;
	private Gson gson = new Gson();
	
	public IncidentService() {
		endpoint = "incident";
	}
	
	public void createNewRecord(RequestSpecBuilder requestBuilder) {
		response = post(requestBuilder, endpoint);
	}
	
	public void getAllRecords(RequestSpecBuilder requestBuilder) {
		response = get(requestBuilder, endpoint);
	}
	
	public void getARecord(RequestSpecBuilder requestBuilder, String sysId) {
		response = get(requestBuilder, endpoint+"/"+sysId);
	}
	
	public void validateResponse(int statusCode, String statusLine, String contentType) {
		assertThat(response.getStatusCode(), equalTo(statusCode));
		assertThat(response.getStatusMessage(), equalTo(statusLine));
		assertThat(response.getContentType(), equalTo(contentType));
	}
	
	public String extractSysId() {
		TableApi tableApi = gson.fromJson(response.getBody(), TableApi.class);
		assertThat(tableApi.getResult().getSysId(), not(emptyOrNullString()));
		return tableApi.getResult().getSysId();
	}
	
	public void validateSysIdValue(String expected) {
		TableApi tableApi = gson.fromJson(response.getBody(), TableApi.class);
		assertThat(tableApi.getResult().getSysId(), equalTo(expected));
	}

}