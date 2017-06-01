<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%
			String n = request.getParameter("n");
			if(n!=null){
		%>                       
		<meta http-equiv="refresh"/>
		<%
		}
		%>
		<title>用户地址</title>
		<script type="text/javascript"  src="js/jquery-3.1.0.js"></script>
		<script type="text/javascript"  src="js/menu_sucai.js"></script>
		<link href="css/menu_css.css" rel="stylesheet" type="text/css" />
		<link href="css/userAddress1.css" rel="stylesheet" type="text/css" />
			
			<style type="text/css">
				div#user_info_address_addinfo{
					display: none;
				}
				div#user_info_address_addinfo label{
					 float: left; 
				}
				
				div#useAddress{
					width:100%;
					/* border: 1px red solid; */
				/* 	word-break: break-all;
					word-wrap: break-word; */
				}
				div#useAddress ul{
					list-style-type: none;
					clear:both;
				}
				div#useAddress ul li{
					font-size:16px;
					width:100px;
					/* border:1px red solid;  */
					padding: 0px;
					float: left;
					margin-right: 100px;
				}
			</style>
	</head>
	<body>
	<jsp:include page="index_head.jsp" />
		<div id="body_wrap">
			<div id="body_main">
				
<!-- 				<div id="user_info_left">
					<div style="width: 100%;height: 202px;background: honeydew;">
						头像区域
					</div>
					  <div class="menu_list" id="secondpane">
    					js特效 菜单栏
					    <p class="menu_head">我的订单</p>
					    <div class="menu_body"> 
					    	<a href="#">全部订单</a> 
					    	<a href="#">待付款</a> 
					    	<a href="#">待收货</a>
					    	<a href="#">待评价</a>
					    	<a href="#">退货退款</a></div>
					    <p class="menu_head">我的钱包</p>
					    <p class="menu_head">我的理财</p>
					  	 <p class="menu_head">优惠特权</p>
					    <div class="menu_body"> 
					    	<a href="#">钻石会员</a> 
					    	<a href="#">我的芒果</a> 
					    	<a href="#">现金券</a> 
					    	<a href="#">店铺优惠券</a> 
					    </div>
					    <p class="menu_head">地址管理</p>
					    <p class="menu_head">安全设置</p>
					    <div class="menu_body"> 
					    	<a href="#">基本信息</a> 
					    	<a href="#">修改头像</a></div>
					  </div>
				</div>
		 -->		
		 <div id="user_info_left">
					<div style="width: 100%;height: 160px;background:white;">
						<img src="img/user/default_head.jpg" style="width:150px;height:150px">
					</div>
					<div id="myInfoType">
						我的订单<br />
						<a href="userOrder.html">全部订单</a><br />
					</div><br />
					<div id="myInfoType">
					帐号设置
					</div>
					<a href="userInfo.jsp">基本信息</a><br />
				</div>
				
				<div id="user_info_right">
					<br />
					<form action="useraddress" method="post">
					<h2>&nbsp;&nbsp;地址管理</h2><br /><br />
					
					<hr style="color: #DFDFDF;" />
					<div id="user_info_address">
						<div id="useAddress">
							<ul style="font-weight: bold;font-size:20px;">
							<li>姓名</li>
							<li>地址</li>
							<li>电话</li>
							<li>删除</li>
							</ul></br>
						</div></br>
						
						<input type="button" name="btn_add" id="btn_add" value="+添加新地址"/></br></br>
						
						<div id="user_info_address_addinfo">
							<label>街道地址：</label>
								<textarea name="address" rows="5px" cols="75px"></textarea><br /><br />
								<label style="width: 400px;">请填写街道地址，最少5个字，最多不能超过100个字</label><br /><br />
							<label>收货人姓名：</label>
								<input type="text" name="conName" id="conName" value="" /><br /><br />
							<label>手机：</label>
								<input type="text" name="phone" maxlength="11" id="phone" value="" /><br /><br />
							<input type="submit" name="btn_true" id="btn_true" value="确认地址" style="margin: 0 0 0 20px; cursor: pointer;"/>
							<input type="button" name="btn_false" id="btn_false" value="取消"  style="background: white;color: black;cursor: pointer;"/><br /><br />
						</div>
					</div>
					</form>
				</div>
				
			</div>
		</div>
	</body>
</html>
	<script type="text/javascript">
				$(function(){
					$('#btn_add').click(function(){
						$("#user_info_address_addinfo").css("display","block");
		              });
					$('#btn_false').click(function(){
			            	$("#user_info_address_addinfo").css("display","none");
			          })
				})
				
				
				$.ajax({
				type:"get",
				url:"useraddress",
				dataType:"json",
				async:true,
				success:function(rs){
					for(var i=0;i<rs.length;i++){ 
						$("#useAddress").append("<ul><li>"+rs[i].name+
													"</li><li>"+rs[i].address+
													"</li><li>"+rs[i].telNum+
													"</li><li><a href='delAddress.jsp?id="+rs[i].id+"'>删除</a></li></ul></br>");
					}
				}
				});
				
			</script>
<jsp:include page="login_foot.html" />

