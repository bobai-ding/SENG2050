<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Specific report</title>
</head>
<body>
	<table>
		<tr><th>UserID</th><th>Report Content</th><th>Time</th><th>Date</th></tr>
		<c:forEach var="report" items="${reports}">
			<tr>
				<td><c:out value="${report.author.uid}"/></td>
				<td><c:out value="${report.reportContent}"/></td>
				<td><c:out value="${report.time}"/></td>
				<td><c:out value="${report.date}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>