<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.03.2018
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/views/include/header.jsp"/>
<html>
<head>
    <fmt:setLocale value="${locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="user.find_all_facylty.title"/> </title>
</head>
<body>
<c:if test="${not empty faculty_list}">
<table>

    <c:forEach var="faculty" items="${faculty_list}" varStatus="status">
        <tr>
            <td><a href="${pageContext.request.contextPath}/controller?command=find_speciality&id=${faculty.facultyId}"><c:out value="${faculty.facultyName}"/></a></td>
        </tr>
    </c:forEach>
</table>

</c:if>
</body>
</html>
