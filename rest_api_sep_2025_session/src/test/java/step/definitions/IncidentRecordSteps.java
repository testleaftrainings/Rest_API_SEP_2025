package step.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class IncidentRecordSteps {
	
	RequestSpecBuilder preConditions = new RequestSpecBuilder();
	Response response;
	
	@Given("user should set the base uri as {string} in the api client")
	public void user_should_set_the_base_uri_as_in_the_api_client(String baseUri) {
	    preConditions.setBaseUri(baseUri);
	}
	
	@Given("user should set the base path {string} of the service now table api")
	public void user_should_set_the_base_path_of_the_service_now_table_api(String basePath) {
	    preConditions.setBasePath(basePath);
	}
	
	@Given("user should set the basic authentication user name as {string} and password as {string}")
	public void user_should_set_the_basic_authentication_user_name_as_and_password_as(String username, String password) {
	   preConditions.setAuth(basic(username, password));
	}
	
	@When("user should hit the get request of the {string} table")
	public void user_should_hit_the_get_request_of_the_table(String endPoint) {
	    response = given()
	    		    .spec(preConditions.build())
	    		    .log().all()
	    		    .get(endPoint);
	}
	
	@Then("user should able to see the success response and with relevant status code and message")
	public void user_should_able_to_see_the_success_response_and_with_relevant_status_code_and_message() {
	   	response.then()
	            .log().all()
	            .assertThat()
	            .statusCode(200)
	            .statusLine(containsString("OK"))
	            .contentType(ContentType.JSON)
	            .time(lessThan(5000L));
	}
	
	@Given("user should set the header key as {string} and value as {string}")
	public void user_should_set_the_header_key_as_and_value_as(String key, String value) {
		preConditions.addHeader(key, value);	    
	}
	
	@When("user should hit the post request of the {string} table")
	public void user_should_hit_the_post_request_of_the_table(String endPoint) {
	    response = given()
	    		    .spec(preConditions.build())
	    		    .log().all()
	    		    .post(endPoint);	    		    
	}
	
	@Then("user should successfully created the new record and with the relevant status code and message")
	public void user_should_successfully_created_the_new_record_and_with_the_relevant_status_code_and_message(DataTable dataTable) {
		List<Map<String, String>> expectedValues = dataTable.asMaps();
		for (Map<String, String> expectedValue : expectedValues) {
			response.then()
			    .log().all()
			    .assertThat()
			    .statusCode(Integer.parseInt(expectedValue.get("statusCode")))
			    .statusLine(containsString(expectedValue.get("statusLine")))
			    .contentType(expectedValue.get("contentType"))
			    .time(lessThan(Long.parseLong(expectedValue.get("responseTime"))));
		}
	}

}