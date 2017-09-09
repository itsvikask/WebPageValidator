package com.webpagevalidator.css;

public class Result {
	private Warnings warnings;
	private Errors errors;
	public Warnings getWarnings() {
		return warnings;
	}
	public void setWarnings(Warnings warnings) {
		this.warnings = warnings;
	}
	public Errors getErrors() {
		return errors;
	}
	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "Result [warnings=" + warnings + ", errors=" + errors + "]";
	}
	
}
