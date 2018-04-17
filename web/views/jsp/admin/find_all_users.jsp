<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 27.03.2018
  Time: 7:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/include/header.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/include/menu.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.find_all_user.title"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=show_all_users"><fmt:message key="admin.action.show"/></a>
<c:if test="${not empty user_list}">
    <table>
        <tr><td><fmt:message key="admin.find_user_by_id.id"/></td>
            <td><fmt:message key="admin.find_user_by_id.first_name"/> </td>
            <td><fmt:message key="admin.find_user_by_id.last_name"/></td>
            <td><fmt:message key="admin.find_user_by_id.login"/></td>
        </tr>
        <c:forEach var="user" items="${user_list}" varStatus="status">
            <tr>
                <td><c:out value="${user.lastName}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.birthday}"/></td>
                <c:choose>
                    <c:when test="${user.specialityId==0}">
                        <td><c:out value="пользователь не зарегистрирован не на один факультет"/></td>
                    </c:when>
                    <c:otherwise>
                        <td><c:out value="пользователь не зарегистрирован на факультет"/></td>
                    </c:otherwise>
                </c:choose>
            </tr>

        </c:forEach>
    </table>
</c:if>
</body>
</html>
