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
    <title><fmt:message key="admin.find_all_users_register_on_speciality.title"/></title>
</head>
<body>
<h3><fmt:message key="admin.find_all_users_register_on_speciality.data"/></h3>
<c:if test="${not empty user_list}">
<table class="table table-bordered table-dark table-inverse" style="width: 50%">
    <tr>
        <th><fmt:message key="admin.find_all_users_register_on_speciality.speciality_name"/>:</th>
        <td>${speciality.specialityName}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.add_speciality_on_faculty.recruitment_plan"/></th>
        <td>${speciality.recruitmentPlan}</td>
    </tr>
    <tr>
        <th><fmt:message key="admin.add_speciality_on_faculty.end_registration"/> </th>
    <td><fmt:parseDate value="${speciality.endRegistration}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDateTime" type="both" />
        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedEndDateTime}" /></td>
    </tr>
</table>
    <c:if test="${is_registration_open==false}">
        <h3><a href="${pageContext.request.contextPath}/controller?command=show_accepted_users&speciality_id=${speciality.specialityId}">
            <fmt:message key="admin.find_all_users_register_on_speciality.link"/></a></h3>
    </c:if>

<table class="table table-bordered table-hover" style="width: 50%">
    <tr>
        <th>#</th>
        <th>
            <fmt:message key="admin.find_user_by_id.id"/>
        </th>
        <th>
            <fmt:message key="admin.find_user_by_id.first_name"/>
        </th>
        <th>
            <fmt:message key="admin.find_user_by_id.last_name"/>
        </th>
    </tr>
    <c:forEach var="user" items="${user_list}" varStatus="loop">
        <tr>
            <td>${(loop.index+1)+counter*10}</td>
            <td>${user.userId}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
        </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${not empty register_user_pages}">
    <h5><fmt:message key="admin.find_all_users.list_pages"/></h5>
    <ul class="pagination">
        <c:forEach var="page" begin="0" end="${register_user_pages}">
            <li><a href="${pageContext.request.contextPath}/controller?command=next_find_register_users_page&counter=${page}&${speciality.specialityId}">${page+1}</a></li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${not empty message}">
    <div class="alert alert-danger">
    <fmt:message key="admin.find_all_users_register_on_speciality.message"/>
    </div>
        </c:if>
</body>
</html>
