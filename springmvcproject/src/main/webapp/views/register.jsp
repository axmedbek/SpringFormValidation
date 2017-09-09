<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: axmedbek
  Date: 9/4/17
  Time: 12:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .id
        {
            color: red;
        }
    </style>
</head>
<body>
<form:form action="/register" method="post" modelAttribute="userForm" enctype="multipart/form-data">
    <form:input path="name"/><br>
    <form:errors path="name" cssClass="id"/>
    <br>
    <form:input path="surname"/><br>
    <form:errors path="surname" cssClass="id"/>
    <br>
    <form:input path="email"/><br>
    <form:errors path="email" cssClass="id"/>
    <br>

    <form:input path="file" id="image" type="file" name="image" accept="image/jpeg,image/png,image/gif,image/jpg"/>

    <input type="submit" value="Register">
</form:form>
</body>
</html>
