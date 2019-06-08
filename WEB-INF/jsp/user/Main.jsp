<%--
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Main page
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Main page</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	<h1>Welcome to the UoN IT Issue Reporting System!</h1>
	<h4>Logged in as: <c:out value="${user.getName()}"/> </h4>
	<h4>What would you like to do?</h4>
	
	<hr>
	<br>
	<br>
	
	<div class="main">
		<table>
			<tr>
				<td>
					<form action="KnowledgeBase" method="GET">
						<%request.setAttribute("user", request.getUserPrincipal()); %>
						<input type="submit" value="Knowledge Base" name="submit" class="bigbutton">
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action="CreateReport" method="GET">
						<%request.setAttribute("user", request.getUserPrincipal()); %>
						<input type="submit" value="Create Report" name="submit" class="bigbutton">
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action="ViewReports" method="GET">
						<%request.setAttribute("user", request.getUserPrincipal()); %>
						<input type="submit" value="View Reports" name="submit" class="bigbutton">
					</form>
				</td>
			</tr>
		
		</table>
	</div>
	<br>
	<hr>
		<form action="Logout" method="POST">
				<input type="submit" value="Logout" name="submit" class="bigbutton">
		</form>
	<br>
	<hr>	
	<% /* <p> commented out for security </p>
	<form action="Controller" method="POST">
		<input type="hidden" value="<c:out value="${user.getName()}"/>" name="uid">
		<input type="submit" value="DEBUG THE SHIT OUT OF EVERYTHING" name="submit" class="bigbutton2">
	</form>
	*/ %>
				
</body>
</html>