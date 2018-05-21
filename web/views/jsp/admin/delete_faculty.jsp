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
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="admin.delete_faculty.title"/> </title>
</head>
<body>
<h2><fmt:message key="admin.delete_faculty.head"/> </h2>
<form name="delete-faculty" method="post" action="${pageContext.request.contextPath}/controller">
    <div class="card shadow">
        <div class="card-block">
    <input type="hidden" name="command" value="delete_faculty">
            <label for="id"> <fmt:message key="admin.update_speciality.faculty_id"/></label>
    <input type="text" id="id" class="form-control" name="faculty_id" value="" required pattern="[1-9]\d*">
    <c:if test="${not empty message}">
        <fmt:message key="admin.delete_faculty.message"/>
    </c:if>
            <input class="btn btn-success" type="submit" value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>
</form>
</body>
</html>
