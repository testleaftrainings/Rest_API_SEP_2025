package com.makaia.jira.serialization.pojos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParaContent {

	@JsonProperty("content")
	private ArrayList<TextContent> textContent;
	private String type;

	public ArrayList<TextContent> getTextContent() {
		return textContent;
	}

	public void setTextContent(ArrayList<TextContent> textContent) {
		this.textContent = textContent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}