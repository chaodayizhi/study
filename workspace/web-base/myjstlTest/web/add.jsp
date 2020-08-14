<%--
  Created by IntelliJ IDEA.
  User: 程炯
  Date: 2020/7/18
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/user" method="post" >
        编号：<input type="text" name="id" /><br>
        姓名：<input type="text" name="name"/><br>
        成绩：<input type="text" name="score"/><br>
        <input type="submit" value="添加" />
    </form>
</body>
</html>
