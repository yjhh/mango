<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>www.MANGO.com</title>
		<!-- 直接用的是登录页的样式 -->
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
		<script src="js/jquery-3.1.0.js" type="text/javascript" charset="utf-8"></script>
		<!-- 网页头样式 -->
		<link rel="stylesheet" type="text/css" href="css/index_head.css"/>
		<!--注册框内 相关js 简单的验证等 -->
		<script type="text/javascript">
					$(function(){
		<%
			String flat = request.getParameter("n");
			if(flat!=null){
				if("1".equals(flat)){
		%>
						alert("注册成功！");
						window.location.replace("login.jsp");
		<%}else{%>
					alert("注册失败！");		
					window.location.replace("regin.jsp");
			<%
		}
			}
		%>
		
			})
		</script> 	
		
		<style type="text/css">
			div#main label {
				width: 60px;
				font-size: 15px;
				/*border: 1px red solid;*/
				display: inline-block;
				text-align: right;
			}
			
			div#main input {
				outline: none;
			}
			
			div#main_right span {
				/*border: 1px red solid;*/
				margin: 0 0 0 80px;
			}
			
			div#otherthing {
				border: 1px red solid;
				width: 90%;
				height: 80px;
				margin: 0 auto;
			}
			
			div#main_right h4 {
				text-align: left;
				padding: 0 0 0 30px;
			}
			/*登录按钮*/
			.button_regin {
				cursor: pointer;
				background: none;
				border: burlywood 1px solid;
				font-size: 14px;
				width: 80px;
				height: 27px;
				display: inline-block;
			}
		</style>
	</head>
	<!--加载验证码-->
	<body onload="createcode()">
	<body>
				<!--登录注册等 -->
		<div id="header_header">
			<div id="header_header_main">
				<img style="width: 25px;height: 25px;margin: 0 0 5px 2px;float: left;"src="img/index/head_home.png"/>
				<span style="display: inline-block;margin-top:6px;"><a href="index.jsp">Mango</a></span>
			<ul id="navigation"> 
				<li><a href="regin.jsp">注册</a></li>
				<li><a href="login.jsp">登录</a></li>
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
				<h4>新用户注册</h4>
				<hr />
				<div id="main_right_login">

					<form action="user_reg.html" method="post">
					<label>手机号:</label>
						<input type="text" name="tel" id="tel" maxlength=11 onblur="telConfirm()" placeholder="请输入手机号" style="height: 20px;width: 70%;"/><br />
						<span id="tel_span"></span><br /><br />
					<label>密码:</label>
						<input type="password" name="pwd" id="pwd" maxlength=16 placeholder="请输入密码" style="height: 20px;width: 70%;"/><br />
						<span id="pwd_span"></span><br /><br />
					<!--验证码-->
					<label>验证码:</label>
					<input type="text" name="code" id="code_input" onblur="validate()"
						style="height: 20px;width: 80px;" placeholder="验证码" />


					<input type="button" id="code" onclick="createcode()" class="button_regin"/><br />
					<span id="code_span"></span><br /><br />
					<input class="search_btn" type="submit" value="注册" id="submit" />

					<br /><br />&nbsp;
					
					<input type="checkbox" checked="checked"/>
					<span style="font-size: 10px;margin: -5px;color: #999;font-weight:normal;padding: 10px 0 0 0 ;">
						我已阅读并且同意
					<a href="#">《mango网络服务使用协议》</a>
					</span>
					<br /><br />
					<span style="float: right;font-weight: normal;font-size:14px">
						<a href="login.jsp">已有帐号</a>
					</span>
					</form>
				</div>
			</div>
		</div>



		<script type="text/javascript" src="js/code.js"></script> 
		<script type="text/javascript" src="js/confirm.js"></script> 
		<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script> 
		<!--脚-->
		<jsp:include page="login_foot.html" />


