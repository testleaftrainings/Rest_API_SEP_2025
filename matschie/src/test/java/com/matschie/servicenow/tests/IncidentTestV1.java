package com.matschie.servicenow.tests;

import static com.matschie.general.utils.PropertiesHandlers.config;
import static com.matschie.general.utils.PropertiesHandlers.secret;

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
		requestBuilder.setBaseUri(config("service.now.base.uri"));
		requestBuilder.setBasePath(config("service.now.base.path"));
		requestBuilder.setAuth(RestAssured.basic(config("sevice.now.instance.username"), secret("service.now.instance.password")));		
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