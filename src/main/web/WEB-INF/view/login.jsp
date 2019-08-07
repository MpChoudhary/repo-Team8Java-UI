<%--
  Created by IntelliJ IDEA.
  User: tau
  Date: 2019-08-01
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <a href="home">Home</a>
    <h1>Login</h1>
    <form action="LoginHandler" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username">
        <label for="password">Password:</label>
        <input type="text" id="password" name="password">
        <input type="submit">
    </form>
</body>
</html>
