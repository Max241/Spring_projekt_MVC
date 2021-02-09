<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2020-11-01
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true"/> - <spring:message code="label.example"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/floating-labels.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap-4.5.3-dist/css/bootstrap.min.css"/>">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
            crossorigin="anonymous"></script>
    <script src="<c:url value="/resources/bootstrap-4.5.3-dist/js/bootstrap.bundle.min.js"/>"></script>
</head>
<body>
<tiles:insertAttribute name="navbar"/>
<div class="container">
    <tiles:insertAttribute name="header"/>
    <div class="row">
        <div class="col-12">
            <tiles:insertAttribute name="menu"/>
            <tiles:insertAttribute name="body"/>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
</body>
</html>


<%--<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="titles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>
<table border="1" cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td height="80" colspan="2"><tiles:insertAttribute name="header"/>
        </td>
    </tr>
    <tr>
        <td height="200"><tiles:insertAttribute name="menu"/></td>
        <td width="600"><tiles:insertAttribute name="body"/></td>
    </tr>
    <tr>
        <td height="30" colspan="2"><tiles:insertAttribute name="footer"/>
        </td>
    </tr>


</table>

</body>
</html>--%>
