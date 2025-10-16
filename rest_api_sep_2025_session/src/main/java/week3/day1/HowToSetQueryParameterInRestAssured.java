package week3.day1;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;

public class HowToSetQueryParameterInRestAssured {
	
	static String tableName = "incident";
	
	public static void main(String[] args) {
		
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("sysparm_query", "category=hardware");
		queryMap.put("sysparm_fields", "sys_id,number,category,description,short_description");
		//queryMap.put("sysparm_limit", "3");
		
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table/{tableName}")
		 .pathParam("tableName", tableName)
		 .queryParams(queryMap)
		 .auth().basic("admin", "e5!pRsPN%lH5")
		 .log().all()
		.when()
		 .get()
		.then()		
		 .log().all()
		 .assertThat()
		 .statusCode(Matchers.equalTo(200))
		 .statusLine(Matchers.containsString("OK"))
		 .time(Matchers.lessThan(5000L))
		 .body("result.category", Matchers.hasSize(10))
		 .body("result", Matchers.everyItem(Matchers.hasKey("sys_id")))
		 .body("result", Matchers.everyItem(Matchers.hasKey("number")))
		 .body("result", Matchers.everyItem(Matchers.hasKey("category")))		 
		 .body("result", Matchers.everyItem(Matchers.hasKey("short_description")))
		 .body("result", Matchers.everyItem(Matchers.hasKey("description")))
		 .body("result.category", Matchers.everyItem(Matchers.equalToIgnoringCase("hardware")));

	}

}
