package com.matschie.servicenow.step.defs;

import com.matschie.servicenow.som.IncidentService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class IncidentServiceSteps {
	
	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	IncidentService incident = new IncidentService();

	@Given("user should set the base uri as {string} in the api client")
	public void user_should_set_the_base_uri_as_in_the_api_client(String baseUri) {
		requestBuilder.setBaseUri(baseUri);
	}

	@Given("user should set the base path {string} of the service now table api")
	public void user_should_set_the_base_path_of_the_service_now_table_api(String basePath) {
		requestBuilder.setBasePath(basePath);
	}

	@Given("user should set the basic authentication user name as {string} and password as {string}")
	public void user_should_set_the_basic_authentication_user_name_as_and_password_as(String username, String password) {
		requestBuilder.setAuth(RestAssured.basic(username, password));
	}

	@When("user should hit the get request of the {string} table")
	public void user_should_hit_the_get_request_of_the_table(String tableName) {
		incident.getAllRecords(requestBuilder);
	}

	@Then("user should able to see the success response and with relevant status code and message")
	public void user_should_able_to_see_the_success_response_and_with_relevant_status_code_and_message() {
		incident.validateResponse(200, "OK", "application/json");
	}

}