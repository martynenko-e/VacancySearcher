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
<h2>List of Vacancies</h2>
<table>
    <tr>
        <td>Title</td><td>Salary</td><td>Type</td><td>Description</td><td>Date</td><td>KeyWord</td><td>Company</td><td>Link</td><td></td>
    </tr>
    <c:forEach items="${vacancies}" var="vacancy">
        <tr>
            <td><a href="<c:url value='${vacancy.link}' />">${vacancy.title}</a></td>
            <td>${vacancy.salary}</td>
            <td>${vacancy.type}</td>
            <td>${vacancy.description}</td>
            <td>${vacancy.date}</td>
            <td>${vacancy.keyWord}</td>
            <td>${vacancy.company.name}</td>
            <td><a href="<c:url value='/vacancy/edit-${vacancy.id}-vacancy/' />">${vacancy.link}</a></td>
            <td><a href="<c:url value='/vacancy/delete-${vacancy.id}-vacancy/' />">delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<c:url value='/vacancy/new/' />">Add New Vacancy</a>
</body>
</html>