package com.webpagevalidator.css;

public class Cssvalidationresponse {
	private Boolean validity;
	private String encodingStyle;
	private String uri;
	private String csslevel;
	private String date;
	private String checkedby;
	private Result result;
	private String m;
	
	public Boolean getValidity() {
		return validity;
	}
	public void setValidity(Boolean validity) {
		this.validity = validity;
	}
	public String getEncodingStyle() {
		return encodingStyle;
	}
	public void setEncodingStyle(String encodingStyle) {
		this.encodingStyle = encodingStyle;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getCsslevel() {
		return csslevel;
	}
	public void setCsslevel(String csslevel) {
		this.csslevel = csslevel;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Cssvalidationresponse [validity=" + validity + ", encodingStyle=" + encodingStyle + ", uri=" + uri
				+ ", csslevel=" + csslevel + ", date=" + date + ", checkedby=" + checkedby + ", result=" + result
				+ ", m=" + m + "]";
	}
	public String getCheckedby() {
		return checkedby;
	}
	public void setCheckedby(String checkedby) {
		this.checkedby = checkedby;
	}
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	
	
}
