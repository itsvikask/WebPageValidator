package com.webpagevalidator.html;

import java.util.Arrays;

public class HTMLErrorPOJO {
	private String url;
	private String language;
	
	private Messages messages[];
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Messages[] getMessages() {
		return messages;
	}
	public void setMessages(Messages[] messages) {
		this.messages = messages;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Override
	public String toString() {
		return "HTMLErrorPOJO [url=" + url + ", language=" + language + ", messages=" + Arrays.toString(messages) + "]";
	}
	
}
