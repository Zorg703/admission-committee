<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 06.04.2018
  Time: 15:31
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
    <title><fmt:message key="admin.add_faculty.title"/> </title>
</head>
<body>
    <h2><fmt:message key="admin.add_faculty.text"/></h2>
<form name="add-faculty-form " method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="add_faculty"/>
    <div class="card shadow">
        <div class="card-block">
            <label for="name"> <fmt:message key="admin.add_faculty.name"/></label>
    <input id="name" type="text" name="faculty_name" value="" required pattern="([А-Я]{1}([а-я]{2,50}(\s)?)+)|[A-Z]{1}([a-z]{2,50}(\s)?)+"/>
            <c:if test="${not empty message}">
                <div class="alert alert-danger">
                <fmt:message key="admin.add_faculty.incorrect_name"/>
                </div>
            </c:if>
            <input class="btn btn-success" type="submit" value=<fmt:message key="user.registration.confirm"/>>

    <c:remove var="message"/>
    </div>
    </div>
</form>
</body>
</html>
