<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
>
<html>
<head>
    <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/nav_bar.jsp" %>
<div class="container">
    <div class="col-sm-6">
        <img src="<c:url value='${company.logo}'/>" alt="${company.name}"/>
        <h3>${company.name}</h3>
        <h6>Company has offices in: ${company.offices}</h6>
        <p>Link to company site: <a href="${company.url}">${company.url}</a></p>
        <h3>About company:</h3>
        ${company.description}

    </div>
    <div class="col-sm-6">
        <h4>Vacancies:</h4>
        <display:table class="table table-hover table-responsive" id="data" name="vacancies" requestURI="/company/${company.id}-about/">
            <display:column title="Title" sortable="true">
                <a href="${data.link}">${data.title}</a>
            </display:column>
            <display:column property="type" title="Type" sortable="true"/>
            <display:column property="location" title="Location" sortable="true"/>
            <display:column property="date" format="{0,date,dd-MM-yyyy}" title="Date" sortable="true"/>
        </display:table>
    </div>
</div>

</body>
</html>
