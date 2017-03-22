<%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/3/22
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alice - 添加用户</title>
</head>
<body>
<form action="users.jsp" method="post">
    用户名：<input type="text" name="username" required/><br>
    密码：<input type="password" name="password" required/><br>
    昵称：<input type="text" name="pickname" required/><br>
    <input type="submit" value="添加"/>
</form>
</body>
</html>
