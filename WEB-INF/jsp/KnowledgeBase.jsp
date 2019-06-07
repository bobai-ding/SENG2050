<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Reports Page</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	<h1> Reports in Knowledge Base</h1>
	<h4>Logged in as: <c:out value="${user.getName()}"/> </h4>
	<hr>
	<div class="bottom">
	<form action="Controller" method="POST">
			<input type="submit" value="LOAD" name="submit">
	</form>
	</div>
		
	<div class="bottom">
	<table>
		<tr>
			<th></th>
			<th>Report ID</th>
			<th>UserID</th>
			<th>Title</th>
			<th>Report Content</th>
			<th>Type</th>
			<th>Time</th>
			<th>Date</th>
			<th>Status</th>
			<th></th>
		</tr>
		
		<%if(request.isUserInRole("staff")){ %>
			<c:forEach var="report" items="${reports}">
			<tr>
				<td> 
					<form action="Controller" method="POST">
						<input type="submit" value="OPEN" name="submit">
						<input type="hidden" value="${report.reportid}" name="reportid">
					</form>
				</td>
				<td><c:out value="${report.reportid}"/></td>
				<td><c:out value="${report.author.uid}"/></td>
				<td><c:out value="${report.title}"/></td>
				<td><p class="limited"><c:out value="${report.reportContent}"/></p></td>
				<td><c:out value="${report.type}"/></td>
				<td><c:out value="${report.time}"/></td>
				<td><c:out value="${report.date}"/></td>
				<td><c:out value="${report.status}"/></td>
				<td> 
					<form action="Controller" method="POST">
						<input type="submit" value="DELETE" name="submit">
						<input type="hidden" value="${report.reportid}" name="reportid">
					</form>
				</td>
			</tr>
		</c:forEach>
		<% } else { %>
			<c:forEach var="report" items="${reports}">
			<tr>
				<td> 
					<form action="Controller" method="POST">
						<input type="submit" value="OPEN" name="submit">
						<input type="hidden" value="${report.reportid}" name="reportid">
					</form>
				</td>
				<td><c:out value="${report.reportid}"/></td>
				<td><c:out value="${report.author.uid}"/></td>
				<td><c:out value="${report.title}"/></td>
				<td><p><c:out value="${report.reportContent}"/></p></td>
				<td><c:out value="${report.type}"/></td>
				<td><c:out value="${report.time}"/></td>
				<td><c:out value="${report.date}"/></td>
				<td><c:out value="${report.status}"/></td>
				<td> 
					<form action="Controller" method="POST">
						<input type="submit" value="DELETE" name="submit">
						<input type="hidden" value="${report.reportid}" name="reportid">
					</form>
				</td>
			</tr>
		</c:forEach>
		<% } %>
		
			
	</table>
	</div>
		<Form action="redirect" method="GET"> 
		<%request.setAttribute("user", request.getUserPrincipal()); %>
		<input class="largebutton" type="submit" value="Return to Main Menu" name="submit">
		</form>
</body>
</html>