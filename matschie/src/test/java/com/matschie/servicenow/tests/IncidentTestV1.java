package com.matschie.servicenow.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.matschie.servicenow.som.IncidentService;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class IncidentTestV1 {
	
	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	IncidentService incident = new IncidentService();
	String sysId;
	
	@BeforeClass
	public void beforeClass() {
		requestBuilder.setBaseUri("https://dev230683.service-now.com");
		requestBuilder.setBasePath("/api/now/table");
		requestBuilder.setAuth(RestAssured.basic("admin", "Hz1e=0AU!fAd"));		
	}
	
	@Test(priority = 1)
	public void userShouldCreateANewIncident() {		
		requestBuilder.addHeader("Content-Type", "application/json");
		
		incident.createNewRecord(requestBuilder);
		incident.validateResponse(201, "Created", "application/json");		
		sysId = incident.extractSysId();
	}
	
	@Test(priority = 2)
	public void userShouldAbleToGetIncidents() {
		incident.getAllRecords(requestBuilder);
		incident.validateResponse(200, "OK", "application/json");
	}
	
	@Test(priority = 3)
	public void userShouldAbleToGetASingleIncident() {
		incident.getARecord(requestBuilder, sysId);
		incident.validateResponse(200, "OK", "application/json");		
		incident.validateSysIdValue(sysId);
	}

}