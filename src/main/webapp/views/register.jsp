<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-02-07
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <form:form class="form-signin" name='registerForm' method="POST" action="/register" modelAttribute="register">
        <div class="text-center col-mb-6">
            <h1 class="h3"><spring:message code="register.title"/></h1>
        </div>

        <div class="form-label-group ">
            <form:input path="name" type="text" id="name" name="name" class="form-control" placeholder="name"
                        required="" autofocus=""/>
            <form:label path="name" for="name"><spring:message code="register.firstName"/></form:label>
            <form:errors path="name"/>
        </div>
        <div class="form-label-group">
            <form:input path="surname" type="text" id="surname" name="surname" class="form-control"
                        placeholder="surname" required="" autofocus=""/>
            <form:label path="surname" for="surname"><spring:message code="register.lastName"/></form:label>
            <form:errors path="surname"/>
        </div>
        <div class="form-label-group">
            <form:input path="pesel1" type="number" size="11" maxlength="11" id="pesel1" name="pesel1"
                        class="form-control" placeholder="Pesel" required="" autofocus=""/>
            <form:label path="pesel" for="pesel"><spring:message code="register.pesel"/></form:label>
            <form:errors path="pesel"/>
        </div>
        <div class="form-label-group">
            <form:input path="phoneNumber" type="text" id="phoneNumber" name="phoneNumber" class="form-control"
                        placeholder="phoneNumber" required="" autofocus=""/>
            <form:label path="phoneNumber" for="phoneNumber"><spring:message code="register.telephone"/></form:label>
            <form:errors path="phoneNumber"/>
        </div>
        <div class="form-label-group">
            <form:input path="email" type="text" id="email" name="email" class="form-control" placeholder="Email"
                        required="" autofocus=""/>
            <form:label path="email" for="email"><spring:message code="register.email"/></form:label>
            <form:errors path="email"/>
        </div>
        <div class="form-label-group">
            <form:input path="password" type="password" id="password" name="password" class="form-control"
                        placeholder="Password" required="" autofocus=""/>
            <form:label path="password" for="password"><spring:message code="register.password"/></form:label>
            <form:errors path="password"/>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="register.title"/></button>
    </form:form>
</div>

