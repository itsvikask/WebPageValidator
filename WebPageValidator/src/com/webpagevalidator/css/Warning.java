package com.webpagevalidator.css;

public class Warning {
	private int line;
	private int level;
	private String message;
	private String type;
	private String context;
	
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	@Override
	public String toString() {
		return "Warning [line=" + line + ", level=" + level + ", message=" + message + ", type=" + type + ", context="
				+ context + "]";
	}
	
	
}
