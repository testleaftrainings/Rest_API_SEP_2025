package week3.day1;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class GetAllHardwareCategoryIncidentRecord {

	public static void main(String[] args) {
		
		given()
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth().basic("admin", "Hz1e=0AU!fAd")
		   .pathParam("tableName", "incident")
		   .queryParam("sysparm_query", "category=hardware")
		   .log().all()
		.when()
		   .get("/{tableName}")
		.then()
		   .assertThat()
		   .statusCode(200)
		   .body("result.category", everyItem(equalToIgnoringCase("hardware")))
		   .body("result.category", hasSize(10))
		   .body("result.category", hasSize(greaterThan(0)));		 

	}

}
