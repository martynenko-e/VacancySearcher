<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
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

        <display:table class="table table-hover table-responsive" id="data" name="vacancies" requestURI="/vacancy/list/">
            <display:column title="Title" sortable="true">
                <a href="${data.link}">${data.title}</a>
            </display:column>
            <display:column property="type" title="Type" sortable="true"/>
            <display:column property="location" title="Location" sortable="true"/>
            <display:column title="Company" sortable="true">
                <a href="/company/${data.company.id}-about/">${data.company.name}</a>
            </display:column>
            <display:column property="date" format="{0,date,dd-MM-yyyy}" title="Date" sortable="true"/>
        </display:table>

    </div>
</div>
</body>
</html>