<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/include/header.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/include/menu.jsp"/>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.02.2018
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title> <fmt:message key="admin.action.title"/></title>
</head>
<body>

<a href="${pageContext.request.contextPath}/controller?command=show_all_users"><fmt:message key="admin.action.show"/></a>
<c:if test="${not empty user_list}">
    <table>
    <tr><td>Фамилия</td><td>Имя</td><td>Отчество</td></tr>
    <c:forEach var="user" items="${user_list}" varStatus="status">
        <tr>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.birthday}"/></td>
        </tr>

    </c:forEach>
    </table>
</c:if>
</body>
</html>
