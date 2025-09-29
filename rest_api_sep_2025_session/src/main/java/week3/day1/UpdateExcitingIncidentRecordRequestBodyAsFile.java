package week3.day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.http.ContentType;

public class UpdateExcitingIncidentRecordRequestBodyAsFile {

	public static void main(String[] args) {		
		
		File requestBody = new File("src/main/resources/request_payload/update_incident.json");
				
		given()
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth().basic("admin", "Hz1e=0AU!fAd")
		   .header("Content-Type", "application/json")
		   .pathParam("tableName", "incident")
		   .pathParam("sys_id", "1c741bd70b2322007518478d83673af3")
		   .log().all()
		.when()
		   .body(requestBody)
		   .put("/{tableName}/{sys_id}")
		.then()
		   .log().all()
		   .assertThat()
		   .statusCode(200)
		   .statusLine(containsString("OK"))
		   //.contentType(containsString("application/json")); 
		   .contentType(ContentType.JSON)
		   .time(lessThan(5000L));

	}

}
