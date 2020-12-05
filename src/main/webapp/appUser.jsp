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
<html>

<body>
<h1><spring:message code="label.appUser"/></h1>
<form:form method="post" action ="addAppUser.html" modelAttribute="appUser">

<table>
    <tr>
        <td><form:label path="name"><spring:message code="label.name"/></form:label></td>
        <td><form:input path="name"/></td>
    </tr>
    <tr>
        <td><form:label path="surname"><spring:message code="label.surname"/></form:label></td>
        <td><form:input path="surname"/></td>
    </tr>
    <tr>
        <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
        <td><form:input path="email"/></td>
    </tr>
    <tr>
        <td><form:label path="phoneNumber"><spring:message code="label.phoneNumber"/></form:label></td>
        <td><form:input path="phoneNumber"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.addAppUser"/>"/>
        </td>
    </tr>

</table>
</form:form>
</body>
</html>
