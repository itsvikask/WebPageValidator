package com.webpagevalidator.css;

public class Errors {
	private String lang;
	private int errorcount;
	private Errorlist errorlist;
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getErrorcount() {
		return errorcount;
	}
	public void setErrorcount(int errorcount) {
		this.errorcount = errorcount;
	}
	public Errorlist getErrorlist() {
		return errorlist;
	}
	public void setErrorlist(Errorlist errorlist) {
		this.errorlist = errorlist;
	}
	@Override
	public String toString() {
		return "Errors [lang=" + lang + ", errorcount=" + errorcount + ", errorlist=" + errorlist + "]";
	}
	
}
