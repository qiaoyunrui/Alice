<%@ page import="com.juhezi.alice.dao.UserDao" %>
<%@ page import="com.juhezi.alice.dao.impl.UserDaoImpl" %>
<%@ page import="com.juhezi.alice.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.xml.ws.Response" %><%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/3/22
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alice - 所有用户</title>
    <style type="text/css">
        .button-primary {
            background-color: #039be5;
            color: #ffffff;
            margin: 4px;
            box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .26);
            padding: 8px 16px;
            font: 500 14px/20px Roboto, sans-serif;
            text-decoration: none;
        }

        .button-wrapper {
            margin: 16px;
            box-sizing: inherit;
        }
    </style>
    <script type="text/javascript">
        window.onload = function () {
            var button = document.getElementById("add_user_button")
            button.onmouseout = function () {
                button.style.backgroundColor = "#039be5"
            }
            button.onmouseover = function () {
                button.style.backgroundColor = "#0376A7"
            }
        }
    </script>
</head>
<body>
<div class="button-wrapper">
    <a id="add_user_button" href="add_user.jsp" class="button-primary">添加用户</a>
</div>

<br>
<%!
    UserDao userDao = new UserDaoImpl();
%>
<%
    request.setCharacterEncoding("UTF-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String pickname = request.getParameter("pickname");
    if (username != null && password != null && pickname != null) {
        User user = new User();
        user.setUsername(username)
                .setPickname(pickname)
                .setPassword(password)
                .setId(UUID.randomUUID().toString());
        try {
            userDao.add(user);
        } catch (Exception ex) {
            out.println("添加失败");
        }
    }

    List<User> users = null;
    try {
        users = userDao.findAll();
    } catch (Exception ex) {
        out.println("查询失败");
    }
    if (users == null || users.isEmpty()) {
        out.println("还没有用户，请点击添加。");
        return;
    }
%>
<table border="1" align="center">
    <tr align="center">
        <td>用户名</td>
        <td>密码</td>
        <td>昵称</td>
        <td colspan="2">操作</td>
    </tr>
    <%
        for (User user : users) {
            out.println("<tr align=\"center\"><td>" + user.getUsername() + "</td>" +
                    "<td>" + user.getPassword() + "</td>" +
                    "<td>" + user.getPickname() + "</td>" +
                    "");
    %>
    <td>
        <a href="update_user.jsp?id=<%=user.getId()%>">修改</a>
    </td>
    <td>
        <a href="delete_user_handler.jsp?id=<%=user.getId()%>">删除</a>
    </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
