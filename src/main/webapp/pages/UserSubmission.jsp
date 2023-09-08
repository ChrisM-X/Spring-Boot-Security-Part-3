<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Form for Database</title>
</head>

<h1 style="background-color:rgb(255, 165, 0);">Welcome to the Site!</h1>

<body>
<form method="post" action="submitData">
    Enter Username: <input type="text" name="username"><br>
    Enter Email: <input type="email" name="email"><br>
    <input type="submit" value="Submit"><br><br>
</form>

<div>
    <a href="http://localhost:8081/getUserProfile" target="_blank">Click here to view User Profile details.</a>
</div>
</body>
</html>