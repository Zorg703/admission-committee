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
<form name="registration-form" method="post" action="${pageContext.request.contextPath}/controller" novalidate>
    <input type="hidden" name="command" value="user_registration">
    <h3><fmt:message key="user.registration.h3"/></h3>
    <label><fmt:message key="user.registration.first_name"/> <br>
        <input type="text" name="first_name" required pattern="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}"  value="${user_params.first_name}"></label>
            <c:if test="${not empty messages.first_name}">
                <fmt:message key="user.registration.message.first_name"/>
            </c:if>

    <br>
    <label><fmt:message key="user.registration.last_name"/> <br>
        <input type="text" name="last_name" required pattern="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}" value="${user_params.last_name}">
        <c:if test="${not empty messages.last_name}">
            <fmt:message key="user.registration.message.last_name"/>
        </c:if>
    </label>
    <br>
    <label><fmt:message key="user.registration.birthday"/> <br>
        <input type="date" name="birthday" required min="1900-01-01" max="2010-12-12" value="${user_params.birthday}"></label>
        <c:if test="${not empty messages.birthday}">
            <fmt:message key="user.registration.message.birthday"/>
        </c:if>
    <br>
    <label><fmt:message key="user.registration.certificate"/> <br>
        <input type="number" name="avg" required min="0" max="100" value="${user_params.avg}">
        <c:if test="${not empty messages.avg}">
            <fmt:message key="user.registration.message.avg"/>
        </c:if>
    </label><br>
    <h3><fmt:message key="user.registration.ct_results"/> </h3>
    <table>
        <tr><td><fmt:message key="user.registration.subjects_name"/>:</td><td><fmt:message key="user.registration.mark"/>:</td><tr>
        <td>
            <select required name="first_subject" value="${user_params.first_subject}">
                <option disabled selected>Выберите предмет</option>
                <option value="1"><fmt:message key="user.registration.maths"/></option>
                <option value="2"><fmt:message key="user.registration.physic"/></option>
                <option value="3"><fmt:message key="user.registration.chemistry"/></option>
                <option value="4"><fmt:message key="user.registration.russian"/></option>
                <option value="5"><fmt:message key="user.registration.belarusian"/></option>
                <option value="6"><fmt:message key="user.registration.english"/></option>
                <option value="7"><fmt:message key="user.registration.biology"/></option>
                <option value="8"><fmt:message key="user.registration.history"/></option>
                <option value="9"><fmt:message key="user.registration.geography"/></option>
            </select>

            <c:if test="${not empty messages.first_subject}">
                <fmt:message key="user.registration.message.subject"/>
            </c:if>

        </td>

        <td>
            <input type="number" name="mark1" required min="0" max="100" value="${user_params.mark1}"><br>
            <c:if test="${not empty messages.mark1}">
                <fmt:message key="user.registration.message.mark"/>
            </c:if>
        </td>
    </tr>
        <tr><td><fmt:message key="user.registration.subjects_name"/>:</td><td><fmt:message key="user.registration.mark"/>:</td><tr>
        <td>
            <select required name="second_subject" value="${user_params.second_subject}">
                <option disabled selected>Выберите предмет</option>
                <option value="1"><fmt:message key="user.registration.maths"/></option>
                <option value="2"><fmt:message key="user.registration.physic"/></option>
                <option value="3"><fmt:message key="user.registration.chemistry"/></option>
                <option value="4"><fmt:message key="user.registration.russian"/></option>
                <option value="5"><fmt:message key="user.registration.belarusian"/></option>
                <option value="6"><fmt:message key="user.registration.english"/></option>
                <option value="7"><fmt:message key="user.registration.biology"/></option>
                <option value="8"><fmt:message key="user.registration.history"/></option>
                <option value="9"><fmt:message key="user.registration.geography"/></option>
            </select>

            <c:if test="${not empty messages.second_subject}">
                <fmt:message key="user.registration.message.subject"/>
            </c:if>
        </td>
        <td>

            <input type="number" required min="0" max="100" name="mark2" value="${user_params.mark2}"><br>
            <c:if test="${not empty messages.mark2}">
                <fmt:message key="user.registration.message.mark"/>
            </c:if>
        </td>
    </tr>
        <tr><td><fmt:message key="user.registration.subjects_name"/>:</td><td><fmt:message key="user.registration.mark"/>:</td><tr>
        <td>
            <label >
                <select required name="third_subject" >
                    <option disabled selected>Выберите предмет</option>
                    <option value="1"><fmt:message key="user.registration.maths"/></option>
                    <option value="2"><fmt:message key="user.registration.physic"/></option>
                    <option value="3"><fmt:message key="user.registration.chemistry"/></option>
                    <option value="4"><fmt:message key="user.registration.russian"/></option>
                    <option value="5"><fmt:message key="user.registration.belarusian"/></option>
                    <option value="6"><fmt:message key="user.registration.english"/></option>
                    <option value="7"><fmt:message key="user.registration.biology"/></option>
                    <option value="8"><fmt:message key="user.registration.history"/></option>
                    <option value="9"><fmt:message key="user.registration.geography"/></option>
                </select>
            </label>
            <c:if test="${not empty messages.third_subject}">
                <fmt:message key="user.registration.message.subject"/>
            </c:if>
        </td>
        <td>
            <input type="number"required min="0" max="100" name="mark3" value="${user_params.mark3}"><br>
            <c:if test="${not empty messages.mark3}">
                <fmt:message key="user.registration.message.mark"/>
            </c:if>
        </td>
        <td>
            <c:if test="${not empty messages.subjects}">
                <fmt:message key="user.registration.message.subjects"/>
            </c:if>
        </td>
    </tr>
    </table>
    <h3><fmt:message key="user.registration.date"/></h3>
    <label><fmt:message key="user.registration.login"/>:<br>
        <input type="text" name="login" required pattern="^[a-zA-Z][a-zA-Z0-9-_]{4,30}" value="${user_params.login}"></label>
    <c:if test="${not empty messages.login}">
        <fmt:message key="user.registration.message.login"/>
    </c:if>
    <c:if test="${not empty messages.login_busy}">
        <fmt:message key="user.registration.message.login_busy"/>
    </c:if>
    <br>

    <label><fmt:message key="user.registration.password"/>:<br>
        <input type="password" required name="password1" id="password1"pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value=""> </label>
      <c:if test="${not empty messages.password1}">
        <fmt:message key="user.registration.message.password1"/>
    </c:if>
    <br>
    <label><fmt:message key="user.registration.password_confirm"/>:<br>
        <input type="password" required name="password2" id="password2"pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value=""> </label>
    <c:if test="${not empty messages.password2}">
        <fmt:message key="user.registration.message.password2"/>
    </c:if>
    <br>
    <label><fmt:message key="user.registration.email"/>:<br>
        <input type="email" required name="email" value="${user_params.email}"></label>
    <c:if test="${not empty messages.email}">
        <fmt:message key="user.registration.message.email"/>
    </c:if>
    <br>
    <input class="button" type="submit"value=<fmt:message key="user.registration.confirm"/>>
    <input class="button" type="reset" value=<fmt:message key="user.registration.cancel"/>>
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

${messages=null}
${user_params=null}
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

