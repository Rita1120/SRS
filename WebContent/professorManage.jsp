<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>教师管理</title>
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
          <span class="am-icon-users"></span><%=request.getSession().getAttribute("username")%> <span class="am-icon-caret-down"></span>
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
      <li class="admin-parent">
      <a class="am-cf" href="courseManage.jsp"><span class="am-icon-table"></span>课程管理</a></li>
      <li id="professorManage"><a href="professorManage.jsp"><span class="am-icon-table"></span> 教师管理</a></li>
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
 <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">教师管理</strong> </div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default"  data-am-modal="{target: '#modal', closeViaDimmer: 0, width: 400, height: 225}"><span class="am-icon-plus"></span> 新增</button>
            </div>
            <!-- 新增遮罩层 -->
            <div class="am-modal am-modal-no-btn" tabindex="-1" id="modal">
              <div class="am-modal-dialog">
                 <div class="am-modal-hd">新增教师
                  <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                </div>
	              <div class="am-modal-bd">
	              <form class="am-form">
	               <label for="ssn" class="am-fl">SSN：</label><input type="text"name="ssn" id="ssn">
	               <label for="name" class="am-fl">姓名：</label><input type="text" name="name" id="name">
	               <label for="title" class="am-fl">职称：</label>
	               <select id="title">
	                   <option value="教授">教授</option>
		               <option value="副教授">副教授</option>
		               <option value="讲师">讲师</option>   
	               </select>
	               <label for="department" class="am-fl">部门：</label>
	               <select id="department">
	                   <option value="管理学院">管理学院</option>
		               <option value="信电学院">信电学院</option>
	               </select>
	                 <br/>
	                <input type="button" value="新增教师" id="addProfessor">
	              </form>
	              </div>
              </div>
             </div>
             <!-- end -->
            <div class="am-form-group am-margin-left am-fl">
                                职称：
              <select id="titleSearch">
                <option value=""></option>
                <option value="教授">教授</option>
                <option value="副教授">副教授</option>
                <option value="讲师">讲师</option>        
              </select>
                                 学院：
              <select id="departmentSearch">
                <option value=""></option>
                <option value="管理学院">管理学院</option>
		        <option value="信电学院">信电学院</option>     
              </select>
              <input type="button" class="am-btn am-btn-xs am-btn-primary" value="查询" id="search">
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
                <th class="table-id">ID</th>
                <th class="table-title">姓名</th>
                <th class="table-type">职称</th>
                <th class="table-author">部门</th>
                <th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>

          </tbody>
        </table>
          <div class="am-cf">
  共 5 条记录
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
         url: "showProfessersServlet",
         data:"",                                     
         success: function(data) {
             //将返回字符串转换为json对象
             var json = null;
             try { 
                 json = eval('(' + data + ')');
                 $("table tr:gt(0)").remove();
                 var rows="";
                 for(var i in json){
                	 rows =rows + "<tr>"+
                	 "<td class='jssn'>"+json[i].ssn+"</td>"+
                	 "<td>"+json[i].name+"</td>"+
                	 "<td>"+json[i].title+"</td>"+
                	 "<td>"+json[i].department+"</td>"+
                	 "<td><div class='am-btn-toolbar'><div class='am-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary'><span class='am-icon-pencil-square-o'></span><a href='EditProfessor.jsp?ssn="+json[i].ssn+"\'>编辑</a></button><button class='am-btn am-btn-default am-btn-xs am-text-danger><span class='am-icon-trash-o'></span><a href='deletePersonServlet?ssn="+json[i].ssn+"\'>删除</a></button></div></div></td></tr>" ;
                 }
                 $("table").append(rows);
                 //alert(json);
                 
             } catch (e) {
                 alert("返回字符串不是json格式!");
                 return;
             }     
         }

     });
	 
	 //新增教师
	 $("#addProfessor").click(function(){
		  $.post("addProfessorServlet",{ssn:$('#ssn').val(),name:$('#name').val(),title:$('#title').val(),department:$('#department').val()},
			  function(data){
			   alert(data)
			   window.location.href="professorManage.jsp"  
			  });
	 });
	 //查询教师
	 $("#search").click(function(){
		  $.post("searchProfessorServlet",{title:$('#titleSearch').val(),department:$('#departmentSearch').val()},  
			  function(data){
			   var json = null;
	             try { 
	                 json = eval('(' + data + ')');
	                 $("table tr:gt(0)").remove();
	                 var rows="";
	                 for(var i in json){
	                	 rows =rows + "<tr>"+
	                	 "<td>"+json[i].ssn+"</td>"+
	                	 "<td>"+json[i].name+"</td>"+
	                	 "<td>"+json[i].title+"</td>"+
	                	 "<td>"+json[i].department+"</td>"+
	                	 "<td><div class='am-btn-toolbar'><div class='am-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary'><span class='am-icon-pencil-square-o'></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs am-text-danger><span class='am-icon-trash-o'></span><a href='deletePersonServlet?ssn="+json[i].ssn+"\'>删除</a></button></div></div></td></tr>" ;
	                 }
	                 $("table").append(rows);	                 
	             } catch (e) {
	                 alert("返回字符串不是json格式!");
	                 return;
	             }     
			  });
	 });
})

//获取参数   
function getUrlParam(name){  
    //构造一个含有目标参数的正则表达式对象  
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");  
    //匹配目标参数  
    var r = window.location.search.substr(1).match(reg);  
    //返回参数值  
    if (r!=null) return unescape(r[2]);  
    return null;  
}
$(document).ready(function(){
	var msg=getUrlParam('msg');
	if(msg==1){
		alert("删除成功");
	}else if(msg==2){
		alert("删除失败");
	}
     else{	}
}); 
</script>
</body>
</html>
