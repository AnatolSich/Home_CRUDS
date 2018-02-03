<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 20.01.2018
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List of All Users</h1>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>Date of Birth</th>
        <th colspan="3">Operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tempUser" items="${users}">
        <tr>
            <td><c:out value="${tempUser.id}"></c:out></td>
            <td><c:out value="${tempUser.name}"></c:out></td>
            <td><fmt:formatDate pattern="${date_pattern}" value="${tempUser.dob}"></fmt:formatDate></td>
            <td><a href="/UserCommit?action=edit&userId=${tempUser.id}"> Edit </a></td>
            <td><a href="/UserCommit?action=delete&userId=${tempUser.id}"> Delete </a></td>
            <td><a href="/BookCommit?action=LIST_BY_ID&userId=${tempUser.id}"> Users books </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/UserCommit?action=create">Create user</a>
</body>
</html>
