<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.04.2018
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/include/header.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.successful.title"/> </title>
</head>
<body>
<c:if test="${not empty faculty}">
    <fmt:message key="admin.successful.add_faculty"/>
    <fmt:message key="admin.successful.faculty_name"/>
    <c:out value="${faculty.facultyName}"/>
    <fmt:message key="admin.successful.faculty_id"/>
    <c:out value="${faculty.facultyId}"/>
</c:if>
</body>
</html>
