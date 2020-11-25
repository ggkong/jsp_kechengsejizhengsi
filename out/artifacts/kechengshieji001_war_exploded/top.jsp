<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/16
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
            //顶部导航切换
            $(".nav li a").click(function(){
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>
    <style type="text/css">
        .layui-nav{
            height: 88px;
            border-radius: 0;
            background: #23262e;
        }
        .layui-nav-item{
            height: 88px;
        }
        .layui-nav .layui-nav-item{
            line-height: 90px;
        }
    </style>
</head>
<body>

<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="main.html" target="_parent"><img src="images/logoo.png" title="系统首页" /></a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/user/showAllUser?page=1" target="rightFrame">用户管理</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/dept/showAllDept?page=1" target="rightFrame">部门管理</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/report/showAllReport?page=1"  target="rightFrame" >日报管理</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/work/showAllWork?page=1"  target="rightFrame">加班管理</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/rest/showAllRest?page=1" target="rightFrame">休假管理</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/work/showAllWorkwait?page=1"  target="rightFrame">加班审批</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/rest/showAllRestwait?page=1"  target="rightFrame">休假审批</a></li>
</ul>
<ul class="layui-nav layui-layout-right">
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/logout" target="_parent">退出</a></li>
</ul>

<script src="https://www.layuicdn.com/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use('element', function(){
        var element = layui.element;
        //导航的hover效果、二级菜单等功能，需要依赖element模块
        //监听导航点击
        element.on('nav(demo)', function(elem){
            console.log(elem)
            layer.msg(elem.text());
        });
    });
</script>
</body>
</html>
