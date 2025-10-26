package com.makaia.uibank.web.api.tests;

import org.hamcrest.Matchers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UiBankAPI {
	
	String token;
	String userCredentials = """
			                 {
			                   "username": "FebApiuser",
                               "password": "Eagle@123"
			                 }
		 	                 """;
	String accounts = """
			          {
                         "friendlyName": "RaviRaj",
                         "type": "checking",
                         "userId": "64290731ba9f8a0047adacfc",
                         "balance": 1000,
                         "accountNumber": 258741963
                      }
			          """;
	
	@BeforeMethod
	public void create_token() {
		token = RestAssured.given()
		           .baseUri("https://uibank-api.azurewebsites.net")
		           .basePath("/api")
		           .contentType(ContentType.JSON)
		           .log().all()
		           .when()
		           .body(userCredentials)
		           .post("/users/login")
		           .then()
		           .log().all()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"))
		           .extract()
		           .jsonPath()
		           .getString("id");
		         
	}
	
	@Test
	public void create_account() {
		RestAssured.given()
        .baseUri("https://uibank-api.azurewebsites.net")
        .basePath("/api")
        .contentType(ContentType.JSON)
        .header("Authorization", "Bearer "+token)
        .log().all()
        .when()
        .body(accounts)
        .post("/accounts")
        .then()
        .log().all()
        .statusCode(200)
        .statusLine(Matchers.containsString("OK"));
	}

}