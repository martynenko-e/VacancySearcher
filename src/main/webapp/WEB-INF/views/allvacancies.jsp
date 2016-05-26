<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <head>
        <%@include file="includes/header.jsp" %>
    </head>
</head>
<body>
<%@include file="includes/nav_bar.jsp" %>

<div id="sidebar-wrapper" class="sidebar-toggle">
    <h4>Filters</h4>
</div>
<div class="page-content">
    <div class="container">
        <c:forEach items="${vacancies}" var="vacancy">
            <div class="row">
                <div class="col-xs-4 col-md-4">
                    <p><a href="${vacancy.link}">${vacancy.title}</a></p>
                </div>
                <div class="col-xs-4 col-md-4">
                    <p>${vacancy.type}</p>
                </div>
                <div class="col-xs-2 col-md-2">
                    <p>${vacancy.company.name}</p>
                </div>
                <div class="col-xs-2 col-md-2">
                    <p><fmt:formatDate value="${vacancy.date}" pattern="dd-MM-yyyy"/></p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>