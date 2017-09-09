package com.webpagevalidator.css;

public class Warnings {
	private String lang;
	private int warningcount;
	private Warninglist warninglist;
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getWarningcount() {
		return warningcount;
	}
	public void setWarningcount(int warningcount) {
		this.warningcount = warningcount;
	}
	public Warninglist getWarninglist() {
		return warninglist;
	}
	public void setWarninglist(Warninglist warninglist) {
		this.warninglist = warninglist;
	}
	@Override
	public String toString() {
		return "Warnings [lang=" + lang + ", warningcount=" + warningcount + ", warninglist=" + warninglist + "]";
	}
	
	
}
