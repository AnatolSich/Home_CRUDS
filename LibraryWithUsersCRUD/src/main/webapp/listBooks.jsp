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
    <title>List of books</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th> id</th>
        <th> name</th>
        <th> author</th>
        <th> release</th>
        <th> quantity</th>
        <th> userId</th>
        <th colspan="2"> Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="tempBook">
        <tr>
            <td><c:out value="${tempBook.id}"/></td>
            <td><c:out value="${tempBook.name}"/></td>
            <td><c:out value="${tempBook.author}"/></td>
            <td><fmt:formatDate pattern='${"dd/MM/yyyy"}' value='${tempBook.release}'/></td>
            <td><c:out value="${tempBook.quantity}"/></td>
            <td><c:out value="${tempBook.userId}"/></td>
            <td>
                <a href="/BookCommit?action=edit&bookId=<c:out value="${tempBook.id}"/>&userId=<c:out value="${userId}"/>">
                    Edit </a></td>
            <td>
                <a href="/BookCommit?action=delete&bookId=<c:out value="${tempBook.id}"/>&userId=<c:out value="${userId}"/>">
                    Delete </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:choose>
    <c:when test="${userId != null}">
        <p><a href="/BookCommit?action=create&userId=<c:out value="${userId}"/>"> Add book </a></p>
    </c:when>
</c:choose>

<p><a href="/UserCommit?action=list"> Back to users list </a></p>
</body>
</html>
