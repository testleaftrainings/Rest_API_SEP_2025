package com.matschie.api.design;

import java.io.File;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;

public interface ApiClient {	
	
	ResponseAPI get(RequestSpecBuilder request, String endPoint);
	
	ResponseAPI post(RequestSpecBuilder request, String endPoint);

	ResponseAPI post(RequestSpecBuilder request, String endPoint, String body);

	ResponseAPI post(RequestSpecBuilder request, String endPoint, File body);

	ResponseAPI post(RequestSpecBuilder request, String endPoint, Object body);

	ResponseAPI post(RequestSpecBuilder request, String endPoint, Map<String, Object> body);
	
	ResponseAPI put(RequestSpecBuilder request, String endPoint, String body);

	ResponseAPI put(RequestSpecBuilder request, String endPoint, File body);

	ResponseAPI put(RequestSpecBuilder request, String endPoint, Object body);
	
	ResponseAPI patch(RequestSpecBuilder request, String endPoint, String body);

	ResponseAPI patch(RequestSpecBuilder request, String endPoint, File body);

	ResponseAPI patch(RequestSpecBuilder request, String endPoint, Object body);
	
	ResponseAPI delete(RequestSpecBuilder request, String endPoint);

}