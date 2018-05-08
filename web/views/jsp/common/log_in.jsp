<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.02.2018
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar.jsp"/>

<html>

<head>

    <fmt:setLocale value="${locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="login.form.sign_in"/> </title>
</head>
<body>

<form class="form-login" name="LoginForm" method="post" action="${pageContext.request.contextPath}/controller">
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login.form.form_name"/> </h1>
    <input type="hidden" name="command" value="login">
    <label for="InputLogin" class="sr-only"> <fmt:message key="login.form.login"/></label>
    <input type="text" name="login" id="InputLogin" value="${params.login}" class="form-control" placeholder="<fmt:message key="login.form.login"/>"/>

    <label for="InputPassword" class="sr-only"><fmt:message key="login.form.password"/></label>
    <input type="password" id="InputPassword" name="password" value="" class="form-control" placeholder="<fmt:message key="login.form.password"/>"/>

    <input class="btn btn-lg btn-primary btn-block" type="submit" value=<fmt:message key="login.form.submit"/> />
    <br/>
    <c:if test= "${not empty message}">
    <fmt:message key="login.form.error_message"/>
    </c:if>
    <c:remove var="message"/>
    <c:remove var="params"/>
</form>
</body>
</html>
