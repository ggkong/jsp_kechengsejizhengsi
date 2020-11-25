<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/21
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>加班管理</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <link href="../css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="../js/select-ui.min.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
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
    </script>
    <script type="text/javascript">
        // 删除单个
        function delWork(record_id) {
            var trueorFalse = confirm("确定要删除日志嘛？")
            if (trueorFalse){
                location.href = "${pageContext.request.contextPath}/work/delWork?record_id="+record_id;
            }else {
                return;
            }
        }
        function delManyWork() {
            var trueorFalse = confirm("确定要删除选中的加班记录嘛？")
            if (trueorFalse){
                // console.log(document.forms[1])
                document.forms[1].submit();
            }else {
                return;
            }
        }
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#" style="color: #ffffff">加班管理</a></li>
    </ul>
</div>

<!--查询条件-->
<br />
<br />
<ul class="seachform">
    <form action="${pageContext.request.contextPath}/work/select" method="post">
    <li>
        <label> 申请日期:</label><input name="startTime" type="text" class="scinput"  value=${startTime} onClick="WdatePicker({startDate:'',dateFmt:'yyyy-MM-dd'})" />
    </li>

    <li>
        <label> 到</label><input name="endTime" type="text" class="scinput" value=${endTime} onClick="WdatePicker({startDate:'',dateFmt:'yyyy-MM-dd'})" />
    </li>

    <li>
        <label>状态：</label>
        <div class="vocation">
            <select name="state" value="${state}" class="select3">
                <option value="0">申请中</option>
                <option value="1">已批准</option>
                <option value="2">已驳回</option>
            </select>
        </div>
    </li>
    <li><label>&nbsp;</label><button type="submit" class="layui-btn layui-btn-warm layui-btn-radius scbtn">查询</button></li>
    </form>
</ul>
</div>

<div class="tools">

    <ul class="toolbar">
        <li class="click"><span></span><a href="${pageContext.request.contextPath}/workManager/workInsert.jsp" target="rightFrame"><button style="background-color: #009688;" type="button" class="layui-btn layui-btn-danger"><i class="layui-icon"></i>添加</button>
        </a></li>
        <li class="click"></span><a href="#" target="rightFrame" onclick="delManyWork()"><button style="background-color: #009688;" type="button" class="layui-btn layui-btn-danger"><i class="layui-icon"></i>删除</button>
        </a></li>
    </ul>
</div>


<table class="tablelist"><tbody><tr><td><table class="tablelist"><tbody><tr><td><table class="tablelist">
    <tbody>
    <tr>
        <td><table class="tablelist">
            <thead>
            <tr>
                <th width="3%"><input id="allCheckWork" name="" type="checkbox" value="" /></th>
                <th width="7%">工号<i class="sort"><img src="../images/px.gif" /></i></th>
                <th width="11%">姓名</th>
                <th width="10%">加班日期</th>
                <th width="9%">开始时间</th>
                <th width="11%">终了时间</th>
                <th width="7%">加班时间小计</th>
                <th width="8%">状态</th>
                <th width="13%">操作</th>
            </tr>
            </thead>
            <tbody>
            <form action="${pageContext.request.contextPath}/work/delManyWork" method="post">
            <c:forEach items="${workVOList}" var="work">
                <tr>
                    <td><input name="delWorkId" value="${work.record_id}" class="delCheck" type="checkbox" /></td>
                    <td>${work.account}</td>
                    <td>${work.name}</td>
                    <td>${work.work_date}</td>
                    <td>${work.start_time}</td>
                    <td>${work.end_time}</td>
                    <td>${work.work_time}</td>
                    <c:if test="${work.state eq '0'}">
                        <td>申请中</td>
                    </c:if>
                    <c:if test="${work.state eq '1'}">
                        <td>已通过</td>
                    </c:if>
                    <c:if test="${work.state eq '2'}">
                        <td>被驳回</td>
                    </c:if>
                    <td>
                        <span>
                            <div class="layui-btn-group">
                                <a href="${pageContext.request.contextPath}/work/showWorkDetail?workId=${work.record_id}" class="tablelink"> <button type="button" class="layui-btn layui-btn-sm"><i class="layui-icon">修改</i></button></a>
                            <a href="#" class="tablelink" onclick="delWork(${work.record_id})"> <button type="button" class="layui-btn layui-btn-sm"><i class="layui-icon">删除</i></button></a>
                            </div>

                        </span>
                    </td>
                </tr>
            </c:forEach>
            </form>

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

<div id="limitDemoWork" style="text-align: center"></div>


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

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

<script src="https://www.layuicdn.com/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;
        //完整功能
        laypage.render({
            elem: 'limitDemoWork'
            ,count: ${workCount}
            ,limit: 10
            ,curr: ${workCurrPage}
            ,layout: ['count', 'prev', 'page', 'next', 'skip']
            ,theme:"#4ab2d0"
            ,jump: function(obj,first){
                var curr = obj.curr;
                if (!first){
                    // 此时的 curr 是多少
                    console.log("点击下一页，此时的curr 是")
                    console.log(curr)
                    location.href = "${pageContext.request.contextPath}/work/showAllWork?page="+curr;
                }
            }
        });

    });
</script>
<script type="text/javascript">
    $("#allCheckWork").click(function () {
        var trueOrFalse = $(this).attr("checked");
        $(".delCheck").attr("checked",trueOrFalse);
    })
</script>
</body>
</html>
