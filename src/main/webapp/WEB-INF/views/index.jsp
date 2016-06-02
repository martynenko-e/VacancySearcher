<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
>
<html>
<head>
    <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/nav_bar.jsp" %>
<div class="container">
    <div class="col-sm-6">
        <div class="row">
            <h4>Present parsers:</h4>
            Parse <a href="<c:url value='/parse/astound-company/' />">Astound Company</a><br/>
            Parse <a href="<c:url value='/parse/luxoft-company/' />">Luxoft Company</a><br/>
            Parse <a href="<c:url value='/parse/add-company/' />">Add Companies</a><br/>
            Parse <a href="<c:url value='/parse/intropro-llc-company/' />">Intropro Company</a><br/>
        </div>
        <div class="row">
            <h4>Useful links:</h4>
        </div>
    </div>
    <div class="col-sm-6">

        <h4>Top companies:</h4>
        <c:forEach items="${companies}" var="company">
            <div class="col-sm-6">
                <a href="/company/${company.id}-about/"><img src="<c:url value='${company.logo}'/>" alt="${company.name}"/></a>
            </div>
        </c:forEach>

    </div>
</div>

</body>
</html>
