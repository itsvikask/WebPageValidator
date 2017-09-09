package com.webpagevalidator.css;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.webpagevalidator.util.PropertyLoader;

public class CSSValidator {

public CSSErrorPOJO validate(String url){
		
		if(url == null || !url.endsWith(".css")){
			return null;
		}
	
		String cssFiles = new PropertyLoader().getProperty("cssSkip");
		
		if(cssFiles != null){
			String cssFilestoSkip[] = cssFiles.split(",");
			for(String css : cssFilestoSkip){
				if(url != null && url.endsWith(css)){
					//System.out.println("Skip "+css+" - "+url);
					return null;
				}
			}
		}
		
		String warningLevel = new PropertyLoader().getProperty("warningLevel");
		
		String response = null;
		Map<String, Object> queryConf = new HashMap<String, Object>();
		queryConf.put("uri", url);
		queryConf.put("profile", "css3");
		//queryConf.put("medium", "all");
		if(warningLevel != null){
			queryConf.put("warning", warningLevel);
		}else{
			queryConf.put("warning", "0");
		}
		
		//queryConf.put("lang", "en");
		queryConf.put("output", "soap12");
		
		HttpResponse<String> uniResponse = null;
		try {
			uniResponse = Unirest.get(new PropertyLoader().getProperty("localhost")+"/css-validator/validator")
			    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36")
			    .header("Content-Type", "text/html; charset=UTF-8")
			    .queryString(queryConf)
			    .asString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = uniResponse.getBody();

	    String TEST_XML_STRING = response.toString();
	    
	    JSONObject xmlJSONObj = null;
		try {
            xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
		
		 CSSErrorPOJO cssErrors = new CSSErrorPOJO();
		 ObjectMapper mapper = new ObjectMapper();
		 
		 //System.out.println(xmlJSONObj.toString().replaceAll("m:", "").replaceAll("xmlns:", "").replaceAll("env:", "").replaceAll("xml:", "").replaceAll("Envelope", "envelope"));
		 
		 if(xmlJSONObj != null){
			 try {
				 cssErrors = mapper.readValue(xmlJSONObj.toString().replaceAll("m:", "").replaceAll("xmlns:", "").replaceAll("env:", "").replaceAll("xml:", "").replaceAll("Envelope", "envelope").replaceAll("Body", "body"), CSSErrorPOJO.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 //System.out.println(cssErrors.toString());
		//System.out.println("xmlJSONObj : "+xmlJSONObj);
		
		return cssErrors;
		//return jsonPrettyPrintString;
	}

	public Warning[] getWarnings(CSSErrorPOJO cssErrors){
		
		if(cssErrors != null && cssErrors.getEnvelope() != null && cssErrors.getEnvelope().getBody() != null && 
			cssErrors.getEnvelope().getBody().getCssvalidationresponse() != null && 
			 cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult() != null &&
			  cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getWarnings() != null &&
				cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getWarnings().getWarninglist() != null &&
				cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getWarnings().getWarningcount() > 0 ){
			return cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getWarnings().getWarninglist().getWarning();
		}
		
		return null;
	}
	
	public Error[] getErrors(CSSErrorPOJO cssErrors){
		
		if(cssErrors != null && cssErrors.getEnvelope() != null && cssErrors.getEnvelope().getBody() != null && 
			cssErrors.getEnvelope().getBody().getCssvalidationresponse() != null && 
			 cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult() != null &&
			  cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getErrors() != null &&
				cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getErrors().getErrorlist() != null &&
				cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getErrors().getErrorcount() > 0 ){
			return cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getErrors().getErrorlist().getError();
		}
		
		return null;
	}
	
	public Boolean isNoErrors(CSSErrorPOJO cssErrors){
		if(null == this.getWarnings(cssErrors) && null == this.getErrors(cssErrors)){
			return true;
		}
		return false;
	}
}
