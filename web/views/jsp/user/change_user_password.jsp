<%--
  Created by IntelliJ IDEA.
  User: Enginer
  Date: 25.03.2018
  Time: 8:35
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
    <title><fmt:message key="user.change_user_data.title"/> </title>
</head>
<body>
<form name="changing-form" method="post" action="${pageContext.request.contextPath}/controller">
  <input type="hidden" name="command" value="change_user_password">
    <label><fmt:message key="user.registration.password"/>:<br>
        <input type="password" required name="password1" id="password1"pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value=""> </label>

    <br>
    <label><fmt:message key="user.registration.password_confirm"/>:<br>
        <input type="password" required name="password2" id="password2"pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value=""> </label>
    <c:if test="${not empty messages.password2}">
        <fmt:message key="user.registration.message.password2"/>
    </c:if>
    <c:if test="${not empty messages.password1}">
        <fmt:message key="user.registration.message.password1"/>
    </c:if>
    <br>
    <input class="button" type="submit"value=<fmt:message key="user.registration.confirm"/>>
    <input class="button" type="reset" value=<fmt:message key="user.registration.cancel"/>>
    <c:if test="${not empty changed}">
        <fmt:message key="user.success.changed"/>
    </c:if>
</form>
<c:remove var="messages"/>
<c:remove var="user_params"/>
<c:remove var="changed"/>

<script type="text/javascript">
    window.onload = function () {
        document.getElementById("password1").onchange = validatePassword;
        document.getElementById("password2").onchange = validatePassword;
    }
    function validatePassword(){
        var pass2=document.getElementById("password2").value;
        var pass1=document.getElementById("password1").value;
        if(pass1!=pass2)
            document.getElementById("password2").setCustomValidity("Пароли не совпадают");
        else
            document.getElementById("password2").setCustomValidity('');

    }

</script>

</body>
</html>
