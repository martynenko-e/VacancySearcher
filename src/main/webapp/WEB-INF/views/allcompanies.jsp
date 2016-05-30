<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<%@include file="includes/header.jsp" %>
<body>
<%@include file="includes/nav_bar.jsp" %>
<div class="page-content">
    <div class="container">
        <c:forEach items="${companies}" var="company">

            <div class="col-xs-5 col-md-5 table-bordered single-company">
                <a href="/company/${company.id}-about/"><h2>${company.name}</h2></a>

                <p><a href="${company.url}">${company.url}</a></p>

                <p><img src="<c:url value='${company.logo}'/>"></p>

                <p>${company.offices}</p>
            </div>

        </c:forEach>
    </div>
</div>

</body>
</html>
