package com.webpagevalidator.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {
	private Document doc;
	private ArrayList<String> allLinks;
	private ArrayList<String> cssLinks;
	private ArrayList<String> jsLinks;
	
	public HTMLParser() {
		super();
		this.doc = null;
		this.allLinks = new ArrayList<String>();
		this.cssLinks = new ArrayList<String>();
		this.jsLinks = new ArrayList<String>();
	}
	
	public ArrayList<String> getAllLinks() {
		return allLinks;
	}

	public ArrayList<String> getCssLinks() {
		return cssLinks;
	}

	public ArrayList<String> getJsLinks() {
		return jsLinks;
	}

	public void generateLinks(String url){
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements media = doc.select("[src]");
		Elements links = doc.select("a[href]");        
        Elements imports = doc.select("link[href]");
        
        //("\nMedia: (%d)", media.size());
        for (Element src : media) {
        	
        	if(src.attr("abs:src") != null && src.attr("abs:src").trim().length() > 0){
        		//System.out.println(src.attr("abs:src"));
        		if (!(src.tagName().equals("img"))){
        			if(!jsLinks.contains(src.attr("abs:src")))
        				jsLinks.add(src.attr("abs:src"));
        		}
        		if(!allLinks.contains(src.attr("abs:src")))
        			allLinks.add(src.attr("abs:src"));
        	}
        	
            
        }
        
  
        //("\nImports: (%d)", imports.size());
        for (Element link : imports) {
        	//System.out.println(link.attr("abs:href"));
        	if(link.attr("abs:href") != null && link.attr("abs:href").trim().length() > 0){
        		if(!cssLinks.contains(link.attr("abs:href"))){
        			cssLinks.add(link.attr("abs:href"));  		
            		allLinks.add(link.attr("abs:href"));
        		}
        	}

        }

        //("\nLinks: (%d)", links.size());
        for (Element link : links) {
        	//System.out.println(link.attr("abs:href"));
        	if(link.attr("abs:href") != null && link.attr("abs:href").trim().length() > 0){
        		if(!allLinks.contains(link.attr("abs:href"))){
            		allLinks.add(link.attr("abs:href"));
        		}
        	}
        	
        }
	}
}
