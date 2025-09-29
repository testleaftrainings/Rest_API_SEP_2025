package week3.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;

public class GetAllIncidetRecords {

	public static void main(String[] args) {
		
		RestAssured.given()
		           .auth()
		           .basic("admin", "Hz1e=0AU!fAd")
		           .log().all() // Request Level Log
		           .when()
		           .get("https://dev230683.service-now.com/api/now/table/incident")
		           .then()
		           .log().all() // Response Level Log
		           .assertThat()
		           .statusCode(Matchers.equalTo(200));

	}

}
