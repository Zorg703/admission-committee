<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 03.03.2018
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/views/img/icon/cap.png">
    <link href="${pageContext.request.contextPath}/views/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/views/css/styler.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <%--<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb00-3 bg-dark border-bottom box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal " style="color: white">Admission</h5>
        <nav class="my-2 my-md-0 mr-md-2 ">
            <div class="top-links">
            <a  href="${pageContext.request.contextPath}/controller?command=update_locale&locale=ru_RU">Русский</a>
            <a  href="${pageContext.request.contextPath}/controller?command=update_locale&locale=en_EN">English</a>
            </div>

    <c:if test="${not empty user}">
        <form class="form-inline col-md-1">
        <input type="hidden" name="command" value="log_out">
            <button class="btn btn-outline-success my-2 mr-md-4" type="submit"><fmt:message key="common.menu.log_out"/></button>
        </form>
    </c:if>
        </nav>
            </div>--%>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand"  href="${pageContext.request.contextPath}/controller?command=go_to_main_page"><fmt:message key="common.header"/></a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/controller?command=update_locale&locale=ru_RU">Русский</a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=update_locale&locale=en_En">English</a></li>
                </ul>
                    <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.servletContext.contextPath}/controller?command=log_out"><span class="glyphicon glyphicon-log-out"></span><fmt:message key="common.menu.log_out"/> </a></li>
                </ul>

            </div>
        </nav>

</body>
</html>
