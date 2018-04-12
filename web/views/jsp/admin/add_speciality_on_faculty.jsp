<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 06.04.2018
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.add_speciality_on_faculty.title"/> </title>
</head>
<body>
<form name="add-speciality" method="post" action="${pageContext.servletContext.contextPath}/controller">
    <input type="hidden" name="command" value="add_speciality">
    <input type="text" name="facultyId" value="">
    <input type="text" name="speciality_name" value="">
    <input type="text" name="recruitment_plan" value="">
    <tr><td><fmt:message key="user.registration.subjects_name"/>:</td><td><fmt:message key="user.registration.mark"/>:</td><tr>
    <td>
        <label>
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
        </label>
        <c:if test="${not empty messages.second_subject}">
            <fmt:message key="user.registration.message.subject"/>
        </c:if>
    </td>
    <td>
        <label>
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
        </label>
        <c:if test="${not empty messages.second_subject}">
            <fmt:message key="user.registration.message.subject"/>
        </c:if>
    </td>
    <td>
        <label>
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
        </label>
        <c:if test="${not empty messages.second_subject}">
            <fmt:message key="user.registration.message.subject"/>
        </c:if>
    </td>
</form>
</body>
</html>
