<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 21.12.2017
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="userController" name="frmAddUser">
    User id: <input type="text" readonly="readonly" name="id"
                    value="<c:out value="${user.id}"/>">
    First name: <input type="text" name="firstName"
                    value="<c:out value="${user.firstName}"/>">
    Last name: <input type="text" name="lastName"
                    value="<c:out value="${user.lastName}"/>">
    Date of Birthday: <input type="text" name="dob"
                    value="<fmt:formatDate pattern="mm/dd/yyyy" value="${user.dob}"/>">
    Email: <input type="text" name="email"
                    value="<c:out value="${user.email}"/>">
    <input type="submit" value="Submit">
</form>
</body>
</html>
