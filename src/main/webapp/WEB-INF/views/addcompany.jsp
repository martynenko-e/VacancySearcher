<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Vacancy Adding Form</title>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2>Update Form</h2>

<form:form method="POST" modelAttribute="company">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="name">Name: </label> </td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="city">City: </label> </td>
            <td><form:input path="city" id="city"/></td>
            <td><form:errors path="city" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="url">URL: </label> </td>
            <td><form:input path="url" id="url"/></td>
            <td><form:errors path="url" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="country">Country: </label> </td>
            <td><form:input path="country" id="country"/></td>
            <td><form:errors path="country" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="description">Description: </label> </td>
            <td><form:input path="description" id="description"/></td>
            <td><form:errors path="description" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="3">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Go back to <a href="<c:url value='/company/list/' />">List of All Companies</a>
</body>
</html>