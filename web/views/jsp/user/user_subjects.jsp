<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="user.subjects.title"/></title>
</head>
<body>
<c:if test="${not empty subjects}">
    <div class="container col-md-8">
    <h3 class="text"><fmt:message key="user.subject_scores.head"/>:</h3>
<table class="table table-bordered table-hover"style="width: 50%">
    <tr>
        <th>
            <fmt:message key="user.subjects.name"/>
        </th>
        <th>
            <fmt:message key="user.subjects.score"/>
        </th>
    </tr>
    <c:forEach var="subject" items="${subjects}">
    <tr>
        <td>
                ${subject.key.subjectName}
        </td>
        <td>
                ${subject.value}
        </td>
    </tr>
    </c:forEach>
    <tr>
        <td>
            <fmt:message key="user.registration.certificate"/>
        </td>
        <td>
            ${user.certificateMark}
        </td>
    </tr>

</table>
    </div>
</c:if>
<c:if test="${not empty empty_subjects}">
    <h3><fmt:message key="user.subjects.empty"/></h3>
</c:if>
</body>
</html>
