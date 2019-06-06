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
	<div class="bottom">
	<form action="Controller" method="POST">
			<input type="submit" value="LOAD" name="submit">
			<input type="submit" value="REMOVE TABLE" name="submit">
	</form>
	</div>
	<div class="bottom">
	Logged in as: <c:out value="${user.getName()}"/>
	User roles: <c:out value="${userRoles[0]}"/>
	<form action="Controller" method="POST">
			<input type="hidden" name="uid" value="<c:out value="${user.getName()}"/>">
			<input type="text" name="title" placeholder="Report Title">
			<input type="text" name="content" placeholder="Write report here">
			Type
			<input type="radio" name="type" value="testing" checked> testing
			<input type="submit" value="ADD" name="submit">
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
			<th></th>
		</tr>
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
				<td> 
					<form action="Controller" method="POST">
						<input type="submit" value="DELETE" name="submit">
						<input type="hidden" value="${report.reportid}" name="reportid">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
		
</body>
</html>