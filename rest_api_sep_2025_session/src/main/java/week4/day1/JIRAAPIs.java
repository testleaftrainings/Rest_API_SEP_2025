package week4.day1;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JIRAAPIs {
	
	@Test
	public void getIssueType() {
		given()
		   .baseUri("https://karthikeselene.atlassian.net")
		   .basePath("/rest/api/3")
		   .auth()  
		   .preemptive()
		   .basic("karthike.selene@gmail.com", "<jira-api-token>")
		   .queryParam("projectId", "10044")
		   .log().all()
		.when()
		   .get("/issuetype/project")
		.then()		   		   
		   .assertThat()
		   .statusCode(200)
		   .statusLine(containsString("OK"))
		   .contentType(ContentType.JSON);
	}

}