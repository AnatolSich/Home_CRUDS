<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Edit user</title>
</head>
<body>
<h1>Edit user</h1>
<form method="post" action="/UserCommit">
    ID <input readonly type="text" name="id" value="<c:out value="${user.id}"/>">
    Name <input type="text" name="name" value="<c:out value="${user.name}"/>">
    Date of Birth <input type="date" name="dob" value="<fmt:formatDate pattern="${date_pattern}" value="${user.dob}"></fmt:formatDate>">
    <input type="submit" value="Submit">
</form>
</body>
</html>
