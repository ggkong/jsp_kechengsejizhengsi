<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/24
  Time: 13:05
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
</head>

<body>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#" style="color: #ffffff">加班管理</a></li>
            <li><a href="#" style="color: #ffffff">查看加班申请</a></li>
        </ul>
    </div>

    <div class="formbody">

        <div class="formtitle"><span>加班申请详情</span></div>

        <ul class="forminfo">
            <li>
                <label>编号</label>
                </label><input value="${workShow.record_id}" name = "id" type="text" class="dfinput"  readonly="readonly"/>
            </li>
            <li>
                <label>工号</label>
                </label><input value="${workShow.account}" name = "account" type="text" class="dfinput"  readonly="readonly"/>
            </li>
            <li>
                <label>姓名</label>
                </label><input value="${workShow.name}" name="name" type="text" class="dfinput" value="admin" readonly="readonly"/>
            </li>
            <li>
                <label>加班日期</label>
                <input type="text" value="${workShow.work_date}" class="dfinput"  name="work_date" onClick="WdatePicker({work_date:'',dateFmt:'yyyy-MM-dd'})"/>
            </li>
            <li>
                <label>开始时间</label>
                <input type="text" value="${workShow.start_time}" class="dfinput"  name="startTime" /> (HH:mm)
            </li>
            <li>
                <label>终了时间</label>
                <input type="text" value="${workShow.end_time}" class="dfinput"  name="endTime" /> (HH:mm)
            </li>
            <li>
                <label>加班时间小计</label>
                <input type="text"  value="${workShow.work_time}" class="dfinput"  name="workTime" readonly/>
            </li>
            <li>
                <label>加班备注 </label>
                <input type="text"  value="${workShow.remark}" class="dfinput"  name="workTime" readonly/>
            </li>
            <li>
                <label>加班原因</label>
                <input type="text"  value="${workShow.work_cause}" class="dfinput"  name="workTime" readonly/>
            </li>
        </ul>
    </div>
</form>

</body>
</html>
