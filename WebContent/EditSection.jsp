<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dao.*" %>
<%@ page import="sqliteDao.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.Map.Entry" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>编辑排课信息</title>
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
          <span class="am-icon-users"></span> <%=request.getSession().getAttribute("username")%><span class="am-icon-caret-down"></span>
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">排课信息</strong></div>
    </div>

    <hr/>

    <div class="am-g">

      <div class="am-u-sm-12 am-u-md-4 ">
      
        <form class="am-form am-form-horizontal">
        <%  SectionDao sectionDao=new SectionDaoImpl();
              Section s=sectionDao.findBySno(request.getParameter("no"));
        %>
         <div class="am-form-group">
            <label for="ssn" class="am-u-sm-3 am-form-label">排课编号</label>
            <div class="am-u-sm-9">
              <input type="text" id="sno" value="<%=s.getSectionNo()%>" readonly>
            </div>
          </div>
          <div class="am-form-group">
            <label for="name" class="am-u-sm-3 am-form-label">课程 </label>
            <div class="am-u-sm-9">
              <input type="text" id="course" value="<%=s.getRepresentedCourse().getCourseNo()%>" readonly >
            </div>
          </div>

          <div class="am-form-group">
            <label for="day" class="am-u-sm-3 am-form-label">上课时间</label>
            <div class="am-u-sm-9">
             <select id="day">
	                   <option value="星期一">星期一</option>
                       <option value="星期二">星期二</option>
                       <option value="星期三">星期三</option>
                       <option value="星期四">星期四</option>
                       <option value="星期五">星期五</option>
                    </select>
	               <select id="time">
	                   <option value="8:00-10:15">8:00-10:15</option>
		               <option value="10:15-12:00">10:15-12:00</option>
		               <option value="14:30-16:15">14:30-16:15</option>
	               </select>
            </div>
          </div>

           <div class="am-form-group">
            <label for="p" class="am-u-sm-3 am-form-label">任课老师</label>
             <div class="am-u-sm-9">
	               <select id="p">
	                  <%    PersonDao PersonDao=new PersonDaoImpl();
	                		Professor professor=new Professor();
	                		professor.setTitle("");
	                		professor.setDepartment("");
                            List ps=PersonDao.findProfessors(professor);
                       
                            Iterator i = ps.iterator();
                    		while(i.hasNext()){
                    			Professor p =(Professor)i.next();
                      %>
	                   <option value="<%=p.getSsn()%>"><%=p.getName()%></option>
                       <%} %>
                    </select>
                    </div>
          </div>
          <div class="am-form-group">
            <label for="seat" class="am-u-sm-3 am-form-label">容量</label>
            <div class="am-u-sm-9">
              <input type="text" id="seat" value="<%=s.getSeatingCapacity() %>">
            </div>
          </div>
          <div class="am-form-group">
            <label for="room" class="am-u-sm-3 am-form-label">上课教室</label>
            <div class="am-u-sm-9">
              <input type="text" id="room" value="<%=s.getRoom() %>">
            </div>
          </div>
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button type="button" class="am-btn am-btn-primary" id="save">保存修改</button>
            </div>
          </div>
        </form>
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
//修改信息
$("#save").click(function(){
	  $.post("updateSectionServlet",{sno:$('#sno').val(),day:$('#day').val(),time:$('#time').val(),p:$('#p').val(),seat:$('#seat').val(),room:$('#room').val(),},
		  
		function(data){
		   alert(data);
		   window.location.href="courseManage.jsp";  
		  });
});
</script>
</body>
</html>
