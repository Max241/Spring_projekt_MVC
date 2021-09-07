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
<%--<html>

<body>
<h1><spring:message code="label.appUser"/></h1>
<form:form method="post" action="addAppUser.html" modelAttribute="appUser">

    <table>
        <tr>
            <td><form:hidden path="id"/>
        </tr>
        <tr>
            <td><form:label path="login"><spring:message code="label.login"/></form:label></td>
            <td><form:input path="login"/></td>
            <td><form:errors path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
            <td><form:input type="password" path="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="enabled"><spring:message code="label.enabled"/></form:label></td>
            <td><form:checkbox path="enabled"/></td>
            <td><form:errors path="enabled"/></td>
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
            <td><form:label path="pesel"><spring:message code="label.pesel"/></form:label></td>
            <td><form:input path="pesel"/></td>
            <td><form:errors path="pesel"/></td>
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
    <c:if test="${!empty appUserList}">
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
                    <td><a href="generatePdf-${appUser.id}">pdf</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</form:form>
</body>
</html>--%>

<main role="main">
    <section class="container">
        <c:if test="${not empty errorDate}">
            <div class="alert alert-primary container col-5 text-center" role="alert">
                <spring:message code="${errorDate}"/>
            </div>
        </c:if>
        <c:if test="${!empty appUserList}">
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
                <c:forEach items="${appUserList}" var="appUser">
                    <tr>
                        <td>${appUser.name} </td>
                        <td>${appUser.surname} </td>
                        <td>${appUser.email}</td>
                        <td>${appUser.phoneNumber}</td>
                        <td>
                            <div class="btn-group" role="group" aria-label="">
                                <form:form class="form-signin" name='deleteUser' method="POST"
                                           action="/delete/${appUser.id}">
                                    <button class="btn btn-lg btn-danger" type="submit"><spring:message
                                            code="label.delete"/></button>
                                </form:form>
                                <form:form class="form-signin" name='editUser' method="POST"
                                           action=" /AppUser/get/${user.id}">
                                    <button class="btn btn-lg btn-success" type="submit"><spring:message
                                            code="label.edit"/></button>
                                </form:form>
                                <form:form class="form-signin" name='generatePdf' method="GET"
                                           action="/generatePdf-${appUser.id}">
                                    <button class="btn btn-lg btn-info" type="submit"><spring:message
                                            code="label.pdf"/></button>
                                </form:form>

                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <%--<div class="container">&lt;%&ndash;
                    &lt;%&ndash;  <form:form class="form-signin" name='editappUser' method="POST" action="/get/${appUser.id}"
                                 modelAttribute="appUser">&ndash;%&gt;
            <form:form class="form-signin" name='appUser' method="POST" action="addAppUser.html"
                       modelAttribute="appUser">
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
                    <form:label path="name" for="name"><spring:message code="editProfile.firstName"/></form:label>
                    <form:errors path="name"/>
                </div>
                <div class="form-label-group">
                    <form:input path="surname" type="text" id="surname" name="surname" class="form-control"
                                placeholder="surname" required=""
                                autofocus=""/>
                    <form:label path="surname" for="surname"><spring:message code="editProfile.lastName"/></form:label>
                    <form:errors path="lastName"/>
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
            </form:form>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message
                    code="editProfile.title"/></button>

        </div>--%>
        <%--</form:form>--%>
        <%--        <form:form class="form-signin" name='editAppUser' method="POST" action="/get/${editAppUser.id}"
                           modelAttribute="editAppUser">
                    <div class="form-label-group">
                        <form:input path="name" type="text" id="name" name="name" class="form-control"
                                    placeholder="name" required=""
                                    autofocus=""/>
                        <form:label path="name" for="name"><spring:message code="label.name"/></form:label>
                        <form:errors path="name"/>
                    </div>
                    <div class="form-label-group">
                        <form:input path="surname" type="text" id="surname" name="surname" class="form-control"
                                    placeholder="surname" required=""
                                    autofocus=""/>
                        <form:label path="surname" for="surname"><spring:message code="label.surname"/></form:label>
                        <form:errors path="surname"/>
                    </div>
                    <div class="form-label-group">
                        <form:input path="phoneNumber" type="number" id="phoneNumber" name="phoneNumber"
                                    class="form-control"
                                    placeholder="email" required=""
                                    autofocus=""/>
                        <form:label path="phoneNumber" for="phoneNumber"><spring:message code="label.phoneNumber"/></form:label>
                        <form:errors path="phoneNumber"/>
                    </div>
                    <div class="form-label-group">
                        <form:input path="pesel" type="number" id="pesel" name="pesel" class="form-control"
                                    placeholder="pesel" required=""
                                    autofocus=""/>
                        <form:label path="pesel" for="pesel"><spring:message code="label.pesel"/></form:label>
                        <form:errors path="pesel"/>
                    </div>
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
                    <form:form class="form-signin" name='appUserEditProfile' method="POST"
                               action="/delete/${appUser.id}">
                        <button class="btn btn-lg btn-primary btn-block m-0" type="submit"><spring:message
                                code="label.editAppUser"/></button>
                    </form:form>
                </form:form>
            </section>
        </main>--%>

    </section>
</main>
