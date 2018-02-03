<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 20.01.2018
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book create</title>
</head>
<body>
<h1>Book create form</h1>
<form method="post" action="/BookCommit">
    ID <input readonly type="text" name="id" value="Auto">
    Title <input type="text" name="title" value="">
    ReleaseDate <input type="date" name="releaseDate" value="${date_pattern}">
    UserId <input readonly type="text" name="userId" value="<c:out value = "${userId}"/>">
    <input type="submit" value="Submit">
</form>
</body>
</html>
