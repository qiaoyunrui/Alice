<%@ page import="com.juhezi.alice.dao.UserDao" %>
<%@ page import="com.juhezi.alice.dao.impl.UserDaoImpl" %>
<%@ page import="com.juhezi.alice.domain.User" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/3/22
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Alice - 修改用户信息</title>
    <%
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        UserDao userdao = new UserDaoImpl();
        User user = null;
        try {
            user = userdao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
</head>
<body>

<form method="post" action="update_user_handler.jsp">
    <input type="text" name="id" value="<%=user.getId()%>" hidden>
    密码：<input type="password" name="password" value="<%=user.getPassword()%>"/><br>
    昵称：<input type="text" name="pickname" value="<%=user.getPickname()%>"/><br>
    <input type="submit" value="修改"/>
</form>
</body>
</html>
