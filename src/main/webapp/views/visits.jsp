<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-08-29
  Time: 12:09
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
        <table class="table table-hover-rounded">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><spring:message code="visit.doctor"/></th>
                <th scope="col"><spring:message code="visit.visitDate"/></th>
                <th scope="col"><spring:message code="visitHours.visitLength"/></th>
                <th scope="col"><spring:message code="visit.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${visitList}" var="visit">
                <tr>
                    <td>${visit.visitHours.doctor.name} ${visit.visitHours.doctor.surname}</td>
                    <td>${visit.visitHours.startDate} </td>
                    <td> ${visit.visitHours.visitLength} </td>
                    <td>
                        <c:if test="${visit.cancelled}">
                            <spring:message code="visit.cancelled"/>
                        </c:if>
                        <c:if test="${visit.approved}">
                            <a class="alert-link" href="<c:url value="/visits/bill/${visit.id}"/>"><spring:message
                                    code="visit.approved"/></a>
                        </c:if>
                        <c:if test="${!visit.cancelled && !visit.approved}">
                            <c:if test="${ldtNow < visit.getVisitHours().getStartDate()}">
                                <form:form class="form-signin" name='cancel' method="POST"
                                           action="/visits/cancel/${visit.id}">
                                    <button class="btn btn-danger btn-block m-0" type="submit"><spring:message
                                            code="visit.cancel"/></button>
                                </form:form>
                            </c:if>
                            <c:if test="${ldtNow >= visit.getVisitHours().getStartDate()}">
                                <spring:message code="visit.pending"/>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</main>