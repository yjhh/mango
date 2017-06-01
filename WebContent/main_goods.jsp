<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%
String parent_id = request.getParameter("parent_id");
String categroy_id = request.getParameter("categroy_id");
if(parent_id==null){
	response.sendError(404);
	return;
}
%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>芒果网MANGO BAR</title>
		<script type="text/javascript"> 
			function displaySubMenu(li) { 
			var subMenu = li.getElementsByTagName("ul")[0]; 
			subMenu.style.display = "block"; 
			} 
			function hideSubMenu(li) { 
			var subMenu = li.getElementsByTagName("ul")[0]; 
			subMenu.style.display = "none"; 
			} 
		</script> 
		<link rel="stylesheet" type="text/css" href="css/main_goods.css"/>
		<link rel="stylesheet" type="text/css" href="css/store/store.css"/>
		<script src="js/jquery-3.1.0.js"></script>
		
		<script type="text/javascript">
		
		 $(function(){
			 //添加顶部样式
			 $(".parent_categroy li").each(function(){
				  if($(this).attr("data") == <%=request.getParameter("parent_id")%> ){
					 $(this).css({"border-top":"2px orangered solid" ,"background":"white"});
				 } 
			 })
			 //结束
			 //ajax动态添加子类li
			 $.post("categroy_Search.html",{"parent_id":<%=request.getParameter("parent_id")%>,"categroy_id":<%=request.getParameter("categroy_id")%>},function(rs){
				 if(rs != null && rs != ""){
					$("#ul a").remove();	
					$("#ul li").remove();	
				 for(var i = 0;i < rs.length;i++){
				 $("#ul").append("<a href='categroy_Search.html?parent_id="+rs[i].parent_id+"&categroy_id="+rs[i].id+"'><li >"+rs[i].name+"</li></a><li>|</li>");
				 } 
				 $("#ul").append("<li style='height:30px;'></li>");
				} 
			 },"JSON")
			 
			 //ajax加载图片
			 $.post("searchPicture.html",{"parent_id":<%=request.getParameter("parent_id")%>,"categroy_id":<%=request.getParameter("categroy_id")%>},function(str){
				 if(str != null && str != ""){
					$("#main_hot_goods label").remove();	
				  for(var i = 0;i < str.length;i++){
					 /* for(var j = 0;j<str[i].list.length;j++) { */
					  $("#main_hot_goods").append("<div class='main_hot_goods_thing'>" +
				    	 		"<a href='good.jsp?goods_id="+str[i].id+"''><img src="+str[i].list[0].url+" width='220px' height='320px' /></a>" +
				    	 		"<p title="+str[i].describe+">"+str[i].name+"</p>" +
				    	 		"<p><img src='img/index/main_hot_goods/rm.png' style='width: 30px;height: 18px;'/>" +
				    	 		"</p><div class='main_hot_goods_info'><label>￥"+str[i].price+"</label>" +
				    	 		"</div></div>");
					 	}
					/*  }  */
				} 
			 },"JSON")
			 
		 }) 
		
		
		</script>
		<style type="text/css">
		#main_hot_goods b{
			display:block;
			margin:80px;
			opacity:0.5;
			color:grey;
			text-align:center;
			font-size:25px;
		}
		
		</style>
	</head>
	<body>
		<jsp:include page="index_head.jsp" />
		
		<!--主体-->
		<div id="main_vessel">
			<div id="main" style="border: 1px red solid;">
				<div id="sp_top">
					
					<!--菜单栏-->
					<div id="sp_top_nav" >
						<ul class="parent_categroy">
							
							<a href="categroy_Search.html?parent_id=2"><li data="8">卧室家具</li></a>
							<a href="categroy_Search.html?parent_id=3"><li data="5">客厅家具</li></a>
							<a href="categroy_Search.html?parent_id=4"><li data="6">餐厅家具</li></a>
							<a href="categroy_Search.html?parent_id=15"><li data="34">书房家具</li></a>
							<a href="categroy_Search.html?parent_id=18"><li data="7">儿童家具</li></a>
						</ul>
						
					</div>
					<!-- <div id="sp_top_type">
						<div id="shop_Coat_box_left">
							<div id="shop_Coat_box">
							<label>当季热卖</label>
							<ul id="ul">
								<img src="img/main_goods/1.jpg"/>
								<a href="javascript:void(0);"><li >卫衣</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >针织衫</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>气质风衣</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>棒球外套</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >时尚套装</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>长袖T恤</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>牛仔外套</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >衬衫</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>韩版西装</li></a>
							</ul>
						</div>
										
							<div id="shop_Coat_box">
							<label>潮流时尚</label>
							<ul>
								<img src="img/main_goods/2.jpg"/>
								<a href="javascript:void(0);"><li >绑带衬衫</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >性感V领</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>俏皮露肩</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>时尚挂脖</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >甜美荷叶边</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>喇叭袖</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>短款上衣</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >衬衫</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>一字领</li></a>
							</ul>
						</div>
							<div id="shop_Coat_box">
							<label>上新推荐</label>
							<ul>
								<img src="img/main_goods/3.jpg"/>
								<a href="javascript:void(0);"><li >针织开衫</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >T恤</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>秋季外套</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>毛衣</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >情侣装</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>毛呢外套</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>棉服</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >马甲</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>羽绒服</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>皮衣</li></a>
							</ul>
						</div>
										<div id="shop_Coat_box">
							<label>经典必备</label>
							<ul>
								<img src="img/main_goods/4.jpg"/>
								<a href="javascript:void(0);"><li >纯色T恤</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >纯白衬衫</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>卡通T恤</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>格纹衬衫</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >长款T恤</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>印花T恤</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li>雪纺衬衫</li></a>
								<li>|</li>
								<a href="javascript:void(0);"><li >条纹衫</li></a>
							</ul>
						</div>
						</div>
						<div id="shop_Coat_box_right">
							<img src="img/main_goods/5.jpg" style="margin: 0 0 0 10px;"/>
						</div>
					</div> -->
				</div>
					<div id="main_hot_goods">
						<label>
						<i><b>还没有商品哦</b></i>
						</label>
				</div>
				</div>
			</div>
		
		<jsp:include page="index_foot.html" />

