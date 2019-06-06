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
		<tr>
			<th>Report ID</th>
			<th>UserID</th>
			<th>Title</th>
			<th>Type</th>
			<th>Time</th>
			<th>Date</th>
		</tr>
			<tr>
				<td><c:out value="${specificReport.reportid}"/></td>
				<td><c:out value="${specificReport.author.uid}"/></td>
				<td><c:out value="${specificReport.title}"/></td>
				<td><c:out value="${specificReport.type}"/></td>
				<td><c:out value="${specificReport.time}"/></td>
				<td><c:out value="${specificReport.date}"/></td>
			</tr>
	</table>
	
	<h3> Report message </h3>
	<p><c:out value="${specificReport.reportContent}"/><p>
	
	<h3> Add Comment </h3>
	
	<form action="Controller" method="POST">
			<input type="text" name="uid" placeholder="UserID">
			<input type="text" name="comment" placeholder="Write Comment here">
			<input type="hidden" name="reportid" value="<c:out value="${specificReport.reportid}"/>">
			<input type="submit" value="ADD COMMENT" name="submit">
	</form>
	
	<h3> Comments </h3>
	
	<table>
		<tr>
			<th>comment number<th>
			<th>Author</th>
			<th>time</th>
			<th>date</th>
		<tr>
		<c:forEach var="comment" items="${comments}">
			<tr>
				<th><c:out value="${comment.commentNum}"/></th>
				<th><c:out value="${comment.reportid}"/></th>
				<th><c:out value="${comment.author}"/></th>
				<th><c:out value="${comment.time}"/></th>
				<th><c:out value="${comment.date}"/></th>
			</tr>
			<tr>
				<td> Comment Message: <td>
				<td colspan="4"><c:out value="${comment.comment}"/></td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>