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
    <%-- <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />--%>
    <title>Update user page</title>
</head>
<body>
<form method="post" action="UserCommit">
    id: <input type="text" readonly="readonly" name="userId" value="<c:out value="${user.id}"/>"><br/>
    name: <input type="text" name="name" value="<c:out value="${user.name}"/>"><br/>
    dob: <input type="text" name="dob" value="<fmt:formatDate pattern='${"dd/MM/yyyy"}' value='${user.dob}'/>"><br/>
    address: <input type="text" name="address" value="<c:out value="${user.address}"/>"><br/>
    email: <input type="text" name="email" value="<c:out value="${user.email}"/>"><br/>
    tel: <input type="text" name="tel" value="<c:out value="${user.tel}"/>"><br/>
    <input type="submit" value="SUBMIT"/>
</form>
</body>
</html>
