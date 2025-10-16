package com.makaia.servicenow.ui.pages;

import static com.makaia.general.utils.PropertiesHandler.config;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.makaia.selenium.api.design.Locators;
import com.makaia.testng.hooks.TestNGHooks;

public class ListofIncidents extends TestNGHooks {
	
	public ListofIncidents() {
		loadUrl(config("makaia.aut.url")+"/incident_list.do");
	}
	
	public ListofIncidents filterByNumber() {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[contains(@id,'_select')]")));
		dropdownSelectByValue(locateElement(Locators.XPATH, "//select[contains(@id,'_select')]"), "number");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
		typeAndEnter(locateElement(Locators.XPATH, "//input[@placeholder='Search']"), incidentNumber);
		return this;
	}
	
	public ListofIncidents filterByNumber(String incidentNumber) {		
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
		typeAndEnter(locateElement(Locators.XPATH, "//input[@placeholder='Search']"), incidentNumber);
		return this;
	}
	
	public ListofIncidents validateIncidentCreation() {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#incident_table > tbody > tr:nth-child(1) > td:nth-child(3) > a")));
		boolean actual = getElementText(locateElement(Locators.CSS_SELECTOR, "table#incident_table > tbody > tr:nth-child(1) > td:nth-child(3) > a")).equals(incidentNumber);
		assertThat("Unable to find "+incidentNumber+" newly created incident in the incident_list table", actual);
		return this;
	}
	
	public ListofIncidents validateIncidentCreation(String incidentNumber) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#incident_table > tbody > tr:nth-child(1) > td:nth-child(3) > a")));
		boolean actual = getElementText(locateElement(Locators.CSS_SELECTOR, "table#incident_table > tbody > tr:nth-child(1) > td:nth-child(3) > a")).equals(incidentNumber);
		assertThat("Unable to find "+incidentNumber+" newly created incident in the incident_list table", actual);
		return this;
	}

}