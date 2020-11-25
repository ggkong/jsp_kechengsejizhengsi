<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/16
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户管理</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <link href="../css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.base64.js"></script>
    <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="../js/select-ui.min.js"></script>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="../css/laydate.css"/>
    <script type="text/javascript">

        // KE.show({
        //     id : 'content7',
        //     cssPath : './index.css'
        // });
        // 确定要删除的按钮
        function YesOrNoDel(account, name){
            var yesOrNo = confirm("确定要删除吗？");
            if (yesOrNo = true){
                // 加密
                var account = $.base64.encode(account);
                var name = $.base64.encode(name);
                // 发送get 请求
                var xhr = new XMLHttpRequest();
                var url = "/user/delUser?account="+account+"&"+"name="+name;
                console.log("发送删除请求")
                xhr.open("GET",url,true);
                xhr.send();
            }else {
                return;
            }
        }
    </script>

    <script type="text/javascript">
        // 声明全局对象 数组
        var delList = [];
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });
            $(".select2").uedSelect({
                width : 167
            });
            $(".select3").uedSelect({
                width : 100
            });
        });
        $(function(){


            // 全选全不选
            $("#allCheck").click(function () {
                var trueOrFalse = $(this).attr("checked");
                $("input[type=checkbox]").attr("checked",trueOrFalse);
                // 将所有的account添加到一个list
                if(trueOrFalse){
                    // 如果全选
                    var del = document.getElementsByClassName("checkdel");
                    for (var x = 0;x < del.length;x++){
                        delList.push(del[x].value)
                    }
                    // 去重
                    console.log(delList);
                }else {
                    window.delList = [];
                    console.log(window.delList)
                }
            })

        })
        function getIndex(value) {
            for (var i = 0; i < delList.length; i++) {
                if (value == delList[i]){
                    return i;
                }
            }
        }
        // 为每个选择class 绑定
        function delYesOrNo(obj){
            console.log(obj.value);
            // 判定是不是选中状态
            var trueOrFalse = $(obj).attr("checked");
            if (trueOrFalse){
                // 选中状态 将其添加到 数组中
                delList.push(obj.value);
                console.log(window.delList)
            }
            if (trueOrFalse == false){
                console.log("删除")
                console.log(delList.length)
                console.log("aaa")
                console.log("获取的index是"+getIndex(obj.value))
                console.log("删除之前的delList")
                console.log(delList)
                delList.splice(getIndex(obj.value),1);
                console.log(window.delList)
            }
        }
        function sendDelPost() {
            var yesOrNo = confirm("确定要删除选择的员工吗？");
            if (yesOrNo = true){
                var xhr = new XMLHttpRequest();
                var url = "/user/delManyUser?";
                console.log(delList.length)
                if (delList.length == 0){
                    return;
                }
                for (var i = 0; i < delList.length; i++) {
                    url = url+"account="+delList[i]+"&";
                }
                console.log(url);
                url = url.substr(0, url.length - 1);
                console.log(url)
                xhr.open("GET",url,true);
                xhr.send();
            }else {
                return;
            }
        }
    </script>
    <style type="text/css">
        .suibian p{
            float: left;
            margin-left: 20px;
        }
        .layui-btn-danger{
            background-color: #009688;
        }
    </style>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#" style="color: #ffffff">用户管理</a></li>
    </ul>
</div>

<!--查询条件-->
<br />
<br />
<ul class="seachform">
    <form action="${pageContext.request.contextPath}/user/select" method="post">
        <li><label>姓名</label><input name="name"  value="${selectName}" type="text" class="scinput" /></li>
        <li><label>部门</label>
            <div class="vocation">
                <select name="deptId" class="select3">
                    <c:forEach items="${sessionScope.departments}" var="dept">
                        <option value="${dept.departmentId}">${dept.departmentName}</option>
                    </c:forEach>
                </select>
            </div>
        </li>
<%--    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询"/></li>--%>
        <li><label>&nbsp;</label><button type="submit" class="layui-btn layui-btn-warm layui-btn-radius scbtn">查询</button></li>
    </form>

</ul>
</div>

<div class="tools">

    <ul class="toolbar">
        <li style="margin-left: 15px" class="click"><a href="${pageContext.request.contextPath}/user/userInsert.jsp" target="rightFrame"><button type="button" class="layui-btn layui-btn-danger"><i class="layui-icon"></i>添加</button></a></li>
        <li class="click" onclick="sendDelPost()"><a href="#" target="rightFrame"><button type="button" class="layui-btn layui-btn-danger"><i class="layui-icon"></i>删除</button></a></li>
    </ul>
</div>


<table class="tablelist"><tbody><tr><td><table class="tablelist"><tbody><tr><td><table class="tablelist">
    <tbody>
    <tr>
        <td><table class="tablelist">
            <thead>
            <tr>
                <th><input id = "allCheck" name=""  type="checkbox" value=""/></th>
                <th width="7%">工号<i class="sort"><img src="../images/px.gif" /></i></th>
                <th width="11%">姓名</th>
                <th width="10%">部门</th>
                <th width="9%">职务</th>
                <th width="11%">注册时间</th>
                <th width="5%">性别</th>
                <th width="10%">手机</th>
                <th width="9%">出生日期</th>
                <th width="12%">邮箱</th>
                <th width="13%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userVOList}" var="userVO">
                <tr>
                    <td><input onclick="delYesOrNo(this)" class="checkdel"  value="${userVO.account}" type="checkbox"  /></td>
                    <td>${userVO.account}</td>
                    <td>${userVO.name}</td>
                    <td>${userVO.department_name}</td>

                    <c:if test="${userVO.user_type == '0'}">
                        <td>管理者</td>
                    </c:if>
                    <c:if test="${userVO.user_type == '1'}">
                        <td>主管</td>
                    </c:if>
                    <c:if test="${userVO.user_type == '2'}">
                        <td>员工</td>
                    </c:if>

                    <td>${userVO.create_time}</td>
                    <c:if test="${userVO.sex == '1'}">
                        <td>男</td>
                    </c:if>
                    <c:if test="${userVO.sex == '2'}">
                        <td>女</td>
                    </c:if>
                    <td>${userVO.mobile}</td>
                    <td>${userVO.birthday}</td>
                    <td>${userVO.email}</td>

                    <td><span>
                         <div class="layui-btn-group">
                             <a href="${pageContext.request.contextPath}/user/updateUser?account=${userVO.account}" class="tablelink"><button type="button" class="layui-btn layui-btn-sm">
                                 <i class="layui-icon">修改</i>
                                </button></a>
                            <a href="#" class="tablelink" onclick=YesOrNoDel("${userVO.account}","${userVO.name}") >  <button type="button" class="layui-btn layui-btn-sm">
                                    <i class="layui-icon">删除</i>
                                </button></a>
                         </div>
                    </span></td>
                </tr>
            </c:forEach>
            </tbody>
        </table></td>
    </tr>
    </tbody>
</table>
</td>
</tr>
</tbody>
</table></td>
</tr>
</tbody>
</table>


<%--<div class="pagin">--%>
<%--    <div class="message">共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${currpage}&nbsp;</i>页,共&nbsp;<i class="blue">${pages}&nbsp;</i>页</div>--%>
<%--    <div class="suibian"><p id="first">首页</p><p id="next">下一页</p><p id="previous">前一页</p><p id="last">最后一页</p></div>--%>
<%--</div>--%>
<div id="limitDemo" style="text-align: center"></div>


<div class="tip">
    <div class="tiptop"><span>提示信息</span><a></a></div>

    <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
            <p>是否确认对信息的修改 ？</p>
            <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
    </div>

    <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
    </div>

</div>




</div>
<script src="https://www.layuicdn.com/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;
        //完整功能
        laypage.render({
            elem: 'limitDemo'
            ,count: ${count}
            ,limit: 4
            ,curr: ${currpage}
            ,layout: ['count', 'prev', 'page', 'next', 'skip']
            ,theme:"#4ab2d0"
            ,jump: function(obj,first){
                var curr = obj.curr;
                if (!first){
                    // 此时的 curr 是多少
                    console.log("点击下一页，此时的curr 是")
                    console.log(curr)
                    location.href = "${pageContext.request.contextPath}/user/showAllUser?page="+curr;
                }
            }
        });

    });
</script>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>