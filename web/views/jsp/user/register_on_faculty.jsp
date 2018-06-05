<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="user.register_on_faculty.title"/></title>
</head>

<body>
<c:if test="${not empty subjects}">
<h3><fmt:message key="user.register_on_faculty.name"/>:</h3>
<form name="subject_marks" method="post" action="${pageContext.request.contextPath}/controller">
<input type="hidden" name="command" value="register_on_speciality">
    <div class="card shadow">
        <div class="card-block">
            <h4>${subjects[0].subjectName}</h4>
            <input type="hidden"  name="first_subject" value="${subjects[0].subjectId}">
            <label for="first"><fmt:message key="user.registration.mark"/>:</label>
            <input type="number" id="first" class="form-control" required min="0" max="100" name="mark1" value="">
            <small id="nameHelpBlock" class="form-text text-muted">
                <fmt:message key="user.user_score.helper"/>
            </small>
            <h4>${subjects[1].subjectName}</h4>
    <input type="hidden" name="second_subject" value="${subjects[1].subjectId}">
    <label for="second"><fmt:message key="user.registration.mark"/>:</label>
    <input type="number" class="form-control"id="second" required min="0" max="100" name="mark2" value=""><br>
            <small id="nameHelpBlock" class="form-text text-muted">
                <fmt:message key="user.user_score.helper"/>
            </small>
            <h4>${subjects[2].subjectName}</h4>
    <input type="hidden" name="third_subject" value="${subjects[2].subjectId}">
    <label for="third"><fmt:message key="user.registration.mark"/>:</label>
    <input type="number" id="third" class="form-control" required min="0" max="100" name="mark3" value=""><br>
            <small id="nameHelpBlock" class="form-text text-muted">
                <fmt:message key="user.user_score.helper"/>
            </small>
            <label for="avg"><fmt:message key="user.registration.certificate"/>:</label>
    <input type="number" id="avg" class="form-control" required min="0" max="100" name="avg" value="">
            <small id="nameHelpBlock" class="form-text text-muted">
                <fmt:message key="user.user_score.helper"/>
            </small>
            <c:if test="${not empty message}">
        <div class="alert alert-danger">
        <fmt:message key="user.registration.message.mark"/>
        </div>
    </c:if>
    <input class="btn btn-success" type="submit" value=<fmt:message key="user.registration.confirm"/>>
        </div>
    </div>
</form>
</c:if>
<c:if test="${not empty message}">
    <div class="alert alert-danger">
        <fmt:message key="admin.delete_faculty.message"/>
    </div>
</c:if>
<c:if test="${not empty messages}">
    <h3><fmt:message key="user.register_on_faculty.error_message"/></h3>
</c:if>
<c:if test="${not empty end_registration}">
    <h3><fmt:message key="user.register_on_faculty.end"/> </h3>
</c:if>
</body>
</html>
