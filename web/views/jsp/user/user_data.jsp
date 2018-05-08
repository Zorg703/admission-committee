<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 25.03.2018
  Time: 9:34
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
    <title><fmt:message key="user.data.title"/></title>
</head>
<body>
<fmt:message key="user.data.first_name"/>: ${user.firstName}
<fmt:message key="user.data.last_name"/>: ${user.lastName}
<fmt:message key="user.data.birthday"/>: ${user.birthday}
<fmt:message key="user.data.email"/> ${user.email}
<br>${user.specialityId}<br>
${user.userId}
</body>
</html>
