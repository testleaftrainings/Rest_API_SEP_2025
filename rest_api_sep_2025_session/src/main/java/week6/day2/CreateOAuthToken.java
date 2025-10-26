package week6.day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

public class CreateOAuthToken {

	public static void main(String[] args) {
		given()
		   .baseUri("https://dev324941.service-now.com")
		   .contentType(ContentType.URLENC)
		   .log().all()
		   .when()
		   .formParam("grant_type", "password")
		   .formParam("client_id", "71511a1865134459a3d8973f9c84c1d9")
		   .formParam("client_secret", "Hspdw@u^ly")
		   .formParam("username", "admin")
		   .formParam("password", "e5!pRsPN%lH5")
		   .post("/oauth_token.do")
		   .then()
		   .log().all()
		   .assertThat()
		   .statusCode(200)
		   .statusLine(containsString("OK"));
	}

}