package com.quakearts.webtools.resteasy.validation.test.helpers;

import javax.validation.constraints.Size;

public class TestParameter {
	@Size(min=5)
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public TestParameter setContent(String content) {
		this.content = content;
		return this;
	}
}