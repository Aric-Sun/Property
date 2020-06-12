<%@ page import="Avengers.Stark.dto.User" %><%--
  Created by IntelliJ IDEA.
  User: IronMan
  Date: 6.12/012
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <!--Bootstrap 的 JavaScript 插件需要在引入bootstrap的js之前引入 jQuery-->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:useBean id="user" type="Avengers.Stark.dto.User" scope="session"/>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only"> 切换导航 </span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=request.getContextPath()%>/mainPage.jsp"> 物业管理信息系统 </a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left" id="nav-btn">
                <c:choose>
                    <c:when test="${user.user_type=='user'}">
                        <li class="active"><a href="<%=request.getContextPath()%>/userOperation/userInfo.jsp"> 基本信息 </a></li>
                    </c:when>
                    <c:when test="${user.user_type=='manager'}">
                        <li><a href="<%=request.getContextPath()%>/allUserInfoServlet"> 业主综合信息 </a></li>
                    </c:when>
                </c:choose>
                <li>
                    <c:choose>
                        <c:when test="${user.user_type=='user'}">
                            <a href="<%=request.getContextPath()%>/fee/pre_fee_personal.jsp"> 抄表数据 </a>
                        </c:when>
                        <c:when test="${user.user_type=='manager'}">
                            <a href="<%=request.getContextPath()%>/fee/pre_fee_manager.jsp"> 业主综合抄表数据 </a>
                        </c:when>
                    </c:choose>
                </li>
                <li>
                    <c:choose>
                        <c:when test="${user.user_type=='user'}">
                            <a href="<%=request.getContextPath()%>/fee/post_fee_personal.jsp"> 缴费情况 </a>
                        </c:when>
                        <c:when test="${user.user_type=='manager'}">
                            <a href="<%=request.getContextPath()%>/fee/post_fee_manager.jsp"> 业主缴费情况 </a>
                        </c:when>
                    </c:choose>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right" >
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        ${user.name} <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <c:if test="${user.user_type=='manager'}">
                            <li>
                                <a href="<%=request.getContextPath()%>/userOperation/userInfo.jsp"> 个人信息 </a>
                            </li>
                        </c:if>
                        <li class="divider"></li>
                        <li>
                            <a href="<%=request.getContextPath() %>/logOutServlet">退出</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<%--<%User user = (User) session.getAttribute("user");%>--%>\
<%--姓名：<jsp:getProperty name="user" property="name"/><br>--%>
<div style="margin-top: 100px">
    用户名：${user.username}<br>
    姓名：${user.name}<br>
    电话号码：${user.phone}<br>
    <%
        if (user.getUser_type().equals("user")){
    %>
    身份证号码：${user.IDNumber}<br>
    楼号：${user.buildNo}<br>
    房号：${user.roomNo}<br>
    工作单位：${user.jobUnit}<br>
    建筑面积：${user.floorage}<br>
    <%
        }
    %>
</div>
</body>
</html>