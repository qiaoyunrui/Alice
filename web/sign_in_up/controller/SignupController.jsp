<%@ page import="com.juhezi.alice.dao.UserDao" %>
<%@ page import="com.juhezi.alice.dao.impl.UserDaoImpl" %>
<%@ page import="com.juhezi.alice.domain.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: tianlong
  Date: 2017/3/26
  Time: 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public String simpleJson(String status, String message) {
        return "{\"status\":\"" + status + "\",\"message\":\"" + message + "\"}";
    }
%>
<%
    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String repassword = request.getParameter("repassword");

    UserDao dao = new UserDaoImpl();
    if ((username != null) && (password != null)) {
        if ((repassword != null) && (password.equals(repassword))) {
            try {
                User result = dao.findByUsername(username);
                if (result != null) {
                    response.getWriter().println(simpleJson("false", "用户名已存在"));
                } else {
                    User user = new User();
                    user.setUsername(username)
                            .setPassword(password)
                            .setId(UUID.randomUUID().toString());
                    dao.add(user);
                    response.getWriter().println(simpleJson("true", "注册成功"));
                }
            } catch (Exception e) {
                response.getWriter().println(simpleJson("false", "注册失败"));
            }
        }else{
            response.getWriter().println(simpleJson("false", "密码不一致"));
        }
    } else {
        response.getWriter().println(simpleJson("false", "密码或帐号不能为空"));
    }
%>