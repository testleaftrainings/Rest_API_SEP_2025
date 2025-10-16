package com.makaia.jira.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

import java.util.ArrayList;

import org.json.JSONObject;

import com.makaia.jira.deserialization.pojos.Issue;
import com.makaia.jira.serialization.pojos.CreateIssuePayload;
import com.makaia.jira.serialization.pojos.Description;
import com.makaia.jira.serialization.pojos.Fields;
import com.makaia.jira.serialization.pojos.IssueType;
import com.makaia.jira.serialization.pojos.ParaContent;
import com.makaia.jira.serialization.pojos.Project;
import com.makaia.jira.serialization.pojos.TextContent;

import io.restassured.http.ContentType;

public class IssueService extends Jira {
	
	private static final String RESOURCE = "issue";
	
	public IssueService() {
		requestBuilder = globalRequest();
	}
	
	public IssueService createBugIssue(String summary) {
		CreateIssuePayload payload = new CreateIssuePayload();
		Fields fields = new Fields();
		IssueType issueType = new IssueType();
		Project project = new Project();
		project.setKey("SCRUM");
		issueType.setName("Bug");
		fields.setSummary(summary);
		fields.setProject(project);
		fields.setIssuetype(issueType);
		payload.setFields(fields);
		response = restAssured.post(requestBuilder.setContentType(ContentType.JSON).build(), RESOURCE, payload);		
		return this;
	}
	
	public IssueService createBugIssue(String summary, String content1, String content2) {
		CreateIssuePayload payload = new CreateIssuePayload();
		Fields fields = new Fields();
		IssueType issueType = new IssueType();
		Project project = new Project();
		Description description = new Description();
		ArrayList<ParaContent> paraContents = new ArrayList<ParaContent>();
		ParaContent paraContent = new ParaContent();
		ArrayList<TextContent> textContents = new ArrayList<TextContent>();
		TextContent textContent1 = new TextContent();
		TextContent textContent2 = new TextContent();
		
		project.setKey("SCRUM");
		issueType.setName("Bug");
		fields.setSummary(summary);
		fields.setProject(project);
		fields.setIssuetype(issueType);
		fields.setDescription(description);
		payload.setFields(fields);
		
		
		description.setVersion(1);
		description.setType("doc");
		description.setParaContent(paraContents);
		paraContents.add(paraContent);
		paraContent.setTextContent(textContents);
		paraContent.setType("paragraph");
		textContent1.setText(content1);
		textContent1.setType("text");
		textContent2.setText(content2);
		textContent2.setType("text");
		textContents.add(textContent1);
		textContents.add(textContent2);
		response = restAssured.post(requestBuilder.setContentType(ContentType.JSON).build(), RESOURCE, payload);		
		return this;
	}
	
	public IssueAttachementService extractIssueId() {
		String id = deSerializeResponse(response.getBody(), Issue.class).getId();
		assertThat(id, not(emptyOrNullString()));
		return new IssueAttachementService(id);
	}
	
	public IssueAttachementService extractIssueKey() {
		String key = deSerializeResponse(response.getBody(), Issue.class).getKey();
		assertThat(key, not(emptyOrNullString()));
		return new IssueAttachementService(key);
	}
	
	public IssueAttachementService extractResponseValue(String jsonPath) {
		String responseBody = response.getBody();
		JSONObject json = new JSONObject(responseBody);
		return new IssueAttachementService(json.getString(jsonPath));
	}

}