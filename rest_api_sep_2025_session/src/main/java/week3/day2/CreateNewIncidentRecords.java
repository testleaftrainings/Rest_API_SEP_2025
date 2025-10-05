package week3.day2;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.servicenow.pojos.CreateIncident;

import io.restassured.http.ContentType;

public class CreateNewIncidentRecords {
	
	@DataProvider
	public String[][] getTestData() {
		return new String[][] {
			{"RESTAPISEP20251","POJO class Record 1"},	
			{"RESTAPISEP20252","POJO class Record 2"},
			{"RESTAPISEP20253","POJO class Record 3"},
			{"RESTAPISEP20254","POJO class Record 4"}
		};
	}

	@Test(dataProvider = "getTestData")
	public void createNewRecords(String shortDescription, String description) {
		
		CreateIncident requestBody = new CreateIncident();
		
		requestBody.setShortDescription(shortDescription);
		requestBody.setDescription(description);		
		
		String sys_id = given()
		  .baseUri("https://dev230683.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .preemptive()
		  .basic("admin", "Hz1e=0AU!fAd")
		  .contentType(ContentType.JSON)
		  .pathParam("tableName", "incident")		  
	   .when()
	      .body(requestBody)
	      .post("/{tableName}")
	   .then()
	      .assertThat()
	      .statusCode(201)
	      .statusLine(containsString("Created")) // HTTP1.1 201 Created == Created
	      .contentType(ContentType.JSON)
	      .time(lessThan(5000L))
	      .body("result", hasKey("sys_id"))
	      .extract()
	      .jsonPath()
	      .getString("result.sys_id");
		
		System.out.println(sys_id);	      

	}

}