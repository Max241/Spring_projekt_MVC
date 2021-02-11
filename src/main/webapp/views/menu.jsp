<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2020-11-01
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<p><spring:message code="label.menu"/></p>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/appUsers.html"><spring:message code="label.title"/></a>
    <br/>
    <a href="/appUserRole.html"><spring:message code="label.role"/></a>
    <br/>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
    <a href="/exampleOne.html"><spring:message code="label.example"/> 1</a>
    <br/>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_STUDENT')">
    <a href="/exampleTwo.html"><spring:message code="label.example"/> 2</a>
    <br/>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_USER')">
    <a href="/exampleThree.html"><spring:message code="label.example"/> 3</a>
    <br/>
</sec:authorize>


<br/>
<%--<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>
<!-- csrf for log out-->
<form action="/logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>--%>

<br/>
<%--<div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>
            <spring:message code="label.welcome"/> : ${pageContext.request.userPrincipal.name} |
            <a href="javascript:formSubmit()"> Logout</a>
        </p>
    </c:if>
</div>--%>
