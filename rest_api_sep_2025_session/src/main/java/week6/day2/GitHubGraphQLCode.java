package week6.day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import io.restassured.http.ContentType;

public class GitHubGraphQLCode {
	
	static String graphlQuery = """
			query {
                    viewer {
                          login
                          name
                          avatarUrl
                          company   
                          repositories {            
                             totalCount
                             totalDiskUsage
                          }
                          followers {
                             totalCount      
                          }    
                        }
            }
			""";
	
	static String userQuery = """
			query {
    viewer {
    login
    name
    avatarUrl
    company   
        
  }
}
			""";

	public static void main(String[] args) {
		given()
		   .baseUri("https://api.github.com")
		   .header("Authorization", "Bearer <github-token>")
		   .contentType(ContentType.JSON)
		   .log().all()
		   .when()
		   .body(convertGraphQLQueryToJsonString(userQuery))
		   .post("/graphql")
		   .then()
		   .log().all()
		   .assertThat()
		   .statusCode(200)
		   .statusLine(containsString("OK"));

	}
	
	private static String convertGraphQLQueryToJsonString(String input) {
		JSONObject json = new JSONObject();
		json.put("query", input);
		return json.toString();
	}

}
