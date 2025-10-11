package com.matschie.api.rest.assured.api.client;

import java.util.logging.Logger;

import org.json.JSONObject;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredListener implements Filter {
	
	private static final Logger LOGGER = Logger.getLogger(RestAssuredListener.class.getName());

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		Response response = ctx.next(requestSpec, responseSpec);
		
		LOGGER.info("""
				    ============ Request Log ============
				    HTTP Method: %s
				    URI: %s
				    Request Headers: %s
				    Request Payload: %s		
				    ======================================		
				    """.formatted(requestSpec.getMethod(), requestSpec.getURI(),
				    		      requestSpec.getHeaders().asList().toString(),
				    		      isRequestPayLoadNull(requestSpec.getBody())));
		
		LOGGER.info("""
				    ============ Response Log ============
				    Response Status Code: %s
				    Response Status Line: %s
				    Response Body: %s
				    Response Headers: %s
				    Response Content-Type: %s
				    ======================================
				    """.formatted(response.getStatusCode(), response.getStatusLine(),
				    		      response.getBody().asPrettyString(), response.getHeaders().asList().toString(),
				    		      response.getHeader("Content-Type")));
		return response;
	}
	
	private Object isRequestPayLoadNull(Object body) {
		if (body != null) {
			return new JSONObject(body.toString());
		} else {
			return "The request payload was not included in this request.";
		}
	}

}