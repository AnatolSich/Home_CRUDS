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
    <title>Update book page</title>
</head>
<body>
<form method="post" action="/BookCommit">
    id: <input type="text" readonly="readonly" name="bookId" value="<c:out value="${book.id}"/>"><br/>
    name: <input type="text" name="name" value="<c:out value="${book.name}"/>"><br/>
    author: <input type="text" name="author" value="<c:out value="${book.author}"/>"><br/>
    release: <input type="text" name="release" value="<fmt:formatDate pattern='${"dd/MM/yyyy"}' value='${book.release}'/>"><br/>
    quantity: <input type="text" name="quantity" value="<c:out value="${book.quantity}"/>"><br/>
    userId: <input type="text" readonly="readonly" name="userId" value="<c:out value="${userId}"/>"><br/>
    <input type="submit" value="SUBMIT"/>
</form>
</body>
</html>
