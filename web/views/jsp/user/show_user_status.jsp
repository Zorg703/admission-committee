<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.04.2018
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="user.show_status.title"/></title>
</head>
<body>
<c:choose>
    <c:when test="${user.specialityId!=0}">
        <fmt:message key="user.show_status.register"/><fmt:message key="user.show_status.faculty"/> ${faculty.facultyName}
        <fmt:message key="user.show_status.speciality"/>${speciality.specialityName}
        <c:choose>
            <c:when test="${is_accepted=true }">
                <fmt:message key="user.show_status.hired"/>
            </c:when>
            <c:when test="${is_open=true}">
                <fmt:message key="user.show_status.open_registration"/>: ${speciality.endRegistration}
            </c:when>
            <c:otherwise>
                <fmt:message key="user.show_status.sorry"/>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
       <h3><fmt:message key="user.show_status.empty"/></h3>
    </c:otherwise>
</c:choose>
</body>
</html>
