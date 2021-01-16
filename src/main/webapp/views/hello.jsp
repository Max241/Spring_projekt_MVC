<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2020-11-01
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<h3><spring:message code="label.hello"/></h3>

<a href="/appUsers.html"><spring:message code="label.title"/></a>

<br/>
${Time}
<br/>
${message}


</body>
</html>
