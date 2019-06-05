<%-- Import the Core taglib--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--create the HTML table using foreach tag--%>
<table>
	<tr><th>UserID</th></tr>
	<c:forEach var="report" items="${reports}">
		<tr>
			<td><c:out value="${report.author.uid}"/></td>
		</tr>
	</c:forEach>
</table>

<form action="Controller" method="POST">
		<input type="submit" value="LOAD" name="submit">
		<input type="submit" value="REMOVE TABLE" name="submit">
</form>

<form action="Controller" method="POST">
		<input type="text" name="uid" placeholder="UserID to delete">
		<input type="submit" value="DELETE" name="submit">
</form>

<form action="Controller" method="POST">
		<input type="text" name="uid" placeholder="UserID">

		<input type="submit" value="ADD" name="submit">
</form>