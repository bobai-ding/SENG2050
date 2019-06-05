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
			<tr>
				<td><c:out value="${specificReport.author.uid}"/></td>
				<td><c:out value="${specificReport.reportContent}"/></td>
				<td><c:out value="${specificReport.time}"/></td>
				<td><c:out value="${specificReport.date}"/></td>
			</tr>
	</table>
</body>
</html>