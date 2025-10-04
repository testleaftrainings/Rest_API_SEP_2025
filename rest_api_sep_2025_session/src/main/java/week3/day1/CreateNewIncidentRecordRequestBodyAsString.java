package week3.day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

public class CreateNewIncidentRecordRequestBodyAsString {

	public static void main(String[] args) {		
		
		String requestBody = """
				           {
				            "short_description": "RESTAPISEP2025",
				            "description": "my first post method"
				            } """;
				
		
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
		   .time(lessThan(5000L))
		   .body("result", hasKey("sys_id"));

	}

}
