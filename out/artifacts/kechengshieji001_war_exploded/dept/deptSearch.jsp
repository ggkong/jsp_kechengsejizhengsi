<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/19
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>部门管理</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <link href="../css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="../js/select-ui.min.js"></script>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui/css/layui.css"  media="all">
    <script type="text/javascript">
        KE.show({
            id : 'content7',
            cssPath : './index.css'
        });
    </script>

    <script type="text/javascript">
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

        window.onload=function () {
            document.getElementById(("allCheck")).onclick=function () {
                var all=document.getElementById("allCheck");
                var boxs= document.getElementsByName("quanbu");
                if(all.checked==true){
                    for(var i=0;i<boxs.length;i++){
                        boxs[i].checked=true;
                    }
                }
                else {
                    for(var i=0;i<boxs.length;i++){
                        boxs[i].checked=false;
                    }
                }
            }
        }


        function deleteManyDept() {
            var trueorFalse = confirm("确定要删除选中的部门吗？")
            if (trueorFalse){
                console.log("adsads");
                // console.log(document.forms[1])
                document.forms[1].submit();
            }else {
                return;
            }
        }

    </script>


</head>

<body>
<form action="${pageContext.request.contextPath}/selectservlet" method="post">
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#" style="color: #ffffff">部门管理</a></li>
    </ul>
</div>

<!--查询条件-->
<br />
<br />

<ul class="seachform">

    <li>
        <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门名称</label><input name="selectDept" type="text" class="scinput" /></li>

    <li><label>&nbsp;</label><input name="" type="submit" class="layui-btn layui-btn-warm layui-btn-radius scbtn" value="查询"/></li>

</ul>

</div>
</form>

<div class="tools">

    <ul class="toolbar">
        <li class="click"><span></span><a href="${pageContext.request.contextPath}/dept/deptinsert" target="rightFrame"><button style="background-color: #009688" type="button" class="layui-btn layui-btn-danger"><i class="layui-icon"></i>添加</button></a></li>
        <li class="click" ></span><a  target="rightFrame" onclick="deleteManyDept()" ><button type="button" style="background-color: #009688" class="layui-btn layui-btn-danger"><i class="layui-icon"></i>删除</button></a></li>
    </ul>
</div>


<table class="tablelist"><tbody><tr><td><table class="tablelist"><tbody><tr><td><table class="tablelist">
    <tbody>
    <tr>
        <td><table class="tablelist">
            <thead>
            <tr>
                <th width="5%" height="83"><input id="allCheck" name="" type="checkbox" value="" /></th>
                <th width="12%">部门编号<i class="sort"><img src="../images/px.gif" /></i></th>
                <th width="16%">部门名称</th>
                <th width="15%">负责人</th>
                <th width="13%">人数</th>
                <th width="21%">注册时间</th>
                <th width="18%">操作</th>
            </tr>
            </thead>
            <form action="${pageContext.request.contextPath}/dept/delmanydept" method="post">
            <tbody>
            <c:forEach items="${deptVOList}" var="dept">
                <tr>
                    <td><input  class="checkdel" name="quanbu" type="checkbox" value="${dept.departmentId}&${dept.managerName}" /></td>
                    <td>${dept.departmentId}</td>
                    <td>${dept.departmentName}</td>
                    <td>${dept.managerName}</td>
                    <td>${dept.total_user}</td>
                    <td>${dept.createTime}</td>
                    <td>
                        <span>
                            <div class="layui-btn-group">
                                <a href="${pageContext.request.contextPath}/dept/updateDept?departmentId=${dept.departmentId}" class="tablelink"><button type="button" class="layui-btn layui-btn-sm"><i class="layui-icon">修改</i></button></a></a>
                                <a href="/dept/delDept?departmentId=${dept.departmentId}&managername=${dept.managerName}" class="tablelink" onclick="confirm('确定要删除吗？')"> <button type="button" class="layui-btn layui-btn-sm"><i class="layui-icon">删除</i></button></a></a>
                            </div>
                        </span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            </form>
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


<%--<div class="pagin">
    <div class="message">共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${deptcurrage}&nbsp;</i>页</div>
    <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
    </ul>
</div>--%>


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

<div id="limitDemo2" style="text-align: center"></div>
<script src="https://www.layuicdn.com/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;
        //完整功能
        laypage.render({
            elem: 'limitDemo2'
            ,count: ${deptcount}
            ,limit: 4
            ,curr: ${deptcurrpage}
            ,layout: ['count', 'prev', 'page', 'next',  'skip']
            ,jump: function(obj,first){
                var curr = obj.curr;
                if (!first){
                    // 此时的 curr 是多少
                    console.log("点击下一页，此时的curr 是")
                    console.log(curr)
                    location.href = "${pageContext.request.contextPath}/dept/showAllDept?page="+curr;
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

