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
    <%
        response.setCharacterEncoding("UTF-8");
    %>
</head>
<body>
<h2>添加好友</h2>
<form action="${pageContext.request.contextPath}/UserServlet" method="post">
    <input type="hidden" name="method" value="add"/>
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" required/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" required/></td>
        </tr>
        <tr>
            <td>昵称：</td>
            <td><input type="text" name="pickname" required/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="确定">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
