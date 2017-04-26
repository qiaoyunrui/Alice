<jsp:useBean id="user" scope="request" type="com.juhezi.alice.domain.User"/>
<%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/4/14
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Alice - 编辑信息</title>
    <%
        response.setCharacterEncoding("UTF-8");
    %>
</head>
<body>
<h2>修改用户信息</h2>
<form action="${pageContext.request.contextPath}/UserServlet" method="post">
    <input type="hidden" name="method" value="update"/>
    <table>
        <tr>
            <td>ID</td>
            <td><input type="text" name="id" readonly="readonly" value="${user.id }"/></td>
        </tr>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="id" readonly="readonly" value="${user.username }"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" value="${user.password }"/></td>
        </tr>
        <tr>
            <td>昵称</td>
            <td><input type="text" name="pickname" value="${user.pickname }"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="确定"/>
                <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
