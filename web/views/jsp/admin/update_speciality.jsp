<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2018
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="/views/jsp/admin/include/menu.jsp"/>

<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.update_speciality.title"/> </title>
</head>
<body>
<h2><fmt:message key="admin.update_speciality.head"/></h2>
<form name="update-speciality" method="post" action="${pageContext.servletContext.contextPath}/controller">
    <div class="card shadow">
        <div class="card-block">
    <input type="hidden" name="command" value="update_speciality">
    <label for="speciality_id" ><fmt:message key="admin.show_all_specialities.id"/></label>
    <input type="text" class="form-control" id="speciality_id" name="speciality_id" required pattern="[1-9]\d*" value="${speciality.speciality_id}"><br>
    <c:if test="${not empty messages.speciality_id}">
        <div class="alert alert-danger">
            <fmt:message key="admin.update_speciality.error_speciality_id"/>
        </div>
    </c:if>
            <label for="id"> <fmt:message key="admin.add_speciality_on_faculty.faculty_id"/></label>
    <input id="id" class="form-control" type="text" name="faculty_id" required pattern="[1-9]\d*" value="${speciality.faculty_id}"><br>
    <c:if test="${not empty messages.faculty_id}">
        <fmt:message key="admin.add_speciality_on_faculty.message.id"/>
    </c:if>
   <label for="name"><fmt:message key="admin.add_speciality_on_faculty.speciality_name"/></label>
    <input type="text" class="form-control" id="name" name="speciality_name" required pattern="([А-Я]{1}([а-я]{2,50}(\s)?)+)|[A-Z]{1}([a-z]{2,50}(\s)?)+" value="${speciality.speciality_name}"><br>
            <c:if test="${not empty messages.speciality_name}">

                    <fmt:message key="admin.add_speciality_on_faculty.speciality_name"/>
                </div>
            </c:if>
            <label for="plan"> <fmt:message key="admin.add_speciality_on_faculty.recruitment_plan"/></label>
    <input id="plan" class="form-control" type="text" name="recruitment_plan" required pattern="[1-9]\d{0,4}" value="${speciality.recruitment_plan}"><br>
        <c:if test="${not empty messages.recruitment_plan}">
            <div class="alert alert-danger">
                <fmt:message key="admin.add_speciality_on_faculty.recruitment_plan"/>
            </div>
        </c:if>
    <h3> <fmt:message key="admin.add_speciality_on_faculty.subject_for_registration"/><br></h3>
    <br>
        <label>
            <select required name="first_subject" value="${speciality.first_subject}" >
                <option disabled selected><fmt:message key="admin.add_speciality_on_faculty.subject"/></option>
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
        <c:if test="${not empty messages.first_subject}">
            <div class="alert alert-danger">
            <fmt:message key="user.registration.message.subject"/>
            </div>
        </c:if>

        <label>
            <select required name="second_subject" value="${speciality.second_subject}">
                <option disabled selected><fmt:message key="admin.add_speciality_on_faculty.subject"/></option>
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
        <div class="alert alert-danger">
            <fmt:message key="user.registration.message.subject"/>
        </div>
        </c:if>
        <label>
            <select required name="third_subject" value="${speciality.third_subject}"><br>
                <option disabled selected><fmt:message key="admin.add_speciality_on_faculty.subject"/></option>
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
        <div class="alert alert-danger">
            <fmt:message key="user.registration.message.subject"/>
        </div>
        </c:if>
    <c:if test="${not empty messages.subjects}">
        <div class="alert alert-danger">
        <fmt:message key="user.registration.message.subjects"/>
        </div>
    </c:if>
        <input id="start" class="form-control" name="start_registration" required type="datetime-local" min="2018-05-01T00:00" max="2018-12-31T23:30">
        <c:if test="${not empty messages.start_registration}">
            <div class="alert alert-danger">
                <fmt:message key=""/>
            </div>
        </c:if>
        <label for="end"><fmt:message key="admin.add_speciality_on_faculty.end_registration"/></label>
        <input id="end" class="form-control" name="end_registration"required type="datetime-local" min="2018-05-01T00:00" max="2018-12-31T23:30">
        <c:if test="${not empty messages.end_registration}">
            <div class="alert alert-danger">
                <fmt:message key=""/>
            </div>
        </c:if>
        <input class="btn btn-success" type="submit" value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>

</form>
</body>
</html>
