package com.makaia.uibank.web.api.tests;

import java.time.Duration;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateAccountTest {	
	
	private static Faker faker = new Faker();
	private static String nickName = faker.name().firstName();
	
	@Test
	public void createAccountViaWebApplication() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://uibank.uipath.com/welcome");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.id("username")).sendKeys("FebApiuser");
		driver.findElement(By.id("password")).sendKeys("Eagle@123");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-agreement-popup")));
		driver.findElement(By.xpath("//button[normalize-space(text())='I agree to the Privacy Policy']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space(text())='Apply For New Account'] "))).click();
	    System.out.println(nickName);
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountNickname"))).sendKeys(nickName);
	    	Select select = new Select(driver.findElement(By.id("typeOfAccount")));
	    	select.selectByValue("savings");
	    	driver.findElement(By.xpath("//button[text()='Apply']")).click();
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class,'header')]")));
	    	Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(@class,'header')]")).getText().trim(), "Congratulations!");
	    Assert.assertEquals(driver.findElement(By.id("accountName")).getText(), nickName);
	    driver.quit();
	}
	
	@Test
	public void userShouldCheckAccountIsAvailabeInAPI() {
		// To create a Bearer Token
		String token = RestAssured.given()
		           .baseUri("https://uibank-api.azurewebsites.net")
		           .basePath("/api")
		           .when()
		           .contentType(ContentType.JSON)
		           .body("""
		           		{
                          "username": "FebApiuser",
                          "password": "Eagle@123"
                        }""")
		           .post("users/login")
		           .then()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"))
		           .extract()
		           .jsonPath()
		           .getString("id");
		
		
		// Create a account
		RestAssured.given()
		           .baseUri("https://uibank-api.azurewebsites.net")
                   .basePath("/api")
                   .header("Authorization", "Bearer "+token)
                   .queryParam("filter[where][friendlyName]", nickName)
                   .log().all()
                   .when()
                   .get("/accounts")
                   .then()
                   .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"));
		           
                   
	}

}