<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <head><%@include file="includes/header.jsp" %></head>
</head>
<body>
<%@include file="includes/nav_bar.jsp" %>
<div class="container">
    <c:forEach items="${vacancies}" var="vacancy">
        <div class="row table-bordered">
            <div class="col-xs-6 col-md-6">
                <p><a href="${vacancy.link}">${vacancy.title}</a></p>
                <p>${vacancy.type}</p>
                <p>${vacancy.skills_required}</p>
            </div>
            <div class="col-xs-3 col-md-3">
                <p>${vacancy.company.name}</p>
            </div>
            <div class="col-xs-3 col-md-3">
                <p><fmt:formatDate value="${vacancy.date}" pattern="dd-MM-yyyy"/></p>
            </div>
        </div>
        </br>
    </c:forEach>
</div>
</body>
</html>