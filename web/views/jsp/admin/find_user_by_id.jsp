<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 06.04.2018
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.find_user_by_id.title"/> </title>
</head>
<body>
<form name="find-user" action="${pageContext.servletContext.contextPath}/controller" method="get">
    <input type="hidden" name="controller" value="find_user_by_id"/>
    <input type="text" name="user_id" value=""/>
    <input type="submit" class="button">
</form>
<c:if test="${not empty message}">
   <fmt:message key="admin.find_user_by_id.error"/>
</c:if>
<c:if test="${not empty user}">
<table>
    <tr>
        <td><fmt:message key="admin.find_user_by_id.first_name"/></td>
        <td><fmt:message key="admin.find_user_by_id.last_name"/></td>
        <td><fmt:message key="admin.find_user_by_id.login"/></td>
        <td><fmt:message key="admin.find_user_by_id.email"/></td>
        <td><fmt:message key="admin.find_user_by_id.date"/></td>
        <td><fmt:message key="admin.find_user_by_id.user_speciality"/></td>
    </tr>
    <tr>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.date}"/></td>
        <td><c:out value="${user.specialityId}"/></td>
    </tr>
</table>
</c:if>
</body>
</html>
