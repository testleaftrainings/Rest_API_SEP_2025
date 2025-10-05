package week4.day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.servicenow.response.pojos.HardwareCategoryResponse;
import com.servicenow.response.pojos.Results;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;

public class GetHardwareCategoryUsingDeserialization {

	@Test
	public void vaildateHardwareCategoryData() {
		
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("sysparm_fields", "sys_id,number,category,state,impact");
		queryParams.put("sysparm_query", "category=hardware");
		
      List<Results> results = given()
		   .baseUri("https://dev230683.service-now.com")
		   .basePath("/api/now/table")
		   .auth()
		   .basic("admin", "Hz1e=0AU!fAd")
		   .pathParam("tableName", "incident")
		   .queryParams(queryParams)
	   .when()
	       .get("/{tableName}")
	   .then()
	       .assertThat()
	       .statusCode(200)
	       .statusLine(containsString("OK"))
	       .contentType(ContentType.JSON)
	       .extract()
	       .response()
	       .as(HardwareCategoryResponse.class, ObjectMapperType.GSON)
	       .getResults();
	       
	     
		assertThat(results, hasSize(greaterThan(0)));
		assertThat(results, hasSize(10));		
		for (Results result : results) {
			assertThat(result.getCategory(), equalToIgnoringCase("hardware"));
		}

	}

}
