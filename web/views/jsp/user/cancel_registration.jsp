<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.05.2018
  Time: 11:36
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
    <title><fmt:message key="user.cancel_registration.title"/> </title>
</head>
<body>
<h3><fmt:message key="user.cancel_registration.text"/> ${speciality.specialityName} <fmt:message key="user.cancel_registration.button"/></h3>
<form name="cancel_registration" method="get" action="${pageContext.servletContext.contextPath}/controller">
    <div class="card shadow">
        <div class="card-block">
    <input type="hidden" name="command" value="cancel_registration">
    <input class="btn btn-danger" type="submit" value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>
</form>
</body>
</html>
