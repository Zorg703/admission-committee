<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 06.04.2018
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.delete_speciality.title"/></title>
</head>
<body>
<form name="delete-speciality" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="delete_speciality">
    <input type="text" name="speciality_id" value="" required pattern="[1-9]\d*">
    <c:if test="${not empty message}">
        <fmt:message key="admin.delete_speciality.message"/>
    </c:if>
</form>

</body>
</html>
