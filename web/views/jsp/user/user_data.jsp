<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 25.03.2018
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="user.data.title"/></title>
</head>
<body>
<h2><fmt:message key="user.data.head"/>: </h2>
<div class="container" style="width: 70%">
<table class="table table-bordered table-hover"style="width: 70%">
    <tr>
        <th><fmt:message key="user.data.first_name"/>:</th>
        <td> ${user.firstName}</td>
    </tr>
    <tr>
        <th>
            <fmt:message key="user.data.last_name"/>:
        </th>
        <td>
            ${user.lastName}
        </td>
    </tr>
    <tr>
        <th>
            <fmt:message key="user.data.birthday"/>:
        </th>
        <td>
            ${user.birthday}
        </td>
    </tr>
    <tr>
        <th>
            <fmt:message key="user.data.email"/>:
        </th>
        <td>
            ${user.email}
        </td>
    </tr>


</table>
</div>
</body>
</html>
