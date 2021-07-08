<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/7/2
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1" align="center">
        <tr>
            <th>sid</th>
            <th>sname</th>
            <th>gender</th>
            <th>hobby</th>
            <th>degree</th>
            <th>mark</th>
        </tr>
        <c:forEach items="${list}" var="s">
        <tr>
            <td>${s.sid}</td>
            <td>${s.name}</td>
            <td>${s.gender}</td>
            <td>${s.hobby}</td>
            <td>${s.degree}</td>
            <td>${s.mark}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
