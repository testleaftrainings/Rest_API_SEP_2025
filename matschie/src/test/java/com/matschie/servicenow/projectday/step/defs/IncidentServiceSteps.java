package com.matschie.servicenow.projectday.step.defs;

import java.util.List;

import static com.matschie.general.utils.PropertiesHandlers.*;
import com.matschie.servicenow.serialization.pojos.CreateIncident;
import com.matschie.servicenow.services.IncidentService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class IncidentServiceSteps {
	
	IncidentService incident = new IncidentService();
	RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	CreateIncident createIncident = new CreateIncident();

	@Given("user should set the base uri as {string} in the api client")
	public void user_should_set_the_base_uri_as_in_the_api_client(String baseUri) {
		requestBuilder.setBaseUri(config(baseUri));
	}

	@Given("user should set the base path {string} of the service now table api")
	public void user_should_set_the_base_path_of_the_service_now_table_api(String basePath) {
		requestBuilder.setBasePath(config(basePath));
	}

	@Given("user should set the basic authentication user name as {string} and password as {string}")
	public void user_should_set_the_basic_authentication_user_name_as_and_password_as(String username, String password) {
		requestBuilder.setAuth(RestAssured.basic(config(username), secret(password)));
	}

	@Given("Set the header {string} key and {string} as value")
	public void set_the_header_key_and_as_value(String key, String value) {
		requestBuilder.addHeader(key, value);
	}

	@Given("Set the Accpet header {string} key and {string} as value")
	public void set_the_accpet_header_key_and_as_value(String key, String value) {
		requestBuilder.addHeader(key, value);
	}

	@When("create the incident record with the following input data")
	public void create_the_incident_record_with_the_following_input_data(DataTable dataTable) {
		List<String> list = dataTable.asList();
		createIncident.setShortDescription(list.get(0));
		createIncident.setDescription(list.get(1));
	}

	@When("hit the post http method with request body as the pojo object of the {string} table")
	public void hit_the_post_http_method_with_request_body_as_the_pojo_object_of_the_table(String string) {
		incident.createNewRecord(requestBuilder, createIncident);
	}

	@Then("validate the status code and status line")
	public void validate_the_status_code_and_status_line() {
		incident.validateResponse(201, "Created", "application/json");
	}

}