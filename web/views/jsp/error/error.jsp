
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18.03.2018
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="common.error.title"/> </title>
</head>
<body>
<c:out value="${exception_message}"/>
<c:out value="${message}"/>
<h3><fmt:message key="common.error.message"/>
<a href="${pageContext.servletContext.contextPath}/views/jsp/common/main.jsp"><fmt:message key="common.error.link"/> </a></h3>
${pageContext.exception}

<c:forEach var="trace"
           items="${pageContext.exception.stackTrace}" end="5">
    <p>${trace}</p>
</c:forEach>


</body>
<c:remove var="message"/>
</html>
