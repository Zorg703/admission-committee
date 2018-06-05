<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<html>
<head>
    <fmt:setLocale value="${locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="user.find_all_faculty.title"/> </title>
</head>
<body>
<c:if test="${not empty faculty_list}">
   <h3> <fmt:message key="admin.choose_faculty_and_speciality.faculty"/></h3>
<table>
    <c:forEach var="faculty" items="${faculty_list}" varStatus="status">
        <tr>
            <td><a href="${pageContext.request.contextPath}/controller?command=find_speciality&id=${faculty.facultyId}"><c:out value="${faculty.facultyName}"/></a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
<c:if test="${not empty message}">
    <h2><fmt:message key="user.register_on_faculty.applied"/> </h2>
</c:if>
</body>
</html>
