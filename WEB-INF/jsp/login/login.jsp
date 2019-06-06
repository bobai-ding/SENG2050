<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="j_security_check" method="POST">
		<input type="text" name="j_username" placeholder="username..."/>
		<br/>
		<input type="password" name="j_password" placeholder="password..."/>
		<br/>
		<input type="submit" value="Login"/>
	</form>
</body>
</html>