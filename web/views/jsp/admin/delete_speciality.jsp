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
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="/views/jsp/admin/include/menu.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.delete_speciality.title"/></title>
</head>
<body>
<h2><fmt:message key="admin.delete_speciality.head"/> </h2>

<form name="delete-speciality" method="post" action="${pageContext.request.contextPath}/controller">
    <div class="card shadow">
        <div class="card-block">
    <input type="hidden" name="command" value="delete_speciality">
            <label for="id"><fmt:message key="admin.delete_speciality.id"/></label>
    <input id="id" class="form-control" type="text" name="speciality_id" value="" required pattern="[1-9]\d*">
    <c:if test="${not empty message}">
        <fmt:message key="admin.delete_speciality.message"/>
    </c:if>
            <input class="btn btn-success" type="submit" value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>
</form>
</body>
</html>
