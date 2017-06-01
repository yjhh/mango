<%@page import="com.jlg.mango.entity.Admin"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<%
//判断登录
  Admin admin = (Admin)session.getAttribute("loginAdmin");
if(admin == null){
	response.sendRedirect("adminLogin.jsp");
	return;
} 
%>
<head>
	<meta content="text/html; charset=utf-8">
	<title>Mango网管理系统</title>
	<!-- The styles -->
	<link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
		*{ margin:0; padding:0; list-style:none;}
		a{ text-decoration:none;}
		a:hover{ text-decoration:none;}
		#page_div{padding: 15px 20px;text-align: left;color: #ccc;text-align: center}
		#page_div a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
		#page_div a:hover{text-decoration: none;border: 1px solid #428bca;}
		#page_div span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
		#page_div span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}
	
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	  
	</style>
	<link href="css/charisma-app.css" rel="stylesheet">
	<link href="css/ajax.css" rel="stylesheet">


	<!-- The fav icon -->
	<link rel="shortcut icon" href="img/favicon.ico">
		
</head>

<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="manager.jsp"> <img alt="Charisma Logo" src="img/login/login_logo.png" /> <span>Admin</span></a>
				
				<!-- theme selector starts -->
				<div class="btn-group pull-right theme-container" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
						<i class="icon-tint"></i><span class="hidden-phone"> 修改主题</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="themes">
						<li><a data-value="classic" href="#"><i class="icon-blank"></i> Classic</a></li>
						<li><a data-value="cerulean" href="#"><i class="icon-blank"></i> Cerulean</a></li>
						<li><a data-value="cyborg" href="#"><i class="icon-blank"></i> Cyborg</a></li>
						<li><a data-value="redy" href="#"><i class="icon-blank"></i> Redy</a></li>
						<li><a data-value="simplex" href="#"><i class="icon-blank"></i> Simplex</a></li>
						<li><a data-value="slate" href="#"><i class="icon-blank"></i> Slate</a></li>
						<li><a data-value="spacelab" href="#"><i class="icon-blank"></i> Spacelab</a></li>
						<li><a data-value="united" href="#"><i class="icon-blank"></i> United</a></li>
					</ul>
				</div>
				
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> <%=admin.getName()%></span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li class="divider"></li>
						<li><a href="adminLogin.html">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">警告！</h4>
					<p>你必须启用<a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
			

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="manager.jsp">首页</a> 
					</li>
				</ul>
			</div>
			<div class="sortable row-fluid">
				<a data-rel="tooltip" class="well span3 top-block"  id="user_a">
					<div class="div">
					<span class="icon32 icon-red icon-user"></span>
					<div>用户管理</div>
					<div>--</div>
					</div>
				</a>

				<a data-rel="tooltip" class="well span3 top-block"  id="store_a">
					<div class="div">
					<span class="icon32 icon-color icon-star-on"></span>
					<div>店铺管理</div>
					<div>--</div>
					</div>
				</a>

				<a data-rel="tooltip" class="well span3 top-block"  id="order_a">
					<div class="div">
					<span class="icon32 icon-color icon-cart"></span>
					<div>订单管理</div>
					<div>--</div>
					</div>
				</a>
				
				<a data-rel="tooltip" class="well span3 top-block"  id="categroy_a">
					<div class="div">
					<span class="icon32 icon-color icon-envelope-closed"></span>
					<div>类别查询</div>
					<div>--</div>
					</div>
				</a>
			</div>
					</div>
				</div><!--/span-->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
				<div id="content_div" style="width:85%;height:100%;margin:5px 40px;padding-top:2px;">
 					
					<label id="form">
						<div id="div_body"><input type="text" name="name" id="text" value="" /><span>查询</span>
					</div>
					</label>
				</div>
				<div id="page_div">
					
				</div>
				
		<hr>

		<footer>
			<p class="pull-left">&copy; Manago 2016</p>
			<p class="pull-right">Powered by: MangoTeam</p>
		</footer>
		
	</div><!--/.fluid-container-->


	<!-- jQuery -->
	 <script src="js/jquery-1.7.2.min.js"></script>
	
	
	<!-- custom dropdown library -->
	<script src="js/bootstrap-dropdown.js"></script>
	
	<script type="text/javascript" src="js/jquery-3.1.0.js" ></script>
	
	<!-- library for cookie management -->
	<script src="js/jquery.cookie.js"></script>
	

	<!-- application script for Charisma demo -->
	<script src="js/charisma.js"></script>
	
	<!-- ajax效果 -->
	
	<script src="js/ajax.js"></script>
	
	<!-- 按钮ajax效果 -->
	<script src="js/button_ajax.js"></script>
	
	<script src="js1/jquery.pagination.min.js"></script>
 	
</body>
</html>