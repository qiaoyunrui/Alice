<%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/3/8
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alice - 享受美好的音乐</title>
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
            var button = document.getElementById("sign_in_button")
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
<h1>Alice</h1>
<audio src="assets/6ba32be8-efa2-4ee6-85d7-74541c7ac929.mp3" controls></audio>
<div class="button-wrapper">
    <a id="sign_in_button" href="sign_in_up/view/login.html" class="button-primary">登录</a>
</div>
<a href="UserServlet?method=list">查看用户列表</a>
</body>
</html>