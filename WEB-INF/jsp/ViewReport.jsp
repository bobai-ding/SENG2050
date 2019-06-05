<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>View Report</title>
</head>
<body>
	<h1> View Report </h1>
	<table>
		<tr><th>UserID</th><th>Title<th>Report Content</th><th>Time</th><th>Date</th></tr>
			<tr>
				<td><c:out value="${specificReport.reportid}"/></td>
				<td><c:out value="${specificReport.author.uid}"/></td>
				<td><c:out value="${specificReport.type}"/></td>
				<td><c:out value="${specificReport.title}"/></td>
				<td><c:out value="${specificReport.time}"/></td>
				<td><c:out value="${specificReport.date}"/></td>
			</tr>
	</table>
	
	<h3> Report message </h3>
	<p><c:out value="${specificReport.reportContent}"/><p>
	
	Need to show comments here
</body>
</html>