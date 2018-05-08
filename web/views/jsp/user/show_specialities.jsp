<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09.03.2018
  Time: 0:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<html>
<head>
    <fmt:setLocale value="${locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="user.show_specialities.title"/></title>
</head>
<body>
<fmt:message key="admin.choose_faculty_and_speciality.speciality"/>
<form name="choose speciality" action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="register_on_speciality">
<table>
<c:forEach var="speciality" items="${speciality_list}" varStatus="status">
    <tr>
        <td><a href="${pageContext.request.contextPath}/controller?command=choose_speciality&speciality_id=${speciality.specialityId}"><c:out value="${speciality.specialityName}"/></a></td>
    </tr>
</c:forEach>
</table>
<select name="speciality">
    <option disabled><fmt:message key="user.show_specialities.choose"/> </option>
    <c:forEach var="speciality" items="${speciality_list}" >
        <option value="${speciality.specialityId}">${speciality.specialityName}</option>
    </c:forEach>
</select>
    <input class="button" type="submit">
    </form>
</body>
</html>
