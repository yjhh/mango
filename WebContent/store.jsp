<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:include page="index_head.jsp" />
<!-- 用户id -->
	<%
	if(request.getParameter("store_id")==null || request.getParameter("store_id").equals("")){
		response.sendError(404);
	}
	%>	
		<script src="js/jquery-3.1.0.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/main_goods.css"/>
		<style type="text/css">
			*{
				margin: 0;
				padding:0;
			}
			#main_hot_goods{
			padding-left:70px;
			}
			
			.shop_head_{
				width:100%;
				border-top: 1px #DDDDDD solid;
			}
			
			.shop_head{
				width: 1200px;
				height: 70px;
				margin:0 auto;
				
				
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
				height: 25px;
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

			.store_name_good{
				display:block;
				margin-bottom:5px;
				font-size:14px;
			}
			a{
				color:grey;
			}
		</style>
	</head>
	<body>
		<!--
        	作者：offline
        	时间：2016-10-11
        	描述：店家信息头像、评分  start
        -->
        <div class="shop_head_"></div>
		<div class="shop_head">
        	<div class="dp_img">
				<img class="top_img" src="img/dp_touxiang.png">
				<div class="top_messg">
					<span class="store_name_good"></span>
					<!-- <span>描述:</span> <span style="color:red;">4.66</span> 
					<span>价格:</span> <span style="color:red;">4.66</span> 
					<span>质量:</span> <span style="color:red;">4.64</span> 
					<span>服务:</span> <span style="color:red;">4.64</span>  -->
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
		<div id="main_hot_goods">
		
		</div>
	<script type="text/javascript">
	
	$(function(){
			//定义全局数组
			var str = new Array();
			//全局计数
			var j = 10;
			var k = 3;
			$.ajax({
				url:"storeServlet",
				type:"POST",
				data:{'id': <%=request.getParameter("store_id")%>},
				dataType:"JSON",
				async : false,
				success:function(rs){
					
					//去需要的值付给数组
					for(var i = 0;i < rs.length;i++ ){
						 str[i] = {id:rs[i].id,name:rs[i].name,price:rs[i].price,describe:rs[i].describe,src:rs[i].list[0].url};
					}
					/* 判断是否有商品 */
					if(str.length == 0){
						/*如果没有商品  */
						$("#main_hot_goods").append("<b>还没有商品，去其他店铺逛逛吧</b>");
						$("#main_hot_goods b").css({
							"color":"grey",
							"opacity":"0.5",
							"display":"block",
							"text-align":"center",
							"font-size":"25px"
						});
						$("#main_hot_goods").css("padding","100px")
					}else{
						//如果有商品
						 for(var i = 0;i<(10>str.length?str.length:10);i++){
			    			 $("#main_hot_goods").append("<div class='main_hot_goods_thing'>" +
			    	 		"<a href='good.jsp?goods_id="+str[i]["id"]+"'><img width='220px' height='320px' src="+str[i]["src"]+" /></a>" +
			    	 		"<p title="+str[i]["describe"]+">"+str[i]["name"]+"</p>" +
			    	 		"<p><img src='img/index/main_hot_goods/rm.png' style='width: 30px;height: 18px;'/>" +
			    	 		"</p><div class='main_hot_goods_info'><label>￥"+str[i]["price"]+"</label>" +
			    	 		"</div></div>");
			    		 }
						 
					};
					/*判断结束  */	
			
		}
			
			});
		
		function loadata()
			 { 
				 //定义一个总的高度变量
				 var totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());     //浏览器的高度加上滚动条的高度 
//				 alert("$('#main_hot_goods').outerHeight()="+$("#main_hot_goods").outerHeight());
		     if (($("#main_hot_goods").outerHeight()) <= totalheight) {    //当文档的高度小于或者等于总的高度的时候，开始动态加载数据

		    	 for(;j<(k*5>str.length?str.length:k*5);j++){
		    		 $("#main_hot_goods").append("<div class='main_hot_goods_thing'>" +
				    	 		"<a href='good.jsp?goods_id="+str[j]["id"]+"'><img src="+str[j]["src"]+" /></a>" +
				    	 		"<p title="+str[j]["describe"]+">"+str[j]["name"]+"</p>" +
				    	 		"<p><img src='img/index/main_hot_goods/rm.png' style='width: 30px;height: 18px;'/>" +
				    	 		"</p><div class='main_hot_goods_info'><label>￥"+str[j]["price"]+"</label>" +
				    	 		"<span style='font: '微软雅黑';font-size: 14px;font-weight: bold;display: inline-block;float: right;'>4400↑</span></div></div>");
		    			 }; 
		    	 k++;
		    		 };
		     
			 };

			 
			 //滚动条滚动时执行
		 $(window).scroll( function() { 
		     loadata();
		 }); 
		
		})
		

	</script>
	<jsp:include page="index_foot.html" />
	</body>
</html>

