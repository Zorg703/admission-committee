<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <link href="${pageContext.request.contextPath}/views/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/views/css/styler.css" rel="stylesheet" type="text/css" >

</head>
<body>
<header>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand"  href="${pageContext.request.contextPath}/controller?command=go_to_main_page"><fmt:message key="common.header"/>
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
</header>
    </body>
</html>
