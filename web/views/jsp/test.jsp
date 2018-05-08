<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.03.2018
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
   <fmt:setLocale value="${sessionScope.locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=SHOW_ALL_USERS_REGISTER_ON_SPECIALITY&speciality_id=2"><fmt:message key="admin.action.show"/></a>


<form name="LoginForm" method="post" action="${pageContext.request.contextPath}/controller">

    <table>
<input list="subject" required name="first-subject">
<datalist id="subject">
    <option 1><fmt:message key="user.registration.belarusian"/>
    <option value="2"><fmt:message key="user.registration.russian"/>
    <option value="3"><fmt:message key="user.registration.maths"/>
    <option value="4"><fmt:message key="user.registration.chemistry"/>
    <option value="5"><fmt:message key="user.registration.physic"/>
    <option value="6"><fmt:message key="user.registration.biology"/>
    <option value="7" <fmt:message key="user.registration.english"/>
    <option value="8"><fmt:message key="user.registration.history"/>
    <option value="9"><fmt:message key="user.registration.geography"/>
</datalist>
</table>
    <input class="button" type="submit"value=<fmt:message key="user.registration.confirm"/>>
</form>
</body>
</html>
