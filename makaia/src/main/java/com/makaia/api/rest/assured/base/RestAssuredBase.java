package com.makaia.api.rest.assured.base;

import java.io.File;
import java.util.Map;

import com.makaia.api.design.ApiClient;
import com.makaia.api.design.ResponseAPI;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBase implements ApiClient {
	
	private Response response;
	
	private RequestSpecification given(RequestSpecification requestSpecification) {
		return RestAssured.given()
				          .spec(requestSpecification)				          
				          .filters(new RestAssuredListener(), new AllureRestAssured());
	}

	@Override
	public ResponseAPI get(RequestSpecification request, String endPoint) {

		response = given(request).urlEncodingEnabled(false).get(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI post(RequestSpecification request, String endPoint, String body) {

		response = given(request).body(body).post(endPoint);

		return new RestAssuredResponseBase(response);
	}
	
	@Override
	public ResponseAPI post(RequestSpecification request, String endPoint) {

		response = given(request).post(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI post(RequestSpecification request, String endPoint, File body) {
		
		response = given(request).body(body).post(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI post(RequestSpecification request, String endPoint, Object body) {
		
		response = given(request).body(body).post(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI post(RequestSpecification request, String endPoint, Map<String, Object> body) {
		
		response = given(request).formParams(body).post(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI put(RequestSpecification request, String endPoint, String body) {
		
		response = given(request).body(body).put(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI put(RequestSpecification request, String endPoint, File body) {
		
		response = given(request).body(body).put(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI put(RequestSpecification request, String endPoint, Object body) {
		
		response = given(request).body(body).put(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI patch(RequestSpecification request, String endPoint, String body) {
		
		response = given(request).body(body).patch(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI patch(RequestSpecification request, String endPoint, File body) {
		
		response = given(request).body(body).patch(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI patch(RequestSpecification request, String endPoint, Object body) {
		
		response = given(request).body(body).patch(endPoint);

		return new RestAssuredResponseBase(response);
	}

	@Override
	public ResponseAPI delete(RequestSpecification request, String endPoint) {
		
		response = given(request).delete(endPoint);
		
		return new RestAssuredResponseBase(response);
	}

}