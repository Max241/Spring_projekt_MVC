<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-01-16
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login Page</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        #login-box {
            width: 300px;
            padding: 20px;
            margin: 10px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body onload='document.loginForm.username.focus();'>

<h3>Spring Security Custom Login Page</h3>
<div id="login-box">

    <h3>Login with Username and Password</h3>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='loginForm' action="<c:url value='/login'/>" method='POST'>

        <table>
            <tr>
                <td>AppUser:</td>
                <td><input type='text' name='login' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
</body>
</html>--%>


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
