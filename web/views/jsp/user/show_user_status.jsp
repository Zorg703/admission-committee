<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="user.show_status.title"/></title>
</head>
<body>
<div class="container">
<c:if test="${not empty dto_speciality}">

<h2><fmt:message key="admin.find_all_users_register_on_speciality.data"/>:</h2>
<table class="table table-bordered table-dark table-inverse" style="width: 50%">
    <tr>
        <th><fmt:message key="user.show_status.register"/><fmt:message key="user.show_status.faculty"/></th>
        <td>${faculty.facultyName}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.add_speciality_on_faculty.speciality_name"/></th>
    <td>${dto_speciality.specialityName}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.add_speciality_on_faculty.recruitment_plan"/> </th>
        <td>${dto_speciality.recruitmentPlan}</td>
    </tr>
    <tr>
    <th><fmt:message key="admin.show_state_of_admission_committee.count_of_users"/> </th>
    <td>${dto_speciality.countRegisterUser}</td>
    </tr>
        <c:if test="${dto_speciality.registerEnd==true}">
            <tr>
            <th><fmt:message key="admin.show_state_of_admission_committee.passing_score"/> </th>
            <td>${dto_speciality.passingScore} </td>
            </tr>
            <tr>
                <th><fmt:message key="admin.find_all_users_register_on_speciality.score"/> </th>
                <td>${dto_user.sumScores}</td>
            </tr>
        </c:if>
        <c:if test="${dto_speciality.registerEnd==false}">
            <tr>
                <th><fmt:message key="admin.add_speciality_on_faculty.end_registration"/> </th>
                <td><fmt:parseDate value="${dto_speciality.endRegistration}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDateTime" type="both" />
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedEndDateTime}" /></td>
            </tr>
        </c:if>
</table>
    <c:if test="${dto_speciality.registerEnd==true}">
        <c:choose>
            <c:when test="${dto_user.accepted}">
                <h3 class="text-center"><fmt:message key="user.show_status.hired"/></h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-center"><fmt:message key="user.show_status.sorry"/></h3>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if test="${dto_speciality.registerEnd==false}">
        <h3 class="text-center"><fmt:message key="admin.show_state_of_admission_committee.activ"/></h3>
    </c:if>
</c:if>
<c:if test="${not empty message}">
    <h3 class="text-center"><fmt:message key="user.show_status.empty"/></h3>
</c:if>
</div>
</body>
</html>
