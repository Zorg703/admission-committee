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
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="user.register_on_faculty.title"/></title>
</head>

<body>
<c:if test="${not empty subjects}">
<fmt:message key="user.register_on_faculty.name"/>:
<form name="subject_marks" method="post" action="${pageContext.request.contextPath}/controller">
<input type="hidden" name="command" value="register_on_speciality">
    <fmt:message key="user.registration.subjects_name"/>:
    <c:out value="${subjects[0].subjectName}"/><br>
    <input type="hidden" name="first_subject" value="${subjects[0].subjectId}">
    <fmt:message key="user.registration.mark"/>:
    <input type="number" required min="0" max="100" name="mark1" value=""><br>
    <fmt:message key="user.registration.subjects_name"/>:
    <c:out value="${subjects[1].subjectName}"/><br>
    <input type="hidden" name="second_subject" value="${subjects[1].subjectId}">
    <fmt:message key="user.registration.mark"/>:
    <input type="number" required min="0" max="100" name="mark2" value=""><br>
    <fmt:message key="user.registration.subjects_name"/>:
    <c:out value="${subjects[2].subjectName}"/><br>
    <input type="hidden" name="third_subject" value="${subjects[2].subjectId}">
    <fmt:message key="user.registration.mark"/>:
    <input type="number" required min="0" max="100" name="mark3" value=""><br>
    <fmt:message key="user.registration.certificate"/>:
    <input type="number"required min="0" max="100" name="avg" value="">
    <c:if test="${not empty message}">
        <fmt:message key="user.registration.message.mark"/>
    </c:if>
    <input class="button" type="submit">
</form>
</c:if>
<c:if test="${not empty message}">
    <fmt:message key="admin.delete_faculty.message"/>
</c:if>
<c:if test="${not empty error_messages}">
    <fmt:message key="user.register_on_faculty.error_message"/>
</c:if>
</body>

</html>
