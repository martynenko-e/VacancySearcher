<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            </div>
            <div class="col-xs-6 col-md-6">
                <p>${vacancy.company.name}</p>
            </div>
        </div>
        </br>
    </c:forEach>
</div>
</body>
</html>