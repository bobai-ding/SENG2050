<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>View Report</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h1> View Report </h1>
	<h4>Logged in as: <c:out value="${user.getName()}"/> </h4>
	<hr>
	<h3>Original Post </h3>
	<table>
		<tr>
			<th>Report ID</th>
			<th>UserID</th>
			<th>Title</th>
			<th>Type</th>
			<th>Time</th>
			<th>Date</th>
			<th>Status</th>
		</tr>
			<tr>
				<td><c:out value="${specificReport.reportid}"/></td>
				<td><c:out value="${specificReport.author.uid}"/></td>
				<td><c:out value="${specificReport.title}"/></td>
				<td><c:out value="${specificReport.type}"/></td>
				<td><c:out value="${specificReport.time}"/></td>
				<td><c:out value="${specificReport.date}"/></td>
				<td><c:out value="${specificReport.status}"/></td>
			</tr>
	</table>
	
		
	<h3> Report message </h3>
	<p><c:out value="${specificReport.reportContent}"/><p>
	
	<hr>
		
	<h3> Add Comment </h3>
	
	<form action="Controller" method="POST">
			<input type="hidden" name="uid" value="<c:out value="${user.getName()}"/>">
			<input type="text" name="comment" placeholder="Write Comment here">
			<input type="hidden" name="reportid" value="<c:out value="${specificReport.reportid}"/>">
			<input type="submit" value="ADD COMMENT" name="submit">
	</form>
	
	
	<h3> Comments </h3>
	<c:forEach var="comment" items="${comments}">
		<div class="bottom">
		<table>
				<tr>
					<th>Comment number</th>
					<th>ReportID</th>
					<th>Author</th>
					<th>time</th>
					<th>date</th>
				<tr>
				<tr>
					<td><c:out value="${comment.commentNum}"/></td>
					<td><c:out value="${comment.reportid}"/></td>
					<td><c:out value="${comment.author}"/></td>
					<td><c:out value="${comment.time}"/></td>
					<td><c:out value="${comment.date}"/></td>
				</tr>
				<tr>
					<td>Comment Message:</td>
					<td colspan="4"><c:out value="${comment.comment}"/></td>
				</tr>
		</table>
		</div>
		<hr>
		<br>
	</c:forEach>
</body>
</html>