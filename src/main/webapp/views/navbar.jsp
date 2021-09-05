<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-02-03
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark nav-fill">
    <a class="navbar-brand" href="#">MediCover</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/"/>"><spring:message code="menu.home"></spring:message> <span
                        class="sr-only">(current)</span></a>
            </li>
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/login"/>"><spring:message code="menu.login"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/register"/>"><spring:message code="menu.register"/></a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-uppercase" href="#" id="adminProfileDropdown"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">Admin Panel</a>
                    <div class="dropdown-menu" aria-labelledby="adminProfileDropdown">
                        <a class="dropdown-item" href="<c:url value="/appUsers"/>"><spring:message
                                code="menu.adminPanel.EditUsers"/></a>
                        <a class="dropdown-item" href="<c:url value="/visitHours/list/1"/>"><spring:message
                                code="menu.visitsHours"/></a>
                        <a class="dropdown-item" href="<c:url value="/visits/list/1"/>"><spring:message
                                code="menu.visits"/></a>
                        <a class="dropdown-item" href="javascript:formSubmit()"><spring:message code="menu.logout"/></a>
                <li class="nav-item">
                </li>
            </sec:authorize>
            <sec:authorize access="!hasRole('ROLE_ADMIN')">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-uppercase" href="#" id="profileDropdown"
                           data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">${pageContext.request.userPrincipal.name}</a>
                        <div class="dropdown-menu" aria-labelledby="profileDropdown">
                            <a class="dropdown-item" href="<c:url value="/appUsers"/>"><spring:message
                                    code="menu.editProfile"/></a>
                            <sec:authorize access="!hasRole('ROLE_DOCTOR')">
                                <a class="dropdown-item" href="<c:url value="/visitHours"/>"><spring:message
                                        code="menu.visitsHours"/></a>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_DOCTOR')">
                                <a class="dropdown-item" href="<c:url value="/visitHours/add"/>"><spring:message
                                        code="menu.visitsHours"/></a>
                                <a class="dropdown-item" href="<c:url value="/visits/list/1"/>"><spring:message
                                        code="menu.visits"/></a>
                            </sec:authorize>
                            <a class="dropdown-item" href="<c:url value="/visits/visits/1"/>">My visits</a>
                            <a class="dropdown-item" href="javascript:formSubmit()"><spring:message
                                    code="menu.logout"/></a>
                        </div>
                    </li>
                </c:if>
            </sec:authorize>
        </ul>
        <form class="form-inline mt-2 mt-md-0">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="languageDropdown" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false"><spring:message code="menu.language"/></a>
                <div class="dropdown-menu" aria-labelledby="languageDropdown">
                    <a class="dropdown-item" href="?lang=pl">PL</a>
                    <a class="dropdown-item" href="?lang=eng">EN</a>
                    <a class="dropdown-item" href="?lang=fr">FR</a>
                </div>
            </li>
        </form>
    </div>
</nav>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>
<!-- csrf for log out-->
<form action="/logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

