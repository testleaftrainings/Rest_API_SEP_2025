package com.makaia.jira.services;

import java.io.File;

import io.restassured.http.ContentType;

public class IssueAttachementService extends Jira {
	
	private String issueIdOrKey;
	private String endpoint = "/issue/{issueIdOrKey}/attachments";
	
	public IssueAttachementService() {
		requestBuilder = globalRequest();
	}
	
    public IssueAttachementService(String issueIdOrKey) {
		this.issueIdOrKey = issueIdOrKey;
		requestBuilder = globalRequest();
	}
    
	public IssueAttachementService testEvidenceAttachment(String filePath) {
		response = restAssured.post(requestBuilder.setHeaderKey("X-Atlassian-Token")
				                                  .setHeaderValue("no-check")
				                                  .setPathParamKey("issueIdOrKey")
				                                  .setPathParamValue(issueIdOrKey)
				                                  .setContentType(ContentType.MULTIPART)
				                                  .build()
				                                  .multiPart(new File(filePath)), endpoint);
		return this;
	}
    
    
    

}