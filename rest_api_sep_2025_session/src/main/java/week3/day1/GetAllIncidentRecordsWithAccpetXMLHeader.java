package week3.day1;

import static io.restassured.RestAssured.*;

public class GetAllIncidentRecordsWithAccpetXMLHeader {

	public static void main(String[] args) {
		given()
		  .baseUri("https://dev230683.service-now.com")
		  .basePath("/api/now/table")
		  .header("Accept", "application/xml")
		  .auth()
		  .basic("admin", "Hz1e=0AU!fAd")
		  .log().all()
	   .when()
	      .get("/incident")
	   .then()
	      .log().all()
	      .assertThat()
	      .statusCode(200);
	}

}