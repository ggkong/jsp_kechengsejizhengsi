<%--
  Created by IntelliJ IDEA.
  User: 孔格
  Date: 2020/11/18
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户添加</title>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
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
        function IsPhoneAvailable (numStr) {
            var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
            return myreg.test(numStr);
        }
        function  IsEmailAvailable(strEmail) {
            var myreg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/
            return myreg.test(strEmail);
        }
        function saveButton(){
            console.log("进入校验方法")
            // 进行前端校验
            // 进行提交
            var account = document.getElementsByName("account")[0].value;
            console.log(account)
            var ps = document.getElementsByName("ps")[0].value;
            var password = document.getElementsByName("password")[0].value;
            var name = document.getElementsByName("name")[0].value;
            var mobile = document.getElementsByName("mobile")[0].value;
            var email = document.getElementsByName("email")[0].value;
            var birthday = document.getElementsByName("birthday")[0].value;
            console.log(birthday);


            if (account == ""){
                alert("工号为空");
                return;
            }
            if (account.length != 6){
                alert("账号必须为六位")
            }
            if (password == "" || ps == ""){
                alert("密码为空")
                return;
            }
            if (password != ps){
                alert("两次输入的密码不一致")
                return;
            }
            if (name == ""){
                alert("姓名为空")
                return;
            }
            if (mobile.length != 11){
                alert("手机号必须为十一位")
                return;
            }
            if (!IsPhoneAvailable(mobile)){
                alert("请输入有效的手机号")
                return;
            }
            if (birthday != ""){
                var inputTime = birthday.replace(/-/g,"/");
                var today = new Date();
                var birDay = new Date(Date.parse(inputTime));
                console.log(today)
                if (birDay > today){
                    alert("输入的时间不正确")
                    return;
                }
            }
            if (email == ""){
                alert("邮箱为必填项")
                return;
            }
            if (!IsEmailAvailable(email)){
                alert("邮箱格式错误")
                return;
            }
            // commit
            document.forms[0].submit();
        }
    </script>

</head>

<body>
<form action="${pageContext.request.contextPath}/user/updateUser" method="post">
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#" style="color: #ffffff">用户管理</a></li>
            <li><a href="#" style="color: #ffffff">个人信息修改</a></li>
        </ul>
    </div>

    <div class="formbody">

        <div class="formtitle"><span>基本信息</span></div>

        <ul class="forminfo">
            <li>
                <label>工号 <font color="red">*</font></label>
                </label><input name="account" type="text" class="scinput" value="${user.account}" readonly="readonly"/></li>
            <li>
                <label>密码 <font color="red">*</font></label>
                <input name="ps" type="password" class="dfinput" value="${user.password}"/>
            </li>
            <li>
                <label>确认密码 <font color="red">*</font></label><input name="password" type="password" class="dfinput" />
            </li>
            <li>
                <label>姓名 <font color="red">*</font></label>
                </label><input name="name" type="text" class="dfinput" value="${user.name}"/>
            </li>
            <li><label>部门</label>
                <div class="vocation">
                    <select class="select3" name="department_id">
                        <c:forEach items="${sessionScope.departments}" var="dept">
                            <c:if test="${dept.departmentId eq userWillUpdate.department_id}">
                                <option selected value="${dept.departmentId}">${dept.departmentName}</option>
                            </c:if>
                            <c:if test="${dept.departmentId ne userWillUpdate.department_id}">
                                <option value="${dept.departmentId}">${dept.departmentName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </li>
            <li>
                <label>性别</label>
                <cite>
                    <c:choose>
                        <c:when test="${user.sex eq '1'}">
                            <input name="sex" type="radio" value="1" checked="checked" />
                            男&nbsp;&nbsp;&nbsp;&nbsp;
                            <input name="sex" type="radio" value="2" />
                            女
                        </c:when>
                        <c:otherwise>
                            <input name="sex" type="radio" value="1"  />
                            男&nbsp;&nbsp;&nbsp;&nbsp;
                            <input name="sex" type="radio" value="2" checked="checked"/>
                            女
                        </c:otherwise>
                    </c:choose>

                </cite>
            </li>
            <li><label>手机号码 <font color="red">*</font></label><input name="mobile" type="text" class="dfinput" value="${user.mobile}" /></li>
            <li><label>出生日期</label>
                <input name="birthday" type="text" class="dfinput" value="${user.birthday}" onClick="WdatePicker({work_date:'',dateFmt:'yyyy-MM-dd'})"/></li>
            <li><label>邮箱 <font color="red">*</font></label><input name="email" type="text" class="dfinput" value="${user.email}" /></li>
            <li><label>&nbsp;</label><input name="" type="button" onclick="saveButton()" class="btn layui-btn layui-btn-lg layui-btn-normal layui-btn-radius" value="确认修改&提交" style="padding-left: 21px;line-height: 30px"/></li>
        </ul>
    </div>
</form>
</body>
</html>

