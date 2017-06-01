<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<jsp:include page="index_head.jsp" />
		<%
			String id = request.getParameter("goods_id");
			try{
				Integer.parseInt(id);
			}catch(Exception e){
				response.sendError(404);
				return;
			}
		%>
		
		
		<script src="js/jquery-3.1.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.1.4.2-min.js"></script>
		<script src="http://www.lanrenzhijia.com/ajaxjs/1.4.4/jquery-1.4.4.min.js"></script>
		<script>
			$(function() {
				
				if($(".mm").text() == "0"){
					$(".mm").hide();
				}
				
				var $store_id,$goods_name;
				
				/*var tmp = ${goods_id};
				alert(tmp+"ddd"); */
				
				//页面加载就加载
				$.ajax({
					url:"goodsInfo.php",
					data:{"goods_id":<%=request.getParameter("goods_id")%>},
					dataType:"json",
					type:"get",
					async:false,
					success:function(rs) {
						//商铺姓名
						$(".store_name_good").append("<a href='store.jsp?store_id="+rs.store.id+"'>"+rs.store.name+"</a>");
						
						//将商铺编号保存入全局变量
						$store_id = rs.goods.store_id;
						//alert($store_id);
						//将商品名存入全局变量
						$goods_name= rs.goods.name;
						//alert($goods_name);
						$(".img1").attr("src", "/imgs/"+rs.goods.list[0].url);
						$("#midimg").attr("src", "/imgs/"+rs.goods.list[0].url);
						$("#bigView img").attr("src", "/imgs/"+rs.goods.list[0].url);
						for(var i = 1;i < rs.goods.list.length;i++){
							$("#img").append("<li><img id='' class='img"+(i+1)+"' src = '/imgs/"+rs.goods.list[i].url+"' width='68' height='68'/></li>")
							
						} 
						//商品名称描述
						$(".good_title").html(""+rs.goods.describe+rs.goods.name);
						//商品价格
						$("#now_price em").html(rs.goods.price);
						//商品描述
						$(".describe").html(rs.goods.describe);
						
						//添加图片
						for(var i=0; i < rs.goods.list.length; i++){
							$(".pics").append($("<img width='520px' height='300px'>").attr("src", "/imgs/"+rs.goods.list[i].url));
						}
						
					}
				})
				<%-- $.getJSON("goodsInfo.php",{"goods_id":<%=request.getParameter("goods_id")%>}, function(rs) {
					//alert(rs.goods.name);
					
					//商铺姓名
					$(".store_name_good").html(rs.store.name);
					
					//将商铺编号保存入全局变量
					$store_id = rs.goods.store_id;
					//alert($store_id);
					//将商品名存入全局变量
					$goods_name= rs.goods.name;
					//alert($goods_name);
					$(".img1").attr("src", "/imgs/"+rs.goods.list[0].url);
					$("#midimg").attr("src", "/imgs/"+rs.goods.list[0].url);
					$("#bigView img").attr("src", "/imgs/"+rs.goods.list[0].url);
					for(var i = 1;i < rs.goods.list.length;i++){
						$("#img").append("<li><img id='' class='img"+(i+1)+"' src = '/imgs/"+rs.goods.list[i].url+"' width='68' height='68'/></li>")
						
					} 
					//商品图片
					
					/* $(".img1").attr("src", "/imgs/"+rs.goods.list[0].url);
					$(".img2").attr("src", "/imgs/"+rs.goods.list[0].url);
					$(".img3").attr("src", "/imgs/"+rs.goods.list[0].url);
					$(".img4").attr("src", "/imgs/"+rs.goods.list[0].url);
					$(".img5").attr("src", "/imgs/"+rs.goods.list[0].url);
					$(".img6").attr("src", "/imgs/"+rs.goods.list[0].url);
					$(".img7").attr("src", "/imgs/"+rs.goods.list[0].url); */
					
					
					//商品名称描述
					$(".good_title").html(""+rs.goods.describe+rs.goods.name);
					//商品价格
					$("#now_price em").html(rs.goods.price);
					//商品描述
					$(".describe").html(rs.goods.describe);
					
					//添加图片
					for(var i=0; i < rs.goods.list.length; i++){
						$(".pics").append($("<img>").attr("src", "/imgs/"+rs.goods.list[i].url));
					}
					
				}); --%>
				
				//点击加入购物车
				$(".add_to_cart").bind('click',function(){
					
					
					var tt = ${ empty sessionScope.User};
					//alert(tt);
					//alert(typeof tt);
					
					if(tt){
						$(this).attr("href","loginFirst.html");
						return;
					}
					
					var tmp = parseInt($(".mm").text());
					tmp += 1;
					
					$(".mm").text(tmp);
						
					if($(".mm").text() != "0" && $(".mm").css("display") == "none"){
							$(".mm").show();
					}
					
					//特效jQuery
					var img = $("#midimg");
					var flyElm = img.clone().css('opacity', 0.75);
					$('body').append(flyElm);
					flyElm.css({
						'z-index': 9000,
						'display': 'block',
						'position': 'absolute',
						'top': img.offset().top +'px',
						'left': img.offset().left +'px',
						'width': img.width() +'px',
						'height': img.height() +'px'
					});
					flyElm.animate({
						top: $('.head-shopcart').offset().top,
						left: $('.head-shopcart').offset().left,
						width: 20,
						height: 32
					}, 'slow', function() {
						flyElm.remove();
					});
					
					
					
					//传当前商品的id给后台Servlet
					$.get("AddToCart",{"goods_id":<%=request.getParameter("goods_id")%>,"store_id":$store_id,"goods_name":$goods_name},function(rs){
						
					});
					
				});
				
				
				
				
				
				
				
			});
			
			
		</script>
		
		
		
		
		<style type="text/css">
			*{
				margin: 0;
				padding:0;
			}
			
			.shop_head_{
				width:100%;
				border-top: 1px #DDDDDD solid;
			}
			
			.shop_head{
				width: 1200px;
				height: 71px;
				margin:0 auto;
				
				
			}
			
			/*第二排的背景图片*/
			div#shop_bg_img{
				width: 100%;
				border-top: 1px #DDDDDD solid;				
			}
			
			/*购买页底框*/
			div#body_wrap{
				width: 100%;
				word-break: break-all;
				word-wrap: break-word;
				/*border: lightyellow;*/
				/*	height: 800px;*/
				position:relative;
			}
			/*购买页大框 包括广告*/
			div#shop_detail{
				margin: 0 auto;
				width: 1200px;
				/*border: 1px red solid;*/
				word-break: break-all;
				word-wrap: break-word;	
			}
			/*购买页大框 */
			div#info_box{
				width: 1056px;
				height: 664px;
			/*	border: 1px deepskyblue solid;*/
				
				margin: 20px 0 0 0;	
			}
			



			/*套餐*/
			div#detail-content{
				width: 100%;
				height: 350px;
			/*	border: 1px royalblue solid;*/
				clear: both;
			}
			/*商品详情页 底框*/
			div#col-main{
				word-break: break-all;
				word-wrap: break-word;
				width: 100%;
				/*border: 1px red solid;*/
				/*height: 800px;*/
			}
			/*店铺名称评价页*/
			div#shop_about{
				width: 198px;
				height: 289px;
				border: 1px solid #E5E5E5;
				float: left;
			}
			
			/*详情页中间*/
			div#shop_panel{
				width: 738px;
				word-break: break-all;
				word-wrap: break-word;
				/*height: 500px;*/
				/*border: 1px solid red;*/
				float: left;
				margin-left:30px;
			}
			
			/*头像*/
			.top_img{
				width:40px;
				height:40px;
				border-radius:50%;
				margin-top:15px;
				margin-left:50px;
				float:left;
				}
			
			.top_messg{
				padding-top:15px;
				padding-left:100px;
				color:666666;
				font-size:13px;
			}

			/*店铺名称*/
			.top_ming{
				font-size:14px;
				width:70px;
				height:20px;
				}
			/*购买页的图片显示*/
			#shop_bg_img{
				width: 100%;
				background: black;
			}

			/*购买页图片下面的选项框*/
			div#shop_bg_img ul{
				width: 1000px;
				height: 25px;
				list-style: none;
				
				margin:0 auto;
				
			}
			/*购买页图片下面的选项框*/
			div#shop_bg_img li{
				width:108px;
				color: white;
				font-weight:bold;
				font-size:15px;
				height: 25px;
				line-height:25px;
				float: left;
				/*border-right-style:inset;*/
				/*border-right: 1px solid wheat;*/
				text-align:center;
			}
			.split{
				width:1px;
				height:25px;
				float: left;
				border-left:1px solid white;
			}

			/*商品信息框*/
			#goods_info{
				width: 500px;
				height: 45px;
				float: right;
				padding:8px 0 5px 5px;
				line-height:45px;
				
				margin-top:50px;
				}
			/*商品信息框里的span字体*/
			div#goods_info span{
			font: "微软雅黑";
			font-size: 16px;
			color: #333333;
			}
			/*显示价格和优惠的底框*/
			#show_goods_price{
				background:#FAFAFA;
				width: 500px;
				height: 96px;
				float: right;
				margin: 8px 0 0 5px;
			}
			/*原价*/
			#frist_price{
				color: #999999;
			}
			/*优惠以后的价格*/
			#now_price {
				color: red;
				font-size: 25px;
			}
			/*#promotion{
				
			}*/
			/*满减优惠*/
			#promotion{
				color: #999999;
				font-size: 15px;
			}
			/*销量 评价*/
			#pinjia{
				float: right;
				padding: 7px 20px 0 0;
			}
			/*店铺评分处的店铺名称框*/
			#body_shopname{
				width: 198px;
				height: 50px;
			}
			/*店铺评分处的店铺名称 字*/
			div#body_shopname span{
				font-size: 25px;
				padding: 30px 0 0 50px;
			}
			/*店铺评分处的评分 ul*/
			div#body_name ul{
				height: 50px;
				float: left;
				margin: 0;
				padding: 10px 15px 0 25px;
			}
			/*店铺评分处的评分 li*/
			div#body_name li{
				list-style-type: none;
				display: inline-block;
				font-size: 13px;
				border-right-style:ridge;
				border-right-color: #E5E5E5;
				padding: 0 3px 0 3px;
			}
			/*店铺评分处的评分  分*/
			p{
				color: red;
				margin: 0;
			}
			/*店铺评分处的收藏 进入按钮*/
			#body_scss{
				text-align: center;
				margin: 0 0 10px 0;
			}
			#Store_search{
				width: 100px;
				height: 20px;
				margin: 0 5px 0 8px;
			}
			#search{
				width: 198px;
				height: 50px;
				margin-top: 20px ;
				padding-top:20px ;
			}
			#body_dp_img{
				padding: 18px 10px 20px 20px;
			}
			div#shop_panel ul{
				border: 1px red solid;
				margin: 0;
			}
			div#shop_panel ul li{
				list-style-type: none;
				font-size: 23px;
				/*border: 1px solid black;*/
				width: 200px;
				height: 50px;
				float: left;
				
			}

			.pics{
				float:left;
			}

			.pics img{
				display:block;
				margin:20px auto;
				
			}

			/* 特效CSS */
			/* reset */
			ul,ol,li,dl{list-style-type:none;}
			em,i,dfn,cite,strong,small{font-style:normal;} 
			img{border:0;}
			fieldset,button,input,select,option{vertical-align:middle;font:12px/18px "宋体",arial,sans-serif;}
			table{border-collapse:collapse;border-spacing:0}
			textarea{resize:none}
			/* color */
			a:link,a:visited{color:#575757;text-decoration:none;}
			a:hover{color:#575757;text-decoration:none;}
			a:active{color:#1d7400;}



			.preview{width:400px; height:465px; margin:80px 0px 0px 100px;border:1px solid #ccc;}
			/* smallImg */
			.smallImg{position:relative; height:52px; margin-top:1px; background-color:#F1F0F0; padding:6px 5px; width:390px; overflow:hidden;float:left;}
			.scrollbutton{width:14px; height:50px; overflow:hidden; position:relative; float:left; cursor:pointer; }
			.scrollbutton.smallImgUp , .scrollbutton.smallImgUp.disabled{background:url(images/d_08.png) no-repeat;}
			.scrollbutton.smallImgDown , .scrollbutton.smallImgDown.disabled{background:url(images/d_09.png) no-repeat; margin-left:375px; margin-top:-50px;}

			#imageMenu {height:50px; width:360px; overflow:hidden; margin-left:0; float:left;}
			#imageMenu li {height:50px; width:60px; overflow:hidden; float:left; text-align:center;}
			#imageMenu li img{width:50px; height:50px;cursor:pointer;}
			#imageMenu li#onlickImg img, #imageMenu li:hover img{ width:44px; height:44px; border:3px solid #959595;}
			/* bigImg */
			.bigImg{position:relative; float:left; width:400px; height:400px; overflow:hidden;}
			.bigImg #midimg{width:400px; height:400px;}
			.bigImg #winSelector{width:235px; height:300px;}
			#winSelector{position:absolute; cursor:crosshair; filter:alpha(opacity=15); -moz-opacity:0.15; opacity:0.15; background-color:#000; border:1px solid #fff;}
			/* bigView */
			#bigView{position:absolute;border: 1px solid #959595; overflow: hidden; z-index:999;}
			#bigView img{position:absolute;}


			.store_name_good{
				display:block;
				margin-bottom:5px;
				font-size:14px;
			}
			
		</style>
	</head>
	<body>
		<!--
        	作者：offline
        	时间：2016-10-11
        	描述：店家信息头像、评分  start
        -->
        
       <%--  ${goods_id}<br>
         ${requestScope.goods_id}<br> --%>
        
        <div class="shop_head_"></div>
		<div class="shop_head">
        	<div class="dp_img">
				<img class="top_img" src="img/dp_touxiang.png">
				<div class="top_messg">
					<span class="store_name_good"></span>
					<span>描述:</span> <span style="color:red;">4.66</span> 
					<span>价格:</span> <span style="color:red;">4.66</span> 
					<span>质量:</span> <span style="color:red;">4.64</span> 
					<span>服务:</span> <span style="color:red;">4.64</span> 
				</div>
            </div>
            
        </div>
        <!--店家信息头像、评分  end-->
        <!--
        	作者：offline
        	时间：2016-10-11
        	描述：第二排广告图片 start
        -->
		<div id="shop_bg_img">
			<ul>
				<div class="split"></div>
				<li><a href="index.jsp">首页</a></li>
				<div class="split"></div>
				<li><a href="main_goods.jsp">全部商品</a></li><div class="split"></div>
				<li><a href="categroy_Search.html?parent_id=2">卧室家具</a></li><div class="split"></div>
				<li><a href="categroy_Search.html?parent_id=3">客厅家具</a></li><div class="split"></div>
				<li><a href="categroy_Search.html?parent_id=4">餐厅家具</a></li><div class="split"></div>
				<li><a href="categroy_Search.html?parent_id=15">书房家具</a></li><div class="split"></div>
				<li><a href="categroy_Search.html?parent_id=18">儿童家具</a></li><div class="split"></div>
			</ul>
		</div>
		<!--广告图片 end-->
        <!--
        	作者：offline
        	时间：2016-10-11
        	描述：购买页底框 start
        -->
		<div id="body_wrap">
			<!--购买页大框 包括广告 start-->
			<div id="shop_detail">
				<!--购买页大框 start-->
				<div id="info_box">
					<!--购买页的图片显示 start-->
					<div style="float:left;">
						
						<!-- 特效 -->
						<div class="preview">
							<!--bigImg start-->	
							<div id="vertical" class="bigImg">
								<img src="" width="400" height="400" alt="" id="midimg" />
								<div style="display:none;" id="winSelector"></div>
							</div><!--bigImg end-->	
							
							<div class="smallImg">
								<div class="scrollbutton smallImgUp disabled"></div>
								<div id="imageMenu">
									<ul id = "img">
										<li><img class="img1" id="onlickImg"  width="68" height="68"/></li>
									</ul>
								</div>
								<div class="scrollbutton smallImgDown"></div>
							</div><!--smallImg end-->	
							<div id="bigView" style="display:none;"><img width="800" height="800" alt="" src="" /></div>
						</div>






					</div>
					<!--购买页的图片显示 end-->
					
					<!--商品信息标题 start-->
					<div id="goods_info">
						<b><span class="good_title"></span></b>
					</div>
					<!--商品信息标题 end-->
					
					<!--商品信息价格 start-->
					<div id="show_goods_price">

						<span>价格：</span>
							<span id="frist_price" style="text-decoration: line-through;">￥18888.00</span><br />
						<span>促销价：</span>
							<span id="now_price" >&yen;<em><em></span>

						<span id="pinjia">评价：6   累计销量：394</span><br />
						<br>
						<span>店铺优惠：</span>
						<span id="promotion">购物满99元立减10元</span>

						<!-- <img src="img/fate1.png" style="margin-top:10px;" /> -->
						<br>
<!-- 						<a href="javascript:void(0);" title="立即购买" class="buy_now"><img src="img/buy_now.png" style="margin-top:10px;"/></a>
 -->						<a href="javascript:void(0);" title="加入购物车" class="add_to_cart"><img src="img/add_to_cart.png" style="margin-top:10px;"/></a>
						<img src="img/fate2.png" style="margin-top:10px;" />
						
					</div>
					<!--商品信息价格 end-->
				</div>
				<!--购买页大框 start-->
				<!--<div id="primary-slide">
					购买页右边的广告
				</div>-->
				<!--购买页右边的广告 end-->
				
				<!--套餐 start-->
				<!-- <div id="detail-content">
					<img src="img/detail-content.png" />
				</div> -->
				<!--套餐 end-->
				<!--商品详情页 底框 start-->
				<div id="col-main">
					<div id="shop_about">
						<img src="img/fate3.png" />
					</div>
					
					<!--店铺名称评价页 end-->
					<!--详情页 中间 start-->
					<div id="shop_panel">
	                    <ul>
							<li>商品详情</li>
							<li>累计评价</li>
							<li>本店相似商品</li>
						</ul>
						<div class="describe" style="font-size:16px;color:#99A3A6;float:left;"></div>
						
						<div class="pics">
							
						</div>
						
						<!-- <img src="img/goods_cpcs.png" /> -->
					</div>
					<!--详情页 中间 end-->
					
				</div>
				<!--商品详情页 底框  end-->
			</div>
			<!--购买页大框 包括广告  end-->
		</div>
		<!--购买页底框 end-->
		

<!--preview end-->
<script type="text/javascript">
$(document).ready(function(){
	// 图片上下滚动
	var count = $("#imageMenu li").length - 5; /* 显示 6 个 li标签内容 */
	var interval = $("#imageMenu li:first").width();
	var curIndex = 0;
	
	$('.scrollbutton').click(function(){
		if( $(this).hasClass('disabled') ) return false;
		
		if ($(this).hasClass('smallImgUp')) --curIndex;
		else ++curIndex;
		
		$('.scrollbutton').removeClass('disabled');
		if (curIndex == 0) $('.smallImgUp').addClass('disabled');
		if (curIndex == count-1) $('.smallImgDown').addClass('disabled');
		
		$("#imageMenu ul").stop(false, true).animate({"marginLeft" : -curIndex*interval + "px"}, 600);
	});	
	// 解决 ie6 select框 问题
	$.fn.decorateIframe = function(options) {
        if ($.browser.msie && $.browser.version < 7) {
            var opts = $.extend({}, $.fn.decorateIframe.defaults, options);
            $(this).each(function() {
                var $myThis = $(this);
                //创建一个IFRAME
                var divIframe = $("<iframe />");
                divIframe.attr("id", opts.iframeId);
                divIframe.css("position", "absolute");
                divIframe.css("display", "none");
                divIframe.css("display", "block");
                divIframe.css("z-index", opts.iframeZIndex);
                divIframe.css("border");
                divIframe.css("top", "0");
                divIframe.css("left", "0");
                if (opts.width == 0) {
                    divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");
                }
                if (opts.height == 0) {
                    divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");
                }
                divIframe.css("filter", "mask(color=#fff)");
                $myThis.append(divIframe);
            });
        }
    }
    $.fn.decorateIframe.defaults = {
        iframeId: "decorateIframe1",
        iframeZIndex: -1,
        width: 0,
        height: 0
    }
    //放大镜视窗
    $("#bigView").decorateIframe();
    //点击到中图
    var midChangeHandler = null;
	
    $("#imageMenu li img").bind("click", function(){
		/* if ($(this).attr("id") != "onlickImg") { */
		
			/* midChange($(this).attr("src").replace("small", "mid")); */
			midChange($(this).attr("src"));
			$("#imageMenu li").removeAttr("id");
			$(this).parent().attr("id", "onlickImg");
		/* } */
	}).bind("mouseover", function(){
		/* if ($(this).attr("id") != "onlickImg") { */
			window.clearTimeout(midChangeHandler);
			
			/* midChange($(this).attr("src").replace("small", "mid")); */
			midChange($(this).attr("src"));
			
			$(this).css({ "border": "3px solid #959595" });
		/* } */
	}).bind("mouseout", function(){
		/* if($(this).attr("id") != "onlickImg"){ */
			$(this).removeAttr("style");
			midChangeHandler = window.setTimeout(function(){
				/* midChange($("#onlickImg img").attr("src").replace("small", "mid")); */
				midChange($("#onlickImg img").attr("src"));
			}, 1000);
		/* } */
	});
    function midChange(src) {
        $("#midimg").attr("src", src).load(function() {
            changeViewImg();
        });
    }
    //大视窗看图
    function mouseover(e) {
        if ($("#winSelector").css("display") == "none") {
            $("#winSelector,#bigView").show();
        }
        $("#winSelector").css(fixedPosition(e));
        e.stopPropagation();
    }
    function mouseOut(e) {
        if ($("#winSelector").css("display") != "none") {
            $("#winSelector,#bigView").hide();
        }
        e.stopPropagation();
    }
    $("#midimg").mouseover(mouseover); //中图事件
    $("#midimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件

    var $divWidth = $("#winSelector").width(); //选择器宽度
    var $divHeight = $("#winSelector").height(); //选择器高度
    var $imgWidth = $("#midimg").width(); //中图宽度
    var $imgHeight = $("#midimg").height(); //中图高度
    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度

    function changeViewImg() {
        /*$("#bigView img").attr("src", $("#midimg").attr("src").replace("mid", "big"));*/
		 $("#bigView img").attr("src", $("#midimg").attr("src"));
    }
    changeViewImg();
    $("#bigView").scrollLeft(0).scrollTop(0);
    function fixedPosition(e) {
        if (e == null) {
            return;
        }
        var $imgLeft = $("#midimg").offset().left; //中图左边距
        var $imgTop = $("#midimg").offset().top; //中图上边距
        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
        X = X < 0 ? 0 : X;
        Y = Y < 0 ? 0 : Y;
        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;

        if ($viewImgWidth == null) {
            $viewImgWidth = $("#bigView img").outerWidth();
            $viewImgHeight = $("#bigView img").height();
            if ($viewImgWidth < 200 || $viewImgHeight < 200) {
                $viewImgWidth = $viewImgHeight = 800;
            }
            $height = $divHeight * $viewImgHeight / $imgHeight;
            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
            $("#bigView").height($height);
        }
        var scrollX = X * $viewImgWidth / $imgWidth;
        var scrollY = Y * $viewImgHeight / $imgHeight;
        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 });
        $("#bigView").css({ "top": 75, "left": $(".preview").offset().left + $(".preview").width() + 15 });

        return { left: X, top: Y };
    }
});
</script>
	<jsp:include page="index_foot.html" />
	</body>
</html>

