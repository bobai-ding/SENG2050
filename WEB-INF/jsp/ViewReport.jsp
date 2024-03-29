<%--
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: View Specific Report page
 --%>

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
	<Form action="redirect" method="GET"> 
		<%request.setAttribute("user", request.getUserPrincipal()); %>
		<input class="largebutton" type="submit" value="Return to Main Menu" name="submit">
	</form>
	<br>
	<Form action="ViewReports" method="GET"> 
		<%request.setAttribute("user", request.getUserPrincipal()); %>
		<input class="largebutton" type="submit" value="Back to Reports" name="submit">
	</form>
	<br>
	
	<%if(request.isUserInRole("staff")) { %>
			<div class=border>
				<form action="editReport" method="POST">
					<p> Please choose the state of the report: </p>
					
					<div class="bottom">
						<c:if test = "${specificReport.status != 'new'}" >
							<input type="radio" name="state" value="new" required>New<br>
						</c:if>
						<c:if test = "${specificReport.status != 'inProgress'}" >
							<input type="radio" name="state" value="inProgress" required >In Progress<br>
						</c:if>
						
						<c:if test = "${specificReport.status != 'completed'}" >
							<input type="radio" name="state" value="completed" required>Completed<br>
						</c:if>
						<c:if test = "${specificReport.status != 'resolved'}" >
							<input type="radio" name="state" value="resolved"  required>Resolved<br>
						</c:if>
					</div>
					
					<c:if test = "${specificReport.status != 'resolved'}" >
						<div class="bottom" ><input type="text" name="resolveDetails" placeholder="Enter Resolution Details Here"><br></div>
					</c:if>
					<input type="hidden" name="knowledge" value="false">
					<input type="hidden" name="reportID" value="${specificReport.reportid}">
					<input type="submit" name="submit">
				</form>
				
				<c:if test = "${specificReport.status == 'resolved' || specificReport.status == 'completed'}" >
					<form action="editReport" method="POST">
						<hr>
						<p> Please choose if the report should be added to the knowledge base: </p>
						<div class="bottom">
							<input type="radio" name="knowledge" value="false">No<br>
							<input type="radio" name="knowledge" value="true" required>Yes<br>
							<input type="hidden" name="reportID" value="${specificReport.reportid}">
						</div>
						<input type="submit" name="submit">
					</form>
				</c:if>
			</div>
		<% } else { %>
		
			<c:if test="${user.getName() == specificReport.getAuthor().getUid()}">
				<c:if test = "${specificReport.status != 'resolved'}">
					<div class=border>
						<c:if test = "${specificReport.status == 'completed'}">
							<h3>Please select if the the issue has been resolved:</h3>
							<form action="editReport" method="POST">
								<div class="bottom">
									<input type="radio" name="state" value="inProgress">No<br>
									<input type="radio" name="state" value="resolved" required>Yes<br>
									<input type="hidden" name="reportID" value="${specificReport.reportid}">
									<input type="hidden" name="knowledge" value="false">
								</div>
								<input type="submit" name="submit">
							</form>
						</c:if>
						<c:if test = "${specificReport.status != 'completed'}">
							<form action="editReport" method="POST">
								<h3>Has the problem been solved?</h3>
								<div class="bottom">
									<input type="checkbox" name="state" value="resolved" required>Yes 
									<input type="hidden" name="reportID" value="${specificReport.reportid}">
									<input type="hidden" name="knowledge" value="false">
								</div>
								<input type="submit" name="submit">
							</form>
						</c:if>
					</div>
				</c:if>	
			</c:if>
				
	
		<% } %>
	
	
	
	<c:if test = "${specificReport.status == 'resolved' || specificReport.status == 'completed'}" >
	
		<h3> Resolution Details </h3>
		<p><c:out value="${specificReport.resolutionDetails}"/><p>
		<hr>
	
	</c:if>
	
	<h3>Author </h3>
	<table>
		<tr>
			<th>UserId</th>
			<th>first name</th>
			<th>Last name</th>
			<th>email</th>
			<th>Phone number</th>
		</tr>
			<tr>
				<td><c:out value="${specificReport.author.uid}"/></td>
				<td><c:out value="${specificReport.author.firstName}"/></td>
				<td><c:out value="${specificReport.author.lastName}"/></td>
				<td><c:out value="${specificReport.author.email}"/></td>
				<td><c:out value="${specificReport.author.phoneNum}"/></td>
			</tr>
	</table>
	
	<h3>Original Post </h3>
	<table>
		<tr>
			<th>Report ID</th>
			<th>Title</th>
			<th>Type</th>
			<th>Time</th>
			<th>Date</th>
			<th>Status</th>
		</tr>
			<tr>
				<td><c:out value="${specificReport.reportid}"/></td>
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
		
	<c:if test = "${(specificReport.status != 'resolved') || specificReport.inKnowledge == 'true'}">
		
		<h3> Add Comment </h3>
		
		<form action="Controller" method="POST">
				<div class="bottom">
					<input type="hidden" name="uid" value="<c:out value="${user.getName()}"/>">
					<input type="text" name="comment" placeholder="Write Comment here">
					<input type="hidden" name="reportid" value="<c:out value="${specificReport.reportid}"/>">
				</div>
				<input type="submit" value="ADD COMMENT" name="submit">
		</form>
		
	</c:if>
	
	<h3> Comments </h3>
	<c:forEach var="comment" items="${comments}">
		<div class="bottom">
		<table>
			<tr>
				<td colspan="5"><c:out value="${comment.comment}"/></td>
			</tr>
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
				
		</table>
		</div>
		<hr>
		<br>
	</c:forEach>
</body>
</html>