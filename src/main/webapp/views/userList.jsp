<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2021-09-03
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main role="main">
    <section class="container text-center">
        <c:if test="${!empty userList}">
            <table class="table table-hover-rounded text-center">
                <thead class="thead-dark">
                <tr>
                    <th><spring:message code="label.name"/></th>
                    <th><spring:message code="label.surname"/></th>
                    <th><spring:message code="label.email"/></th>
                    <th><spring:message code="label.phoneNumber"/></th>
                    <th><spring:message code="label.manage"/></th>
                </tr>
                </thead>
                <c:forEach items="${userList}" var="appUser">
                    <tr>
                        <td>${appUser.name} </td>
                        <td>${appUser.surname} </td>
                        <td>${appUser.email}</td>
                        <td>${appUser.phoneNumber}</td>
                        <td>
                            <div class="btn-group" role="group" aria-label="">
                                <a href="/appUsers/get/${appUser.id}" class="btn btn-lg btn-primary"
                                   role="button"><spring:message
                                        code="label.pdf"/> </button></a>
                                <form:form class="form-signin" name='generatePdf' method="GET"
                                           action="/generatePdf-${appUser.id}">
                                    <button class="btn btn-lg btn-info" type="submit"><spring:message
                                            code="label.pdf"/></button>
                                </form:form>
                                <form:form class="form-signin" name='deleteUser' method="POST"
                                           action="/delete/${appUser.id}">
                                    <button class="btn btn-lg btn-danger" type="submit"><spring:message
                                            code="label.delete"/></button>
                                </form:form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        </table>
    </section>
</main>