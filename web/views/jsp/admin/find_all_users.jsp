<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/admin/include/menu.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.find_all_user.title"/></title>
</head>
<body>
<div class="container col-md-8">
<h3><fmt:message key="admin.find_all_user.show"/>:</h3>
<c:if test="${not empty message}">
<h4><fmt:message key="admin.find_all_users.message"/></h4>
</c:if>
<c:if test="${not empty user_list}">
    <table class="table table-bordered table-hover" style="width: 50%">
        <tr>
            <th>#</th>
            <th style="width: 15%"><fmt:message key="admin.find_user_by_id.id"/></th>
            <th><fmt:message key="admin.find_user_by_id.first_name"/> </th>
            <th><fmt:message key="admin.find_user_by_id.last_name"/></th>
        </tr>
        <c:forEach var="user" items="${user_list}" varStatus="loop">
            <tr>
                <td>${(loop.index+1)+counter*10}</td>
                <td><c:out value="${user.userId}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
            </tr>

        </c:forEach>
    </table>
</c:if>
<c:if test="${not empty user_pages}">
   <h5><fmt:message key="admin.find_all_users.list_pages"/></h5>
<ul class="pagination">
<c:forEach var="page" begin="0" end="${user_pages-1}" varStatus="loop">
        <li><a href="${pageContext.request.contextPath}/controller?command=next_find_users_page&counter=${page}">${loop.index+1}</a></li>
    </c:forEach>
</ul>
</c:if>
</div>

</body>
</html>
