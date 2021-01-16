<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2020-11-01
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>



<div class="header">
    <h3><spring:message code="label.title" /></h3>
    <span style="float: right">
    	<a style="background-color: white" href="?lang=pl">pl</a> | <a style="background-color: white" href="?lang=eng">eng</a> | <a style="background-color: white" href="?lang=fr">fr</a>
	</span>
    <br>

    <form id="langForm" action="" method="get">
	<span style="float: right">
		<select size="1" name="lang" onchange="form.submit()">
	        <option>Language</option>
	        <option value ="eng">ENG</option>
            <option value ="pl">PL</option>
	        <option value ="fr">FR</option>
        </select>
	</span>
    </form>
</div>