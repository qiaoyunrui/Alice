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
    <script type="text/javascript">
        function addUser() {
            window.location.href = "add_user.jsp";
        }
    </script>
</head>
<body>
<button onclick="addUser()">添加用户
</button>
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
