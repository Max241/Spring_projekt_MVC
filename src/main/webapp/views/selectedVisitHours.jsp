<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-08-28
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<main role="main">
    <section class="container text-center">
        <c:if test="${not empty notAvailable}">
            <div class="alert alert-primary container col-8 text-center" role="alert">
                <spring:message code="${notAvailable}"/>
            </div>
        </c:if>
        <h5><spring:message code="visitHours.doctor"/>: ${visitHours.doctor.name} ${visitHours.doctor.surname}<br></h5>
        <h5><spring:message code="visitHours.description"/>: ${visitHours.description}<br></h5>
        <h5><spring:message code="visitHours.visitCost"/>: ${visitHours.visitCost}</h5>
        <c:if test="${not empty hourList}">
            <div class="alert alert-success container col-5" role="alert">
                <c:forEach items="${hourList}" var="hour" varStatus="loop">
                    ${visitHours.startDate} - ${visitHours.endDate}
                    <c:if test="${hour[3]}">
                        <sec:authorize access="hasRole('PATIENT')">
                            <a href="/visits/book/${visitHours.id}}"><spring:message
                                    code="visitHours.book"/></a>
                        </sec:authorize>
                    </c:if>
                    <br>
                </c:forEach>
            </div>
        </c:if>
        <sec:authorize access="isAnonymous()">
            <spring:message code="visitHours.signin"/>
        </sec:authorize>
    </section>
</main>