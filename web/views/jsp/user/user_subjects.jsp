<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.03.2018
  Time: 20:36
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
    <title><fmt:message key="user.subjects.title"/></title>
</head>
<body>
<c:forEach var="subject" items="${subjects}">
    <fmt:message key="user.subjects.name"/> <c:out value="${subject.key.subjectName}"/>
    <fmt:message key="user.subjects.score"/> <c:out value="${subject.value}"/>
</c:forEach>
</body>
</html>
