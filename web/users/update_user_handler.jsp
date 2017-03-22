<%@ page import="com.juhezi.alice.domain.User" %>
<%@ page import="com.juhezi.alice.dao.impl.UserDaoImpl" %>
<%@ page import="com.juhezi.alice.dao.UserDao" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/3/22
  Time: 20:18

  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alice</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    String pickname = request.getParameter("pickname");
    UserDao userDao = new UserDaoImpl();
    User user = null;
    try {
        user = userDao.findById(id);
    } catch (SQLException e) {
        e.printStackTrace();
        out.println("用户丢失");
    }
    if(user == null) {
        return;
    }
    user.setPickname(pickname).setPassword(password);
    try {
        userDao.update(user);
        response.sendRedirect("users.jsp");
    } catch (SQLException e) {
        out.println("更新失败");
        e.printStackTrace();
    }

%>
</body>
</html>

