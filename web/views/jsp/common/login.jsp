<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.02.2018
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<form name="LoginForm" method="post" action="/controller">
    <input type="hidden" name="command" value="login">
    Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password<br/>
    <input type="text" name="password" value=""/>
    <input type="submit" value="Log in"/>
</form>
</body>
</html>
