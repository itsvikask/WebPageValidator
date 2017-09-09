package com.webpagevalidator.html;

public class Messages {
	private String type;
	private int firstLine;
	private int lastLine;
	private int lastColumn;
	private int firstColumn;
	private String subType;
	private String message;
	private String extract;
	private int hiliteStart;
	private int hiliteLength;
	private String url;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLastLine() {
		return lastLine;
	}
	public void setLastLine(int lastLine) {
		this.lastLine = lastLine;
	}
	public int getLastColumn() {
		return lastColumn;
	}
	public void setLastColumn(int lastColumn) {
		this.lastColumn = lastColumn;
	}
	public int getFirstColumn() {
		return firstColumn;
	}
	public void setFirstColumn(int firstColumn) {
		this.firstColumn = firstColumn;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getExtract() {
		return extract;
	}
	public void setExtract(String extract) {
		this.extract = extract;
	}
	public int getHiliteStart() {
		return hiliteStart;
	}
	public void setHiliteStart(int hiliteStart) {
		this.hiliteStart = hiliteStart;
	}
	public int getHiliteLength() {
		return hiliteLength;
	}
	public void setHiliteLength(int hiliteLength) {
		this.hiliteLength = hiliteLength;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Messages [type=" + type + ", firstLine=" + firstLine + ", lastLine=" + lastLine + ", lastColumn="
				+ lastColumn + ", firstColumn=" + firstColumn + ", subType=" + subType + ", message=" + message
				+ ", extract=" + extract + ", hiliteStart=" + hiliteStart + ", hiliteLength=" + hiliteLength + ", url="
				+ url + "]";
	}
	public int getFirstLine() {
		return firstLine;
	}
	public void setFirstLine(int firstLine) {
		this.firstLine = firstLine;
	}

}
