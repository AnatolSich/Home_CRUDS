<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 20.01.2018
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of books</title>
</head>
<body>
<h1>List of Books By userId</h1>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>Date of Release</th>
        <th>userId</th>
        <th colspan="2">Operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tempBook" items="${books}">
        <tr>
            <td><c:out value="${tempBook.id}"></c:out></td>
            <td><c:out value="${tempBook.title}"></c:out></td>
            <td><fmt:formatDate pattern="${date_pattern}" value="${tempBook.releaseDate}"/></td>
            <td><c:out value="${tempBook.userId}"></c:out></td>
            <td><a href="/BookCommit?action=edit&bookId=${tempBook.id}"> Edit </a></td>
            <td><a href="/BookCommit?action=delete&userId=${tempBook.userId}&bookId=${tempBook.id}"> Delete </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/BookCommit?action=create&userId=${userId}">Create book</a>
<br>
<br>
<br>
<a href="/UserCommit?action=list">Users list</a>
</body>
</html>
