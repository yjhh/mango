<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>www.MANGO.com</title>
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
		<!-- 网页头样式 -->
		<link rel="stylesheet" type="text/css" href="css/index_head.css"/>
		
		<script src="js/jquery-3.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/login.js" type="text/javascript" charset="utf-8"></script>
		
		<%
		    String n = request.getParameter("n");
		if(n != null && !"".equals(n)){
			if(n.equals("0")){
				%>
		<script type="text/javascript">
			alert("帐号或密码错误!")
		</script>
		<%
			}else if(n.equals("1")){
				%>
				<script type="text/javascript">
					alert("该账户已被冻结")
		</script>
				<%
			}
		}
		%>
	
		
	</head>
		
	<body>
		<!--登录注册等 -->
		<div id="header_header">
			<div id="header_header_main">
				<img style="width: 25px;height: 25px;margin: 0 0 5px 2px;float: left;"src="img/index/head_home.png"/>
				<span style="display: inline-block;margin-top:6px;"><a href="index.jsp">Mango</a></span>
			<ul id="navigation"> 
				<li><a href="regin.jsp">注册</a></li>
				<li><a href="login.jsp">登录</a></li>
				</li>
			<li>|</li>
			<li> 
				<div class="head-shopcart"><a href="CartServlet.php">购物车</a><div style="display:none;" class="mm">0</div></div>
			</li> 
		 <li><a href="userOrder.html">订单详情</a></li> 
			<li>|</li>
			<li> 
			<a href="shop.html">我的小店</a> 
			</li> 
			</ul> 
			</div>
		</div>
	
		
		<!--logo标题等-->
		<!--已删除-->
		<!--主体-->
		<div id="main" style="padding-top:50px;">
			<div id="main_left">
			<img src="img/login/main_left1.png" style="width:500px;height:340px"/>
			</div>
			<div id="main_right">
				<h4>用户登录</h4>
				<hr />
				<div id="main_right_login">
					<form action="login.html" method="post">
						<input class="search_box" type="text" name="userName" id="userName" style="color: #999999;font-size: 14px;padding: 0 0 0 3px;"
							 value="昵称/手机号" /><br /><br />
						<input class="search_box" type="text" name="password"id="password" style="color: #999999;font-size: 14px;padding: 0 0 0 3px;"
							value="密码" /><br /><br />
						<input type="checkbox" checked="checked" value="" />
						<label>2周内自动登录</label>
						<label style="display:inline-block;text-align: right;float: right;"><a href="#">忘记密码？</a></label><br /><br />
						<input class="search_btn" type="submit"title="登录" value="登录"/><br /><br />
						<label style="display:block;text-align: center;"><a href="regin.jsp">新用户注册</a></label><br />
						<!-- <label>你也可以用以下方式登录：</label><br />
						<label>QQ</label><br />
						<label>微信</label><br />
						<label>微博</label><br />
						<label>一个都不可以用！</label> -->
					</form>
				</div>
			</div>
		</div>
		 <jsp:include page="login_foot.html" /> 
