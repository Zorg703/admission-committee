<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06.03.2018
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<link href="${pageContext.request.contextPath}/views/css/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<head>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<ui class="nav nav-pills nav-stacked navbar-dark">
    <li><a href="${pageContext.request.contextPath}/controller?command=show_user_status"><fmt:message key="user.menu.status"/> </a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_all_faculty"><fmt:message key="user.menu.registration" /></a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=go_to_change_password_page"><fmt:message key="user.menu.change_password"/></a> </li>
    <li><a href="${pageContext.request.contextPath}/controller?command=go_to_cancel_registration_page"><fmt:message key="user.menu.cancel"/> </a> </li>
    <li><a href="${pageContext.request.contextPath}/controller?command=go_to_user_data_page"><fmt:message key="user.menu.user_data"/> </a> </li>

</ui>
</nav>
</body>
</html>
