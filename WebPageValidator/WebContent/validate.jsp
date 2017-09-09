<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
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
if(request.getAttribute("urlList") != null){
	
	ArrayList<String> urls = (ArrayList<String>)request.getAttribute("urlList");
	session.setAttribute("urlList", urls);
	
	if(urls != null && urls.size() > 0){
		
%>
<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>URL</th>
      <th> Click To</th>
    </tr>
  </thead>
  <tbody>

<%	
		for(int i = 0; i<urls.size(); i++){
			
		

%>
    <tr>
      <th scope="row"><%= i+1 %></th>
      <td> <%= urls.get(i) %></td>
      <td> <a href='<%= "/WebPageValidator/result.jsp?inputUrl="+ urls.get(i) %>' class="btn btn-primary btn-lg" role="button" aria-disabled="true" target=_blank >Validate</a> </td>

    </tr>
    <%} %>
  </tbody>
</table>

<a href='<%= "/WebPageValidator/report.jsp" %>' class="btn btn-primary btn-lg" role="button" aria-disabled="true" target=_blank >Generate Report</a>

<%
	}else{
%>
<div class="p-3 mb-2 bg-danger text-white">
		Upload Failed! Please upload an excel file with extension *.xls. Click <a href="/WebPageValidator/index.jsp">here</a> to go back to Home Page.
	</div>
<%
}
}
else{
%>

	<div class="p-3 mb-2 bg-danger text-white">
		Upload Failed! Please upload an excel file with extension *.xls. Click <a href="/WebPageValidator/index.jsp">here</a> to go back to Home Page.
	</div>


<%} %>
</div>

</body>
</html>