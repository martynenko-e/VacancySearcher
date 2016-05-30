<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<%@include file="includes/header.jsp" %>
<body>
<%@include file="includes/nav_bar.jsp" %>
<div class="container">
    <div class="page-content">
        <div class="col-sm-6">
            <div class="row">
                <h4>Present parsers:</h4>
                Parse <a href="<c:url value='/parse/ciklum-company/' />">Ciklum Company</a><br/>
                Parse <a href="<c:url value='/parse/luxoft-company/' />">Luxoft Company</a><br/>
            </div>
            <div class="row">
                <h4>Useful links:</h4>
            </div>
        </div>
        <div class="col-sm-6">

            <h4>Top companies:</h4>
            <c:forEach items="${companies}" var="company">
                <div class="col-sm-6">
                    <a href="/company/${company.id}-about/"><img src="<c:url value='${company.logo}'/>"
                                                                 alt="${company.name}"/></a>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
</body>
</html>
