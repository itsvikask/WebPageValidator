package com.webpagevalidator.css;

import java.util.Arrays;

public class Errorlist {
	private String uri;
	private Error error[];
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Error[] getError() {
		return error;
	}
	public void setError(Error[] error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "Errorlist [uri=" + uri + ", error=" + Arrays.toString(error) + "]";
	}
	
}
