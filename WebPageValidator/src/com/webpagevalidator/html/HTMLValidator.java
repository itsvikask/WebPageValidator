package com.webpagevalidator.html;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.webpagevalidator.util.PropertyLoader;

public class HTMLValidator {
	
	public HTMLErrorPOJO validate(String url){
		
		String response = null;
		Map<String, Object> queryConf = new HashMap<String, Object>();
		queryConf.put("doc", url);
		queryConf.put("out", "json");

		HttpResponse<String> uniResponse = null;
		try {
			uniResponse = Unirest.get(new PropertyLoader().getProperty("localhost")+"/vnu/")
			    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36")
			    .header("Content-Type", "text/html; charset=UTF-8")
			    .queryString(queryConf)
			    .asString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = uniResponse.getBody();
		
		JSONObject jsonObj = null;
		 
		 try {
			 jsonObj = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 //System.out.println("JSON Object :"+jsonObj);
		 
		 HTMLErrorPOJO htmlErrors = new HTMLErrorPOJO();
		 ObjectMapper mapper = new ObjectMapper();
			
		 if(jsonObj != null){
			 try {
				 htmlErrors = mapper.readValue(jsonObj.toString(), HTMLErrorPOJO.class);
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
		 
		 //System.out.println(htmlErrors.getMessages().toString());
		
		return htmlErrors;
	}
}
