<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户订单</title>
		<link href="css/userAddress.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
		<script type="text/javascript" src="js/menu_sucai.js"></script>
		<link href="css/menu_css.css" rel="stylesheet" type="text/css" />	
		<script type="text/javascript" src="js/pdl_user_order_manager/user_order.js"></script>
		
		<style type="text/css">
			.order_body a{
				text-decoration: none;
				color: #000000;
			}
			.order_body a:hover{
				color: #000000;
			}
			.order_body a:focus{
				color: #FF0077;
			}
			a:HOVER{
			cursor: pointer;
			text-decoration: none;
			}
			div#pagging {margin-left:220px;padding: 15px 20px;text-align: left;color: #ccc;}
			div#pagging a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
			div#pagging a:hover{text-decoration: none;border: 1px solid #428bca;}
			div#pagging span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
			div#pagging span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;
		</style>
	</head>
	<body>
	<jsp:include page="index_head.jsp" />
		<div id="body_wrap">
		
			<div id="body_main" >
				<div id="user_info_left">
					<div style="width: 100%;height: 160px;background:white;">
						<img src="/imgs/user/${User.icon }" style="width:150px;height:150px">
					</div>
					<div id="myInfoType">
						我的订单<br />
						<a href="userOrder.html">全部订单</a><br />
					</div><br />
						
				<!-- 	<div id="myInfoType">	
					我的钱包<br />
					</div><br /><br />
					<div id="myInfoType">
					我的理财
					</div><br /><br />
					<div id="myInfoType">
					优惠特权
					</div><br /><br /> -->
					<div id="myInfoType">
					帐号设置
					</div>
					<a href="userInfo.jsp">基本信息</a><br />
					
				</div>
				<form action="userInfo" method="post">
				<form action="" method="post">
					
				<!-- <div id="user_info_left">
					<div style="width: 100%;height: 202px;background: honeydew;">
						头像区域
					</div>
					  <div class="menu_list" id="secondpane">
    					js特效 菜单栏
					    <p class="menu_head">我的订单</p>
					    <div class="menu_body"> 
					    	<a href="javascript:void(0)">全部订单</a> 
					    	<a href="javascript:void(0)">待付款</a> 
					    	<a href="javascript:void(0)">待收货</a>
					    	<a href="javascript:void(0)">待评价</a>
					    	<a href="javascript:void(0)">退货退款</a></div>
					    	
					    <p class="menu_head">我的钱包</p>
					    <p class="menu_head">我的理财</p>
					  	 <p class="menu_head">优惠特权</p>
					    <div class="menu_body"> 
					    	<a href="javascript:void(0)">钻石会员</a> 
					    	<a href="javascript:void(0)">我的芒果</a> 
					    	<a href="javascript:void(0)">现金券</a> 
					    	<a href="javascript:void(0)">店铺优惠券</a> 
					    </div>
					    <p class="menu_head">地址管理</p>
					    <p class="menu_head">安全设置</p>
					    <div class="menu_body"> 
					    	<a href="javascript:void(0)">基本信息</a> 
					    	<a href="javascript:void(0)">修改头像</a></div>
					  </div>
				</div> -->
				
				<div id="user_info_right">
					<ul>
						<a href="javascript:void(0)"><li style="width: 331px;">商品</li></a>
						<a href="javascript:void(0)"><li style="width: 121px;">单价（元）</li></a>
						<a href="javascript:void(0)"><li style="width: 70px;">数量</li></a>
						<a href="javascript:void(0)"><li style="width: 121px;">售后</li></a>
						<a href="javascript:void(0)"><li style="width: 130px;">实付款（元）</li></a>
						<a href="javascript:void(0)"><li style="width: 130px;">交易状态</li></a>
						<a href="javascript:void(0)"><li style="width: 130px;">操作</li></a>
					</ul>
					<div id="order_body" style="width:1050px;">
					<ul class="order_body" style="margin-left:40px;width:980px;background-color:white;">
								
					</ul>
					</div>
					<!-- <img src="img/userOrder/userOrder_main.png" style="border:1px red solid"/> -->
					
				</div>
				
				</form>
			</div>
		</div>
		
		
	</body>
	<script type="text/javascript" src="js/jquery.pagination.js"></script>
</html>
<jsp:include page="index_foot.html" />

