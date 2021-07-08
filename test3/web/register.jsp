<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/7/2
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="reg" method="post">
    姓名:<input type="text" name="sname"/><br/><br/>
    性别:男<input type="radio" value="1" name="gender"/>
    女<input type="radio" value="2" name="gender"/><br/><br/>
    爱好:
    睡觉<input type="checkbox" value="睡觉" name="hobby"/>
    游戏<input type="checkbox" value="游戏" name="hobby"/>
    玩耍<input type="checkbox" value="玩耍" name="hobby"/>
    吃饭<input type="checkbox" value="吃饭" name="hobby"/><br/><br/>

    学历:本科<input type="radio" value="本科" name="degree"/>
    大专<input type="radio" value="大专" name="degree"/><br/><br/>

    简介：<textarea name="mark">

    </textarea>

    <input type="submit" value="注册">
</form>
</body>
</html>
