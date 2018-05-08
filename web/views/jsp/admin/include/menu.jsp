<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2018
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<body>
<div class="col-md-4">
<nav id="sidebar" class="navbar navbar-expand-md navbar-dark">
        <div class="list-group">

    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=find_all_faculty"><fmt:message key="admin.menu.show_all_faculty"/></a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_add_faculty_page"><fmt:message key="admin.menu.add_faculty"/></a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_update_faculty_page"><fmt:message key="admin.menu.update_faculty"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_delete_faculty_page"><fmt:message key="admin.menu.delete_faculty"/></a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=show_all_specialities"><fmt:message key="admin.menu.show_all_specialities"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_add_speciality_page"><fmt:message key="admin.menu.add_speciality"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_update_speciality_page"><fmt:message key="admin.menu.update_speciality"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_delete_speciality_page"><fmt:message key="admin.menu.delete_speciality"/></a>
    <a class="list-group-item" class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=show_all_users"><fmt:message key="admin.menu.show_all_users"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_find_user_by_id_page"><fmt:message key="admin.menu.find_user_by_id"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_choose_faculty_and_speciality_page"><fmt:message key="admin.menu.find_register_users_on_speciality"/> </a>


    </div>
</nav>
</div>

</body>
</html>
