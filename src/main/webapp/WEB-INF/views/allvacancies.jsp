<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<html>
<%@include file="includes/header.jsp" %>
<body>
<%@include file="includes/nav_bar.jsp" %>
<div class="page-content">
    <div class="container">

        <display:table class="table table-hover table-responsive" id="data" name="vacancies"
                       requestURI="/vacancy/list/">
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
<script language="javascript" type="text/javascript">
    var tableFilters = {
        btn: false,
        col_1: "select",
        col_2: "select",
        col_3: "select",
        col_4: "none",
        rows_counter: true,
        btn_reset: true,
        btn_text: "  >  ",
    };
    setFilterGrid("data", 0, tableFilters);
</script>
</body>
</html>