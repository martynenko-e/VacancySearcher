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

<form:form method="POST" modelAttribute="vacancy">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="title">Title: </label> </td>
            <td><form:input path="title" id="title"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="type">Type: </label> </td>
            <td><form:input path="type" id="type"/></td>
            <td><form:errors path="type" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="description">Description: </label> </td>
            <td><form:input path="description" id="description"/></td>
            <td><form:errors path="description" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="keyWord">Key Words: </label> </td>
            <td><form:input path="keyWord" id="keyWord"/></td>
            <td><form:errors path="keyWord" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="salary">Salary: </label> </td>
            <td><form:input path="salary" id="salary"/></td>
            <td><form:errors path="salary" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="company.id">Company: </label> </td>
            <td><form:select path="company.id" items="${companies}" multiple="false" itemValue="id"/></td>
            <td><form:errors path="company.id" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="link">Link: </label> </td>
            <td><form:input path="link" id="link"/></td>
            <td><form:errors path="link" cssClass="error"/></td>
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
Go back to <a href="<c:url value='/vacancy/list/' />">List of All Vacancies</a>
</body>
</html>