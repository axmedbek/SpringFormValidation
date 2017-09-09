<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: axmedbek
  Date: 9/4/17
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title" /></title>
</head>
<body>
<form:form action="/user/${user.id}/edit" method="post" modelAttribute="user" enctype="multipart/form-data">
    <form:input path="name" value="${user.name}"/>
    <br>
    <form:input path="surname" value="${user.surname}"/>
    <br>
    <form:input path="file" id="image" type="file" name="image" accept="image/jpeg,image/png,image/gif,image/jpg"/>

    <input type="submit" value="Update">
</form:form>
</body>
</html>
