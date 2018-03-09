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
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
<c:forEach var="speciality" items="${speciality_list}" varStatus="status">
    <tr>
        <td><c:out value="${speciality.specialityName}"/></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
