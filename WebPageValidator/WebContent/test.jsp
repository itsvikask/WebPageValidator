<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing the loading</title>
    <script type="text/javascript">
    function processRecords(){
        document.getElementById("mainContent").src = "report.jsp";
    }
    </script>
</head>
<body onload="processRecords()">
    <iframe id="mainContent" width="100%" style="border: none;margin: 0; padding: 0; height: 100%; overflow: hidden;" seamless src="loader.jsp"></iframe>
</body>
</html>