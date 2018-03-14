<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09.03.2018
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/include/header.jsp"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="user.registration.title"/> </title>
</head>
<body>
<h2><fmt:message key="user.registration.h2"/> </h2>
<form name="registration-form" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="user_registration">
    <h3><fmt:message key="user.registration.h3"/></h3>
    <label><fmt:message key="user.registration.first_name"/> <br>
        <input type="text" name="first-name" required pattern="[А-Я][а-я]{1,50}" value=""></label>
    <br>
    <label><fmt:message key="user.registration.last_name"/> <br>
        <input type="text" name="last-name" required pattern="[А-Я][а-я]{1,50}" value="">
    </label>
    <br>
    <label><fmt:message key="user.registration.birthday"/> <br>
        <input type="date" name="birthday" required min="1897-01-01" max="2010-01-01" value=""></label>
    <br>
    <label><fmt:message key="user.registration.certificate"/> <br>
        <input type="number" name="avg" required min="0" max="100" value="">
    </label><br>
    <h3><fmt:message key="user.registration.ct_results"/> </h3>
    <table>
        <tr><td><fmt:message key="user.registration.subjects_name"/>:</td><td><fmt:message key="user.registration.mark"/>:</td><tr>
        <td>
            <input list="subject" required name="first-subject">
            <datalist id="subject">
                <option value="1"><fmt:message key="user.registration.maths"/>
                <option value="2"><fmt:message key="user.registration.physic"/>
                <option value="3"><fmt:message key="user.registration.chemistry"/>
                <option value="4"><fmt:message key="user.registration.russian"/>
                <option value="5"><fmt:message key="user.registration.belarusian"/>
                <option value="6"><fmt:message key="user.registration.english"/>
                <option value="7"><fmt:message key="user.registration.biology"/>
                <option value="8"><fmt:message key="user.registration.history"/>
                <option value="9"><fmt:message key="user.registration.geography"/>
            </datalist>
        </td>
        <td>
            <input type="number" name="mark1" required min="0" max="100" value=""><br>
        </td>
    </tr>
        <tr><td><fmt:message key="user.registration.subjects_name"/>:</td><td><fmt:message key="user.registration.mark"/>:</td><tr>
        <td>
            <input list="subject" required name="second-subject">
        </td>
        <td>
            <input type="number" required min="0" max="100" name="mark2" value=""><br>
        </td>
    </tr>
        <tr><td><fmt:message key="user.registration.subjects_name"/>:</td><td><fmt:message key="user.registration.mark"/>:</td><tr>
        <td>
            <input list="subject"required name="third-subject">
        </td>
        <td>
            <input type="number"required min="0" max="100" name="mark3" value=""><br>
        </td>
    </tr>
    </table>
    <h3><fmt:message key="user.registration.date"/></h3>
    <label><fmt:message key="user.registration.login"/>:<br>
        <input type="text" name="login" required pattern="^[a-zA-Z][a-zA-Z0-9-_]{4,30}"></label>
    <br>
    <label><fmt:message key="user.registration.password"/>:<br>
        <input type="password" required name="password1" id="password1" pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value=""> </label>
    <br>
    <label><fmt:message key="user.registration.password_confirm"/>:<br>
        <input type="password" required name="password2" id="password2" pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value=""> </label>
    <br>
    <label><fmt:message key="user.registration.email"/>:<br>
        <input type="email" required name="email" value=""></label>
    <br>
    <input class="button" type="submit"value=<fmt:message key="user.registration.confirm"/>>
    <input class="button" type="reset" value=<fmt:message key="user.registration.cancel"/>>
</form>
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

