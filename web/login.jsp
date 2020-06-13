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
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <!--Bootstrap 的 JavaScript 插件需要在引入bootstrap的js之前引入 jQuery-->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>
<body>
<div style="margin: 100px auto; width: 900px;height: 200px">
    <form action="<%=request.getContextPath()%>/loginServlet" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="password" name="password" placeholder="请输入密码">
            </div>
        </div>
        <div class="form-group" style="margin-left: 0px">
            <div class="col-sm-offset-2">
                <label class="radio-inline">
                    <input type="radio" name="user_type" value="user">业主
                </label>
                <label class="radio-inline">
                    <input type="radio" name="user_type" value="manager">管理员
                </label>
            </div>
        </div>
        <div class="form-group" style="margin-left: 0px">
            <div class="col-sm-offset-2">
                <button type="submit" class="btn btn-default">
                    登录
                </button>
            </div>
        </div>

    </form>
</div>
</body>
</html>
