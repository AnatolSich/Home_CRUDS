<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 30.12.2017
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />--%>
    <title>List of users</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th> id</th>
        <th> name</th>
        <th> dob</th>
        <th> address</th>
        <th> email</th>
        <th> tel</th>
        <th colspan="3"> Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="tempUser">
        <tr>
            <td><c:out value="${tempUser.id}"/></td>
            <td><c:out value="${tempUser.name}"/></td>
            <td><fmt:formatDate pattern='${"dd/MM/yyyy"}' value='${tempUser.dob}'/></td>
            <td><c:out value="${tempUser.address}"/></td>
            <td><c:out value="${tempUser.email}"/></td>
            <td><c:out value="${tempUser.tel}"/></td>
            <td><a href="/UserCommit?action=edit&userId=<c:out value="${tempUser.id}"/>"> Edit </a></td>
            <td><a href="/UserCommit?action=delete&userId=<c:out value="${tempUser.id}"/>"> Delete </a></td>
            <td><a href="/BookCommit?action=review_books_of_user&userId=<c:out value="${tempUser.id}"/>"> Review list of books </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="/UserCommit?action=create"> Add user </a></p>
<p><a href="/BookCommit?action=list"> Review all books </a></p>
</body>
</html>
