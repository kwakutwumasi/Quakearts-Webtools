<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="org.apache.log4j.*" %>
<%!
	private static final Logger log = Logger.getLogger("Test.logger");
%>
<%
	if(request.getParameter("info")!=null){
		log.info("Test info message");
	} else if(request.getParameter("error")!=null){
		log.error("Test error message", new Exception("Test error"));
	} else if(request.getParameter("warn")!=null){
		log.warn("Test warn message");
	} else if(request.getParameter("debug")!=null){
		log.debug("Test debug message");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="post">
		<input type="submit" name="info" value="Info" />
		<input type="submit" name="error" value="Error" />
		<input type="submit" name="warn" value="Warn" />
		<input type="submit" name="debug" value="Debug" />
	</form>
</body>
</html>