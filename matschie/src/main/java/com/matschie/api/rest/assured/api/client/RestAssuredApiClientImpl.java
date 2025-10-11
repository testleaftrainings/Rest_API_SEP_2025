package com.matschie.api.rest.assured.api.client;

import java.io.File;
import java.util.Map;

import com.matschie.api.design.ApiClient;
import com.matschie.api.design.ResponseAPI;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredApiClientImpl implements ApiClient {

	private Response response;

	private RequestSpecification given(RequestSpecBuilder requestBuilder) {
		return RestAssured.given()
				          .spec(requestBuilder.build())
				          .filter(new RestAssuredListener())
				          .filter(new AllureRestAssured());
	}

	@Override
	public ResponseAPI get(RequestSpecBuilder request, String endPoint) {

		response = given(request).get(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI post(RequestSpecBuilder request, String endPoint, String body) {

		response = given(request).body(body).post(endPoint);

		return new RestAssuredResponseImpl(response);
	}
	
	@Override
	public ResponseAPI post(RequestSpecBuilder request, String endPoint) {

		response = given(request).post(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI post(RequestSpecBuilder request, String endPoint, File body) {
		
		response = given(request).body(body).post(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI post(RequestSpecBuilder request, String endPoint, Object body) {
		
		response = given(request).body(body).post(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI post(RequestSpecBuilder request, String endPoint, Map<String, Object> body) {
		
		response = given(request).formParams(body).post(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI put(RequestSpecBuilder request, String endPoint, String body) {
		
		response = given(request).body(body).put(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI put(RequestSpecBuilder request, String endPoint, File body) {
		
		response = given(request).body(body).put(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI put(RequestSpecBuilder request, String endPoint, Object body) {
		
		response = given(request).body(body).put(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI patch(RequestSpecBuilder request, String endPoint, String body) {
		
		response = given(request).body(body).patch(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI patch(RequestSpecBuilder request, String endPoint, File body) {
		
		response = given(request).body(body).patch(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI patch(RequestSpecBuilder request, String endPoint, Object body) {
		
		response = given(request).body(body).patch(endPoint);

		return new RestAssuredResponseImpl(response);
	}

	@Override
	public ResponseAPI delete(RequestSpecBuilder request, String endPoint) {
		
		response = given(request).delete(endPoint);
		
		return new RestAssuredResponseImpl(response);
	}

}