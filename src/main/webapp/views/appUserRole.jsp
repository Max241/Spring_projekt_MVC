<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-02-05
  Time: 00:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<body>
<form:form method="post" action="addAppUserRole.html" modelAttribute="appUserRole">
    <table>
        <tr>
            <td><form:hidden path="id"/>
        </tr>
        <tr>
            <td><form:label path="role"><spring:message code="label.role"/></form:label></td>
            <td><form:input path="role"/></td>
            <td><form:errors path="role"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message code="label.addAppUserRole"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
