package week3.day2;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import com.servicenow.pojos.CreateIncident;

import io.restassured.http.ContentType;

public class CreateNewIncidentRecordRequestBodyAsPojoObject {

	public static void main(String[] args) {
		
		CreateIncident requestBody = new CreateIncident();
		
		requestBody.setShortDescription("RESTAPISEP20251");
		requestBody.setDescription("POJO class Record");		
		
		given()
		  .baseUri("https://dev230683.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .basic("admin", "Hz1e=0AU!fAd")
		  .contentType(ContentType.JSON)
		  .pathParam("tableName", "incident")
		  .log().all()
	   .when()
	      .body(requestBody)
	      .post("/{tableName}")
	   .then()
	      .assertThat()
	      .statusCode(201)
	      .statusLine(containsString("Created")) // HTTP1.1 201 Created == Created
	      .contentType(ContentType.JSON)
	      .time(lessThan(5000L));
	      

	}

}