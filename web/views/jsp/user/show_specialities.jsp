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
<c:import url="/views/include/header.jsp"/>
<html>
<head>
    <fmt:setLocale value="${locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title>Title</title>
</head>
<body>
<form name="choose speciality" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="choose_speciality">
<table>
<c:forEach var="speciality" items="${speciality_list}" varStatus="status">
    <tr>
        <td><c:out value="${speciality.specialityName}"/></td>
    </tr>
</c:forEach>
</table>
<select name="speciality">
    <option disabled>Выберите специальность</option>
    <c:forEach var="speciality" items="${speciality_list}" >
        <option value="${speciality.specialityId}">${speciality.specialityName}</option>
    </c:forEach>
</select>
    <input class="button" type="submit">
    </form>
</body>
</html>
