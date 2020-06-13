<%--
  Created by IntelliJ IDEA.
  User: IronMan
  Date: 6.14/014
  Time: 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Avengers.Stark.dto.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>费用应收未收汇总表</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <!--Bootstrap 的 JavaScript 插件需要在引入bootstrap的js之前引入 jQuery-->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function searchPaymentRecord(columnNum) {
            // 声明变量
            var input, filter, table, tr, td, i;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("costTotal_list_table");
            tr = table.getElementsByTagName("tr");

            // 循环表格每一行，查找匹配项
            for (i = 1; i < tr.length; i++) {  // i=1 跳过字段名
                td = tr[i].getElementsByTagName("td")[columnNum];// 0表示第一列
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
                <li>
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
                    <li class="dropdown active">
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
            <form class="navbar-form navbar-left" role="search" action="<%=request.getContextPath()%>/costTotalServlet">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="搜索月份（例如：2020-4）" name="month">
                </div>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
            <div class="navbar-right">
                <button class="btn btn-default navbar-btn" onclick="callSystemPrint4Data('data')" id="print">
                    打印
                </button>
            </div>
        </div>
    </div>
</nav>
<div style="margin-top: 60px"></div>
<input class="form-control" type="text" id="searchInput" placeholder="搜索用户名……" onkeyup="searchPaymentRecord(0)">
<div id="data">
    <%--<ul id="fee_tab" class="nav nav-tabs">--%>
    <%--    <li class="active">--%>
    <%--        <a href="#property" data-toggle="tab">物业费</a>--%>
    <%--    </li>--%>
    <%--    <li>--%>
    <%--        <a href="#water_electricity_gas" data-toggle="tab">水电煤气费</a>--%>
    <%--    </li>--%>
    <%--    <li>--%>
    <%--        <a href="#tv" data-toggle="tab">有线电视费</a>--%>
    <%--    </li>--%>
    <%--    <li>--%>
    <%--        <a href="#heating" data-toggle="tab">供暖费</a>--%>
    <%--    </li>--%>
    <%--    <li>--%>
    <%--        <a href="#mortgage" data-toggle="tab">分期房款</a>--%>
    <%--    </li>--%>
    <%--</ul>--%>
    <%--<div id="fee_tab_content" class="tab-content">--%>
    <%--    <div class="tab-pane fade in active" id="property">--%>
    <%--        物业费--%>
    <%--    </div>--%>
    <%--    <div class="tab-pane fade" id="water_electricity_gas">--%>
    <%--        水电煤气费--%>
    <%--    </div>--%>
    <%--    <div class="tab-pane fade" id="tv">--%>
    <%--        有线电视费--%>
    <%--    </div>--%>
    <%--    <div class="tab-pane fade" id="heating">--%>
    <%--        供暖费--%>
    <%--    </div>--%>
    <%--    <div class="tab-pane fade" id="mortgage">--%>
    <%--        分期房款--%>
    <%--    </div>--%>
    <%--</div>--%>
    <table class="table table-striped" id="costTotal_list_table">
        <tr>
            <td>用户名</td>
            <td>月份</td>
            <td>需要缴费的总额</td>
        </tr>
        <c:forEach var="costTotal" items="${costTotalList}">
            <tr>
                <td>${costTotal.username}</td>
                <td>${costTotal.month}</td>
                <td>${costTotal.total_cost}</td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
