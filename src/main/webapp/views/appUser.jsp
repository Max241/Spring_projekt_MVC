<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2020-11-01
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<h1><spring:message code="label.appUser"/></h1>
<form:form method="post" action ="addAppUser.html" modelAttribute="appUser">

<table>
    <tr>
        <td><form:hidden path="id"/>
    </tr>
    <tr>
        <td><form:label path="name"><spring:message code="label.name"/></form:label></td>
        <td><form:input path="name"/></td
        <td><form:errors path="name"/></td>
    </tr>
    <tr>
        <td><form:label path="surname"><spring:message code="label.surname"/></form:label></td>
        <td><form:input path="surname"/></td>
        <td><form:errors path="surname"/></td>
    </tr>
    <tr>
        <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
        <td><form:input path="email"/></td>
        <td><form:errors path="email"/></td>

    </tr>
    <tr>
        <td><form:label path="phoneNumber"><spring:message code="label.phoneNumber"/></form:label></td>
        <td><form:input path="phoneNumber"/></td>
        <td><form:errors path="phoneNumber"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <c:if test="${appUser.id==0}">
                <input type="submit" value="<spring:message code="label.addAppUser"/>"/>
            </c:if>
            <c:if test="${appUser.id!=0}">
                <input type="submit" value="<spring:message code="label.editAppUser"/>"/>
            </c:if>
        </td>
    </tr>
</table>
    <h3><spring:message code="label.userList"/></h3>
    <c:if  test="${!empty appUserList}">
        <table class="data">
            <tr>
                <th><spring:message code="label.name"/></th>
                <th><spring:message code="label.surname"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.phoneNumber"/></th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${appUserList}" var="appUser">
                <tr>
                    <td>${appUser.name} </td>
                    <td>${appUser.surname} </td>
                    <td>${appUser.email}</td>
                    <td>${appUser.phoneNumber}</td>
                    <td><a href="delete/${appUser.id}.html">delete</a></td>
                    <td><a href="appUsers.html?appUserId=${appUser.id}">edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</form:form>
</body>
</html>
