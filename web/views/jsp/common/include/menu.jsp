<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
</head>
<body>
<ul>
    <li><a href="${pageContext.servletContext.contextPath}/views/jsp/common/log_in.jsp" }><fmt:message key="common.menu.log_in"/> </a> </li>
    <li><a href="${pageContext.servletContext.contextPath}/views/jsp/common/sign_up.jsp"><fmt:message key="common.menu.sign_up"/> </a> </li>
</ul>
</body>
</html>
