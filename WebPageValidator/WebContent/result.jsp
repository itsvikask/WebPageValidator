<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.webpagevalidator.html.*" %>
<%@ page import="com.webpagevalidator.parser.*" %>
<%@ page import="com.webpagevalidator.css.*" %>
<%@ page import="com.webpagevalidator.js.*" %>
<%@ page import="com.webpagevalidator.util.*" %>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="org.json.*" %>
<%@ page import="com.google.javascript.jscomp.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Page Validator</title>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/popper.min.js"></script>
<link href="css/style.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">

</br>
<div id="banner" class="">
	<h1 id="title">
		<a href="/WebPageValidator/index.jsp"><span>Web Page Validator</span></a>
	</h1>
</div>

<%
	
	if(request.getParameter("inputUrl") != null){
	
	//https://wesqa.customersvc.com/servlet/Show?WESPAGE=csp-ngs/login.jsp&MSRSMAG=NF
	String url = request.getParameter("inputUrl");
	
	int pageStatus = new HTTPStatus().findHTTPStatus(url);
	//out.println(pageStatus);
	if(pageStatus > 400){
%>
	<div class="p-3 mb-2 bg-danger text-white">
		Oops! Something wrong with the URL: <a href="<%= url %>"> <%= url %> </a>. Please check the URL again.
	</div>
<%
	}else{
	HTMLValidator htmlvalidator = new HTMLValidator();
	HTMLErrorPOJO htmlErrors  = htmlvalidator.validate(url);
	
	HTMLParser htmlParser = new HTMLParser();
	htmlParser.generateLinks(url);
	
%>

<h5>Showing results for <a href="<%= url %>" target=_blank ><%= url %></a></h5>
</br>
<%
	if(pageStatus != 200){
%>
	<div class="p-3 mb-2 bg-danger text-white">
		Alert! This URL seems to be redirected to some other page!
	</div>
<%
	}
%>
<div id="accordion" role="tablist">
  
  <%-- HTML --%>
  <div class="card">
  
    <div class="card-header" role="tab" id="headingOne">
      <h5 class="mb-0">
        <a class="collapsed" data-toggle="collapse" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          HTML Validation Result ->
        </a>
      </h5>
    </div>

	<% if( htmlErrors != null && htmlErrors.getMessages() != null ){ %>
    <div id="collapseOne" class="collapse" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
        
        <div id="results">
		<ol class="">
			<% for(Messages message:htmlErrors.getMessages()){ %>
				<% if(message.getType() != null && message.getType().equalsIgnoreCase("error")){ %>
					<li class="<%= message.getType() %>">
				<%}else{ %>
					<li class="<%= message.getType()+' '+message.getSubType() %>">
				<%} %>
			
					<p>
						<% if(message.getType() != null && message.getType().equalsIgnoreCase("error")){ %>
							<strong> <%= message.getType() %></strong>
						<%}else{%>
							<strong> <%= message.getSubType() %></strong>
						<%} %>
						<span>
						
						<%
						if(message.getMessage() != null){
						%>
						<code>
						<%
							out.println(HTMLStringEscape.stringToHTMLString(message.getMessage().toString()));
						%>
						</code>
						<%
						}
						%>
						
						</span>
						
					</p>
					
					<p class="location">
						From line <span class="first-line"> <%= message.getLastLine() %></span>, 
						column <span class="first-col"> <%= message.getFirstColumn() %></span>; 
						to line <span class="last-line"><%= message.getLastLine() %></span>, 
						column <span class="last-col"><%= message.getLastColumn() %></span>
					</p>
					
					<p class="extract">
					<%
					if(message.getExtract() != null){
					%>
					<code>
					<%
						out.println(HTMLStringEscape.stringToHTMLString(message.getExtract().toString()));	
					%>
					</code>
					<%
					}
					%>
					</p>
				</li>
			<%}
		   %> 
		</ol>
		</div>

      </div>
    </div>
    <%} %>
  </div>
  
  <%-- CSS --%>
  <div class="card">
  
    <div class="card-header" role="tab" id="headingTwo">
      <h5 class="mb-0">
        <a class="collapsed" data-toggle="collapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
         CSS Validation Report ->
        </a>
      </h5>
    </div>
    <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion">
      <div class="card-body">
        
        <%
        CSSValidator cssValidator = new CSSValidator();
        
        if(htmlParser.getCssLinks() != null){
    	for(String cssUrl : htmlParser.getCssLinks()){
    		
    		CSSErrorPOJO cssErrors = cssValidator.validate(cssUrl);
    		if(!cssValidator.isNoErrors(cssErrors)){
    	%>
    	<div id="results">
			<ol class="">
			<h5> CSS : <%= cssUrl %></h5>
    	<%
    		if(cssValidator.getWarnings(cssErrors) != null){
    			for(Warning warning : cssValidator.getWarnings(cssErrors)){
    				
    	%>
			<li class="info warning">
			
					<p>
						<strong> warning </strong>
						<span>
						
						<%
						if(warning.getMessage() != null){
						%>
						<code>
						<%= warning.getMessage() %>
						</code>
						<%
						}
						%>
						
						</span>
						
					</p>
					
					<p class="location">
						At Line No <span class="first-line"> <strong><%= warning.getLine() %></strong></span>	
					</p>
					
				</li>
			<%}	}%> 
	
    	
    	<%
    		
    		
 		if(cssValidator.getErrors(cssErrors) != null){
		for(com.webpagevalidator.css.Error error : cssValidator.getErrors(cssErrors)){
    				
    	%>
			<li class="error">
			
					<p>
						<strong> error </strong>
						<span>
						<code>
						<%= error.getMessage() %>
						</code>	
						</span>
						
					</p>
					
					<p class="location">
						At Line No <span class="first-line"><strong><%= error.getLine() %></strong></span>; 
						For <span class="first-col"><strong> <%= error.getContext() %> </strong> </span> 
					</p>
					
					<p class="extract"> 
					<%
						if(error.getSkippedstring() != null){
						%>
						In
						<code>
						<%= error.getSkippedstring() %>
						</code>
						<%
						}
						%>
					
					</p>
					
				</li>
			<%}	
			}
    		%>
    		</ol>
			</div>
    		<%
    		}
    		}
    		}%> 
        	
        
      </div>
    </div>
  </div>
  
  <%-- JS --%>
  <div class="card">
    <div class="card-header" role="tab" id="headingThree">
      <h5 class="mb-0">
        <a class="collapsed" data-toggle="collapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          JavaScript Validation Report ->
        </a>
      </h5>
    </div>
    <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree" data-parent="#accordion">
      <div class="card-body">
        
        <%	
			String test="";
			JSValidator jsValidator = new JSValidator();
			
			if(htmlParser.getJsLinks() != null){
			for(String jsUrl : htmlParser.getJsLinks()){
				
				
				com.google.javascript.jscomp.Result jsResult = jsValidator.validate(jsUrl);
			    
				if(jsResult != null && ((jsResult.warnings != null && jsResult.warnings.length > 0) || 
						(jsResult.errors != null && jsResult.errors.length > 0))){
		%>
			<div id="results">
			<ol class="">
			<h5> JavaScript : <%= jsUrl %></h5>
		<%
					//out.println(jsUrl+" : ");
					
					if(jsResult.warnings != null && jsResult.warnings.length > 0){
						for(JSError warning:jsResult.warnings){
							
		%>
			
					<li class="info warning">
					
							<p>
								<strong> warning </strong>
								<span>

								<code>
								<%= warning.description%>
								</code>
								
								</span>
								
							</p>
							
							<p class="location">
								At Line <span class="first-line"> <strong><%= warning.lineNumber %> : <%= warning.getCharno() %></strong></span>	
							</p>

						</li>
		<%					
							//out.println(warning.toString());
						}
					}
					
					if(jsResult.errors != null && jsResult.errors.length > 0){
						for(JSError error:jsResult.errors){
		%>
					<li class="error">
					
							<p>
								<strong> error </strong>
								<span>			
	
								<code>
								<%= error.description%>
								</code>
								
								</span>
								
							</p>
							
							<p class="location">
								At Line <span class="first-line"> <strong><%= error.lineNumber %> : <%= error.getCharno() %></strong></span>	
							</p>

						</li>
		<%
							//out.println(error.toString());
						}
					}

			%>
			</ol>
			</div>
			<%
				}
			  }  				
			}

		%>
		
        
      </div>
    </div>
  </div>


	<%-- Broken Link --%>
	
	<div class="card">
    <div class="card-header" role="tab" id="headingFour">
      <h5 class="mb-0">
        <a class="collapsed" data-toggle="collapse" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
          Broken Link Checker ->
        </a>
      </h5>
    </div>
    <div id="collapseFour" class="collapse" role="tabpanel" aria-labelledby="headingFour" data-parent="#accordion">
      <div class="card-body">
        
        <%
         HTTPStatus status = new HTTPStatus();
         ArrayList<String> urls = htmlParser.getAllLinks();
         
         if( urls != null){
        %>
        <table class="table table-sm">
		 <thead>
		   <tr>
		     <th>#</th>
		     <th>URL</th>
		     <th>Status Code</th>
		     <th>Status</th>
		   </tr>
		 </thead>
		 <tbody>
        <%
         for(int i=0;  i < urls.size() ; i++ ){
         %>
         	<tr>
		      <th scope="row"><%= i+1 %></th>
		      <td> <%= urls.get(i).toString() %> </td>
		  <%	  	
		  	  int httpStatus = status.findHTTPStatus(urls.get(i).toString());
		  %>
		  	<td> <%= httpStatus %></td>
		  <%
		  	  if(httpStatus < 400){
		  %>
		  	<td><a href="#" class="badge badge-success">Success</a></td>
		  <%
		  	  }else{
		  %>
			<td><a href="#" class="badge badge-danger">Failure</a></td>
		  <%
		  	  }
		  %>
		    </tr>  
         <%
        	}
        %>
          </tbody>
	</table>
	<%} %>
      </div>
    </div>
  </div>
  
</div>
<%-- Accordion End --%>

<%} } %>
</div>
<%-- Container End --%>

</body>
</html>