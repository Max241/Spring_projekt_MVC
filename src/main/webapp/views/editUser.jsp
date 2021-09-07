<%--Created by IntelliJ IDEA.
User: db2admin
Date: 2021-09-04
Time: 16;30
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form:form class="form-signin" name='editappUser' method="POST" action="/appUsers/get/${editUser.id}"
               modelAttribute="editUser">
        <c:if test="${not empty success}">
            <div class="alert alert-primary container col-8 text-center" role="alert">
                <spring:message code="${success}"/>
            </div>
        </c:if>
        <c:if test="${not empty deleted}">
            <div class="alert alert-danger container col-8 text-center" role="alert">
                <spring:message code="${deleted}"/>
            </div>
        </c:if>


        <div class="form-label-group">
            <form:input path="name" type="text" id="name" name="name" class="form-control" placeholder="name"
                        required=""
                        autofocus=""/>
            <form:label path="name" for="name"><spring:message code="editProfile.name"/></form:label>
            <form:errors path="name"/>
        </div>
        <div class="form-label-group">
            <form:input path="surname" type="text" id="surname" name="surname" class="form-control"
                        placeholder="surname" required=""
                        autofocus=""/>
            <form:label path="surname" for="surname"><spring:message code="editProfile.surname"/></form:label>
            <form:errors path="surname"/>
        </div>
        <div class="form-label-group">
            <form:input path="pesel" type="number" min="10000000000" max="99999999999" size="11" maxlength="11"
                        id="pesel" name="pesel" class="form-control" placeholder="Pesel" required=""
                        autofocus=""/>
            <form:label path="pesel" for="pesel"><spring:message code="editProfile.pesel"/></form:label>
            <form:errors path="pesel"/>
        </div>
        <div class="form-label-group">
            <form:input path="phoneNumber" type="text" id="phoneNumber" name="phoneNumber" class="form-control"
                        placeholder="phoneNumber" required=""
                        autofocus=""/>
            <form:label path="phoneNumber" for="phoneNumber"><spring:message
                    code="editProfile.telephone"/></form:label>
            <form:errors path="phoneNumber"/>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message
                code="editProfile.title"/></button>
    </form:form>
    <form:form class="form-signin" name='deleteUser' method="POST" action="/users/delete/${editUser.id}">
        <button class="btn btn-lg btn-danger btn-block" type="submit"><spring:message
                code="editProfile.title"/></button>
    </form:form>

</div>