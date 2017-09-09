package com.webpagevalidator.css;

public class Error {
	private String errortype;
	private int line;
	private String skippedstring;
	private String message;
	private String context;
	private String errorsubtype;
	private String type;
	public String getErrortype() {
		return errortype;
	}
	public void setErrortype(String errortype) {
		this.errortype = errortype;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getSkippedstring() {
		return skippedstring;
	}
	public void setSkippedstring(String skippedstring) {
		this.skippedstring = skippedstring;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getErrorsubtype() {
		return errorsubtype;
	}
	public void setErrorsubtype(String errorsubtype) {
		this.errorsubtype = errorsubtype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Error [errortype=" + errortype + ", line=" + line + ", skippedstring=" + skippedstring + ", message="
				+ message + ", context=" + context + ", errorsubtype=" + errorsubtype + ", type=" + type + "]";
	}
	
	
}
