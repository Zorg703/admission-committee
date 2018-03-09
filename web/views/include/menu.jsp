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
<head>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<ui>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_all_faculty"><fmt:message key="user.menu.faculty"/> </a> </li>
    <li><a href="${pageContext.request.contextPath}/controller"><fmt:message key="user.menu.speciality"/> </a></li>
    <li><a href="${pageContext.request.contextPath}/controller"><fmt:message key="user.menu.registration" /></a></li>
    <li><a href="/controller"><fmt:message key="user.menu.change_profile"/> </a> </li>
    <li><a href="/controller"><fmt:message key="user.menu.sign_out"/> </a> </li>
</ui>
</body>
</html>
