<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2018
  Time: 20:01
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
    <title><fmt:message key="admin.update_speciality.title"/></title>
</head>
<body>
<h2><fmt:message key="admin.update_faculty.head"/> </h2>
<form name="update-faculty" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="update_faculty">
    <div class="card shadow">
        <div class="card-block">
            <label for="id"><fmt:message key="admin.update_speciality.faculty_id"/></label>
    <input id="id" type="text" class="form-control" name="faculty_id" pattern="[1-9]\d*" required value=""/>
            <label for="name"><fmt:message key="admin.add_faculty.name"/></label>
    <input id="name" class="form-control" type="text" name="faculty_name" required pattern="([А-Я]{1}([а-я]{2,50}(\s)?)+)|[A-Z]{1}([a-z]{2,50}(\s)?)+" value=""/>

            <c:if test="${not empty error_id}">
            <div class="alert alert-danger">
      <fmt:message key="admin.update_speciality.error_id"/>
            </div>
    </c:if>


    <input class="btn btn-success" type="submit" value=<fmt:message key="user.registration.confirm"/> >
        </div>
    </div>
    <c:if test="${not empty error_name}">
        <div class="alert alert-warning">
            <strong><fmt:message key="admin.update_speciality.error_name"/></strong>
        </div>
    </c:if>
</form>

<c:remove var="error_id"/>
<c:remove var="error_name"/>
</body>
</html>
