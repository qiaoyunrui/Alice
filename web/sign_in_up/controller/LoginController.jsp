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
<%
    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String Re_me    = request.getParameter("Re_me");

    UserDao dao = new UserDaoImpl();
    User user;
    String EncodeUsername;
    String EncodePassword;

        if ((username != null && !username.equals("")) && (password != null && !password.equals(""))) {
            try {
                user = dao.findByUsername(username);
                if (password.equals(user.getPassword())) {
                    //如果选择记录了cookie，则保存
                    if (Re_me != null && !Re_me.equals("") && Re_me.equals("Re_me")) {

                        //注意要进行编码，否则中文会乱码
                        EncodeUsername = URLEncoder.encode(username, "utf-8");
                        EncodePassword = URLEncoder.encode(password, "utf-8");
                        Cookie usercookie = new Cookie("alice_username", EncodeUsername);
                        Cookie pwdcookie = new Cookie("alice_password", EncodePassword);
                        usercookie.setMaxAge(60*60*24*30);
                        usercookie.setPath("/sign_in_up/");
                        pwdcookie.setMaxAge(60*60*24*30);
                        pwdcookie.setPath("/sign_in_up/");
                        response.addCookie(usercookie);
                        response.addCookie(pwdcookie);
                        session.setAttribute("username", username);


                        String str = "{\"status\":\"true\"}";
                        //response.sendRedirect("../view/index.jsp");
                        response.getWriter().println(str);

                    } else {
                        Cookie[] cookies = request.getCookies();

                        if (cookies != null && cookies.length > 0) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("alice_password")) {
                                    cookie.setMaxAge(0);
                                    cookie.setPath("/sign_in_up/");
                                    response.addCookie(cookie);
                                }
                                if(cookie.getName().equals("alice_username")){
                                    cookie.setMaxAge(0);
                                    cookie.setPath("/sign_in_up/");
                                    response.addCookie(cookie);
                                }
                            }
                            session.setAttribute("username", username);
                            String str = "{\"status\":\"true\"}";
                            response.getWriter().println(str);
                            //response.sendRedirect("../view/index.jsp");
                        }
                    }
                }else{
                    String str = "{\"status\":\"false\",\"message\":\"密码不正确\"}";

                    response.getWriter().println(str);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            response.getWriter().println("{\"status\":\"false\",\"message\":\"密码或账号不能为空\"}");
        }

%>