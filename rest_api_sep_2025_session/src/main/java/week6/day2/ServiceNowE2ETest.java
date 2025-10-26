package week6.day2;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.servicenow.pojos.CreateIncident;
import com.servicenow.pojos.UpdateIncident;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ServiceNowE2ETest {
	
	CreateIncident createRequestPaylod;
	UpdateIncident updateRequestPayload;
	String sys_id;
	RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	String accessToken;
	
	@BeforeSuite
	public void createOAuthToken() {
		accessToken = given()
		   .baseUri("https://dev324941.service-now.com")
		   .contentType(ContentType.URLENC)
		   .log().all()
		   .when()
		   .formParam("grant_type", "password")
		   .formParam("client_id", "71511a1865134459a3d8973f9c84c1d9")
		   .formParam("client_secret", "Hspdw@u^ly")
		   .formParam("username", "admin")
		   .formParam("password", "e5!pRsPN%lH5")
		   .post("/oauth_token.do")
		   .then()
		   .log().all()
		   .assertThat()
		   .statusCode(200)
		   .statusLine(containsString("OK"))
		   .extract()
		   .jsonPath()
		   .getString("access_token");
	}
	
	
	@BeforeClass
	public void beforeClass() {
		createRequestPaylod = new CreateIncident();
		updateRequestPayload = new UpdateIncident();
		requestSpecBuilder.addHeader("Authorization", "Bearer "+accessToken);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		requestSpecBuilder.setBaseUri("https://dev324941.service-now.com");
		requestSpecBuilder.setBasePath("/api/now/table");		
		requestSpecBuilder.addPathParam("tableName", "incident");
	}
	
	@Test(priority = 1)
	public void createRecord() {
		
		createRequestPaylod.setShortDescription("RESTSEPSession2025");
		createRequestPaylod.setDescription("Create Record in Request Chaining");
		createRequestPaylod.setCategory("hardware");		
		
		sys_id = given()
				  .spec(requestSpecBuilder.build())
				  .contentType(ContentType.JSON)
				  .accept(ContentType.JSON)				  
				  .log().all()
			   .when()
			      .body(new Gson().toJson(createRequestPaylod))
			      .post("/{tableName}")
			   .then()
			      .log().all()
			      .assertThat()
			      .statusCode(201)
			      .statusLine(containsString("Created"))
			      .contentType(ContentType.JSON)
			      .time(lessThan(5000L))
			      .body("result", hasKey("sys_id"))
			      .extract()
			      .jsonPath()
			      .getString("result.sys_id");
		
		System.out.println(sys_id);
		
	}
	
	@Test(priority = 2)
	public void getRecord() {		
		
		given()
		   .spec(requestSpecBuilder.build())
		   .accept(ContentType.JSON)		  
		   .pathParam("sysId", sys_id)
		   .log().all()
		.when()
		   .get("/{tableName}/{sysId}")
		.then()
		   .log().all()
		   .assertThat()
		   .statusCode(200)
		   .statusLine(containsString("OK"))
		   .contentType(ContentType.JSON)
		   .time(lessThan(2500L))
		   .body("result", hasKey("sys_id"))
		   .body("result.sys_id", equalTo(sys_id))
		   .body("result", hasKey("short_description"))
		   .body("result.short_description", equalTo(createRequestPaylod.getShortDescription()))
		   .body("result", hasKey("description"))
		   .body("result.description", equalTo(createRequestPaylod.getDescription()));
	}
	
	@Test(priority = 3)
	public void updateRecord() {		
		
		updateRequestPayload.setDescription("update Record in Request Chaining");
		updateRequestPayload.setCategory("software");
		
		given()
		   .spec(requestSpecBuilder.build())
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)		   	
		   .pathParam("sysId", sys_id)
		   .log().all()
		.when()
		   .body(new Gson().toJson(updateRequestPayload))
		   .put("/{tableName}/{sysId}")
		.then()
		   .assertThat()
		   .statusCode(200)
		   .statusLine(containsString("OK"))
		   .contentType(ContentType.JSON)
		   .time(lessThan(2500L))
		   .body("result", hasKey("sys_id"))
		   .body("result.sys_id", equalTo(sys_id))		   
		   .body("result", hasKey("description"))
		   .body("result.description", equalTo(updateRequestPayload.getDescription()))
		   .body("result", hasKey("category"))
		   .body("result.category", equalTo(updateRequestPayload.getCategory()));
			      
	}
	
	@Test(priority = 4)
	public void deleteRecord() {
		given()
		   .spec(requestSpecBuilder.build())
		   .pathParam("sysId", sys_id)
		.when()
		   .delete("/{tableName}/{sysId}")
		.then()
		   .assertThat()
		   .statusCode(204)
		   .statusLine(containsString("No Content"))		   
		   .time(lessThan(3500L));
	}
	
	@Test(priority = 5)
	public void validateIsRecordDeleted() {
		given()
		   .spec(requestSpecBuilder.build())
		   .accept(ContentType.JSON)		   
		   .pathParam("sysId", sys_id)
		.when()
		   .get("/{tableName}/{sysId}")
		.then()
		   .assertThat()
		   .statusCode(404)
		   .statusLine(containsString("Not Found"))
		   .contentType(ContentType.JSON)
		   .time(lessThan(2500L));
	}

}