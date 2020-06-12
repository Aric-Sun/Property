<%--
  Created by IntelliJ IDEA.
  User: IronMan
  Date: 6.11/011
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>入口</title>
</head>
<body>
<%
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
%>
<a href="login.jsp">业主/管理员登录</a>
</body>
</html>
