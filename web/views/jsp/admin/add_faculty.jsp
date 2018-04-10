<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 06.04.2018
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/include/header.jsp"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="admin.add_faculty.title"/> </title>
</head>
<body>
<form name="add-faculty-form" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="add_faculty"/>
    <fmt:message key="admin.add_faculty.name"/>
    <input type="text" name="faculty" value="" required pattern="([А-Я]{1}([а-я]{2,50}(\s)?)+)|[A-Z]{1}([a-z]{2,50}(\s)?)+"/>
    <input class="button" type="submit">
    <c:if test="${not empty faculty_name}">
        <fmt:message key="admin.add_faculty.incorrect_name"/>
    </c:if>
</form>
</body>
</html>
