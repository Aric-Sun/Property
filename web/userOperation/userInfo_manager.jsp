<%--
  Created by IntelliJ IDEA.
  User: IronMan
  Date: 6.12/012
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Avengers.Stark.dto.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>业主综合信息</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <!--Bootstrap 的 JavaScript 插件需要在引入bootstrap的js之前引入 jQuery-->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function searchUserInfo() {
            // 声明变量
            var input, filter, table, tr, td, i;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("userInfo_list_table");
            tr = table.getElementsByTagName("tr");

            // 循环表格每一行，查找匹配项
            for (i = 1; i < tr.length; i++) {  // i=1 跳过字段名
                td = tr[i].getElementsByTagName("td")[1];// 0表示第一列
                if (td) {
                    if (td.innerHTML.indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
        function callSystemPrint4Data(id) {
            var sprnhtml = $('#'+id).html();  // 获取区域内容
            var selfhtml = $('body').html();  // 获取当前页的html
            $('body').html(sprnhtml);
            window.print();
            $('body').html(selfhtml);
        }
    </script>
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
                <li class="active">
                    <c:choose>
                        <c:when test="${user.user_type=='user'}">
                            <a href="<%=request.getContextPath()%>/userOperation/userInfo.jsp"> 基本信息 </a>
                        </c:when>
                        <c:when test="${user.user_type=='manager'}">
                            <a href="<%=request.getContextPath()%>/allUserInfoServlet"> 业主综合信息 </a>
                        </c:when>
                    </c:choose>
                </li>
                <li>
                    <c:choose>
                        <c:when test="${user.user_type=='user'}">
                            <a href="<%=request.getContextPath()%>/currentCostServlet"> 抄表数据 </a>
                        </c:when>
                        <c:when test="${user.user_type=='manager'}">
                            <a href="<%=request.getContextPath()%>/allCostRecordServlet"> 业主综合抄表数据 </a>
                        </c:when>
                    </c:choose>
                </li>
                <li>
                    <c:choose>
                        <c:when test="${user.user_type=='user'}">
                            <a href="<%=request.getContextPath()%>/paymentRecordServlet"> 缴费情况 </a>
                        </c:when>
                        <c:when test="${user.user_type=='manager'}">
                            <a href="<%=request.getContextPath()%>/allPaymentRecordServlet"> 业主缴费情况 </a>
                        </c:when>
                    </c:choose>
                </li>
                <c:if test="${user.user_type=='manager'}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            报表<b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/unpaidRecordServlet">费用应收未收汇总表</a> </li>
                            <li><a href="<%=request.getContextPath()%>/fee/sum_month_manger.jsp">月度账单总额汇总表</a> </li>
                        </ul>
                    </li>
                </c:if>
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
            <div class="navbar-right">
                <button class="btn btn-default navbar-btn" onclick="callSystemPrint4Data('data')" id="print">
                    打印
                </button>
            </div>
        </div>
    </div>
</nav>
<%--<jsp:useBean id="all_userInfo_list" type="java.util.List" scope="request"/>--%>
<div style="margin-top: 60px"></div>
<input class="form-control" type="text" id="searchInput" placeholder="搜索姓名……" onkeyup="searchUserInfo()">
<div id="data">
    <%--<%List<User> all_userInfo_list = (List<User>) request.getAttribute("all_userInfo_list");%>--%>
    <table class="table table-striped" id="userInfo_list_table">
        <tr>
            <td>用户名</td>
            <td>姓名</td>
            <td>身份证号</td>
            <td>手机号</td>
            <td>楼号</td>
            <td>房号</td>
            <td>工作单位</td>
            <td>建筑面积</td>
        </tr>
        <c:forEach var="all_userInfo_item" items="${all_userInfo_list}">
            <tr>
                <td>${all_userInfo_item.username}</td>
                <td>${all_userInfo_item.name}</td>
                <td>${all_userInfo_item.IDNumber}</td>
                <td>${all_userInfo_item.phone}</td>
                <td>${all_userInfo_item.buildNo}</td>
                <td>${all_userInfo_item.roomNo}</td>
                <td>${all_userInfo_item.jobUnit}</td>
                <td>${all_userInfo_item.floorage}</td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
