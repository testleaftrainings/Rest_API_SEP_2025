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

public class IncidentServiceChain extends RestAssuredApiClientImpl {
	
	private String endpoint;
	private ResponseAPI response;
	private Gson gson = new Gson();
	
	public IncidentServiceChain() {
		endpoint = "incident";
	}
	
	public IncidentServiceChain createNewRecord(RequestSpecBuilder requestBuilder) {
		response = post(requestBuilder, endpoint);
		return this;
	}
	
	public IncidentServiceChain getAllRecords(RequestSpecBuilder requestBuilder) {
		response = get(requestBuilder, endpoint);
		return this;
	}
	
	public IncidentServiceChain getARecord(RequestSpecBuilder requestBuilder, String sysId) {
		response = get(requestBuilder, endpoint+"/"+sysId);
		return this;
	}
	
	public IncidentServiceChain validateResponse(int statusCode, String statusLine, String contentType) {
		assertThat(response.getStatusCode(), equalTo(statusCode));
		assertThat(response.getStatusMessage(), equalTo(statusLine));
		assertThat(response.getContentType(), equalTo(contentType));
		return this;
	}
	
	public String extractSysId() {
		TableApi tableApi = gson.fromJson(response.getBody(), TableApi.class);
		assertThat(tableApi.getResult().getSysId(), not(emptyOrNullString()));
		return tableApi.getResult().getSysId();
	}
	
	public IncidentServiceChain validateSysIdValue(String expected) {
		TableApi tableApi = gson.fromJson(response.getBody(), TableApi.class);
		assertThat(tableApi.getResult().getSysId(), equalTo(expected));
		return this;
	}

}