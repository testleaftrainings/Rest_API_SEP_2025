package com.matschie.servicenow.services;

public class ChangeRequestService extends ServiceNow {
	
	private static final String TABLE_NAME = "change_request";	
	
	public ChangeRequestService() {
		globalPreRequest();
	}

}