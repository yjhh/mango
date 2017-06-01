<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="index_head.jsp"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商家注册页面</title>
		
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			div#reg_shop_head span{
				font-size: 30px;
				color: black;
				font-family: "微软雅黑";
				display: inline-block;
				float: left;
			}
			div#reg_shop_head ul{
				height: 50px;
				/*border: 1px red solid;*/
			}
			div#reg_shop_head ul li{
				float: left;
				margin-right:20px;
				margin-top: 16px;
				margin-left: 20px;
				list-style-type: none;
			}
			/*头 链接样式*/
			div#reg_shop_head a:link{
				color: black;
				font-weight: bold;
			}
			div#reg_shop_head a:hover{
				color: red;
				text-decoration: underline;
			}
			div#reg_shop_head a:visited{
				color: #9E1E1F;
			}
			div#reg_shop_main_vessel{
				width: 100%;
				height: 500px;
				background: honeydew;
			}
			div#reg_shop_main{
				margin: 0 auto;
				/* width: 1200px; */
				word-break: break-all;
				word-wrap: break-word;
				padding:50px 35%;
			}
			div#reg_shop_main label{
				width: 100px;
				display: inline-block;
				font-size: 16px;
				float: left;
				/*border: 1px red solid;*/
				margin-top: 20px;
				margin-right: 5px;
			}

			div#reg_shop_main input[type=text]{
				width: 200px;
				height: 25px;
				margin-top: 20px;
				font-size: 16px;
				outline: none;
				padding-left: 3px ;
			}
			div#reg_shop_main input[type=submit]{
				width: 115px;
				height: 32px;
				/*border: none;*/
				border: 1px #FAFAFA solid;
				border-radius: 2px;
				background: #FFAB15;
				color: white;
				font-weight: bold;
				box-shadow:1px 1px 3px black;
				cursor: pointer;
				margin-left: 150px;
				
			}
			div#reg_shop_main_info {
				margin-top: 50px;
				margin-left:150px;
				border: 1px red solid;
				word-break: break-all;
				word-wrap: break-word;				
			}
			div#reg_shop_main_info span{
				color: #999;
				font-size: 14px;
				font-family: '幼圆';
			}
		</style>

		<!--描述：js包-->
		<script src="js/jquery-3.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
			$('#set_shop').click(function(){
				$("#shop_name_span").text("* 必须填写");
				$("#shop_address_span").text("* 必须填写");
				$("#email_span").text("* 必须填写");
				$("#telNum_span").text("* 必须填写");
				$("#shop_info_span").text("* 必须填写");
				var shop_name=$("#shop_name").val();
				var shop_address=$("#shop_address").val();
				var email=$("#email").val();
				var telNum=$("#telNum").val();
				var shop_info=$("#shop_info").val();
				
				/*判断是否为空*/
				if(shop_name==""){
					$("#shop_name_span").text("* 不能为空").css("color","red");
					return false;
				}else if(shop_address==""){
					$("#shop_address_span").text("* 不能为空").css("color","red");
					return false;
				}else if(email==""){
					$("#email_span").text("* 不能为空").css("color","red");
					return false;
				}else if(telNum==""){
					$("#telNum_span").text("* 不能为空").css("color","red");
					return false;
				}else if(shop_info==""){
					$("#shop_info_span").text("* 不能为空").css("color","red");
					return false;
				}
				
				/*电话号码*/
				var reg=/^1[3578]\d{9}/;
				var tel=document.getElementById("telNum").value;
				var tel_span=document.getElementById("telNum_span");
				if(!reg.test(tel)){
					tel_span.innerHTML="* 手机号码输入有误！";
					tel_span.style.color="red";
					return false;
				}else{
					tel_span.innerHTML="* 正确！";
					tel_span.style.color="green";
				}
              });
              
			})
			
		</script>
		
		
		
			<script type="text/javascript">
				$(function(){
					<%
					String reg_shop_type = request.getParameter("reg_shop_type");
					if("1".equals(reg_shop_type)){%>
					alert("申请成功！等待管理员审核...");
					window.location.href="index.jsp"; 
					<%	
					}else{
						if(reg_shop_type != null){
						%>
						alert("申请失败！请检查登陆状态和注册信息...");
						<%
					}
					}
				%>
				})
			</script>
		
	</head>
	<body>
		<!-- <div id="reg_shop_head">
			<ul>
				<span>小店</span><span>|后台管理</span>
				<a href="#"><li>入住市场</li></a>
				<a href="#"><li>商家社区</li></a>
				<a href="#"><li>蘑菇商学院</li></a>
				<a href="#"><li>服务中心</li></a>
				<a href="#"><li>规则中心</li></a>
				<a href="#"><li>通知中心</li></a>
			</ul>
		</div> -->
		
		<div id="reg_shop_main_vessel">
			
				<div id="reg_shop_main">
					
					<form action="reg_shop.html" method="post">
						<!-- <div id="reg_shop_main_info"> -->
						<label>店铺名称：</label>
						<input type="text" name="shop_name" id="shop_name"/>
						<span id="shop_name_span">* 必须填写</span>
						<br />
						<label>实体店地址：</label>
							<input type="text" name="shop_address" id="shop_address"/>
							<span id="shop_address_span">* 必须填写</span>
							<br />
						<label>邮箱地址：</label>
							<input type="text" name="email" id="email"/>
							<span id="email_span">* 必须填写</span>
							<br />
						<label>手机号：</label>
							<input type="text" name="telNum" id="telNum"/>
							<span id="telNum_span">* 必须填写</span>
							<br />
						<label>店铺描述：</label>
							<input type="text" name="shop_info" id="shop_info"/>
							<span id="shop_info_span">* 必须填写</span>
							<br /><br />
						<!--描述：默认给商铺 状态0
								往后台传当前user_id -->
						<input type="submit" id="set_shop" value="开启小店"/>
						<!-- </div> -->
					</form>
					
				</div>
		</div>
		
	</body>
</html>
<jsp:include page="login_foot.html" />
