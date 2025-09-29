package week3.day1;

import static io.restassured.RestAssured.*;

public class GetAllIncidentRecordsWithPathParameter {

	public static void main(String[] args) {
		
		given()
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth().basic("admin", "Hz1e=0AU!fAd")
		   .pathParam("table-name", "incident")
		   .log().all()
		.when()
		   .get("/{table-name}")
		.then()
		   .assertThat()
		   .statusCode(200);
		

	}

}