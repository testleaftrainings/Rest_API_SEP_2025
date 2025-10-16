package com.makaia.api.design;

import java.io.File;
import java.util.Map;

import io.restassured.specification.RequestSpecification;

public interface ApiClient {

	ResponseAPI get(RequestSpecification request, String endPoint);

	ResponseAPI post(RequestSpecification request, String endPoint);

	ResponseAPI post(RequestSpecification request, String endPoint, String body);

	ResponseAPI post(RequestSpecification request, String endPoint, File body);

	ResponseAPI post(RequestSpecification request, String endPoint, Object body);

	ResponseAPI post(RequestSpecification request, String endPoint, Map<String, Object> body);

	ResponseAPI put(RequestSpecification request, String endPoint, String body);

	ResponseAPI put(RequestSpecification request, String endPoint, File body);

	ResponseAPI put(RequestSpecification request, String endPoint, Object body);

	ResponseAPI patch(RequestSpecification request, String endPoint, String body);

	ResponseAPI patch(RequestSpecification request, String endPoint, File body);

	ResponseAPI patch(RequestSpecification request, String endPoint, Object body);

	ResponseAPI delete(RequestSpecification request, String endPoint);

}