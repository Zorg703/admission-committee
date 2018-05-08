<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 06.04.2018
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/views/jsp/admin/include/menu.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.show_accepted_users.title"/> </title>
</head>
<body>
<table>
    <tr>
        <th>
            <fmt:message key="admin.find_user_by_id.first_name"/>
        </th>
        <th>
            <fmt:message key="admin.find_user_by_id.last_name"/>
        </th>
        <th>
            <fmt:message key="user.registration.email"/>
        </th>
    </tr>

       <c:forEach var="user" items="${user_list}">
    <tr>
           <td><c:out value="${user.firstName}"/></td>
           <td><c:out value="${user.lastName}"/></td>
           <td><c:out value="${user.email}"/></td>
    </tr>
       </c:forEach>

</table>
</body>
</html>
