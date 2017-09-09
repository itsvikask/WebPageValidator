package com.webpagevalidator.js;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;

import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.SourceFile;
import com.webpagevalidator.util.PropertyLoader;


public class JSValidator {
	
	public Result validate(String url){
		
		String code=getJSCode(url);
		
		if( code != null){
		//System.out.println(code);
		Compiler compiler = new Compiler();

	    CompilerOptions options = new CompilerOptions();
	    
	    // Set Compilation Level
	    
	    String compLevel = new PropertyLoader().getProperty("warningLevel");
	    
	    if(compLevel != null && compLevel.equalsIgnoreCase("WHITESPACE_ONLY")){
	    	CompilationLevel.WHITESPACE_ONLY.setOptionsForCompilationLevel(
	    	        options);
	    }else if(compLevel != null && compLevel.equalsIgnoreCase("ADVANCED_OPTIMIZATIONS")){
	    	CompilationLevel.ADVANCED_OPTIMIZATIONS.setOptionsForCompilationLevel(
	    	        options);
		}else if(compLevel != null && compLevel.equalsIgnoreCase("SIMPLE_OPTIMIZATIONS")){
			CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(
			        options);
		}else{
			CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(
			        options);
		}   
	    
	    
	    // To get the complete set of externs, the logic in
	    // CompilerRunner.getDefaultExterns() should be used here.
	    SourceFile extern = SourceFile.fromCode("externs.js","");
	    
	    // The dummy input name "input.js" is used here so that any warnings or
	    // errors will cite line numbers in terms of input.js.
	    SourceFile input = SourceFile.fromCode("input.js", code);

	    // compile() returns a Result, but it is not needed here.
	    compiler.compile(extern, input, options);

	    //System.out.println(url+" : "+compiler.getErrorCount()+" "+compiler.getWarningCount());
	    
		return compiler.getResult();
		
		}
		
		return null;
	}
	
	public String getJSCode(String url){
		
		try {
			return IOUtils.toString(new URI(url), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
