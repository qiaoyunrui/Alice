<%@ page import="com.juhezi.alice.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/4/5
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alice - 用户列表</title>
</head>
<body>
<h2>好友列表</h2>
<table border="1">
    <tr align="center">
        <td>id</td>
        <td>用户名</td>
        <td>密码</td>
        <td>昵称</td>
        <td colspan="2">操作</td>
    </tr>
    <c:forEach items="${requestScope.list}" var="user">
        <tr>
            <td>${user.id }</td>
            <td>${user.username }</td>
            <td>${user.password }</td>
            <td>${user.pickname }</td>
            <td>
                <a href="UserServlet?method=find&id=${user.id}">修改</a>
                <a href="UserServlet?method=delete&id=${user.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td align="center" colspan="4"><a href="/users/add_user.jsp">添加</a></td>
    </tr>
</table>
</body>
</html>
