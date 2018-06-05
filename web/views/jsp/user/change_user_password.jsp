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
    <title><fmt:message key="user.change_user_password.title"/> </title>
</head>
<body>
<h3><fmt:message key="user.change_user_password.head"/> </h3>
<form name="changing-form" method="post" action="${pageContext.request.contextPath}/controller">
    <div class="card shadow">
        <div class="card-block">
    <input type="hidden" name="command" value="change_user_password">
   <label for="old"><fmt:message key="user.change_user_password.old_password"/></label>
        <input type="password" class="form-control" name="old_password" id="old" required value="">
            <c:if test="${not empty message}">
                <div class="alert alert-danger">
                    <fmt:message key="user.change_user_password.error"/>
                </div>
            </c:if>
    <label for="password1"><fmt:message key="user.change_user_password.new_password"/></label>
        <input type="password" class="form-control" required name="password1" id="password1"pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value="">
            <small id="passwordHelpBlock" class="form-text text-muted">
                <fmt:message key="user.registration.password_helper"/>
            </small>
            <c:if test="${not empty messages.password1}">
                <div class="alert alert-danger">
                    <fmt:message key="user.registration.message.password1"/>
                </div>
            </c:if>
            <label for="password2"><fmt:message key="user.registration.password_confirm"/></label>
        <input type="password" class="form-control" required name="password2" id="password2"pattern= "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}" value=""> </label>
            <c:if test="${not empty messages.password2}">
                <div class="alert alert-danger">
                     <fmt:message key="user.registration.message.password2"/>
                </div>
            </c:if>

    <input class="btn btn-success" type="submit"value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>
</form>

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
