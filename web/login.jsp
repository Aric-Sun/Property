<%--
  Created by IntelliJ IDEA.
  User: IronMan
  Date: 6.11/011
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/loginServlet" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>

    <input type="radio" name="user_type" value="user">业主&nbsp;
    <input type="radio" name="user_type" value="manager">管理员<br>

    <input type="submit" value="登录">

</form>
</body>
</html>
