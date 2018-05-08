<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 06.04.2018
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="/views/jsp/admin/include/menu.jsp"/>

<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.find_user_by_id.title"/> </title>
</head>
<body>
<form name="find-user" action="${pageContext.servletContext.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="find_user_by_id"/>
    <fmt:message key="admin.find_user_by_id.input_id"/>
    <input type="text" name="user_id" value=""/>
    <input type="submit" class="button">
</form>
<c:if test="${not empty message}">
   <fmt:message key="admin.find_user_by_id.error"/>
</c:if>
<c:if test="${not empty user_find}">
<table>
    <tr>
        <th><fmt:message key="admin.find_user_by_id.first_name"/></th>
        <th><fmt:message key="admin.find_user_by_id.last_name"/></th>
        <th><fmt:message key="admin.find_user_by_id.login"/></th>
        <th><fmt:message key="admin.find_user_by_id.email"/></th>
        <th><fmt:message key="admin.find_user_by_id.date"/></th>
        <th><fmt:message key="admin.find_user_by_id.user_speciality"/></th>
    </tr>
    <tr>
        <td><c:out value="${user_find.firstName}"/></td>
        <td><c:out value="${user_find.lastName}"/></td>
        <td><c:out value="${user_find.login}"/></td>
        <td><c:out value="${user_find.email}"/></td>
        <td><c:out value="${user_find.birthday}"/></td>
        <td><c:out value="${user_find.specialityId}"/></td>
    </tr>
</table>
</c:if>
<c:remove var="message"/>
</body>
</html>
