package com.matschie.servicenow.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.matschie.general.utils.PropertiesHandlers.*;
import com.matschie.servicenow.som.IncidentServiceChain;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class IncidentTestV2 {
	
	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();	
	String sysId;
	
	@BeforeClass
	public void beforeClass() {
		requestBuilder.setBaseUri(config("service.now.base.uri"));
		requestBuilder.setBasePath(config("service.now.base.path"));
		requestBuilder.setAuth(RestAssured.basic(config("sevice.now.instance.username"), secret("service.now.instance.password")));		
	}
	
	@Test(priority = 1)
	public void userShouldCreateANewIncident() {		
		requestBuilder.addHeader("Content-Type", "application/json");
		
		sysId = new IncidentServiceChain()
		    .createNewRecord(requestBuilder)
		    .validateResponse(201, "Created", "application/json")
		    .extractSysId();
	}
	
	@Test(priority = 2)
	public void userShouldAbleToGetIncidents() {
		new IncidentServiceChain().getAllRecords(requestBuilder)
		    .validateResponse(200, "OK", "application/json");
	}
	
	@Test(priority = 3)
	public void userShouldAbleToGetASingleIncident() {
		new IncidentServiceChain().getARecord(requestBuilder, sysId)
		    .validateResponse(200, "OK", "application/json")		
		    .validateSysIdValue(sysId);
	}

}