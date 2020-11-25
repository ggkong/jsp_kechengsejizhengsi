<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门修改</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="../js/select-ui.min.js"></script>
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
    //进行校验
 document.forms[0].submit();
}
</script>


</head>

<body>
<form action="${pageContext.request.contextPath}/dept/updateDept" method="post" >
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#" style="color: #ffffff">部门管理</a></li>
    <li><a href="#" style="color: #ffffff">修改部门</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
    <li>
      <label>部门编号</label>
      </label><input name="departmentId" type="text" class="dfinput" readonly="readonly" value="${deptWillUpdate.departmentId}"/></li>
    <li>
      <label>部门名称</label>
      <input name="departmentName" type="text" class="dfinput" value="${deptWillUpdate.departmentName}"/>
    </li>
     <li>
      <label>负责人 </label>
       <div class="vocation">
    <select class="select3" name="manager_id">

        <c:forEach items="${managers}" var="manager">
            <c:if test="${manager.account eq deptWillUpdate.account}">
                <option selected value="${manager.account}">${manager.name}</option>
            </c:if>
            <c:if test="${manager.account ne deptWillUpdate.account}">
                <option value="${manager.account}">${manager.name}</option>
            </c:if>
        </c:forEach>

    </select>
    </div>
    </li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存" onclick="saveButton()"/></li>
    </ul>
    </div>
</form>

</body>
</html>
