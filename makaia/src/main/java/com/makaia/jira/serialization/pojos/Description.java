package com.makaia.jira.serialization.pojos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Description {

	@JsonProperty("content")
	private ArrayList<ParaContent> paraContent;
	private String type;
	private int version;

	public ArrayList<ParaContent> getParaContent() {
		return paraContent;
	}

	public void setParaContent(ArrayList<ParaContent> paraContent) {
		this.paraContent = paraContent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}