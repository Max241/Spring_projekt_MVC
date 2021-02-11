<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-02-10
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container">
    <form:form class="form-signin" name='appUserEditProfile' method="POST" action="/appUserEditProfile"
               modelAttribute="appUserEditProfile">
        <div class="text-center col-mb-6">
            <h1 class="h3"><spring:message code="editProfile.title"/></h1>
        </div>

        <div class="form-label-group">
            <form:input path="name" type="text" id="name" name="name" class="form-control"
                        placeholder="name" required=""
                        autofocus=""/>
            <form:label path="name" for="name"><spring:message code="editProfile.firstName"/></form:label>
            <form:errors path="name"/>
        </div>
        <div class="form-label-group">
            <form:input path="surname" type="text" id="surname" name="surname" class="form-control"
                        placeholder="surname" required=""
                        autofocus=""/>
            <form:label path="surname" for="surname"><spring:message code="editProfile.lastName"/></form:label>
            <form:errors path="surname"/>
        </div>
        <div class="form-label-group">
            <form:input path="pesel" type="number" size="11" maxlength="11"
                        id="pesel" name="pesel" class="form-control" placeholder="Pesel" required=""
                        autofocus=""/>
            <form:label path="pesel" for="pesel"><spring:message code="editProfile.pesel"/></form:label>
            <form:errors path="pesel"/>
        </div>
        <div class="form-label-group">
            <form:input path="phoneNumber" type="text" id="phoneNumber" name="phoneNumber" class="form-control"
                        placeholder="phoneNumber" required=""
                        autofocus=""/>
            <form:label path="phoneNumber" for="phoneNumber"><spring:message code="editProfile.telephone"/></form:label>
            <form:errors path="phoneNumber"/>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message
                code="editProfile.title"/></button>
    </form:form>
</div>
