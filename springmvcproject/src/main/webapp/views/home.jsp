<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: axmedbek
  Date: 9/3/17
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <!-- FontsOnline -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i,800|Open+Sans:400,400i,600,600i,700,700i,800,800i&text=%21%22%23%24%25%26%27%28%29%30+,-./0123456789:;%3C=%3E%3F@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`%E2%82%AC„‘’“”™©®°µ±÷abcdefghijklmnopqrstuvwxyz{|}~%C3%9C%C3%96%C4%9E%C4%B0%C6%8F%C3%87%C5%9E%C3%BC%C3%B6%C4%9F%C4%B1%C9%99%C3%A7%C5%9F"
          rel="stylesheet">
</head>
<body>
<h1 style="text-align: center;color: lightpink;">Hello , Welcome to Home Page</h1>
<h1 style="text-align: center"><a style="color: lightpink;font-size: large;text-align: center" href="/register">Registration</a></h1>
<div style="text-align: center;margin-left: 430px">
<table border="1" style="border: lightpink 2px;font-size: 25px;">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Image</th>
        <th>Profile Bax</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td><img src="images/${user.imagePath}.png" alt="" width="79px" height="84px"></td>
            <td><a href="/user/${user.name}">Look</a></td>
            <td><a href="/user/${user.id}/edit" style="color: green">-V-</a></td>
            <td><a href="/user/${user.name}/delete" style="color: red">-X-</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</div>
</body>
</html>
