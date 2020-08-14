<%--
  Created by IntelliJ IDEA.
  User: 程炯
  Date: 2020/7/18
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <table>
    <tr>
      <th>编号</th>
      <th>姓名</th>
      <th>成绩</th>

    </tr>
    <c:forEach items="${list}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.score}</td>

      </tr>
    </c:forEach>

  </table>

  </body>
</html>
