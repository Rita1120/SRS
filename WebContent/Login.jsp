<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>登录</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
</head>
<body>
<div class="header"> 
  <hr />
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <h2>登录</h2>
    <hr>

    <form class="am-form">
      <label for="email">学号/教师号</label>
      <input type="text" name="username" id="username" value="">
      <br>
      <label for="password">密码</label>
      <input type="password" name="password" id="password" value="">
      <br />
      <input type="radio" class="am-radio-inline" name="type" value="2">学生
      <input type="radio" class="am-radio-inline" name="type" value="3">教师
      <input type="radio" class="am-radio-inline" name="type" value="1">管理员
      <br><br>
      <div class="am-cf">
        <input type="button" name="login" id="login" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
      </div>
    <hr>
    </form>
  </div>
</div>
<script src="assets/js/jquery1.11.1.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="assets/js/app.js"></script>
<script type="text/javascript">
$("#login").click(function(){

    if ($("#username").val() == "" || $("#password").val() == "") {
        alert('请将信息填写完整');
    }
    var type=$('input:radio[name="type"]:checked').val();
    if(type==null){
        alert("请选择身份");
        return false;
    }
    if($("#username").val() != ""){
    	   $.post(
  			    "loginServlet",
  			     {username:$("#username").val().trim(),password:$("#password").val().trim(),type:type},
  			    function(data) {
  			    	alert(data); 
  			    if(data=="登录成功"){
  			    	if(type==2){
  			    	window.location.href="studentCourseManage.jsp"
  			    	}
  			    	if(type==3){
  			    		window.location.href="professorCheck.jsp"
  			    	}
  			    	if(type==1){
  			    		window.location.href="courseManage.jsp"
  			    	}
  			    
  			    }
  			    else{
  			    	window.location.href="Login.jsp"
  			    }
    	    });
    }
})
</script>
</body>
</html>