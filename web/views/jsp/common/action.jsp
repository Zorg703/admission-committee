<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.02.2018
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all users</title>
</head>
<body>
<%--a href="/controller">Show all users</a>--%>

<form name="doAction" method="post" action="/controller">
    <input type="hidden" name="command" value="show_all_users"/>
    <input type="submit" name="show" value="action"/>
<c:forEach var="user" items="${userList}" varStatus="loop">
    
</form>
</body>
</html>
