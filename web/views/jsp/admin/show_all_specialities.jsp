<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2018
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="/views/jsp/admin/include/menu.jsp"/>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="admin.show_all_specialities.title"/> </title>
</head>
<body>
<h3><fmt:message key="admin.show_all_specialities.table"/></h3>
<table class="table table-bordered table-hover" style="width: 60%">
    <tr>
        <th>#</th>
        <th>
            <fmt:message key="admin.show_all_specialities.id"/>
        </th>
        <th>
            <fmt:message key="admin.add_speciality_on_faculty.speciality_name"/>
        </th>
        <th>
            <fmt:message key="admin.add_speciality_on_faculty.recruitment_plan"/>
        </th>
        <th>
            <fmt:message key="admin.add_speciality_on_faculty.faculty_id"/>
        </th>
        <th>
            <fmt:message key="admin.add_speciality_on_faculty.start_registration"/>
        </th>
        <th>
            <fmt:message key="admin.add_speciality_on_faculty.end_registration"/>
        </th>
    </tr>
        <c:forEach items="${speciality_list}" var="speciality" varStatus="loop">
            <tr>
                <td>${loop.index+1}</td>
            <td>${speciality.specialityId}</td>
            <td>${speciality.specialityName}</td>
            <td>${speciality.recruitmentPlan}</td>
            <td>${speciality.facultyId}</td>
                <td>
                    <fmt:parseDate value="${speciality.startRegistration}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedStartDateTime}" /> </td>
                <td><fmt:parseDate value="${speciality.endRegistration}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDateTime" type="both" />
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedEndDateTime}" /></td>
            </tr>
        </c:forEach>
</table>
</body>
</html>