<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 27.03.2018
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/views/jsp/admin/include/menu.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.find_all_users_register_on_speciality.title"/> </title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=show_accepted_users&speciality_id=${speciality.specialityId}"><fmt:message key="admin.find_all_users_register_on_speciality.show_accepted"/> </a>
<c:if test="${not empty user_list}">

<fmt:message key="admin.show_all_faculty.name"/>: ${faculty.facultyName}
<fmt:message key="user.menu.speciality"/>: ${speciality.specialityName}
<fmt:message key="admin.add_speciality_on_faculty.recruitment_plan"/>: ${speciality.recruitmentPlan}
<fmt:message key="admin.show_all_specialities.count"/>: ${fn:length(user_list)}
<table>
    <tr>
        <th>
            <fmt:message key="admin.find_user_by_id.id"/>
        </th>
        <th>
            <fmt:message key="admin.find_user_by_id.first_name"/>
        </th>
        <th>
            <fmt:message key="admin.find_user_by_id.last_name"/>
        </th>
        <th>
            <fmt:message key="admin.find_all_users_register_on_speciality.score"/>
        </th>
    </tr>
    <c:forEach var="user" items="${user_list}">
        <tr>
            <td>${user.userId}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <c:set var="counter" value="0" scope="page"/>
            <c:forEach items= "${user.subjectMark}" var="entry">
                <c:set var="counter" value="${counter=counter+entry.value}" scope="page"/>
            </c:forEach>
            <td>${user.certificateMark+counter}</td>
        </tr>
    </c:forEach>

</table>
</c:if>

<c:if test="${not empty message}">
    Пользователи не зарегистрированы на данную специальность
</c:if>
<c:remove var="message"/>
</body>
</html>
