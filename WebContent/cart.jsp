<%@page import="com.jlg.mango.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%
			User user = (User)session.getAttribute("User");
		 if(user == null){
				response.sendRedirect("index.jsp");
			}	 
		%>

<!DOCTYPE html>
<html lang="en">
<head>
	<!--HTML4.01-->
	<!-- <meta htt-equiv='Content-Type' content='text/html'  charset="UTF-8"> -->
	<!--HTML5-->
    <meta charset="UTF-8">
	<meta name='viewport' content="width=device-width,initial-scale=1,user-scalable=no" />
	<title> 我的购物车 </title>
	<link href="css/screen.css" media="screen, projection" rel="stylesheet" type="text/css" />
	<script src="js/jquery-3.1.0.min.js"></script>
	<!-- 网页头样式 -->
	<link rel="stylesheet" type="text/css" href="css/index_head.css"/>
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
		
		<!-- 购物车小红点显示数量 -->
		<c:if test="${ !empty sessionScope.User.id }" var="result" scope="page">
			<script>
				$(function(){
					//alert(${sessionScope.User.id});
					$.post("CartServlet.php",{"choice":1,"user_id":${sessionScope.User.id}},function(rs){
						
						$(".mm").text(rs.length);
						if($(".mm").text() != "0"){
							$(".mm").show();
						}
						
					},"json");
				});
			</script>
		</c:if>
		
	
	
</head>
<body>

	<%-- ${sessionScope.User.id} --%>

			<!--登录注册等 -->
		<div id="header_header">
			<div id="header_header_main">
				<img style="width: 25px;height: 25px;margin: 0 0 5px 2px;float: left;"src="img/index/head_home.png"/>
				<span style="display: inline-block;margin-top:6px;"><a href="index.jsp">Mango</a></span>
			<ul id="navigation"> 
			
			
				<li><a href="userInfo.jsp">
				 <%=user.getLoginName()%> 
				</a>
				</li>
				<li><a href="userOperator.html">退出</a></li>
				<li><a href="userAddress.jsp">收货信息</a></li>
			
			<li>|</li>
			<li> 
				<div class="head-shopcart"><a href="CartServlet.php">购物车</a><div style="display:none;" class="mm">0</div></div>
			</li> 
		 <li><a href="userOrder.html">订单详情</a></li> 
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

	<div class='main'>
		
		<div class="container">
		
			<!--  收索框，需要将网页头导入后，给使用到js事件
			<div class="header">
				<a href="#"></a>
				<div class="header-search">
					<input type="text" placeholder="请输入内容" class='header-search-input' autocomplete="off">
					<button type='submit'>搜 索</button>
					<div class="list">
					</div>
				</div>
			</div>
			-->
			
			<div class="content">
				<div class="tbBar">
					<ul class = 'switch-cart'>
					
						<!-- switch-cart-0 鼠标离开时，默认为这个被选中 -->
						<!-- selectColumn 鼠标进入当前被选择的商品项 -->
						<!-- 共三个选项 0 1 2  -->
						<li class='btn-switch-cart switch-cart-0 selectColumn'> 
							<a href="javascript:;" class='btn-switch-href '>
								<em>全部商品</em>
								<span class='number'></span> <!-- 购物车商品数量 -->
								<span class='pipe'></span> <!-- span 做分割竖线 -->
							</a>
						</li>
						<li class='btn-switch-cart switch-cart-1'>
							<a href="javascript:;" class="btn-switch-href btn-switch-color">
								<em>降价商品</em>
								<span class="number">0</span>
								<span class="pipe"></span>
							</a>
						</li>
						<li class='btn-switch-cart switch-cart-2'>
							<a href="javascript:;" class="btn-switch-href btn-switch-color">
								<em>库存紧张</em>
								<span class="number">0</span>
								<span class="pipe pipe-display"></span> <!-- 最后一个，需要隐藏 -->
							</a>
						</li>
					</ul>
					<!-- 顶部结算 -->
					<div class="cart-sum">
						<span>已选商品（不含运费）</span>
						<strong class='price'>￥
							<span class='total-symbol'>0.00</span>
						</strong>
						<a href="#" class="submit-btn btn-common">结算</a>
					</div>
					<div class="wrap-line"></div>
				</div>
				<!-- 商品项 -->
				<div class="tbMain">
					<!-- 顶部栏位 -->
					<div class="commodityColumn">
						<div class="th-chk">
							<div id="selectAll" class="selectAll ">
								<input type="checkbox" name="selectAllChckbox" id='selectAllChckbox' class='allSelected1' autocomplete="off">
								<label for="selectAllChckbox">全选</label>
							</div>
						</div>
						<div class="th-inner">
							<div class="commodityMsg">
								<div>商品信息</div>
							</div>
						</div>
						<div class="th-space">
							<div class="td-inner">&nbsp;</div>
						</div>
						<div class="th-price">
							<div class="td-inner">单价</div>
						</div>
						<div class="th-amount">
							<div class="td-inner">数量</div>
						</div>
						<div class="th-sum">
							<div class="td-inner">金额</div>
						</div>
						<div class="th-operation">
							<div class="td-inner">操作</div>
						</div>
					</div>
					<div class='commodityContainer'>
					</div>
				</div>
			</div>
		
<!-- width: 990px;
height: 50px;
overflow: hidden;
position: fixed;
bottom: 0;
z-index: 1;
background-color: #E5E5E5; 
-->
			
			<!-- 悬浮的下单页 -->
			<div id="place_an_order">
				<div class="cart_top1">
					<span class="menu_order">下单页</span>
					<a href="javascript:;"><img src="images/icons/icon_cart1.png" /></a>
				</div>
				<div class="cart_items">
					<!--<div class="tr_item">
						<div class="itme_goods_pic"><img src="images/0931224551.jpg" style="width:56px;height:56px;""/></div>
						<div class="item_goods_name">2016秋季新款，蓝色秋款女式牛仔裤</div>
						<div class="item_goods_attr">L 黑色</div>
						<div class="item_goods_num">1</div>
						<div class="item_goods_sumPrice">小计:￥<span>230.0</span></div>
					 -->
					
				</div>
				
				<div class="line_items">
				</div>
				<!-- 收货 -->
				<div class="receiveInfos">
					<div class="top_receiveInfo">收货信息：</div>
					<div class="choice_receiveInfo">
						<span>选择已有：</span>
						<select name="choice">
						</select>
					</div>
					<div class="add_receiveInfo">
						<label><span class="noe">收货人：</span><input type="text" name="name" class="recName" /></label> 
						<label><span>收货地址：</span><input type="text" name="name" class="recAddress" /></label> 
						<label><span>收货电话：</span><input type="text" name="name" class="recTelNum" /></label> 
					</div>
				</div>
				
				
				
				<div class="footer_porder">
					<div class="menu_order">总价：<span style="color:red;font-weight:bold;font-size:14px;"></span></div>
					<a href="javascript:;">
						<span class="btn_css">下 单</span>
					</a>
				</div>
			</div>
			
			<div class="footer">
				<div class="all-selected">
					<input type="checkbox" name='all-selected' id='all-selected' class='allSelected2' autocomplete="off">
					<label for="all-selected">全选</label>
				</div>
				<div class="operation">
					<!-- <a href="#" class='delete-all'>删除</a>
					<a href="#">移入收藏夹</a>
					<a href="#">分享</a> -->
				</div>
				<div class="float-bar-right">
					<div class="amount-sum">
						<span>已选商品</span>
						<em class='totalSum'>0</em>
						<span>件</span>
					</div>
					<div class="price-sum">
						<span>合计（不含运费）：</span>
						<span class='moneySym'>￥</span>
						<em class='total-sum'>0.00</em>
					</div>
					<div class="btn-area">
						<a href="javascript:void(0)" class='btn-common' id='btn-sum'>结 算</a>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script src='js/jquery-2.2.1.min.js'></script>
	<script src='js/template.js'></script>
	
	<script src='js/myOrder.js'></script>
	<script>
		var rec;  //存放ajax获取的收货信息 
		var default_reci; //默认的收货地址的下标
		$(function(){    //1表示查询添加  2表示删除单个
			
			$.post("CartServlet.php",{"choice":1,"user_id":"${sessionScope.User.id}"},function(rs){
				
				$(".switch-cart-0 .btn-switch-href .number").text(rs.length);
				
				for(var i = 0; i < rs.length; i++){
					
					var str1 = "<input type='checkbox' name='shopMsg'  class='shopMsg-input' autocomplete='off' >";
					var str2 = "<input type='checkbox' name='checkbox' autocomplete='off'>";
					
					var num_tmp = parseInt(rs[i].cart.goods_num);
					var tt = rs[i].goods.price*rs[i].cart.goods_num;
					var sum_tip = parseFloat(tt);
					var stock_cart = parseInt(rs[i].goods.stock);
						
					if(rs[i].goods.stock <num_tmp || rs[i].goods.stock <= 0){
						//alert("您收藏的："+rs[i].goods.describe+rs[i].goods.name+" 宝贝已过期！");
						num_tmp = 0;
						sum_tip = 0.0;
						str1 = "";
						str2 = "<span style='color:red;'>已<br>过<br>期!</span>";
						stock_cart = 0;
					}
					
					var tmp = "";
					
					tmp += "<div class='mainCommodity'>"
							+ "<div class='shopInfo'>"
									+ "<div class='shopMsg'>"
											+ "<label>"+str1+""
											+ "店铺：</label>"
											+ "<a href='#'>"+rs[i].store.name+"<span hidden>"+rs[i].store.id+"</span></a>"
										+ "</div>"
								+ "</div>"
								+ "<div class='commodityInfo'>"
										+ "<ul>"
											+ "<li class='td-chk'>"
												+ "<div class='td-inner'>"
														+ ""+str2+""
												+ "</div>"
											+ "</li>"
											+ "<li class='td-item'>"
												+ "<div class='td-inner'>"
														+ "<a href='#' class='boyShoes'><img src='/imgs/"+rs[i].goods.list[0].url+"' alt='"+rs[i].goods.id+"' />"
														+ "</a>"
														+ "<div class='item-info'>"
																+ "<div class='item-basis-info'>"
																		+ "<a href='#'>"+rs[i].goods.name+"<span hidden>"+rs[i].goods.id+"</span></a>"
																+ "</div>"
																+ "<div class='item-other-info'>"
																	+ "<div class='item-other-space'></div>"
																	+ "<div class='item-other-list'>"
																			+ "<a href='#' title='支持信用卡支付'>"
																					+ "<div class='bandCard'></div>"
																			+ "</a>"
																			+ "<a href='#' class='sevenDay' title='7天无理由'>"
																					+ "<div class='sevenDay'></div>"
																			+ "</a>"
																			+ "<a href='#' title='消费者保障服务'>"
																					+ "<div class='guarantee'></div>"
																			+ "</a>"
																	+ "</div>"
														+ "</div>"
								+ "</div>"
							+ "</div>"
						+ "</li>"
						+ "<li class='td-info'>"
							+ "<div class='td-info-msg'>"
									+ "<p>颜色分类：玫瑰色</p>"
									+ "<p>尺码：标准</p>"
							+ "</div>"
						+ "</li>"
						+ "<li class='td-price'>"
							+ "<div class='td-inner'>"
								+ "<p class='non-discount'>￥10000.00</p>"
									+ "<p class='discount'>￥<span>"+rs[i].goods.price+"</span></p>"
									+ "<div class='promotion'>"
										+ "卖家促销"
										+ "<i class='promotionIcon'></i>"
									+ "</div>"
									+ "<div class='proSlidedown'>"
											+ "<p class='newPro'>卖家促销：新品大特价</p>"
											+ "<p>优惠："+rs[i].goods.price+"</p>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
							+ "<li class='td-amount'>"
								+ "<div class='item-amount'>" //更新商品数量
										+ "<a href='#' class='amount-left amount-color'>-</a>"
										+ "<input type='text' name='amountNum' value='"+num_tmp+"' autocomplete='off'>"
										+ "<a href='#' class='amount-right'>+</a>"
								+ "</div>"  //如果库存为小于等于0 说明商品已下架或售空  -- 需要做操作 ！！！  -- bug
								+ "<div class='stock'>"+stock_cart+"</div>"
								+ "<div class='outNum'>"
										+ "<span class='instr'>最多只能购买</span>"
										+ "<span class='stockNum'>"+stock_cart+"</span>"
										+ "<em>件</em>"
								+ "</div>"
							+ "</li>"
							+ "<li class='td-sum'>"
								+ "<em>￥</em>"
								+ "<span>"+sum_tip+"</span>"
							+ "</li>"
							+ "<li class='td-operation'>"
								+ "<p><a href='#' class='delete'>删除<span hidden>"+rs[i].cart.id+"</span></a></p>"
							+ "</li>"
						+ "</ul>"
					+ "</div>"
				+ "</div>";
					
					
					$(".commodityContainer").append(tmp);
					
					
					
				}
				
				//删除商品
				var thisInfo;
				var previous;
				var next;
					
				$('body').on('click','.delete',function(event){
						var choice = confirm("确定删除！");
						
						if(choice){
							var $this = $(event.target);
							       
							//获取购物项编号 cart编号
							//alert($this.parents('.mainCommodity').find(".delete span").text());
							//调用ajax
							$.post("CartServlet.php",{"choice":2,"cart_id":$this.parents('.mainCommodity').find(".delete span").text()},function(rs){
								alert(rs=="true"?"删除成功！":"删除成功！");
							});
							
							thisInfo = $this.parents('.mainCommodity');
							thisInfo.detach();
						}else{
							return false;
						}
				});
			},"json");
		
			//获取该用户的收货地址  -- 存入js
			$.post("CartServlet.php",{"choice":3,"user_id":"${sessionScope.User.id}"},function(rs){
				
				//alert(rs);
				if(!$.isEmptyObject(rs)){
					rec = rs;
					
					//将第一个地址编号设置为默认
					default_reci = 0;
				}else{
					default_reci = -1;  //表示该用户无收货地址
				}
				
			},"json");
			
			
			
			
			//点击结算按钮
			$(".btn-common").click(function(){
				var style = $(this).css("cursor");//not-allowed
				
				var checkeds = false;
				
				$('.td-inner input').each(function(index, el) {
					if ($(this).prop('checked')) {
						checkeds = true;
					}
				});
				
				if(style != "not-allowed" && checkeds && $("#place_an_order").css("display") == "none"){
					
					//显示下单页
					$("#place_an_order").show();
					
					if(default_reci != -1){
						
						//将当前的收货信息填入input
						$(".recName").val(rec[default_reci].name);
						$(".recAddress").val(rec[default_reci].address);
						$(".recTelNum").val(rec[default_reci].telNum); 
					}
					
					//结算  -- 让下单页显示  -- 传数据 -- 和结构
					//alert($(this).parents("body").find(".total-symbol").text());
					$(".menu_order span").text($(this).parents("body").find(".total-symbol").text());
					
					//循环遍历每个checked，获取其值  -- 生成元素
					$('.td-inner input').each(function(index, el) {
						if ($(this).prop('checked')) {
							for (var i = 0; i < $(this).length; i++) {
								var thisInfo = $(this).parents('.mainCommodity');
								
								var tmp1 = "";
								 tmp1 += "<div class='tr_item'>"
								 			+"<div class='itme_goods_pic'>"
								 				+"<img src='"+thisInfo.find(".boyShoes img").attr("src")+"' style='width:56px;height:56px;''/></div>"
							 				+"<div class='item_goods_name'>"+thisInfo.find(".item-basis-info a").text().split("<")[0]+"</div>"
							 				+"<div class='item_goods_attr'>L 黑色</div>"
							 				+"<div class='item_goods_num'>"+thisInfo.find(".item-amount input").val()+" 件</div>"
							 				+"<div class='item_goods_sumPrice'>小计:￥<span>"+thisInfo.find(".td-sum span").text()+"</span></div>"
								 		+"</div>";
								 
								 
								$(".cart_items").append(tmp1);
							}
						}
					});
					
					//根据用户编号传其所有收货地址
					var tmp2 = "";
					
					for(var j = 0; j < rec.length; j++){
						tmp2 += "<span hidden>"+j+"</span><option>"+rec[j].address+"</option>";
					}
					
					//先清空
					$(".choice_receiveInfo select").empty();
					
					$(".choice_receiveInfo select").append(tmp2);
					
					$(".choice_receiveInfo select").change(function(){
						//得到地址下标
						//alert($(":selected").prev().text());
						
						//更新保存的默认地址编号default_reci
						default_reci = parseInt($(":selected").prev().text());
						
						//往下面的input中添加对应记录
						if(default_reci != -1){
							//将当前的收货信息填入input
							$(".recName").val(rec[default_reci].name);
							$(".recAddress").val(rec[default_reci].address);
							$(".recTelNum").val(rec[default_reci].telNum); 
						}
					});
					
				}else{
					return false;
				}
				
			});
		
			/*  */

			//鼠标悬浮在关闭下单页上 X
			$(".cart_top1 a img").mouseover(function(){
				$(this).attr("src","images/icons/icon_cart2.png");
			}).click(function(){
				$("#place_an_order").hide();
				$(".cart_items").empty();
			});
			$(".cart_top1 a img").mouseout(function(){
				$(this).attr("src","images/icons/icon_cart1.png");
			});
			
			/* 22 */
			//点击下单按钮
			
		$(".btn_css").click(function(){
			//判断非空（收货地址）
			if($(".recName").val() == "" || $(".recAddress").val() == "" || $(".recTelNum").val() == ""){
				alert("为了您能准确无误的获得宝贝，请您完善你的收货信息！");
				return false;
			}
				
			//获取购物项数量
			var count = 0;
			
			//获取店铺编号
			var store_ids = new Array();
			
			$('.td-inner input').each(function(index, el) {
				if ($(this).prop('checked')) {
					count++;
					
					for (var i = 0; i < $(this).length; i++) {
						var thisInfo = $(this).parents('.mainCommodity');
						
						//alert("chage"+thisInfo.find(".shopMsg a span").text());
						//获取店铺编号
						store_ids.push(thisInfo.find(".shopMsg a span").text());
					}
					
				}
			});
			
			
			//JS数组去重
			var tmp4 = store_ids.sort(); 
			var re = [store_ids[0]]; 
			for(var i = 1; i < store_ids.length; i++) { 
				if( store_ids[i] !== re[re.length-1]) { 
					re.push(store_ids[i]); 
				}
			}
			
			store_ids = re;
			
			var length = store_ids.length;
			
			
			/* 定义存放 */
			//定义一个数组  -- 对应长度和store_ids 一样， 因为一个store_id对应一个订单信息。
			var orders = [ ];
			
			//定义一个数组  -- 对应长度和store_ids 一样， 因为一个store_id对应一个总价。
			var sum_price_order = new Array(store_ids.length);
			for(var pp = 0; pp < sum_price_order.length; pp++){
				sum_price_order[pp] = 0.0;
			}
			
			
			//定义一个数组  -- 对应长度和store_ids 一样， 因为一个store_id对应一个商品数组。
			var goods = new Array(length);
			for(var mm = 0; mm < goods.length; mm++){
				goods[mm] = [ ];
			}
			
			
			//遍历获取不同编号的商品数组（里面存放json格式）
			$('.td-inner input').each(function(index, el) {
				if ($(this).prop('checked')) {
					
					//遍历所选商品项
					for (var i = 0,l = $(this).length; i < l; i++) {
						var thisInfo = $(this).parents('.mainCommodity');
						
						//遍历store_ids
						for(var s = 0, ll = store_ids.length; s < ll; s++){
							//alert("333");
						
							//当店铺编号在store_ids里面时
							if(thisInfo.find(".shopMsg a span").text() == store_ids[s]){
								//alert("444");
								
								var temp1 = thisInfo.find(".item-basis-info a span").text();
								var temp2 = parseFloat(thisInfo.find(".td-sum span").text())/parseInt(thisInfo.find(".item-amount input").val());
								var temp3 = thisInfo.find(".item-amount input").val();
								
								var good = { };
								
								good.goods_id = temp1;
								good.goods_price = temp2;
								good.goods_num = temp3;
								
								//alert("555");
								console.log(good);
								
								//放入预先定义的数组
								goods[s].push(good);
								
								var tmp = sum_price_order[s]+parseFloat(thisInfo.find(".td-sum span").text());
								sum_price_order[s] = tmp;
								
								
							}
							
						}
					
					}
				
				}
			});
			
			
			//组装数据    
			for(var b = 0, lll = store_ids.length; b < lll; b++){
				
				var ord = {
						store_id: store_ids[b],
						totalPrice: sum_price_order[b],
						receive_name: $(".recName").val(),
						full_address: $(".recAddress").val(),
						mobile: $(".recTelNum").val(),
						goods: goods[b]
					};
				orders.push(ord);
					
			}
			
			//数据库删除
			$.post("OrderServlet.php",{"order":JSON.stringify(orders),"user_id":"${sessionScope.User.id}"},function(rs){
				//alert(typeof rs);   // string
				if(rs == "true"){
					
					//遍历删除
					$('.td-inner input').each(function(index, el) {
						if ($(this).prop('checked')) {
							for (var i = 0; i < $(this).length; i++) {
								var thisInfo = $(this).parents('.mainCommodity');
								
								//获取购物项编号 cart编号
								//alert(thisInfo.find(".delete span").text());
								//调用ajax
								$.post("CartServlet.php",{"choice":2,"cart_id":thisInfo.find(".delete span").text()},function(rs){
									//alert(rs=="true"?"删除成功！":"删除成功！");
								});
								
								thisInfo.detach();
							}
						}
					});
					
					//下单页删除
					$("#place_an_order").hide();
					$(".cart_items").empty();
					
					//更新小红点
					var curRedPoint = $(".mm").text();
					
					$(".mm").text(curRedPoint - count);
					
					if($(".mm").text() == "0"){
						$(".mm").hide();
					}
					
					alert("下单成功！");
					
				}else{
					alert("下单失败！请重新尝试！");
				}
			});
				
			});
		
		});
		
	</script>
</body>
</html>