<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/nav_bar.jsp" %>

<div class="container">
    <c:forEach items="${companies}" var="company">
        <div class="row table-bordered">
            <div class="col-xs-4 col-md-4">
                <h2>${company.name}</h2>
                <p><a href="${company.url}">${company.url}</a></p>
                <p><img src="<c:url value='${company.logo}'/>"></p>
                <p>${company.offices}</p>
            </div>
            <div class="col-xs-8 col-md-8">
                <p>${fn:substring(company.description, 0, 1000)}...</p>
            </div>
        </div>
        </br>
    </c:forEach>
</div>
</body>
</html>
