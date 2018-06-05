<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:choose>
    <c:when test="${not empty user}">
        <c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar.jsp"/>
    </c:otherwise>
</c:choose>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<html>
<head>
    <title>404</title>
</head>
<body>
<h2>404<br><fmt:message key="common.error_404.head"/><a href="${pageContext.servletContext.contextPath}/controller?command=go_to_main_page"><fmt:message key="common.error.link"/> </a></h2>
</body>
</html>
