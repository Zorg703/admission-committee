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
    <title><fmt:message key="admin.find_user_by_id.title"/> </title>
</head>
<body>
<h2><fmt:message key="admin.find_user_by_id.head"/> </h2>
<form name="find-user" action="${pageContext.servletContext.contextPath}/controller" method="get">
    <div class="card shadow">
        <div class="card-block">
    <input type="hidden" name="command" value="find_user_by_id"/>

    <label for="id"><fmt:message key="admin.find_user_by_id.input_id"/></label>
    <input id="id" class="form-control" type="text" name="user_id" value=""/>
            <c:if test="${not empty message}">
                <div class="alert alert-danger">
                    <fmt:message key="admin.find_user_by_id.error"/>
                </div>
            </c:if>
    <input class="btn btn-success" type="submit" value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>
</form>

<c:if test="${not empty user_find}">
<table class="table table-hover ">
    <tr>
        <th><fmt:message key="admin.find_user_by_id.first_name"/></th>
        <th><fmt:message key="admin.find_user_by_id.last_name"/></th>
        <th><fmt:message key="admin.find_user_by_id.login"/></th>
        <th><fmt:message key="admin.find_user_by_id.email"/></th>
        <th><fmt:message key="admin.find_user_by_id.date"/></th>
        <th><fmt:message key="admin.find_user_by_id.user_speciality"/></th>
    </tr>
    <tr>
        <td><c:out value="${user_find.firstName}"/></td>
        <td><c:out value="${user_find.lastName}"/></td>
        <td><c:out value="${user_find.login}"/></td>
        <td><c:out value="${user_find.email}"/></td>
        <td><c:out value="${user_find.birthday}"/></td>
        <td><c:out value="${speciality.specialityName}"/></td>
    </tr>

</table>
</c:if>
</body>
</html>
