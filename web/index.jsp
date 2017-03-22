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
    <script type="text/javascript">
        function turn2Users() {
            window.location.href = "users/users.jsp";
        }
    </script>
</head>
<body>
<button onclick="turn2Users()">用户列表</button>

</body>
</html>
<%--include 静态包含，只能包含静态的资源，编译后想成一个资源--%>