<%@ page import="com.juhezi.alice.dao.UserDao" %>
<%@ page import="com.juhezi.alice.dao.impl.UserDaoImpl" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/3/22
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alice</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    UserDao userDao = new UserDaoImpl();
    try {
        userDao.delete(id);
        response.sendRedirect("users.jsp");
    } catch (SQLException e) {
        e.printStackTrace();
        out.println("删除失败");
    }
%>
</body>
</html>
