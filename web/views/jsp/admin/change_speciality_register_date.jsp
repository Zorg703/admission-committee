<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/admin/include/menu.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.change_register_date.title"/></title>
</head>
<body>
<h3><fmt:message key="admin.find_all_users_register_on_speciality.data"/> </h3>
<table>
<tr>
    <th>
        <fmt:message key="admin.add_speciality_on_faculty.speciality_name"/>
    </th>
    <td>
        ${speciality.specialityName}
    </td>
</tr>
    <tr>
    <th>
        <fmt:message key="admin.add_speciality_on_faculty.start_registration"/>
    </th>
        <td>
            <fmt:parseDate value="${speciality.startRegistration}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />
            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedStartDateTime}" />
        </td>
    </tr>
    <tr>
    <th>
        <fmt:message key="admin.add_speciality_on_faculty.end_registration"/>
    </th>
        <td>
            <fmt:parseDate value="${speciality.endRegistration}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDateTime" type="both" />
            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedEndDateTime}" />
        </td>
</tr>
</table>
<h2><fmt:message key="admin.change_register_date.head"/> </h2>
    <form name="change-speciality" method="post" action="${pageContext.request.contextPath}/controller">
    <div class="card shadow">
        <div class="card-block">
            <input type="hidden" name="command" value="update_speciality_register_date">
            <input type="hidden" name="speciality_id" value="${speciality.specialityId}">
            <label for="startDate"> <fmt:message key="admin.add_speciality_on_faculty.start_registration"/></label>
            <input type="datetime-local" id="startDate" class="form-control" name="start_registration" required min="2018-05-01T00:00" max="2018-12-31T23:30">
            <c:if test="${not empty messages.start_registration}">
                <fmt:message key="admin.add_speciality_on_faculty.start_registration.message"/>
            </c:if>
            <label for="endDate"><fmt:message key="admin.add_speciality_on_faculty.end_registration"/></label>
            <input id="endDate" class="form-control" name="end_registration"required type="datetime-local" min="2018-05-01T00:00" max="2018-12-31T23:30">
            <c:if test="${not empty messages.end_registration}">
                <div class="alert alert-danger">
                    <fmt:message key="admin.add_speciality_on_faculty.end_registration.message"/>
                </div>
            </c:if>
            <input class="btn btn-success" type="submit" name="end_registration" value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>
</form>

</body>
</html>
