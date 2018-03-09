<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 03.03.2018
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<c:url var="current" value="${pageContext.request.requestURI}" scope="session"/>
<a href="${pageContext.request.contextPath}/controller?command=update_locale&locale=ru_RU">Русский</a>


<a href="${pageContext.request.contextPath}/controller?command=update_locale&locale=en_EN">English</a>


</body>
</html>
