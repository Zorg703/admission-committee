<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.05.2018
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<html>
<head>
    <title><fmt:message key="admin.change_register.date"/></title>
</head>
<body>
<h2><fmt:message key="admin.delete_faculty.head"/> </h2>
<form name="delete-faculty" method="post" action="${pageContext.request.contextPath}/controller">
    <div class="card shadow">
        <div class="card-block">
            <input type="hidden" name="command" value="update_speciality_register_date">
            <label for="id"><fmt:message key="admin.delete_speciality.id"/> </label>
            <input type="text" id="id"  class="form-control" name="speciality_id" value="" required pattern="[1-9]\d*">
            <c:if test="${not empty messages.speciality_id}">
                <fmt:message key="admin.delete_speciality.message"/>
            </c:if>
            <label for="startDate"> <fmt:message key="admin.add_speciality_on_faculty.start_registration"/></label>
            <input type="datetime-local" id="startDate" class="form-control" name="start_registration" required min="2018-05-01T00:00" max="2018-12-31T23:30">
            <c:if test="${not empty messages.start_registration}">
                <fmt:message key="admin.add_speciality_on_faculty.start_registration.message"/>
            </c:if>
            <
            <label for="endDate"><fmt:message key="admin.add_speciality_on_faculty.end_registration"/></label>
            <input id="endDate" class="form-control" name="end_registration"required type="datetime-local" min="2018-05-01T00:00" max="2018-12-31T23:30">
            <c:if test="${not empty messages.end_registration}">
                <div class="alert alert-danger">
                    <fmt:message key="admin.add_speciality_on_faculty.end_registration.message"/>
                </div>
            </c:if>
            <input class="btn btn-success" type="submit" name="end_registration" value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>
</form>

</body>
</html>
