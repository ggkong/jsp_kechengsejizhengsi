<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/16
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui/css/layui.css" type="text/css" media="all">
    <style>
        .font01{
            color: #ffffff;
            font-size: 15px;
            line-height:40px;
        }
        .lefttop01{
            height: 40px;
        }
        .lefttop01 span{
            margin-left:8px;
            margin-top:10px;
            margin-right:8px;
            background:url(images/leftico.png) no-repeat;
            width:20px;
            height:21px;
            float:left;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            //导航切换
            $(".menuson li").click(function(){
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if($ul.is(':visible')){
                    $(this).next('ul').slideUp();
                }else{
                    $(this).next('ul').slideDown();
                }
            });
        })
    </script>
</head>

<body style="background:#393d49;">

    <div class="lefttop01"><span></span><p class="font01">用户管理</p></div>
    <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
        <li class="layui-nav-item layui-nav-itemed">
            <a href="javascript:;">用户管理</a>     <!-- 默认展开 -->
            <dl class="layui-nav-child">
                <dd><a href="${pageContext.request.contextPath}/index.jsp" target="rightFrame">首页</a></dd>
                <dd><a href="${pageContext.request.contextPath}/user/showAllUser?page=1" target="rightFrame">用户管理</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
                <dd><a href="${pageContext.request.contextPath}/dept/showAllDept?page=1" target="rightFrame">部门管理</a></dd>
        </li>
        <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/user/userPasswordUpdate.jsp" target="rightFrame">个人信息修改</a></li>
        <li class="layui-nav-item">
                <dd><a href="${pageContext.request.contextPath}/report/showAllReport?page=1" target="rightFrame">日报管理</a></dd>
        </li>
        <li class="layui-nav-item">
                <dd><a href="${pageContext.request.contextPath}/work/showAllWork?page=1" target="rightFrame">加班管理</a></dd>
        </li>
        <li class="layui-nav-item">
                <dd><a href="${pageContext.request.contextPath}/rest/showAllRest?page=1" target="rightFrame">休假管理</a></dd>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">审批</a>
            <dl class="layui-nav-child">
                <dd><a href="${pageContext.request.contextPath}/work/showAllWorkwait?page=1" target="rightFrame">加班审批</a></dd>
                <dd><a href="${pageContext.request.contextPath}/rest/showAllRestwait?page=1" target="rightFrame">休假审批</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
                <dd><a href="${pageContext.request.contextPath}/mydesktop" target="rightFrame">我的桌面</a></dd>

        </li>
    </ul>


    <script src="https://www.layuicdn.com/layui/layui.js" charset="utf-8"></script>


    <script>
        layui.use('element', function(){
            var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
            //监听导航点击
            element.on('nav(demo)', function(elem){
                //console.log(elem)
                layer.msg(elem.text());
            });
        });
    </script>

</body>
<%--<body style="background:#f0f9fd;">--%>
<%--<div class="lefttop"><span></span>用户管理</div>--%>

<%--<dl class="leftmenu">--%>

<%--    <dd>--%>
<%--        <div class="title">--%>
<%--            <span><img src="images/leftico01.png" /></span>用户管理--%>
<%--        </div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="${pageContext.request.contextPath}/index.jsp" target="rightFrame">首页</a><i></i></li>--%>
<%--            <li class="active"><cite></cite><a href="${pageContext.request.contextPath}/user/showAllUser?page=1" target="rightFrame">用户管理</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>

<%--    <dd><div class="title"><span><img src="images/leftico03.png" /></span>部门管理</div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="${pageContext.request.contextPath}/dept/showAllDept?page=1" target="rightFrame">部门管理</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>
<%--    <dd>--%>
<%--        <div class="title">--%>
<%--            <span><img src="images/leftico02.png" /></span><a href="${pageContext.request.contextPath}/user/userPasswordUpdate.jsp" target="rightFrame">个人信息修改</a>--%>
<%--        </div>--%>

<%--    <dd><div class="title"><span><img src="images/leftico03.png" /></span>日报管理</div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="${pageContext.request.contextPath}/report/showAllReport?page=1" target="rightFrame">日报管理</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>

<%--    <dd><div class="title"><span><img src="images/leftico01.png" /></span>加班管理</div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="${pageContext.request.contextPath}/work/showAllWork?page=1" target="rightFrame">加班管理</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>
<%--    <dd><div class="title"><span><img src="images/leftico01.png" /></span>休假管理</div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="${pageContext.request.contextPath}/rest/showAllRest?page=1" target="rightFrame">休假管理</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>
<%--    <dd><div class="title"><span><img src="images/leftico04.png" /></span>审批</div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="${pageContext.request.contextPath}/work/showAllWorkwait?page=1" target="rightFrame">加班审批</a><i></i></li>--%>
<%--            <li><cite></cite><a href="${pageContext.request.contextPath}/rest/showAllRestwait?page=1" target="rightFrame">休假审批</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>
<%--    <dd><div class="title"><span><img src="images/leftico04.png" /></span>我的桌面</div>--%>
<%--        <ul class="menuson">--%>
<%--            <li><cite></cite><a href="${pageContext.request.contextPath}/mydesktop" target="rightFrame">我的桌面</a><i></i></li>--%>
<%--        </ul>--%>
<%--    </dd>--%>
<%--</dl>--%>
<%--</body>--%>
</html>
