<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
