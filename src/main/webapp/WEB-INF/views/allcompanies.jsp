<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>University Enrollments</title>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>

</head>


<body>
<h2>List of Companies</h2>
<table>
    <tr>
        <td>Name</td><td>Country</td><td>City</td><td>Description</td><td>Url</td><td></td>
    </tr>
    <c:forEach items="${companies}" var="company">
        <tr>
            <td>${company.name}</td>
            <td>${company.country}</td>
            <td>${company.city}</td>
            <td>${company.description}</td>
            <td><a href="<c:url value='/company/edit-${company.url}-company/' />">${company.url}</a></td>
            <td><a href="<c:url value='/company/delete-${company.url}-company/' />">delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<c:url value='/company/new/' />">Add New Company</a>
</body>
</html>
