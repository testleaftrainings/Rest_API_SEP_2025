package week3.day2;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.servicenow.pojos.CreateIncident;
import com.servicenow.pojos.UpdateIncident;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ServiceNowE2ETest {
	
	CreateIncident createRequestPaylod;
	UpdateIncident updateRequestPayload;
	String sys_id;
	
	@BeforeClass
	public void beforeClass() {
		createRequestPaylod = new CreateIncident();
		updateRequestPayload = new UpdateIncident();
	}
	
	@Test(priority = 1)
	public void createRecord() {
		
		createRequestPaylod.setShortDescription("RESTSEPSession2025");
		createRequestPaylod.setDescription("Create Record in Request Chaining");
		createRequestPaylod.setCategory("hardware");
		
		System.out.println(createRequestPaylod.getCategory());
		
		sys_id = given()
				  .baseUri("https://dev230683.service-now.com")
				  .basePath("/api/now/table")
				  .auth()
				  .basic("admin", "Hz1e=0AU!fAd")
				  .contentType(ContentType.JSON)
				  .accept(ContentType.JSON)
				  .pathParam("tableName", "incident")	
				  .log().all()
			   .when()
			      .body(createRequestPaylod)
			      .post("/{tableName}")
			   .then()
			      .assertThat()
			      .statusCode(201)
			      .statusLine(containsString("Created"))
			      .contentType(ContentType.JSON)
			      .time(lessThan(5000L))
			      .body("result", hasKey("sys_id"))
			      .extract()
			      .jsonPath()
			      .getString("result.sys_id");
	}
	
	@Test(priority = 2)
	public void getRecord() {
		given()
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth()
		   .basic("admin", "Hz1e=0AU!fAd")
		   .accept(ContentType.JSON)
		   .pathParam("tableName", "incident")
		   .pathParam("sysId", sys_id)
		.when()
		   .get("/{tableName}/{sysId}")
		.then()
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
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth()
	       .basic("admin", "Hz1e=0AU!fAd")
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .pathParam("tableName", "incident")	
		   .pathParam("sysId", sys_id)
		   .log().all()
		.when()
		   .body(updateRequestPayload)
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
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth()
		   .basic("admin", "Hz1e=0AU!fAd")		   
		   .pathParam("tableName", "incident")
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
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth()
		   .basic("admin", "Hz1e=0AU!fAd")
		   .accept(ContentType.JSON)
		   .pathParam("tableName", "incident")
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