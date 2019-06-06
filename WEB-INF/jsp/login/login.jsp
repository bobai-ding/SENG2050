<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login Page</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h1> Welcome to the UoN IT Issue Reporting System </h1>
	<hr>
	<h3>Please Login</h3>
	<form action="j_security_check" method="POST">
		<input type="text" name="j_username" placeholder="username..."/>
		<br/>
		<input type="password" name="j_password" placeholder="password..."/>
		<br/>
		<input type="submit" value="Login"/>
	</form>
</body>
</html>