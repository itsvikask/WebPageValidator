<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<a href="."><span>Web Page Validator</span></a>
	</h1>
</div>

<!-- <form method="post" action="result.jsp">
  <div class="form-group">
    <input type="text" class="form-control" name="inputUrl" id="inputUrl" placeholder="Enter URL" required>
    <small id="urlHelp" class="form-text text-muted">Enter the web page URL to validate</small>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form> -->

</br>
</br>

<h5> Upload the file with the list of URL's to validate</h5>

</br>
</br>

 <div class="row">
    <div class="col">
    <div class="alert alert-success" role="alert">
     Instructions:
     <ol>
     	<li> Excel sheet to contain the list of URL's of pages to be validated</li>
     	<li> Excel sheet should be of format *.xls or *.xlsx</li>
     </ol>
     </div>
    </div>
    
    <div class="col">
    
    <div class="alert alert-success" role="alert">
 
      <form action="/WebPageValidator/FileUploader" method="post" enctype="multipart/form-data">


		Please select Excel file to upload : </br></br>
		
		<label class="custom-file">
		<input type="file" name="file" accept=".xls,.xlsx" class="btn btn-primary"/>
		</label>
		
		</br></br>
		<input type="submit" value="Upload File" class="btn btn-primary"/>
		
		
	  </form>
	
	</div>
    </div>
    <div class="col">
      
    </div>
  </div>
  <div class="row">
  	<div class="col">
  		<div class="alert alert-success" role="alert">
  			<p>Sample screenshot of excel with URL's is as shown below:</p>
	 		<img src="images/sample.jpg" />
	 	</div>
	</div>
  </div>


</div>
</body>
</html>