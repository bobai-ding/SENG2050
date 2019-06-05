<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>This is a specific report</title>
</head>
<body>
	<h1>This is a specific report</h1>
	
	<table <c:out/>>
		<tr><th>UserID</th></tr>
		<c:forEach var="report" items="${reports}">
			<tr>
				<td><c:out value="${report.author.uid}"/></td>
				<td><c:out value="${report.reportContent}"/></td>
				<td><c:out value="${report.time}"/></td>
				<td><c:out value="${report.date}"/></td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="Controller" method="POST">
			<input type="submit" value="BACK" name="submit">
	</form>
</body>
</html>