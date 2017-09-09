package com.webpagevalidator.util;


import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.SourceFile;
import com.webpagevalidator.css.CSSErrorPOJO;
import com.webpagevalidator.css.CSSValidator;
import com.webpagevalidator.css.Error;
import com.webpagevalidator.css.Warning;

public class ResultTester {
	public void testCSSResult(String cssUrl){
		 CSSValidator cssValidator = new CSSValidator();
		CSSErrorPOJO cssErrors = cssValidator.validate(cssUrl);
		
		if(cssValidator.getWarnings(cssErrors) != null){
			for(Warning warning : cssValidator.getWarnings(cssErrors)){
				System.out.println(warning.getType()+" "+warning.getMessage());
			}
			
		}
		
		if(cssValidator.getErrors(cssErrors) != null){
			for(Error error : cssValidator.getErrors(cssErrors)){
				System.out.println(error.getType()+" "+error.getMessage());
			}
			
		}
		//System.out.println(cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getWarnings().getWarninglist().getWarning().toString());
		
		//System.out.println(cssErrors.getEnvelope().getBody().getCssvalidationresponse().getResult().getErrors().getErrorlist().getError().toString());
	}
	
	public void testJSResult(String code){
		
		
		 Compiler compiler = new Compiler();

		    CompilerOptions options = new CompilerOptions();
		    // Advanced mode is used here, but additional options could be set, too.
		    CompilationLevel.ADVANCED_OPTIMIZATIONS.setOptionsForCompilationLevel(
		        options);

		    // To get the complete set of externs, the logic in
		    // CompilerRunner.getDefaultExterns() should be used here.
		    SourceFile extern = SourceFile.fromCode("externs.js",
		        "function alert(x) {}");

		    // The dummy input name "input.js" is used here so that any warnings or
		    // errors will cite line numbers in terms of input.js.
		    SourceFile input = SourceFile.fromCode("input.js", code);

		    // compile() returns a Result, but it is not needed here.
		    compiler.compile(extern, input, options);
		    
		    System.out.println(compiler.getResult().toString());
		    
	}
}
