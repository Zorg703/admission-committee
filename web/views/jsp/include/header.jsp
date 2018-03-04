<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04.03.2018
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <a href="${pageContext.request.contextPath}/controller?command=update_locale&
+locale=RU">Русский</a>
    <a href="${pageContext.request.contextPath}/controller?command=update_locale
&locale=EN">English</a>


    <fmt:setLocale value="ru_RU" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <fmt:message key="login.form.login"/>


  
</head>
<body>
<header>

</header>
</body>
</html>
