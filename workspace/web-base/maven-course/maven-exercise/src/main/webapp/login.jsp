<%--
  Created by IntelliJ IDEA.
  User: 程炯
  Date: 2020/8/12
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post" id="loginForm"  >
<table>
    <tr><th colspan="2">请登录</th></tr>
    <tr>
        <td>用户名</td><td><input type="text" name="userName"></td>
    </tr>
    <tr>
        <td>密码</td><td><input type="text" name="userPwd"></td>
    </tr>
    <tr>
        <td><input type="submit" value="登录"></td><td><input type="reset" value="重置"></td>
    </tr>
</table>
</form>
</body>
</html>
