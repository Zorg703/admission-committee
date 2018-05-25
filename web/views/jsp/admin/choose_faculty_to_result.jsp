<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.05.2018
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/admin/include/menu.jsp"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty faculty_list}">
<table>
    <c:forEach var="faculty" items="${faculty_list}" varStatus="status">
        <tr>
            <td><a href="${pageContext.request.contextPath}/controller?command=show_result_of_admission_committee&id=${faculty.facultyId}"><c:out value="${faculty.facultyName}"/></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
