package com.makaia.serivcenow.api.services;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.makaia.api.rest.assured.base.ResponseContentType;
import com.makaia.servicenow.deserialization.pojos.TableApiJson;
import com.makaia.servicenow.deserialization.pojos.TableApiJsonArray;
import com.makaia.servicenow.serialization.pojos.CreateIncident;
import com.makaia.servicenow.serialization.pojos.UpdateIncident;

import io.restassured.http.ContentType;

public class IncidentSerivce extends ServiceNow {
	
	private static final String TABLE_NAME = "incident";	
	
	public IncidentSerivce() {
		requestBuilder = globalRequest();
	}
	
	public IncidentSerivce fetchIncidentRecords() {
		response = apiClient.get(requestBuilder.build(), TABLE_NAME);
		return this;
	}
	
	public IncidentSerivce fetchIncidentRecord(String sysId) {
		response = apiClient.get(requestBuilder.build(), TABLE_NAME+"/"+sysId);
		return this;
	}
	
	public IncidentSerivce fetchIncidentRecordByNumber(String number) {
		response = apiClient.get(requestBuilder
				                   .setQueryParamKey("sysparm_query")
				                   .setQueryParamValue("number="+number)
				                   .build(), 
				                   TABLE_NAME);
		return this;
	}
	
	public IncidentSerivce createIncidentRecord() {
		response = apiClient.post(requestBuilder.setContentType(ContentType.JSON).build(), TABLE_NAME);
		return this;
	}
	
	public IncidentSerivce createIncidentRecord(CreateIncident payload) {
		response = apiClient.post(requestBuilder.build(), TABLE_NAME, payload);
		return this;
	}
	
	public IncidentSerivce updateIncidentRecord(UpdateIncident payload, String sysId) {
		response = apiClient.put(requestBuilder.setContentType(ContentType.JSON).build(),  TABLE_NAME+"/"+sysId, payload);
		return this;
	}
	
	public IncidentSerivce fetchOnlyHardwareCategoryIncidentRecords() {		
		response = apiClient.get(requestBuilder.build().queryParam("sysparm_query", "category=hardware"), TABLE_NAME);
		return this;
	}
	
	public IncidentSerivce deleteIncidentRecord(String sysId) {
		response = apiClient.delete(requestBuilder.build(), TABLE_NAME+"/"+sysId);
		return this;
	}
	
	public IncidentSerivce validateSuccessResponse() {		
		assertThat(response.getStatusCode(), equalTo(200));
		assertThat(response.getStatusMessage(), equalToIgnoringCase("OK"));
		assertThat(response.getContentType(), equalTo(ResponseContentType.JSON));
		return this;
	}
	
	public IncidentSerivce validateCreationResponse() {
		assertThat(response.getStatusCode(), equalTo(201));
		assertThat(response.getStatusMessage(), equalToIgnoringCase("Created"));
		assertThat(response.getContentType(), equalTo(ResponseContentType.JSON));
		return this;
	}
	
	public IncidentSerivce validateDeletionResponse() {
		assertThat(response.getStatusCode(), equalTo(204));
		assertThat(response.getStatusMessage(), equalToIgnoringCase(ResponseContentType.JSON));
		return this;
	}
	
	public IncidentSerivce validateNotFoundResponse() {
		assertThat(response.getStatusCode(), equalTo(404));
		assertThat(response.getStatusMessage(), equalToIgnoringCase("Not Found"));
		assertThat(response.getContentType(), equalTo(ResponseContentType.JSON));
		return this;
	}
	
	public IncidentSerivce validateCategories(String expected) {
		deSerializeResponse(response.getBody(), TableApiJsonArray.class);
		return this;
	}
	
	public IncidentSerivce validateSysId(String expected) {
		String sys_id = deSerializeResponse(response.getBody(), TableApiJson.class).getResult().getSysId();
		assertThat(sys_id, not(emptyOrNullString()));
		assertThat(sys_id, equalTo(expected));
		return this;
	}
	
	public IncidentSerivce validateIncidentNumber(String expected) {
		assertThat(deSerializeResponse(response.getBody(), TableApiJsonArray.class).getResultJsonArray().getFirst().getNumber(), not(emptyOrNullString()));
		assertThat(deSerializeResponse(response.getBody(), TableApiJsonArray.class).getResultJsonArray().getFirst().getNumber(), equalTo(expected));
		return this;
	}	
	
	public String extractIncidentNumber() {
		String number = deSerializeResponse(response.getBody(), TableApiJson.class).getResult().getNumber();
		assertThat(number, not(emptyOrNullString()));
		return number;
	}

}
