<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 20.01.2018
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User create page</title>
</head>
<body>
<h1>User create form</h1>
<form method="post" action="/UserCommit">
    ID <input readonly type="number" name="id" value="Auto">
    Name <input type="text" name="name" value="Please enter">
    Date of Birth <input type="date" name="dob" value="${date_pattern}">
    <input type="submit" value="Submit">
</form>
</body>
</html>
