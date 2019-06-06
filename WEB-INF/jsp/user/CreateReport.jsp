<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Create Report</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	<h1>Welcome to the UoN IT Issue Reporting System!</h1>
	<h4>Logged in as: <c:out value="${user.getName()}"/> </h4>
	<hr>
	
	<form action="CreateReport" method="POST">
		<p> Please chose a category: </p>
		<input type="radio" name="category" value="network">Network<br>
		<input type="radio" name="category" value="software">Software<br>
		<input type="radio" name="category" value="hardware">Hardware<br>
		<input type="radio" name="category" value="email">Email<br>
		<input type="radio" name="category" value="account">Account<br>
	
		<hr>
		
		
	
		<%
		String text = "I wish to report a problem occurring whilst using the internet.\r\n"
		+ "My location (Building/Room):\r\n"
		+ "My internet browser is (eg Internet Explorer v9 / Mozilla Firefox v12):\r\n"
		+ "I am trying to connect to the following website:\r\n"
		+ "I am able to access internal websites (Y/N):\r\n"
		+ "I have tried using an alternate internet browser (Y/N):\r\n"
		+ "I have tried restarting my computer (Y/N):\r\n"
		+ "Problem description and error message:" ; 
		%>
		

		<input type="hidden" name="uid" value="<c:out value="${user.getName()}"/>">
		<label for="title">Report Title:  </label>
		<input type="text" name="title" placeholder="Report Title">
		<br><br>
		<p>Please fill in any relevant information below: </p>
		<textarea name="content" cols="80" rows="10"><%=text %></textarea>
		<br>			
		<input class="largebutton" type="submit" value="Submit Report" name="submit">
	</form>

</body>
</html>