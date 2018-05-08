<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 07.04.2018
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar.jsp"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="user.registration.title"/> </title>
</head>
<body>
<div class="py-5 text-center">
<h2><fmt:message key="user.registration.h2"/> </h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
<form novalidate name="registration-form" method="post" action="${pageContext.request.contextPath}/controller" >
    <input type="hidden" name="command" value="user_registration">
    <h3><fmt:message key="user.registration.h3"/></h3>
<div class="row">
    <div class="col-md-6 mb-3">
        <label  for="firstName"><fmt:message key="user.registration.first_name"/> </label>
        <input class="form-control" id=firstName type="text" name="first_name"  placeholder="" required pattern="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}"  value="${user_params.first_name}">
    </div>

        <c:if test="${not empty messages.first_name}">
                <fmt:message key="user.registration.message.first_name"/>

        </c:if>
    <div class="col-md-6 mb-3">
        <label  for="lastName"><fmt:message key="user.registration.last_name"/> </label>
        <input class="form-control" id=lastName type="text" name="last_name"  placeholder="" required pattern="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}"  value="${user_params.last_name}">
    </div>

        <c:if test="${not empty messages.last_name}">
            <fmt:message key="user.registration.message.last_name"/>
        </c:if>
</div>
    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="birthDay"><fmt:message key="user.registration.birthday"/></label>
            <input type="date" name="birthday" class="form-control" id="birthDay" placeholder="01-01-2000" required min="1900-01-01" max="2010-12-12" value="${user_params.birthday}"></label>
                <c:if test="${not empty messages.birthday}">
                    <fmt:message key="user.registration.message.birthday"/>
                </c:if>
        </div>
    </div>
 <%--   <div class="form-group">
        <label class="form-control" for="firstName"><fmt:message key="user.registration.first_name"/> </label>
        <div class="col-9">
        <input id=firstName type="text" name="first_name" required pattern="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}"  value="${user_params.first_name}">
<div class="invalid-feedback">
    <fmt:message key="user.registration.message.first_name"/>
</div>
        </div>
    <c:if test="${not empty messages.first_name}">
        <fmt:message key="user.registration.message.first_name"/>
    </c:if>
    </div>
    <br>
    <label><fmt:message key="user.registration.last_name"/> <br>
        <input type="text" name="last_name" required pattern="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}" value="${user_params.last_name}">

    </label>
    <br>--%>


        <h3><fmt:message key="user.registration.date"/></h3>
    <div class="mb-3">
        <label for="login"><fmt:message key="user.registration.login"/></label>
        <input class="form-control" type="text" id="login" placeholder="" name="login" required pattern="^[a-zA-Z][a-zA-Z0-9-_]{4,30}" value="${user_params.login}">
        <small id="loginHelpBlock" class="form-text text-muted">
            <fmt:message key="user.registration.login_helper"/>
        </small>
            <c:if test="${not empty messages.login}">
                <fmt:message key="user.registration.message.login"/>
            </c:if>
            <c:if test="${not empty messages.login_busy}">
                <fmt:message key="user.registration.message.login_busy"/>
            </c:if>

    </div>

    <div class="mb-3">
        <label for="password1"><fmt:message key="user.registration.password"/></label>
            <input class="form-control" type="password" required name="password1" id="password1"pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}" value="">
        <small id="passwordHelpBlock" class="form-text text-muted">
            <fmt:message key="user.registration.password_helper"/>
        </small>
          <c:if test="${not empty messages.password1}">
              <fmt:message key="user.registration.message.password1"/>
          </c:if>
    </div>


    <div class="mb-3">
        <label for="password2"><fmt:message key="user.registration.password_confirm"/> </label>
            <input class="form-control" type="password" required name="password2" id="password2"pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value="">
            <c:if test="${not empty messages.password2}">
                <fmt:message key="user.registration.message.password2"/>
            </c:if>

    </div>


    <div class="mb-3">
        <label for="email"><fmt:message key="user.registration.email"/></label>
            <input class="form-control" id="email" type="email" required name="email" placeholder="your@email.com" value="${user_params.email}">
            <c:if test="${not empty messages.email}">
                <fmt:message key="user.registration.message.email"/>
            </c:if>
    </div>
    <c:set var="count" value="0" scope="page"/>
    <c:forEach items="${messages}" var="message">
        <c:if test="${empty message.value }">
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:if>
    </c:forEach>
    <c:if test="${count!=0}">
        <fmt:message key="user.registration.empty_field" />
    </c:if>
<hr class="mb-4">
    <input class="btn btn-primary btn-block btn-lg btn-block" type="submit"value=<fmt:message key="user.registration.confirm"/>>
    <input class="btn btn-primary btn-block btn-lg btn-danger" type="reset" value=<fmt:message key="user.registration.cancel"/>>
    <c:set var="count" value="0" scope="page"/>
    <c:forEach items="${messages}" var="message">
        <c:if test="${empty message.value }">
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:if>
    </c:forEach>
    <c:if test="${count!=0}">
        <fmt:message key="user.registration.empty_field" />
    </c:if>
</form>
        </div>
    </div>
</div>
<c:remove var="messages" scope="session"/>
<c:remove var="user_params" scope="session"/>
<script type="text/javascript">
    window.onload = function () {

        document.getElementById("password1").onchange = validatePassword;
        document.getElementById("password2").onchange = validatePassword;
    }
    function validatePassword(){
        var pass2=document.getElementById("password2").value;
        var pass1=document.getElementById("password1").value;
        if(pass1!=pass2)
            document.getElementById("password2").setCustomValidity("Пароли не совпадают");
        else
            document.getElementById("password2").setCustomValidity('');

    }

</script>
</body></html>

