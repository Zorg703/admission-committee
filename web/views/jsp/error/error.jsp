<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<c:choose>
<c:when test="${not empty user}">
    <c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
</c:when>
<c:otherwise>
    <c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar.jsp"/>
</c:otherwise>
</c:choose>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="common.error.title"/> </title>
</head>
<body>
<h3><fmt:message key="common.error.message"/>
<a href="${pageContext.servletContext.contextPath}/controller?command=go_to_main_page"><fmt:message key="common.error.link"/> </a></h3>

</body>
</html>
