package week6.day1;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class IntegrateMockingWithRestAssuredCode {
	
	WireMockServer mockServer = new WireMockServer(8081);
	String request_payload = """
			                 {
                               "username": "FebApiuser",
                               "password": "Eagle@123"
                              }
			                 """;
	String response_payload = """
			                  {
                                "id": "XZ3BjG21ckenv1qDYslljzOc6EIrI4QkzhMwzlK1cQZ7pDWxWbthb22XF397Xv1f",
                                "ttl": 1209600,
                                "created": "2025-10-19T10:56:35.965Z",
                                "userId": "64290731ba9f8a0047adacfc"
                              }
		 	                  """;
	
	@BeforeClass
	public void beforeClass() {
		mockServer.start();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		// Request Mocking
		MappingBuilder requestMocking = WireMock.post("/api/users/login")
				        .withHeader("Content-Type", WireMock.equalTo("application/json"))
				        .withHeader("Authorization", WireMock.equalTo("Bearer XZ3BjG21ckenv1qDYslljzOc6EIrI4QkzhMwzlK1cQZ7pDWxWbthb22XF397Xv1f"))
				        .withRequestBody(WireMock.equalToJson(request_payload));
		
		// Response Mocking
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
				        .withStatus(200)
				        .withStatusMessage("OK")
				        .withHeader("Content-Type", "application/json")
				        .withBody(response_payload);
		
		mockServer.stubFor(requestMocking.willReturn(responseMocking));
		
	}
	
	@Test
	public void userShouldAbleToCreateUIBankTokenMock() {
		RestAssured.given()
		           .baseUri("http://localhost:8081")
		           .basePath("/api")
		           .contentType(ContentType.JSON)
		           .log().all()
		           .when()
		           .body(request_payload)
		           .post("/users/login")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"));
	}
	
	@Test
	public void userShouldAbleToCreateUIBankTokenReal() {
		RestAssured.given()
		           .baseUri("https://uibank-api.azurewebsites.net/")
		           .basePath("/api")
		           .contentType(ContentType.JSON)
		           .log().all()
		           .when()
		           .body(request_payload)
		           .post("/users/login")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"));
	}
	
	@AfterClass
	public void afterClass() {
		mockServer.stop();
	}

}