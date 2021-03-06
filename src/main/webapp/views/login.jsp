<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-01-16
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <c:if test="${not empty error}">
        <div class="alert alert-danger container col-5" role="alert">
            <spring:message code="login.error"/>
        </div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="alert alert-primary container col-5" role="alert">
            <spring:message code="login.logout"/>
        </div>
    </c:if>
    <form class="form-signin" name='loginForm' action="<c:url value='/login'/>" method='POST'>
        <div class="text-center mb-4">
            <h1 class="h3 mb-3 font-weight-normal"><spring:message code="login.title"/></h1>
        </div>

        <div class="form-label-group">
            <input type="login" id="login" name="login" class="form-control"
                   placeholder="<spring:message code="login.email"/>" required="" autofocus="">
            <label for="login"><spring:message code="login.email"/></label>
        </div>

        <div class="form-label-group">
            <input type="password" id="password" name="password" class="form-control"
                   placeholder="<spring:message code="login.password"/>"
                   required="">
            <label for="password"><spring:message code="login.password"/></label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.title"/></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
