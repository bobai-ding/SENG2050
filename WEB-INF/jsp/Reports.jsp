<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Reports Page</title>
</head>

<body>
	<form action="Controller" method="POST">
			<input type="submit" value="LOAD" name="submit">
			<input type="submit" value="REMOVE TABLE" name="submit">
	</form>
	
	<table>
		<tr>
			<th>Report ID</th>
			<th>UserID</th>
			<th>Title</th>
			<th>Report Content</th>
			<th>Type</th>
			<th>Time</th>
			<th>Date</th>
		</tr>
		<c:forEach var="report" items="${reports}">
			<tr>
				<td><c:out value="${report.reportid}"/></td>
				<td><c:out value="${report.author.uid}"/></td>
				<td><c:out value="${report.title}"/></td>
				<td><c:out value="${report.reportContent}"/></td>
				<td><c:out value="${report.type}"/></td>
				<td><c:out value="${report.time}"/></td>
				<td><c:out value="${report.date}"/></td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="Controller" method="POST">
			<input type="text" name="uid" placeholder="UserID to delete/find">
			<input type="text" name="title" placeholder="Report Title">
			<input type="submit" value="DELETE" name="submit">
			<input type="submit" value="FIND" name="submit">
	</form>
	
	<form action="Controller" method="POST">
			<input type="text" name="uid" placeholder="UserID">
			<input type="text" name="title" placeholder="Report Title">
			<input type="text" name="content" placeholder="Write report here">
			Type
			<input type="radio" name="type" value="testing"> testing
			<input type="submit" value="ADD" name="submit">
	</form>
</body>
</html>