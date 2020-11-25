<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/16
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>index01</title>--%>
<%--    <link href="css/style.css" rel="stylesheet" type="text/css" />--%>
<%--    <style>--%>
<%--        .place span{--%>
<%--            margin-left: 25px;--%>
<%--        }--%>
<%--        .fontshouye{--%>
<%--            color: #ffffff;--%>
<%--        }--%>

<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="place">--%>
<%--    <span>位置 /</span>--%>
<%--    <ul class="placeul">--%>
<%--        <li><a href="#" class="fontshouye">首页</a></li>--%>
<%--    </ul>--%>
<%--</div>--%>
<%--<div class="mainindex">--%>
<%--    <div class="welinfo">--%>
<%--        <span><img src="images/sun.png" alt="天气" /></span>--%>
<%--        <b>${sessionScope.user.name}早上好，欢迎使用考勤信息管理系统</b>--%>
<%--    </div>--%>

<%--    <div class="welinfo">--%>
<%--        <span><img src="images/time.png" alt="时间" /></span>--%>
<%--        <i>您本次登录的时间：${sessionScope.loginTime}</i>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<html>
  <head>
      <title>$Title$</title>
      <link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
  <div class="place">
      <span>位置：</span>
      <ul class="placeul">
          <li><a href="#" style="color: #ffffff">首页</a></li>
      </ul>
  </div>
  <div class="mainindex">
      <div class="welinfo">
          <span><img src="images/sun.png" alt="天气" /></span>
          <b>${sessionScope.user.name}早上好，欢迎使用考勤信息管理系统</b>
      </div>

      <div class="welinfo">
          <span><img src="images/time.png" alt="时间" /></span>
          <i>您本次登录的时间：${sessionScope.loginTime}</i>
      </div>
  </div>
  </body>

</html>
