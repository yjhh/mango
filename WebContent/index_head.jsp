<%@page import="com.jlg.mango.entity.User"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>  
		<title>芒果网MANGO BAR</title>
		<!-- jquery包  -->
		<script src="js/jquery-3.1.0.js" type="text/javascript" charset="utf-8"></script>
		<!-- 网页头样式 -->
		<link rel="stylesheet" type="text/css" href="css/index_head.css"/>
		
		<!-- index 主体样式 -->
		<link rel="stylesheet" type="text/css" href="css/index1.css"/>
		<!-- 购物二级导航 css js-->
		<link rel="stylesheet" href="css/daohang.css" type="text/css">
		
		<script type="text/javascript" src="js/daohang.js"></script>
		<!--轮播 css-->
		<link rel="stylesheet" href="css/lunbo1.css">
		<style>
			.choice{
				background:url("img/index/choice.png") no-repeat right center;
			}
		</style>
		
		<!-- 购物车小红点显示数量 -->
		<c:if test="${ !empty sessionScope.User.id }" var="result" scope="page">
			<script>
				$(function(){
					//alert(${sessionScope.User.id});
					 $.ajax({
						url:"CartServlet.php",
						type:"post",
						data:{"choice":1,"user_id":"${sessionScope.User.id}"},
						dataType:"json",
						success:function(rs){
							$(".mm").text(rs.length);
							if($(".mm").text() != "0"){
								$(".mm").show();
							}
						}
					});
					/*  $.post("CartServlet.php",
					{"choice":1,"user_id":${sessionScope.User.id},function(rs){
						$(".mm").text(rs.length);
						if($(".mm").text() != "0"){
							$(".mm").show();
						}
					}
					},"json"); */
				}); 
			</script>
		</c:if>
		
		<style>
			.choice{
				background:url("img/index/choice.png") no-repeat right center;
			}
			
			.head-shopcart{
				position:relative;
			}
			.mm{
				width:15px;
				heigth:15px;
				border-radius:15px;
				border:1px solid #fff;
				font-size:10px;
				display:block;
				background:red;
				position:absolute;
				top:-3px;
				left:50px;
				color:#fff;
				
			}
		</style>
		
	</head>
	<body>
		
		<!--登录注册等 -->
		<div id="header_header">
			<div id="header_header_main">
				<img style="width: 25px;height: 25px;margin: 0 0 5px 2px;float: left;"src="img/index/head_home.png"/>
				<span style="display: inline-block;margin-top:6px;"><a href="index.jsp">Mango</a></span>
			<ul id="navigation"> 
			
			<%User user = (User)session.getAttribute("User");
				if(user == null){
			%>
			
				<li><a href="regin.jsp">注册</a></li>
				<li><a href="login.jsp">登录</a></li>
			<%}else{
			%>
				<li><a href="userInfo.jsp">
				<%=user.getLoginName()%>
				</a>
				</li>
				<li><a href="userOperator.html">退出</a></li>
				<li><a href="userAddress.jsp">收货信息</a></li>
				<%
			} %>
			<li>|</li>
			<li> 
				<div class="head-shopcart"><a href="CartServlet.php">购物车</a><div style="display:none;" class="mm">0</div></div>
			</li> 
		 <li><a href="userOrder.html">订单详情</a></li> 
			<li>|</li>
			
			<!--<li> 
			<a href="javascript:void(0)">客户服务</a> 
			</li> 
			<li>|</li> -->
			
			<li> 
			<a href="shop.html">我的小店</a> 
			</li> 
			</ul> 
			</div>
			
		</div>
		<!--页面容器-->
		<div id="header_mid_vessel">
			<div id="header_mid">
				<div id="header_logo">
					<a href="index.jsp"><img title="回到首页" src="img/index/headLogo1.png"></a>
				</div>
				
				<form action="searchGood.html" method="get">
					<div id="header_search">
						<div class = "choice" >搜商品</div>
						
						<div class="choice_list">
							<ul>
								<li>商品</li>
								<li>店铺</li>
							</ul>
						</div>
					
					<input type="text" name="search" placeholder=""  autocomplete="off" 
					class="search"/>
					<input type="text" id="text" hidden="hidden" name="type" value="1" />
					<button type="submit" id="btn" name="btn" title="搜索一下" 
					class="search_submit">搜索</button>
					
					<div class="list_search" >
						<ul>
							<!-- <li>11111</li>
							<li>22222</li>
							<li>33333</li>
							<li>44444</li>
							<li>55555</li> -->
						</ul>
					</div>
					<div id="header_hotwords">
					<!-- 	<ul>
							<li><a href="categroy_Search.html?parent_id=8" class="under-search">秋季连衣裙</a></li>
							<li><a href="categroy_Search.html?parent_id=7" class="under-search">小白鞋</a></li>
							<li><a href="categroy_Search.html?parent_id=8" class="under-search">长袖T恤</a></li>
							<li><a href="categroy_Search.html?parent_id=5" class="under-search">牛仔裤</a></li>
							<li><a href="categroy_Search.html?parent_id=8" class="under-search">卫衣</a></li>
							<li><a href="categroy_Search.html?parent_id=34" class="under-search">斜挎包</a></li>
							<li><a href="categroy_Search.html?parent_id=5" class="under-search">打底裤</a></li>
							<li><a href="categroy_Search.html?parent_id=7" class="under-search">短靴</a></li>

						</ul> -->
					</div>
				</div>
					
				</form>
				
			</div>
		</div>
		</body>
		</html>
