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
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<link href="${pageContext.request.contextPath}/views/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<head>
</head>
<body>
<div class="col-md-4">
    <nav id="sidebar" class="navbar navbar-dark">
        <div class="list-group">
    <a class="list-group-item list-group-item-action"  href="${pageContext.request.contextPath}/controller?command=show_user_status"><fmt:message key="user.menu.status"/> </a>
    <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/controller?command=show_all_faculty"><fmt:message key="user.menu.registration" /></a>
    <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/controller?command=go_to_change_password_page"><fmt:message key="user.menu.change_password"/></a>
    <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/controller?command=go_to_cancel_registration_page"><fmt:message key="user.menu.cancel"/> </a>
    <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/controller?command=go_to_user_data_page"><fmt:message key="user.menu.user_data"/> </a>
            <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/controller?command=show_user_subjects"><fmt:message key="user.registration.message.subjects"/> </a>
        </div>
    </nav>
</div>
</body>
</html>
