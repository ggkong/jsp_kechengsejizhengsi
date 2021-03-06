<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>加班审批</title>
<link href="../css/style.css" rel="stylesheet" ty+++pe="text/css" />
<link href="../css/select.css" rel="stylesheet" type="text/css" />
<link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
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
</head>
<% request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8");%>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#" style="color: #ffffff">审批</a></li>
    <li><a href="#" style="color: #ffffff">加班审批</a></li>
    </ul>
    </div>
   
	<!--查询条件-->
    <br />
    <br />
    <ul class="seachform">
    <form action="${pageContext.request.contextPath}/work/selectWait" method="post">
    <li>
      <label> 加班日期:</label><input name="startTime" type="text" class="scinput"  value="${startTime}" onClick="WdatePicker({startDate:'',dateFmt:'yyyy-MM-dd'})" /></li>
     <li>  <label> 到</label><input name="endTime" type="text" class="scinput" value="${endTime}" onClick="WdatePicker({startDate:'',dateFmt:'yyyy-MM-dd'})" /></li>
    <li>
    <label>姓名：</label>  
    <input name="Workname" type="text" class="scinput" value="${workname}" />
    </li>
    <li><label>&nbsp;</label><button type="submit" class="layui-btn layui-btn-warm layui-btn-radius scbtn">查询</button></li>
    </form>
    </ul>
    </div>
    <table class="tablelist"><tbody><tr><td><table class="tablelist"><tbody><tr><td><table class="tablelist"><tbody><tr><td><table class="tablelist">
      <tbody>
        <tr>
          <td><table class="tablelist">
            <tbody>
              <tr>
                <td><table class="tablelist">
                      <tbody>
                        <tr>
                          <td><table class="tablelist">
                              <thead>
                                <tr>
                                  <th width="7%">工号<i class="sort"><img src="../images/px.gif" /></i></th>
                                  <th width="11%">姓名</th>
                                  <th width="10%">加班日期</th>
                                  <th width="9%">开始时间</th>
                                  <th width="11%">终了时间</th>
                                  <th width="7%">加班原因</th>
                                  <th width="13%">操作</th>
                                </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${workVOList}" var="work">
                                  <tr>
                                      <td>${work.account}</td>
                                      <td>${work.name}</td>
                                      <td>${work.work_date}</td>
                                      <td>${work.start_time}</td>
                                      <td>${work.end_time}</td>
                                      <td>${work.work_cause}</td>
                                      <td><span>
                                          <a href="/work/passWork?record_id=${work.record_id}" class="tablelink"  /><button type="button" class="layui-btn layui-btn-sm"><i class="layui-icon">通过</i></button>
                                          <a href="/work/refausework?record_id=${work.record_id}" class="tablelink" > <button type="button" class="layui-btn layui-btn-sm"><i class="layui-icon">驳回</i></button></a>
                                      </span></td>
                                  </tr>
                              </c:forEach>
                              </tbody>
                          </table></td>
                        </tr>
                      </tbody>
                  </table></td>
              </tr>
              </tbody>
          </table></td>
        </tr>
      </tbody>
    </table>
    </td>
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
    <div id="limitDemoWork" style="text-align: center"></div>
    
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
                ,limit: 5
                ,curr: ${workCurrPage}
                ,layout: ['count', 'prev', 'page', 'next', 'skip']
                ,theme:"#4ab2d0"
                ,jump: function(obj,first){
                    var curr = obj.curr;
                    if (!first){
                        // 此时的 curr 是多少
                        console.log("点击下一页，此时的curr 是")
                        console.log(curr)
                        location.href = "${pageContext.request.contextPath}${url}"+curr+"&Workname=${workname}&startTime=${startTime}&endTime=${endTime}";
                    }
                }
            });

        });
    </script>
</body>
</html>
