package com.webpagevalidator.css;

import java.util.Arrays;

public class Warninglist {
	private String uri;
	private Warning warning[];
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Warning[] getWarning() {
		return warning;
	}
	public void setWarning(Warning[] warning) {
		this.warning = warning;
	}
	@Override
	public String toString() {
		return "Warninglist [uri=" + uri + ", warning=" + Arrays.toString(warning) + "]";
	}
	
}
