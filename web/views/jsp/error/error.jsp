<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18.03.2018
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>

${pageContext.exception}

<c:forEach var="trace"
           items="${pageContext.exception.stackTrace}" end="5">
    <p>${trace}</p>
</c:forEach>
</body>
</html>
