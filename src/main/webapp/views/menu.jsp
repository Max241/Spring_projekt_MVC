<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2020-11-01
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<p><spring:message code="label.menu"/></p>
<a href="/appUsers.html"><spring:message code="label.title"/></a>
<br/>
<a href="/exampleOne.html"><spring:message code="label.example"/> 1</a>
<br/>
<a href="/exampleTwo.html"><spring:message code="label.example"/> 2</a>
<br/>
<a href="/exampleThree.html"><spring:message code="label.example"/> 3</a>

<br/>
<div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>
            <spring:message code="label.welcome"/> : ${pageContext.request.userPrincipal.name} |
            <a href="/logout"> Logout</a>
        </p>
    </c:if>
</div>