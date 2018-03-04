<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.02.2018
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <fmt:setLocale value="ru_RU" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title> <fmt:message key="admin.action.title"/> </title>
</head>
<body>
<%--a href="/controller">Show all users</a>--%>

<a href="${pageContext.request.contextPath}/controller?command=show_all_users"><fmt:message key="admin.action.show"/></a>
<input type="hidden" name="command" value="show_all_users"/>
    <table>

    <c:forEach var="user" items="${user_list}" varStatus="status">
        <tr>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
         <td><c:out value="${user.birthday}"/></td>
        </tr>

    </c:forEach>
    </table>

</body>
</html>
