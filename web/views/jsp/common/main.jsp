<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<c:import url="${pageContext.request.contextPath}/views/jsp/common/include/footer.jsp"/>
<c:choose>
<c:when test="${not empty user}">
        <c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar_common.jsp"/>
</c:when>
    <c:when test="${user.roleId==1}">
        <c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
    </c:when>
    <c:when test="${user.roleId==2}">
        <c:import url="${pageContext.request.contextPath}/views/jsp/admin/include/menu.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="${pageContext.request.contextPath}/views/jsp/common/include/navbar.jsp"/>
    </c:otherwise>

</c:choose>
<c:if test="${user.roleId==1}">
    <c:import url="${pageContext.request.contextPath}/views/jsp/user/include/menu.jsp"/>
</c:if>
<c:if test="${user.roleId==2}">
    <c:import url="${pageContext.request.contextPath}/views/jsp/admin/include/menu.jsp"/>
</c:if>

<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="request"/>
    <fmt:setBundle basename="localization"/>
    <title><fmt:message key="main.page.title"/> </title>
</head>


   <body>

  <div class="container col-md-8" style="width: 100%">
   <h2 class="text-center"><fmt:message key="main.page.header"/>, ${user.firstName}, <fmt:message key="main.page.date"/> <ctg:date-tag/></h2>
   <div id="UniversityCarousel" class="carousel slide" data-ride="carousel">
       <!-- Indicators -->
       <ol class="carousel-indicators">
           <li data-target="#UniversityCarousel" data-slide-to="0" class="active"></li>
           <li data-target="#UniversityCarousel" data-slide-to="1"></li>
           <li data-target="#UniversityCarousel" data-slide-to="2"></li>
       </ol>

       <!-- Wrapper for slides -->
       <div class="carousel-inner">
           <div class="item active">
               <img src="${pageContext.request.contextPath}/views/img/carousel/1.jpg" alt="Oxford" style="width:100%;">
           </div>

           <div class="item">
               <img src="${pageContext.request.contextPath}/views/img/carousel/2.jpeg" alt="Campus" style="width:100%;">
           </div>

           <div class="item">
               <img src="${pageContext.request.contextPath}/views/img/carousel/3.jpg" alt="Graduates" style="width:100%;">
           </div>
       </div>

       <!-- Left and right controls -->
       <a class="left carousel-control" href="#UniversityCarousel" data-slide="prev">
           <span class="glyphicon glyphicon-chevron-left"></span>
           <span class="sr-only">Previous</span>
       </a>
       <a class="right carousel-control" href="#UniversityCarousel" data-slide="next">
           <span class="glyphicon glyphicon-chevron-right"></span>
           <span class="sr-only">Next</span>
       </a>
   </div>
   </div>

</body>

</html>
