<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>成绩查询</title>
  <meta name="description" content="这是一个 user 页面">
  <meta name="keywords" content="user">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>学生选课管理系统</strong> 
  </div>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span><%=request.getSession().getAttribute("username")%><span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
       <li id="studentCourseManage"><a href="studentCourseManage.jsp"><span class="am-icon-table"></span>学生课程管理</a></li>
      <li id="gradeSearch"><a href="gradeSearch.jsp"><span class="am-icon-table"></span> 成绩查询</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>学生选课系统--黄家雯</p>
      </div>
    </div>

  
  </div>
  <!-- sidebar end -->

 <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">成绩查询</strong> </div>
    </div>
      <input type="hidden" id="ssn" value="010">
    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">

            <div class="am-form-group am-margin-left am-fl">
                                 成绩：
              <select id="grade">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
                <option value="E">E</option>
         
              </select>
              <input type="button" id="search" value="查询">
            </div>
          </div>
        </div>
      </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th>排课编号</th>
                <th>课程名称</th>
                <th>学分</th>
                <th>成绩</th>
              </tr>
          </thead>
          <tbody>
            <tr>
             
            </tr>
          </tbody>
        </table>
          <div class="am-cf">
  共 2 条记录
  <div class="am-fr">
    <ul class="am-pagination">
      <li class="am-disabled"><a href="#">«</a></li>
      <li class="am-active"><a href="#">1</a></li>
      <li><a href="#">»</a></li>
    </ul>
  </div>
</div>
          <hr />
          <p>注：.....</p>
        </form>
      </div>

    </div>
  </div>
  <!-- content end -->

</div>



<!--[if lt IE 9]>
<script src="assets/js/jquery1.11.1.min.js"></script>
<script src="assets/js/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="assets/js/app.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $.ajax({
         type: "POST",
         url: "showGradeServlet",
         data:{ssn:$("#ssn").val()},                                     
         success: function(data) {
             //将返回字符串转换为json对象
             var json = null;
             try { 
                 json = eval('(' + data + ')');
                 $("table tr:gt(0)").remove();
                 var rows="";
                 for(var i in json){
                	 rows =rows + "<tr>"+
                	 "<td class='jssn'>"+json[i].sno+"</td>"+
                	 "<td>"+json[i].cname+"</td>"+
                	 "<td>"+json[i].credits+"</td>"+
                	 "<td>"+json[i].grade+"</td></tr>"         
                 }
                 $("table").append(rows);
                 //alert(json);
                 
             } catch (e) {
                 alert("返回字符串不是json格式!");
                 return;
             }     
         }

     });
	 $("#search").click(function(){
		  $.post("searchGradeServlet",{ssn:$('#ssn').val(),grade:$('#grade').val()},  
			  function(data){
			   var json = null;
	            try { 
	                json = eval('(' + data + ')');
	                $("table tr:gt(0)").remove();
	                var rows="";
	                for(var i in json){
	               	 rows =rows + "<tr>"+
	               	 "<td class='jssn'>"+json[i].sno+"</td>"+
	               	 "<td>"+json[i].cname+"</td>"+
	               	 "<td>"+json[i].credits+"</td>"+
	               	 "<td>"+json[i].grade+"</td></tr>"
	               	}
	                $("table").append(rows);	                 
	            } catch (e) {
	                alert("返回字符串不是json格式!");
	                return;
	            }     
			  });
	});
});
</script>
</body>
</html>
