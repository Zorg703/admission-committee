<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2018
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<link href="${pageContext.request.contextPath}/views/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<body>
<div class="col-md-4">
<nav id="sidebar" class="navbar navbar-dark">
        <div class="list-group">

    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=find_all_faculty"><fmt:message key="admin.menu.show_all_faculty"/></a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_add_faculty_page"><fmt:message key="admin.menu.add_faculty"/></a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_update_faculty_page"><fmt:message key="admin.menu.update_faculty"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_delete_faculty_page"><fmt:message key="admin.menu.delete_faculty"/></a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=show_all_specialities"><fmt:message key="admin.menu.show_all_specialities"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_add_speciality_page"><fmt:message key="admin.menu.add_speciality"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_update_speciality_page"><fmt:message key="admin.menu.update_speciality"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_delete_speciality_page"><fmt:message key="admin.menu.delete_speciality"/></a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=show_all_users"><fmt:message key="admin.menu.show_all_users"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_find_user_by_id_page"><fmt:message key="admin.menu.find_user_by_id"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_choose_faculty_and_speciality_page"><fmt:message key="admin.menu.find_register_users_on_speciality"/> </a>
    <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_choose_faculty_to_result"><fmt:message key="admin.menu.state_of_admission_committee"/> </a>

    </div>
</nav>
</div>
<%--<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <div class="sidebar-nav">
                <div class="navbar navbar-default" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <span class="visible-xs navbar-brand">Sidebar menu</span>
                    </div>
                    <div class="navbar-collapse collapse sidebar-navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=find_all_faculty"><fmt:message key="admin.menu.show_all_faculty"/></a></li>
                            <li><a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_add_faculty_page"><fmt:message key="admin.menu.add_faculty"/></a></li>
                            <li><a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_update_faculty_page"><fmt:message key="admin.menu.update_faculty"/> </a></li>
                            <li> <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_delete_faculty_page"><fmt:message key="admin.menu.delete_faculty"/></a></li>
                            <li><a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=show_all_specialities"><fmt:message key="admin.menu.show_all_specialities"/> </a></li>
                            <li><a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_add_speciality_page"><fmt:message key="admin.menu.add_speciality"/> </a></li>
                            <li> <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_update_speciality_page"><fmt:message key="admin.menu.update_speciality"/> </a></li>
                            <li> <a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_delete_speciality_page"><fmt:message key="admin.menu.delete_speciality"/></a></li>
                            <li><a class="list-group-item" class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=show_all_users"><fmt:message key="admin.menu.show_all_users"/> </a></li>
                            <li><a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_find_user_by_id_page"><fmt:message key="admin.menu.find_user_by_id"/> </a></li>
                            <li><a class="list-group-item" href="${pageContext.servletContext.contextPath}/controller?command=go_to_choose_faculty_and_speciality_page"><fmt:message key="admin.menu.find_register_users_on_speciality"/> </a></li>

                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>--%>
</body>
</html>
