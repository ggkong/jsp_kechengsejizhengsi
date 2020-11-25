<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/22
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>休假申请修改</title>
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
    <script language="javascript">
        function saveButton(){
            var rest_start_date = document.getElementsByName("rest_start_date")[0].value;
            var rest_end_date = document.getElementsByName("rest_end_date")[0].value;
            var cause = document.getElementsByName("cause")[0].value;
            if (rest_start_date == ""){
                alert("开始时间为空")
                return;
            }
            if (rest_end_date == ""){
                alert("结束时间为空")
                return;
            }
            if (cause == ""){
                alert("原因为空")
                return;
            }
            document.forms[0].submit();
        }
    </script>

</head>

<body>
<form action="${pageContext.request.contextPath}/rest/updateRest" method="post">
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#" style="color: #ffffff">休假管理</a></li>
            <li><a href="#" style="color: #ffffff">休假修改</a></li>
        </ul>
    </div>

    <div class="formbody">

        <div class="formtitle"><span>休假修改</span></div>

        <ul class="forminfo">
            <li>
                <label>编号</label>
                </label><input  name="rest_id" type="text" class="dfinput" value="${willUpdateRest.restId}" readonly="readonly"/>
            </li>
            <li>
                <label>工号</label>
                </label><input  name="account" type="text" class="dfinput" value="${willUpdateRest.account}" readonly="readonly"/>
            </li>
            <li>
                <label>姓名</label>
                </label><input name="name" type="text" class="dfinput" value="${willUpdateRest.name}" readonly="readonly"/>
            </li>
            <li>
                <label>开始日期时间 <font color="red">*</font></label>
                <input type="text" value="${willUpdateRest.rest_start_date}" name="rest_start_date" class="layui-input" id="rest_start_date" placeholder="yyyy-MM-dd HH:mm:ss" style="width: 50%">
            </li>
            <li>
                <label>结束日期时间 <font color="red">*</font></label>
                <input type="text" value="${willUpdateRest.rest_end_date}" name="rest_end_date" class="layui-input" id="rest_end_date" placeholder="yyyy-MM-dd HH:mm:ss" style="width: 50%">
            </li>
            <li>
                <label>休假原因 <font color="red">*</font></label>
                <textarea rows="5" cols="10" id="cause" required="required"  name="cause" style="width: 500px; height: 30px; padding-left:5px; border: 1px solid #eaeff2; margin-top: 20px;ime-mode:disabled;" maxlength="255">${willUpdateRest.rest_cause}</textarea>
            </li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="saveButton()"/></li>
        </ul>
    </div>
</form>
<script type="text/javascript" src="https://www.layuicdn.com/layui/layui.js"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#rest_start_date'
            ,type: 'datetime'
        });
        laydate.render({
            elem: '#rest_end_date'
            ,type: 'datetime'
        });
    });
</script>
</body>
</html>
