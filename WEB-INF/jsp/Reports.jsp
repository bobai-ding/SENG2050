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
	<h1> Reports </h1>
	<h4>Logged in as: <c:out value="${user.getName()}"/> </h4>
	
	<%if (session.getAttribute("newReport").equals(true)){
        	out.print("<h4>You have sucessfully made a new report</h4>");
        	session.setAttribute("newReport", false);
    	}
    	%>
	
	<hr>
	<div class="bottom">
	<form action="ViewReports" method="POST">
			<input type="submit" value="LOAD" name="submit">
			<%if(request.isUserInRole("staff")){ //remove button only exists for staff%>
			<input type="submit" value="REMOVE TABLE" name="submit">
			<%} %>
	</form>
	</div>
	
	<div class="left">
		<form action="redirect" method="POST">
			Sort by: <input type="radio" name="order" value="status" required checked>By status <input type="radio" name="order" value="categories">By category <input type="radio" name="order" value="date">By date
			<input type="submit" value="Sort!">
			<input type="hidden" name="sender" value="/WEB-INF/jsp/Reports.jsp">
			<input type="hidden" name="knowledge" value="false">
			<input type="hidden" name="user" value="${user.getName()}">			
		</form>
	</div>
	
	
	<h3>List of Reports</h3>
		
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
			<th>Time Resolved</th>
			<th>Date Resolved</th>
			<th>Status</th>
			<th>in knowledge base</th>
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
				<td><c:out value="${report.getType()}"/></td>
				<td><c:out value="${report.getTime()}"/></td>
				<td><c:out value="${report.getDate()}"/></td>
				<td><c:out value="${report.getTimeResolved()}"/></td>
				<td><c:out value="${report.getDateResolved()}"/></td>
				<td><c:out value="${report.status}"/></td>
				<td><c:out value="${report.inKnowledge}"/></td>
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
				<td><c:out value="${report.getTime()}"/></td>
				<td><c:out value="${report.getDate()}"/></td>
				<td><c:out value="${report.getTimeResolved()}"/></td>
				<td><c:out value="${report.getDateResolved()}"/></td>
				<td><c:out value="${report.status}"/></td>
				<td><c:out value="${report.inKnowledge}"/></td>
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
		<input class="largebutton" type="submit" value="Return to Main Menu" name="submit">
		</form>
</body>
</html>
