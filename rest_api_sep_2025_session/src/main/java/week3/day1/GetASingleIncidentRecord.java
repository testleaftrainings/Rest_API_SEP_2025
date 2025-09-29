package week3.day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetASingleIncidentRecord {

	public static void main(String[] args) {
		
		given()
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth().basic("admin", "Hz1e=0AU!fAd")
		   .pathParam("table-name", "incident")
		   .pathParam("sys_id", "1c741bd70b2322007518478d83673af3")
		   .log().all()
	   .when()
		   .get("/{table-name}/{sys_id}")
		.then()
		   .log().all()
		   .assertThat()
		   .statusCode(200)
		   .body("result.sys_id", equalTo("1c741bd70b2322007518478d83673af3"));

	}

}