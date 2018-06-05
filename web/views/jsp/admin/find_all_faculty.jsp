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
    <title><fmt:message key="admin.menu.show_all_faculty"/> </title>
</head>
<body>
<h3><fmt:message key="admin.show_all_faculty.head"/> </h3>
<div class="container" style="width: 70%">
<table class="table table-bordered table-hover"style="width: 70%">
    <tr>
        <th scope="col" style="width: 25%">#</th>
    <th scope="col" style="width: 25%">
        <fmt:message key="admin.show_all_faculty.id"/>
    </th>
    <th scope="col" style="width: 50%">
        <fmt:message key="admin.show_all_faculty.name"/>
    </th>
    </tr>
        <c:forEach var="faculty" items="${faculty_list}" varStatus="loop" >
            <tr>
                <th scope="row">${loop.index+1}</th>
            <td >
            <c:out value="${faculty.facultyId}"/>
            </td>
            <td>
            <c:out value="${faculty.facultyName}"/>
            </td>
            </tr>
        </c:forEach>
</table>
</div>
</body>
</html>
