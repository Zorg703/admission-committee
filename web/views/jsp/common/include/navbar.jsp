<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
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
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand"  href="${pageContext.request.contextPath}/views/jsp/common/log_in.jsp"><fmt:message key="common.header"/></a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/controller?command=update_locale&locale=ru_RU">Русский</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=update_locale&locale=en_En">English</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.servletContext.contextPath}/views/jsp/common/sign_up.jsp"><span class="glyphicon glyphicon-user"></span> <fmt:message key="common.menu.sign_up"/> </a></li>
                <li><a href="${pageContext.servletContext.contextPath}/views/jsp/common/log_in.jsp"><span class="glyphicon glyphicon-log-in"></span><fmt:message key="login.form.sign_in"/> </a></li>
            </ul>
        </div>

    </nav>


</body>
</html>

