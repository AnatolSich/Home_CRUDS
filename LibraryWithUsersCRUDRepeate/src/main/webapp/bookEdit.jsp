<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 20.01.2018
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book edit</title>
</head>
<body>
<h1>Edit book</h1>
<form method="post" action="/BookCommit">
    ID <input readonly type="text" name="id" value="<c:out value="${book.id}"/>">
    Title <input type="text" name="title" value="<c:out value="${book.title}"/>">
    ReleaseDate <input type="date" name="releaseDate"
                       value="<fmt:formatDate pattern="${date_pattern}" value="${book.releaseDate}"/>">
    UserId <input type="text" name="userId" value="<c:out value = "${book.userId}"/>">
    <input type="submit" value="Submit">
</form>
</body>
</html>
