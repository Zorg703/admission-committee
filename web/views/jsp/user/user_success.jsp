<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.03.2018
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<html>
<head>
    <fmt:setLocale value="${locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="admin.successful.title"/> </title>
</head>
<body>
<h3><fmt:message key="admin.successful.success"/></h3>
</body>
</html>
