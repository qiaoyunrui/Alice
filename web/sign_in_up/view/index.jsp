<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: tianlong
  Date: 2017/3/23
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String username = (String) session.getAttribute("username");
    String cookieuser = "";
    String cookiepwd = "";
    Cookie[] cookies = request.getCookies();
    if(cookies != null && cookies.length > 0){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("alice_username")){
                cookieuser = URLDecoder.decode(cookie.getValue(),"utf-8");
            }

            if(cookie.getName().equals("online")){
                cookiepwd = URLDecoder.decode(cookie.getValue(), "utf-8");
            }
        }
    }
%>

<h1>这是<%=username %>主页</h1>
<h1>账号:<%=cookieuser %></h1>
在线状态:<%=cookiepwd %>
</body>
</html>
