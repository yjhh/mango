<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>店铺订单页</title>
		<script src="js/jquery-3.1.0.js"></script>
		<!-- <script src="js/pdl_store_good_manager/store_add_good.js"></script> -->
		<!-- <link rel="stylesheet" type="text/css" href="css/good.css"> -->
		<style type="text/css">
		
			body{
				margin: 0 auto;
				width: 100%;
			}
			
			ul{
				list-style:none;
				margin:0 ;
				padding: 0;
			}
			
			a{
				text-decoration: none;
				color: #000000;
			}
			a:hover{
				color: #000000;
			}
			a:focus{
				color: #FF0077;
			}
			select{
				width: 180px;
			}
			
			
			div#main_vessel{
				width: 100%;
				height: 1310px;
				/* border: 1px red solid; */
				background:white;
			
			}
			/**
			 * 左边菜单栏
			 */
			div#left_block{
				float: left;
				margin-left:100px;
				width:200px;
				height:800px;
				/*border: 1px springgreen solid;*/
				background-color:#f9f9f9;
			}
			div#items{
				float: left;
				margin-left:10px;
				width:170px;
				height:670px;
				/*border: 1px springgreen solid;*/
			}
			/**
			 * 店铺名
			 */
			div#store_name{
				float: left;
				width:170px;
				height:170px;
				/*border: 1px black solid;*/
			}
			/**
			 * 宝贝管理
			 */
			div#store_goods{
				float: left;
				width:170px;
				height:190px;
				/*border: 1px black solid;*/
			}
			/**
			 * 订单管理
			 */
			div#store_orders{
				float: left;
				width:170px;
				height:200px;
				/*border: 1px black solid;*/
			}
			/**
			  * 店铺管理
			  */
			div#store_infos{
				float: left;
				width:170px;
				height:100px;
				/*border: 1px black solid;*/
			}
			div#main{
				width: 1200px;
				
				margin: 0 auto;
				height:1000px;
				/*background-color: #FF6AA2;*/
			}
			
			div#store_orders label{
				display:inline-block;
				margin-left:45px;
				font-size:15px;
				margin-top:10px;
			}
			
			div#store_goods label{
				display:inline-block;
				margin-left:45px;
				font-size:15px;
				margin-top:10px;
			}
			/**
			 * 右边菜单栏
			 */
			div#right_block{
				float: right;
				margin-right:96px;
				width:800px;
				/*background-color: #98FB98;*/
				/*border: 1px slateblue solid;*/
				/*text-align: center;*/
			}
			/**
			 * 最上方菜单栏
			 */
			table#order_items{
				width:800px;
				text-align:center;
				/*border:1px palevioletred solid;*/
				/*background-image: url(img/bg.PNG);*/
				
			}
			/**
			 * 主体
			 */
			/**
			 * 基本信息
			 */
			/* div#base_info{
				width:800px;
				height:900px;
				/*border:1px solid blue;*/
			} */
			div#shouhou{
				width:800px;
				height:135px;
				/*border:1px solid darkgreen;*/
			}
			div#toLeft{
				display: inline-block;
				float: left;
				width:290px;
				height:270px;
				/*border:1px solid darkgreen;*/
			}
			div#toRight{
				display: inline-block;
				float: right;
				width:290px;
				height:270px;
				/*border:1px solid darkgreen;*/
			}
			.color{
				margin-left: 50px;
				margin-top: 15px;
				font-size:12pt;
			}
			/* input[type=file][name=img], form.imgform {
				width: 100%;
				height: 100%;
				opacity: 0;
				cursor: pointer;
				position: absolute;
				z-index: 9999;
			}
			 */
			/* img.upimg {
				position: absolute;
				width: 100%;
				height: 100%;
				z-index: 980;
			} */
			
		</style>
	</head>
	<body>
	<jsp:include page="index_head.jsp" />
		
			
		<!--主体-->
		<div id="main_vessel">
			<div id="main">
				<!--
					左侧菜单栏
                -->
				<div id="left_block">
					<div id="items">
						<div id="store_name">
						<!-- 店铺头像 -->
						<img src="img/shop_head.png" 
						style="width:170px;height:170px;margin-left:5px;margin-top:5px;">
						</div>
						<div id="store_goods">
							<div>
								<label style="display:inline-block;margin-left:20px;font-size:20px;">宝贝管理</label>
							</div>
							<label ><a href="store_add_good.jsp">宝贝上架</a></label>
							<label ><a href="store_good_down.jsp">宝贝下架</a></label>
							<label><a href="store_good_del.jsp">删除宝贝</a></label>
							<label><a href="store_goods.jsp">宝贝查询</a></label>
							
						</div>
						<div id="store_orders">
							<div>
								<label style="display:inline-block;margin-left:20px;font-size:20px;">订单管理</label>
							</div>
							<label><a href="store_AllOrders.jsp">全部订单</a></label>
							<label><a href="store_order_waitfordo">未处理订单</a></label>
							<label><a href="store_order_needTui.jsp">申请退货订单</a></label>
							<label><a href="store_order_complete.jsp">已完成订单</a></label>
						</div>
						<div id="store_infos">
							<div>
								<label style="display:inline-block;margin-left:20px;font-size:20px;">店铺管理</label>
							</div>
							<div >
								<label style="display:inline-block;margin-left:45px;font-size:15px;margin-top:15px;"><a  href="javascript:void(0)" class="zhuxiao">申请注销</a></label>
							</div>
						</div>
					</div>
				</div>
				<!--
					右侧显示栏
					订单管理
                -->
				<div id="right_block">
					<div style="background-color:#f9f9f9;" >
							<table id="order_items" >
								<thead>
									<tr>
										<td ><a href="store_goods.jsp">全部宝贝</a></td>
										<td ><a href="store_good_down.jsp">宝贝下架</a></td>
										<td><a href="store_good_del.jsp">删除宝贝</a></td>
										<td><a href="store_goods.jsp">宝贝查询</a></td>
									</tr>
								</thead>
							</table>
					</div>
					<form id="form" method="post" enctype="multipart/form-data" action="addPics_ajax">
						<div style="width:800px;height: 30px;background-color:#f9f9f9;text-align: center;font-size: 18pt;margin-top:10px ;">
							<span>宝贝上架</span>
						</div>
						<div id="base_info">
							<!--信息-->
							<div style="margin-left:10px;margin-top: 8px;color: #FF6AA2;">
								1.宝贝基本信息
							</div>
							<div style="margin-left:10px;margin-top: 8px;">
<!-- 这个要存起来 -->
								<span style="color: red;">*</span><span>宝贝类型</span>
								<select name="category" class="category_id">
										<option value="" selected></option>
										<option value="5" >床</option>
										<option value="6">床垫</option>
										<option value="7">衣柜</option>
										<option value="8">沙发</option>
										<option value="9">茶几</option>
										<option value="10">电视柜</option>
										<option value="11">鞋柜</option>
										<option value="12">餐桌</option>
										<option value="13">餐椅</option>
										<option value="14">餐边柜</option>
										<option value="16">书桌</option>
										<option value="17">书柜</option>
										<option value="19">儿童床</option>
										<option value="20">儿童衣柜</option>
										<option value="21">儿童椅</option>
									</select>
							</div>
<!-- 商品名 -->					<div style="margin-left:10px;margin-top: 8px;">
<!-- 这个要存起来 -->
								<span style="color: red;">*</span><span>宝贝标题</span>
								<input type="text" name="goods_name" class="goods_name" >
							</div>
							 <div style="margin-left:10px;margin-top: 8px;">
							 
								<span>宝贝图片</span><span style="font-size:8pt;">(最多7张图片)</span>
								<div style="background-color:#F9F9F9;width: 600px;height:100px;margin-left:60px;">
										<input class="img" name="img" type="file" multiple="multiple"
											accept="image/jpg,image/jpeg,image/png"></input>
										<div id="show" style="width:600px;height:80px">
										</div>
								</div>
							</div> 
							<div style="margin-left:10px;margin-top: 8px;">
								<span style="color: red;">*</span><span>一口价及总库存</span>
								<div style="width: 400px;height:75px;margin-left:60px;">
									<table border="1" cellspacing="0" width="400px" height="50px" bordercolor="darkgray" >
											<tr style="text-align: center;background-color:#F9F9F9 ;">
<!-- 商品的价格	 -->							<td >
<!--这个要存起来-->
													<span style="color: red;">*</span> 
													<span >价格（元）</span>
												</td>
<!-- 商品的库存 -->								<td >
<!--这个要存起来-->
													<span style="color: red;">*</span> 
													<span>总数量</span>
												</td>
<!-- 店铺id	 -->								<td >
<!--这个要存起来-->
													<span style="color: red;">*</span> 
													<span>商家编号</span>
												</td>
											</tr>
											<tr  >
												<td contenteditable="true"height="20px" class="price">
													<!-- <span name="price" ></span> -->
												</td>
												<td contenteditable="true"class="stock">
													<!-- <span ></span> -->
												</td>
												<td class="store_id">
													${store_id}
												</td>
											</tr>
									</table>
								</div>
							</div>
							
							
						</div>
						<div id="for_cars">
							<!--物流服务-->
							<div style="margin-left:10px;margin-top: 8px;color: #FF6AA2;">
								2.宝贝物流服务
							</div>
							<div style="margin-left:10px;margin-top: 8px;">
								<span style="color: red;">*</span><span>提取方式</span>
								<input type="checkbox"> 使用物流配送
							</div>
							<div style="margin-left:60px;margin-top: 8px; width:600px;background-color: #F9F9F9;">
								<span style="font-family:'黑体';">物流设置</span>
								<div style="margin-top: 8px;">
									<span style="color: red;font-size: 12pt;">*</span><span>运费模板</span>
									<select name="yunfei" style="width: 180px;height:20px;">
												<option value="" selected=""></option>
												<option value="">满8888包邮</option>
												<option value="">只发顺丰</option>
									</select>
								</div>
							</div>
						</div>
						<div id="shouhou">
							<div style="margin-left:10px;margin-top: 8px;color: #FF6AA2;">
								3.售后保障信息
							</div>
							<div style="margin-left:60px;margin-top: 8px;width: 600px;height: 80px;">
								<div style="float:left;display:inline-block;width: 90px;height: 80px;">
									<div style="margin-left: 10px;margin-top:6px ;">售后服务</div>
								</div>
								<div style="margin-top: 8px;display:inline-block;width:200px;height:80px;">
									<input type="checkbox"/>提供发票<br>
									<input type="checkbox"/>保修服务<br>
									<input type="checkbox"/>七天无理由退货<br>
									<input type="checkbox"/>48小时内发货
								</div>
							</div>
							
						</div>
						<div style="width:800px;height:50px;">
							<button type="button" id="add" style="width:200px ;height:30px;font-size: 15pt;
							border-radius: 5px;margin-left: 125px;margin-top:10px;background-image: url(img/bg.PNG);">
							</button>
						</div>
					</form>		
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				$(".img").change(function(){
					$("#show").empty();
					if (!/^image\//.test(this.files[0].type)) {
						alert('选择文件非图片!');
						return 0;
					}
					
					
					for(var i=0;i<this.files.length;i++){
						var url = window.URL.createObjectURL(this.files[i]);
						$("#show").append("<img src='"+url+"' style='margin-left:5px;width:80px;height:80px'/>");
					}
					
				});
				
				$(".zhuxiao").click(function(){
					var a = confirm("确定注销？");
					if(a){
						$.get("store_editgoodsajax",{"type":3},function(rs){
							alert(rs=="true"?"申请注销店铺成功":"申请注销店铺失败");
						if(rs=="true"){
							$("#order_id").empty();
							$("#pagging").empty();
							$("#pagging").append("<span>您已申请注销店铺成功，请等待1~2个工作日查看结果</span>");
							}
						})
					}
				});
				
			 	$("#add").click(function(){
					/* var describe=$(".pingpai").val()+$(".season").val()+$(".banxing").val()+$(".baohou").val()+$(".fengge").val()+
					$(".lingzi").val()+$(".xiuxing").val()+$(".tuan").val()+$(".caizhi").val()+$(".goods_name").val()
 */
					
					  $.post("store_addgoodajax",
									{"name":$(".goods_name").val(),
								   "price":$(".price").text(),
									"stock":$(".stock").text(),
									"describe":$(".goods_name").val(),
									"s_id":$(".store_id").text(),
									"category_id":$(".category_id").val()
									},
									
							function(rs){
								/* alert(rs) */
								alert(rs=="true"?"上架成功":"上架失败");
								if(rs=="true"){
									$("#form").submit();
								}
							});
				
					
				});
				 //alert("123");
			});

		</script>
		<!--尾部-->
		<jsp:include page="index_foot.html" />
	</body>
</html>
		