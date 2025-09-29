package week3.day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.http.ContentType;

public class CreateNewIncidentRecordRequestBodyAsFile {

	public static void main(String[] args) {		
		
		File requestBody = new File("src/main/resources/request_payload/create_incident.json");
				
		given()
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth().basic("admin", "Hz1e=0AU!fAd")
		   .header("Content-Type", "application/json")
		   .pathParam("tableName", "incident")
		   .log().all()
		.when()
		   .body(requestBody)
		   .post("/{tableName}")
		.then()
		   .log().all()
		   .assertThat()
		   .statusCode(201)
		   .statusLine(containsString("Created"))
		   //.contentType(containsString("application/json")); 
		   .contentType(ContentType.JSON)
		   .time(lessThan(5000L));

	}

}
