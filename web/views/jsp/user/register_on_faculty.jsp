<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01.04.2018
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="${pageContext.request.contextPath}/views/include/header.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="user.register_on_faculty.title"/></title>
</head>

<body>
<form name="subject_marks" method="post" action="${pageContext.request.contextPath}/controller">
<input type="hidden" name="command" value="register_on_speciality">
    <c:out value="${subjects[0].subjectName}"/>
    <input type="hidden" name="first_subject" value="${subjects[0].subjectId}">
    <input type="number" required min="0" max="100" name="mark1" value="${user_params.mark1}"><br>
    <c:out value="${subjects[1].subjectName}"/>
    <input type="hidden" name="second_subject" value="${subjects[1].subjectId}">
    <input type="number" required min="0" max="100" name="mark2" value="${user_params.mark2}"><br>
    <c:out value="${subjects[2].subjectName}"/>
    <input type="hidden" name="third_subject" value="${subjects[2].subjectId}">
    <input type="number" required min="0" max="100" name="mark3" value="${user_params.mark3}"><br>
    <input class="button" type="submit">
</form>
</body>
</html>
