<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.02.2018
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="en_EN" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="login.form.sign_in"/> </title>
</head>
<body>
<form name="LoginForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login">
    <fmt:message key="login.form.login"/>:<br/>
    <input type="text" name="login" value=""/>
    <br/><fmt:message key="login.form.password"/>:<br/>
    <input type="text" name="password" value=""/>
    <input type="submit" value=<fmt:message key="login.form.submit"/>
</form>
</body>
</html>
