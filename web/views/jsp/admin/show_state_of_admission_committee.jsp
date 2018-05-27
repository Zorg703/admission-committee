<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.05.2018
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
<c:import url="/views/jsp/admin/include/menu.jsp"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="request"/>
<fmt:setBundle basename="localization"/>
<head>
    <title><fmt:message key="admin.show_state_of_admission_committee.title"/> </title>
</head>
<body>
<h3> <fmt:message key="admin.show_state_of_admission_committee.head"/> : ${faculty.facultyName}</h3>
<c:if test="${not empty dto_list}">
    <table class="table table-bordered table-hover"style="width: 70%">
        <tr>
            <th scope="col" style="width: 25%">#</th>
            <th scope="col" style="width: 25%">
                <fmt:message key="admin.show_all_specialities.id"/>
            </th>
            <th scope="col" style="width: 50%">
                <fmt:message key="admin.add_speciality_on_faculty.speciality_name"/>
            </th>
            <th>
                <fmt:message key="admin.add_speciality_on_faculty.recruitment_plan"/>
            </th>
            <th>
                <fmt:message key="admin.show_state_of_admission_committee.count_of_users"/>
            </th>
            <th>
                <fmt:message key="admin.show_state_of_admission_committee.passing_score"/>
            </th>
            <th>
                <fmt:message key="admin.show_state_of_admission_committee.speciality_set"/>
            </th>
            <th>
                <fmt:message key="admin.change_register_date.title"/>
            </th>
        </tr>
        <c:forEach items="${dto_list}" var="speciality_dto" varStatus="loop">
            <tr>
                <td>
                    ${loop.index+1}
                </td>
                <td>
                    ${speciality_dto.specialityId}
                </td>
                <td>
                    ${speciality_dto.specialityName}
                </td>

                <td>
                    ${speciality_dto.recruitmentPlan}
                </td>
                <td>
                    ${speciality_dto.countRegisterUser}
                </td>
                <td>
                    <c:if test="${speciality_dto.passingScore!=0}">
                        ${speciality_dto.passingScore}
                    </c:if>
                </td>
                  <td>
                    <c:choose>
                        <c:when test="${speciality_dto.specialityFull==true and speciality_dto.registerEnd==true}">
                            <fmt:message key="admin.show_state_of_admission_committee.full"/>
                        </c:when>
                        <c:when test="${speciality_dto.specialityFull==false and speciality_dto.registerEnd==true}">
                            <fmt:message key="admin.show_state_of_admission_committee.not_full"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="admin.show_state_of_admission_committee.activ"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_change_speciality_register_date_page&speciality_id=${speciality_dto.specialityId}"><fmt:message key="admin.show_state_of_admission_committee.change"/> </a>
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>
</body>

</html>
