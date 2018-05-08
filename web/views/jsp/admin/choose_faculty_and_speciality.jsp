<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.05.2018
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="/views/jsp/admin/include/menu.jsp"/>
<html>
<fmt:setLocale value="${locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.choose_faculty_and_speciality.title"/> </title>
</head>
<body>
<c:if test="${not empty faculty_list}">
<fmt:message key="admin.choose_faculty_and_speciality.faculty"/>

<form name="find_all_faculty_form" method="get">
    <input type="hidden" name="command" value="find_specialities_for_choose_faculty">
    <label>
        <select name="faculty_id">
            <option disabled><fmt:message key="user.show_specialities.choose"/></option>
            <c:forEach var="faculty" items="${faculty_list}">
                <option value="${faculty.facultyId}">${faculty.facultyName}</option>
            </c:forEach>
        </select>
    </label>
    <input class="button" type="submit" >
</form>
</c:if>
<c:if test="${not empty speciality_list}">
    <fmt:message key="admin.show_all_faculty.name"/> : <c:out value="${faculty.facultyName}"/>
<fmt:message key="admin.choose_faculty_and_speciality.speciality"/>
<form name="find_users_on_current_speciality" method="get">
    <input type="hidden" name="command" value="show_all_users_register_on_speciality">
    <label>
        <select name="speciality_id">
            <option disabled><fmt:message key="user.show_specialities.choose"/></option>
            <c:forEach var="speciality" items="${speciality_list}">
                <option value="${speciality.specialityId}">${speciality.specialityName}</option>
            </c:forEach>
        </select>
    </label>
    <input class="button" type="submit">
</form>
</c:if>
</body>
</html>
