<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/19
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>日报添加</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <link href="../css/select.css" rel="stylesheet" type="text/css" />
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
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
            document.forms[0].action="userSearch.html";
            document.forms[0].submit();
        }
    </script>

    <script language="javascript">
        function saveButton(){
            document.forms[0].action="reportSearch.html";
            document.forms[0].submit();
        }
    </script>

</head>

<body>
<form action="${pageContext.request.contextPath}/report/addReport" class="layui-form" method="post">
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#" style="color: #ffffff">日报管理</a></li>
            <li><a href="#" style="color: #ffffff">添加日报</a></li>
        </ul>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>日报基本信息</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">工号</label>
        <div class="layui-input-block">
            <input type="text" name="account" lay-verify="required" lay-reqtext="工号是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input" style="width: 50%">
        </div>
    </div>

    <div class="layui-inline">
        <label class="layui-form-label">日期</label>
        <div class="layui-input-inline">
            <input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
    <br>
    <br>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input" style="width: 50%">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">进度</label>
        <div class="layui-input-block" style="width: 10%">
            <select name="work_process" lay-verify="required" lay-reqtext="进度是必填项，岂能为空？">
                <option value="0">0%</option>
                <option value="10">10%</option>
                <option value="20">20%</option>
                <option value="30">30%</option>
                <option value="40">40%</option>
                <option value="50">50%</option>
                <option value="60">60%</option>
                <option value="70">70%</option>
                <option value="80">80%</option>
                <option value="90">90%</option>
                <option value="100">100%</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">作业内容</label>
        <div class="layui-input-block">
            <textarea style="width: 50%" name="work_content" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">明日计划</label>
        <div class="layui-input-block">
            <textarea style="width: 50%" name="tomorrow_plan" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">问题点</label>
        <div class="layui-input-block">
            <textarea style="width: 50%" name="problem" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea style="width: 50%" name="other" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
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
            elem: '#date'
        });
    })
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            // layer.msg(JSON.stringify(data.field));
            // 提交到一个servlet
            console.log(data.field)
            if (data.field["account"].length != 6){
                console.log("工号错误")
                return false;
            }
            document.forms[0].submit();
        });
    });
</script>
</body>
</html>
