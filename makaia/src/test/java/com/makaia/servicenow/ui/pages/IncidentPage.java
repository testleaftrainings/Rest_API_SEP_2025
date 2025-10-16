package com.makaia.servicenow.ui.pages;

import static com.makaia.general.utils.PropertiesHandler.config;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.makaia.selenium.api.design.Locators;
import com.makaia.testng.hooks.TestNGHooks;


public class IncidentPage extends TestNGHooks {
	
	public IncidentPage() {
		loadUrl(config("makaia.aut.url")+"/incident.do");
	}
	
	public IncidentPage getIncidentNumber() {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='incident.number']")));
		incidentNumber = getAttributeValue(locateElement(Locators.XPATH, "//input[@id='incident.number']"), "value");		
		return this;
	}
	
	public IncidentPage enterCallerId(String caller) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.input-group > input[id$='caller_id']")));
		type(locateElement(Locators.CSS_SELECTOR, "div.input-group > input[id$='caller_id']"), caller);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='ac_table_completer']/tbody/tr[starts-with(@id,'ac_option')]/td[text()='"+caller+"']")));
		click(locateElement(Locators.XPATH, "//table[@class='ac_table_completer']/tbody/tr[starts-with(@id,'ac_option')]/td[text()='"+caller+"']"));
		return this;		
	}
	
	public IncidentPage enterShortDescription(String short_description) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='incident.short_description']")));
		type(locateElement(Locators.XPATH, "//input[@id='incident.short_description']"), short_description);
		return this;
	}
	
	public ListofIncidents clickSubmitButton() {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("sysverb_insert_bottom")));
		click(locateElement(Locators.ID, "sysverb_insert_bottom"));
		return new ListofIncidents();
	}

}