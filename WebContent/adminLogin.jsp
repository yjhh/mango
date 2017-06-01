<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.jlg.mango.entity.Admin"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
 	<%
	Cookie[] cookie = request.getCookies();
	String username = "";
	String pwd = "";
	if(cookie != null){
		for(Cookie cook:cookie){
			if(cook.getName().equalsIgnoreCase("userName")){
				username = URLDecoder.decode(cook.getValue(), "utf-8");
			}
			if(cook.getName().equalsIgnoreCase("password")){
				pwd = cook.getValue();
			}
		}
	}
	%>

	<meta charset="utf-8">
	<title>mango网后台管理系统</title>
	<!-- 样式表 -->
	<link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="css/charisma-app.css" rel="stylesheet">
	<link href='css/jquery.cleditor.css' rel='stylesheet'>
	<link href='css/jquery.noty.css' rel='stylesheet'>
	<link href='css/noty_theme_default.css' rel='stylesheet'>
	<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>


	<!-- The fav icon -->
	<link rel="shortcut icon" href="img/favicon.ico">
		
</head>

<body>
		<div class="container-fluid">
		<div class="row-fluid">
		
			<div class="row-fluid">
				<div class="span12 center login-header">
					<h2>欢迎使用mango网后台管理系统</h2>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div class="alert alert-info">
						<h3><%=session.getAttribute("Errorlog")==null?"请输入用户名和密码":session.getAttribute("Errorlog") %>
					</h3>
					</div>
					<form class="form-horizontal" action="adminLogin.html" method="post">
						<fieldset>
							<div class="input-prepend" title="Username" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large span10" name="username" id="username" type="text" value="<%=username %>"  placeholder="请输入用户名"  />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="Password" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span><input class="input-large span10" name="password" id="password" type="password" value="<%=pwd %>"  placeholder="请输入密码" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend">
							<label class="remember" for="remember"><input type="checkbox" id="remember" name="checkbox" />记住密码(七天免登录)</label>
							</div>
							<div class="clearfix"></div>

							<p class="center span5">
							<button type="submit" class="btn btn-primary">登录</button>
							</p>
						</fieldset>
					</form>
				</div><!--/span-->
			</div><!--/row-->
				</div><!--/fluid-row-->
		
	</div><!--/.fluid-container-->

</body>

<!-- <script type="text/javascript">

function getCookies(){
	var cookie = document.cookie.split(";");
	var name = document.getElementById("#username");
	var pwd = document.getElementById("#password");
	for(var i = 0;i<cookie.length;i++){
		var coo = cookie[i].split("=");
		alert(coo[0]=='userName')
		 if (coo[0] == 'userName'){
			alert(coo[0]);
		}  
	}
}


getCookies();
</script>
 --></html>
