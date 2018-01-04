<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 28.12.2017
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th> User id</th>
        <th> First name</th>
        <th> Last Name</th>
        <th> Email</th>
        <th> Date of Birth</th>
        <th colspan="2"> ActionValues </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.emali}"/></td>
        <td><c:out value="${user.dob}"/></td>
        <td><a href="/usersAction?action=edit&userID=<c:out value="${user.id}"/>"> Edit </a></td>
        <td><a href="/usersAction?action=delete&userID=<c:out value="${user.id}"/>"> Delete </a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="/usersAction?action=create"> Add user </a></p>
</body>
</html>
