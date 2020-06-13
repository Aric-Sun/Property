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
    <title>物业管理信息系统-业主/管理员登录</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <!--Bootstrap 的 JavaScript 插件需要在引入bootstrap的js之前引入 jQuery-->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>
<body>
<%
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
%>
<div class="center-block" style="width: 50%;text-align: center;margin-top: 100px;">
    <h2 style="margin-bottom: 40px">物业管理信息系统</h2>
    <a href="login.jsp">
        <button type="button" class="btn btn-primary btn-lg btn-block">
            业主/管理员登录
        </button>
    </a>
</div>

</body>
</html>
