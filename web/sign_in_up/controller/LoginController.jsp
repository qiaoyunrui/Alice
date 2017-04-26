<%@ page import="com.juhezi.alice.domain.User" %>
<%@ page import="com.juhezi.alice.dao.UserDao" %>
<%@ page import="com.juhezi.alice.dao.impl.UserDaoImpl" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.net.URLEncoder" %>
<%--
  Created by IntelliJ IDEA.
  User: tianlong
  Date: 2017/3/23
  Time: 14:53
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
    String Re_me = request.getParameter("Re_me");

    UserDao dao = new UserDaoImpl();
    User user;
    String EncodeUsername;
    String EncodePassword;

    if ((username != null && !username.equals("")) && (password != null && !password.equals(""))) {
        try {
            user = dao.findByUsername(username);
            if (user != null) {
                if (password.equals(user.getPassword())) {
                    if (Re_me != null && !Re_me.equals("") && Re_me.equals("Re_me")) {

                        EncodeUsername = URLEncoder.encode(username, "utf-8");
                        EncodePassword = URLEncoder.encode(password, "utf-8");
                        Cookie usercookie = new Cookie("alice_username", EncodeUsername);
                        Cookie is_online = new Cookie("online", URLEncoder.encode("true", "utf-8"));
                        usercookie.setMaxAge(60 * 60 * 24 * 30);
                        usercookie.setPath("/sign_in_up/");
                        is_online.setMaxAge(60 * 60 * 24 * 30);
                        is_online.setPath("/sign_in_up/");
                        response.addCookie(usercookie);
                        response.addCookie(is_online);
                        //session.setAttribute("username", username);

                        response.getWriter().println(simpleJson("true", ""));

                    } else {
                        Cookie[] cookies = request.getCookies();

                        if (cookies != null && cookies.length > 0) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("alice_password")) {
                                    cookie.setMaxAge(0);
                                    cookie.setPath("/sign_in_up/");
                                    response.addCookie(cookie);
                                }
                                if (cookie.getName().equals("alice_username")) {
                                    cookie.setMaxAge(0);
                                    cookie.setPath("/sign_in_up/");
                                    response.addCookie(cookie);
                                }
                            }
                            session.setAttribute("username", username);
                            response.getWriter().println(simpleJson("true", ""));
                        }
                    }
                } else {
                    response.getWriter().println(simpleJson("false", "密码不正确"));
                }
            }else{
                response.getWriter().println(simpleJson("false", "帐号不存在"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        response.getWriter().println(simpleJson("false", "帐号或密码不能为空"));
    }

%>