package com.matschie.servicenow.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.matschie.servicenow.som.IncidentServiceChain;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class IncidentTestV2 {
	
	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();	
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