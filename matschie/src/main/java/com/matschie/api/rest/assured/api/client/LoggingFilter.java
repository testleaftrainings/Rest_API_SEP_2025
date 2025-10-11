package com.matschie.api.rest.assured.api.client;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilter implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		Response response = ctx.next(requestSpec, responseSpec);
		System.out.println(requestSpec.getBaseUri());
		System.out.println(requestSpec.getMethod());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		return response;
	}

}