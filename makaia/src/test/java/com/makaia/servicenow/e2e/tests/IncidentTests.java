package com.makaia.servicenow.e2e.tests;

import org.testng.annotations.Test;

import static com.makaia.general.utils.PropertiesHandler.*;

import com.makaia.serivcenow.api.services.IncidentSerivce;
import com.makaia.servicenow.ui.pages.LoginPage;
import com.makaia.testng.hooks.TestNGHooks;

public class IncidentTests extends TestNGHooks {
	
	String number;
	
	@Test(priority = 1)
	public void userShouldAbleToCreateNewIncidentInUI() {
		new LoginPage()
		    .enterUserName(config("makaia.servicenow.username"))
		    .enterPassword(secret("service.now.instance.password"))
		    .clickLoingButton()
		    .gotoIncidentPage()
		    .getIncidentNumber()
		    .enterCallerId("Service Desk")
		    .enterShortDescription("Sub")
		    .clickSubmitButton()
		    .filterByNumber()
		    .validateIncidentCreation();		
	}
	
	@Test(priority = 2)
	public void userShouldAbleToFetchCreatedIncidentInAPI() {
		new IncidentSerivce()
		    .fetchIncidentRecordByNumber(incidentNumber)
		    .validateSuccessResponse()
		    .validateIncidentNumber(incidentNumber);	
	}
	
	@Test(priority = 3)
	public void userShouldAbleToCreatedIncidentInAPI() {
		number = new IncidentSerivce()
				             .createIncidentRecord()
				             .validateCreationResponse()
				             .extractIncidentNumber();
		System.out.println(number);
	}
	
	@Test(priority = 4)
	public void userShouldAbleToSeeIncidentInUI() {
		new LoginPage()
		    .enterUserName(config("makaia.servicenow.username"))
		    .enterPassword(secret("service.now.instance.password"))
		    .clickLoingButton()
		    .gotoListofIncidentsPage()
		    .filterByNumber(number)
		    .validateIncidentCreation(number);		
	}

}