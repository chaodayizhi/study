<%--
  Created by IntelliJ IDEA.
  User: 程炯
  Date: 2020/7/17
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcoem_page</title>
</head>
<body>
    <h1>Welcome</h1>
    欢迎回来<%=session.getAttribute("username")%>！<a href="/logout">退出登录</a>
</body>
</html>
