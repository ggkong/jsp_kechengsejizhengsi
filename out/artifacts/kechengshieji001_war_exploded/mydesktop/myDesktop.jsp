<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/23
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>我的桌面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript">
        function closeMessage(obj){
            console.log("调用了关闭方法");
            $(obj.parentElement).css('display', 'none');
        };
    </script>
</head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#" style="color: #ffffff">我的桌面</a></li>
    </ul>
</div>
<br />
<br />
<div class="mainindex">

    <div class="welinfo">
        <span><img src="images/sun.png" alt="天气" /></span>
        <b>${user.name}早上好，您有新消息</b>
    </div>

    <c:forEach items="${Mymessage}" var="message">
            <p style="font-size: 15px">
                    ${message}           <a style="color: #3653e5;font-size:15px" onclick="closeMessage(this)">删除</a><br><br>
            </p>
    </c:forEach>
</div>
</body>
</html>

