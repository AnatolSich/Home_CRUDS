<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 21.12.2017
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<table border="2">
    <thead>
    <tr>
        <th>User id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Dob</th>
        <th>Email</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><c:out value="${user.dob}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><a href="userController?action=edit&userId=<c:out value="${user.id}"/>">Update</a></td>
                <td><a href="userController?action=delete&userId=<c:out value="${user.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<p><a href="userController?action=create">Add user</a></p>
</body>
</html>
