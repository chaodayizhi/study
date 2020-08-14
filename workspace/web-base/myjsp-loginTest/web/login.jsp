<%--
  Created by IntelliJ IDEA.
  User: 程炯
  Date: 2020/7/17
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login_Page</title>
</head>
<body>
    <form action="/login" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>密　码：</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="登录"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
