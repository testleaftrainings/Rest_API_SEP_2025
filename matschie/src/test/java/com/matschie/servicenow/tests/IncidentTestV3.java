package com.matschie.servicenow.tests;

import org.testng.annotations.Test;

import com.matschie.servicenow.services.IncidentService;

public class IncidentTestV3 {	
	
	IncidentService incident = new IncidentService();
	String sysId;
	
	@Test(priority = 1)
	public void userShouldCreateANewIncident() {
		incident.createNewRecord();
		incident.validateResponse(201, "Created", "application/json");		
		sysId = incident.extractSysId();
	}
	
	@Test(priority = 2)
	public void userShouldAbleToGetIncidents() {
		incident.getAllRecords();
		incident.validateResponse(200, "OK", "application/json");
	}
	
	@Test(priority = 3)
	public void userShouldAbleToGetASingleIncident() {
		incident.getARecord(sysId);
		incident.validateResponse(200, "OK", "application/json");		
		incident.validateSysIdValue(sysId);
	}

}