package com.matschie.servicenow.services;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.matschie.servicenow.deserialization.pojos.TableApi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class IncidentService extends ServiceNow {
	
	private static final String TABLE_NAME = "incident";	
	
	public IncidentService() {
		globalPreRequest();
	}	
	
	public void createNewRecord() {
		response = apiClient.post(requestBuilder
				                  .setContentType(ContentType.JSON), 
				                  TABLE_NAME);
	}
	
	public void getAllRecords() {
		response = apiClient.get(requestBuilder, TABLE_NAME);
	}
	
	public void getARecord(String sysId) {
		response = apiClient.get(requestBuilder, TABLE_NAME+"/"+sysId);
	}
	
	public void createNewRecord(RequestSpecBuilder requestBuilder) {
		response = apiClient.post(requestBuilder, TABLE_NAME);
	}
	
	public void createNewRecord(RequestSpecBuilder requestBuilder, Object body) {
		response = apiClient.post(requestBuilder, TABLE_NAME, body);
	}
	
	public void getAllRecords(RequestSpecBuilder requestBuilder) {
		response = apiClient.get(requestBuilder, TABLE_NAME);
	}
	
	public void getARecord(RequestSpecBuilder requestBuilder, String sysId) {
		response = apiClient.get(requestBuilder, TABLE_NAME+"/"+sysId);
	}
	
	public void validateResponse(int statusCode, String statusLine, String contentType) {
		assertThat(response.getStatusCode(), equalTo(statusCode));
		assertThat(response.getStatusMessage(), equalTo(statusLine));
		assertThat(response.getContentType(), equalTo(contentType));
	}
	
	public String extractSysId() {		
		String sysId = deSerializeResponse(response.getBody(), TableApi.class).getResult().getSysId();
		assertThat(sysId, not(emptyOrNullString()));
		return sysId;
	}
	
	public void validateSysIdValue(String expected) {		
		assertThat(deSerializeResponse(response.getBody(), TableApi.class).getResult().getSysId(), 
				   equalTo(expected));
	}	

}