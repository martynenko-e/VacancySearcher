<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update Confirmation Page</title>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>
<body>
message : ${success}
<br/>
<br/>
Go back to <a href="<c:url value='/vacancy/list/' />">List of All Vacancies</a>
Go back to <a href="<c:url value='/company/list/' />">List of All Companies</a>

</body>

</html>