<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/21
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>加班申请添加</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <link href="../css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="../js/select-ui.min.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
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
            var work_date = document.getElementsByName("work_date")[0].value;
            var startTime = document.getElementsByName("startTime")[0].value;
            var endTime = document.getElementsByName("endTime")[0].value;
            var cause = document.getElementsByName("cause")[0].value;
            if (startTime == ""){
                alert("开始时间不能为空")
                return;
            }
            if (endTime == ""){
                alert("结束时间不能为空")
                return;
            }
            if (cause == ""){
                alert("原因不能为空")
                return;
            }
            if (work_date == ""){
                alert("加班日期不能为空");
                return;
            }
            document.forms[0].submit();
        }
    </script>

</head>

<body>
<form action="${pageContext.request.contextPath}/work/insertWork" method="post">
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#" style="color: #ffffff">加班管理</a></li>
            <li><a href="#" style="color: #ffffff">添加加班申请</a></li>
        </ul>
    </div>

    <div class="formbody">

        <div class="formtitle"><span>加班申请</span></div>

        <ul class="forminfo">
            <li>
                <label>工号</label>
                </label><input value="${user.account}" name = "account" type="text" class="dfinput"  readonly="readonly"/>
            </li>
            <li>
                <label>姓名</label>
                </label><input value="${user.name}" name="name" type="text" class="dfinput" value="admin" readonly="readonly"/>
            </li>
            <li>
                <label>加班日期 <font color="red">*</font></label>
                <input type="text" class="dfinput"  name="work_date" onClick="WdatePicker({work_date:'',dateFmt:'yyyy-MM-dd'})"/>
            </li>
            <li>
                <label>开始时间 <font color="red">*</font></label>
                <input type="text" class="dfinput"  name="startTime" /> (HH:mm)
            </li>
            <li>
                <label>终了时间 <font color="red">*</font></label>
                <input type="text" class="dfinput"  name="endTime" /> (HH:mm)
            </li>
            <li>
                <label>加班时间小计 </label>
                <input type="text" class="dfinput"  name="workTime" readonly/>
            </li>
            <li>
                <br />
                <label>加班原因 <font color="red">*</font></label>
                <textarea rows="5" cols="10" id="tomorrow_plan" required="required"  name="cause" style="width: 500px; height: 30px; padding-left:5px; border: 1px solid #eaeff2; margin-top: 20px;ime-mode:disabled;" maxlength="255">

                </textarea></li>
            <li><label>&nbsp;</label><input name="cause" type="button" class="btn" value="确认保存" onclick="saveButton()"/></li>
        </ul>
    </div>
</form>
<script type="text/javascript">
    function legal(string){
        string = string.split(":")
        if(string[0] < 0 || string[0] >=24){
            return false
        }
        if(string[1] < 0 || string[1] >=60){
            return false
        }
        return true
    }

    // 计算加班时间小计
    function calculateTimeInterval(startTime,endTime){

        // 判断传入的时间字符串是否合法
        if(legal(startTime) && legal(endTime)){
            // 计算时间间隔
            var startTime = new Date("1-1 " + startTime)
            var endTime = new Date("1-1 " + endTime)

            var finalTime = endTime - startTime


            // 计算小时
            var hour = Math.floor(finalTime / 1000 / 60 / 60)
            var minute = Math.floor((finalTime - (hour*1000*60*60)) / 1000 / 60)

            // 判断时间合法性
            // 如果 小时 或 分钟 为负数，则时间非法，返回false
            if(hour < 0 || minute < 0){
                return ''
            }
            return hour + ":" + minute
        }
        return ''
    }

    $("input[name='startTime']").change(function(){
        var value = $("input[name='endTime']").val()
        //console.log(value)
        if(legal(this.value) && value != ''){
            //console.log(this.value,value)
            $("input[name='workTime']").attr("value",calculateTimeInterval(this.value,value))
        }
    });
    $("input[name='endTime']").change(function(){
        var value = $("input[name='startTime']").val()
        //console.log(value)
        if(legal(this.value) && value != ''){
            //console.log(value,this.value)
            $("input[name='workTime']").attr("value",calculateTimeInterval(value,this.value))
        }
    });

</script>
</body>
</html>

