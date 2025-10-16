package com.makaia.uibank.e2e.tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.makaia.api.design.ResponseAPI;
import com.makaia.api.rest.assured.base.RequestSpecBuilder;
import com.makaia.api.rest.assured.base.RestAssuredBase;
import static com.makaia.general.utils.PropertiesHandler.*;

import java.time.Duration;
import java.util.Locale;

import com.makaia.selenium.api.base.SeleniumBase;
import com.makaia.selenium.api.design.Locators;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class UiBankAccountsTest {
	
	SeleniumBase ui = new SeleniumBase();
	RestAssuredBase api = new RestAssuredBase();
	Faker faker = new Faker(Locale.US);
	String nickName = faker.name().firstName();
	String token;
	RequestSpecBuilder builder = new RequestSpecBuilder();
	String token_payload = """
			{
			  "username": "FebApiuser",
			  "password": "Eagle@123"
           }			
			""";
	ResponseAPI response;
	JsonPath jsonpath;
	
	@BeforeClass
	public void beforeClass(ITestContext testContext) {		
		for (ITestNGMethod testMethod : testContext.getAllTestMethods()) {			
			if(testMethod.getMethodName().contains("UI")) {
				// Pre Steps for the UI Test
				ui.browserLaunch();
				ui.loadUrl(config("uibank.frontend.url"));
				ui.type(ui.locateElement(Locators.ID, "username"), config("uibank.username"));
				ui.type(ui.locateElement(Locators.ID, "password"), secret("uibank.app.password"));
				ui.click(ui.locateElement(Locators.XPATH, "//button[text()='Sign In']"));
				ui.click(ui.locateElement(Locators.XPATH, "//button[normalize-space(text())='I agree to the Privacy Policy']"));
			} else {
				//Pre Steps for the API Test
				builder.setBaseUri("https://uibank-api.azurewebsites.net");
				builder.setBasePath("/api");
				builder.setContentType(ContentType.JSON);
				response = api.post(builder.build(), "/users/login", token_payload);
				Assert.assertEquals(response.getStatusCode(), 200);
				Assert.assertEquals(response.getStatusMessage(), "OK");
				Assert.assertEquals(response.getContentType(), "application/json");
				jsonpath = new JsonPath(response.getBody());
				token = jsonpath.getString("id");
			}
		}	    
	}
	
	@Test
	public void userShouldCreateAccountInUI() throws InterruptedException {
		Thread.sleep(Duration.ofSeconds(5));
		ui.click(ui.locateElement(Locators.XPATH, "//div[normalize-space(text())='Apply For New Account']"));
		ui.type(ui.locateElement(Locators.ID, "accountNickname"), nickName);
	    ui.dropdownSelectByValue(ui.locateElement(Locators.ID, "typeOfAccount"), "savings");
	    ui.click(ui.locateElement(Locators.XPATH, "//button[text()='Apply']"));
	    Thread.sleep(Duration.ofSeconds(5));
	    Assert.assertEquals(ui.getElementText(ui.locateElement(Locators.ID, "accountName")), nickName);
	}
	
	@Test
	public void userShouldFilterAccountByNicknameInAPI() {
		builder.setQueryParamKey("filter[where][friendlyName]");
		builder.setQueryParamValue(nickName);
		builder.setHeaderKey("Authorization");
		builder.setHeaderValue("Bearer "+token);
		response = api.get(builder.build(), "/accounts");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusMessage(), "OK");
		Assert.assertEquals(response.getContentType(), "application/json");
		jsonpath = new JsonPath(response.getBody());
		Assert.assertEquals(jsonpath.getString("[0].friendlyName"), nickName);
	}
	
	@AfterClass
	public void afterClass(ITestContext testContext) {
		for (ITestNGMethod testMethod : testContext.getAllTestMethods()) {
			if(testMethod.getMethodName().contains("UI")) {
				ui.quit();
			}
		}
		
	}

}