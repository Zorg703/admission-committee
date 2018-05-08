<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.04.2018
  Time: 20:43
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

    <title><fmt:message key="admin.menu.show_all_faculty"/> </title>
</head>
<body>
<div class="container">
<table class="table table-bordered table-hover"style="width: 70%">
    <tr>
        <th scope="col" style="width: 10%">#</th>
    <th scope="col" style="width: 15%">
        <fmt:message key="admin.show_all_faculty.id"/>
    </th>
    <th scope="col" style="width: 25%">
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
