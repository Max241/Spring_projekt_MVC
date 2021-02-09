<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2020-11-01
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<body class="d-flex flex-column min-vh-100 ">
<div class="wrapper flex-grow-1"></div>
<footer class="sticky-footer py-3 fixed-bottom " style="background: #343a40">
    <div class="container-fluid">
        <span class="text-muted">
            <div class="row">
                <div class="col"><spring:message code="label.footer"/> </div>
            </div>
        </span>
    </div>
</footer>
</body>


