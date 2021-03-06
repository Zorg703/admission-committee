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
    <title><fmt:message key="user.show_specialities.title"/></title>
</head>
<body>
<h3><fmt:message key="admin.choose_faculty_and_speciality.speciality"/></h3>
<table>
<c:forEach var="speciality" items="${speciality_list}" varStatus="status">
    <tr>
        <td><a href="${pageContext.request.contextPath}/controller?command=choose_speciality&speciality_id=${speciality.specialityId}"><c:out value="${speciality.specialityName}"/></a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
