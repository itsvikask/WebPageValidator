package com.webpagevalidator.css;

public class Envelope {
	private String env;
	private Body body;
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Envelope [env=" + env + ", body=" + body + "]";
	}
	
	
}
