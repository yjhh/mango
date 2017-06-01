<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 店铺：查询所有本店商品 -->
<!DOCTYPE html>
<html>
<%
if(session.getAttribute("store_id")==null){
	response.sendError(404);
}
%>
<c:if test="${status == 1}">
		<script>
		window.history.back(-1);
		alert("该店铺已被冻结");
		</script>
		
	</c:if>	
	<c:if test="${status == 2}">
		<script>
		window.history.back(-1);
		alert("店铺审核已提交，请等待审核");
		</script>
	</c:if>	
	<c:if test="${status == 3}">
		<script>
		window.history.back(-1);
		alert("该店铺已被注销");
		</script>
	</c:if>	
	<c:if test="${status == 4}">
		<script>
		window.history.back(-1);
		alert("该店铺申请注销");
		</script>
	</c:if>	
	<head>
		<meta charset="utf-8" />
		<title>searchAllMyStoreGoods</title>
		<script src="js/jquery-3.1.0.js"></script>
		<!-- <script src="js/pdl_store_good_manager/store_goods.js"></script> -->
		
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
			
			
			
			div#main_vessel{
				width: 100%;
				background: white;
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
				background:#f9f9f9;
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
			/**
			 * 右边菜单栏
			 */
			div#right_block{
				float: right;
				margin-right:96px;
				width:800px;
				height:800px;
				/*border: 1px slateblue solid;*/
				text-align: center;
			}
			/**
			 * 最上方菜单栏
			 */
			table #order_items{
				width:800px;
				/* text_align:center; */
				/*border:1px palevioletred solid;*/
				/*background-image: url(img/bg.PNG);*/
				
			}
			/**
			 * 订单项
			 */
			table .order{
				margin-left: 50px;
				width:700px;
				height:130px;
				/*border:1px red solid;*/
				background-color:palegreen;
				text-align: left;
			}
			table .order_caozuo{
				
				margin-left:90px;
				width:600px;
				height:30px;
				font-size:16pt;
				
			}
			/**
			 * 表格的标题栏div
			 */
			.order-title-column li
			{
				float:left;
				font-weight:550;
				text-align:center;
				margin-top:7px;
				}
			.order-table-column li
			{
				float:left;
				font-weight:550;
				text-align:center;
				
				}
			div#main{
				width: 1200px;
				height: 800px;
				/*border: 1px red solid;*/
				margin: 0 auto;
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
			div#pagging {margin-left:220px;padding: 15px 20px;text-align: left;color: #ccc;}
			div#pagging a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
			div#pagging a:hover{text-decoration: none;border: 1px solid #428bca;}
			div#pagging span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
			div#pagging span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;
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
							<label><a href="store_order_waitfordo.jsp">未处理订单</a></label>
							<label><a href="store_order-needTui.jsp">申请退货订单</a></label>
							<label><a href="store_order_completed.jsp">已完成订单</a></label>
						</div>
						<div id="store_infos">
							<div>
								<label style="display:inline-block;margin-left:20px;font-size:20px;">店铺管理</label>
							</div>
							<div >
								<label style="display:inline-block;margin-left:45px;font-size:15px;margin-top:15px;"><a href="javascript:void(0)" class="zhuxiao">申请注销</a></label>
							</div>
						</div>
					</div>
				</div>
				<!--
					右侧显示栏
					宝贝管理
                -->
				<div id="right_block">
					<!--
						宝贝管理菜单栏
                    -->
						<div style="background:#f9f9f9;" >
							<table id="order_items" >
								<thead>
									<tr>
										<td style="width:200px;text-align:center;"><a href="store_goods.jsp">全部宝贝</a></td>
										<td style="width:200px;text-align:center;"><a href="store_good_down.jsp">宝贝上架</a></td>
										<td style="width:200px;text-align:center;"><a href="store_good_down.jsp">宝贝下架</a></td>
										<td style="width:200px;text-align:center;"><a href="store_good_del.jsp">删除宝贝</a > </td>
									</tr>
								</thead>
							</table>
						</div>
	  					<div style="width:800px;height: 30px;">
							<form style="float: right;margin-right:50px ;margin-top:10px;" >
								<input type="text" id="sens" name="goods_id" style="width:200px;height: 20px;" placeholder="请输入描述">
								<button type="button" id="searchbtn" style="width:50px;height: 28px;">查询</button>
							</form>
						</div>
		                <!--
	                    	商品
	                    -->
	                    <!--表格的部分开始-->
	                   <div id="orders" > 
	                   	<!--关于宝贝详细信息-->
	                   	<div  id="goods" style="margin-top:15px ;margin-left:70px">

	               </div><!--表格的部分结束-->
						</div>
						<!--分页-->
						<div id="pagging">
							
						</div>
					</div>
					
		
				</div>
			</div>
		</div>
		<script src="js/jquery.pagination.js"></script>
		<script type="text/javascript">
		//一开始就加载
		$(function(){
			
			
			//店铺iD,分页第一页，搜索内容
			ajax_order(${store_id},"1",'a');
			$("#searchbtn").click(function(){
				$("#goods").empty();
				ajax_order(${store_id},"1",$('#sens').val());
			});
			$(".zhuxiao").click(function(){
				var a = confirm("确定注销？");
				if(a){
					$.get("store_editgoodsajax",{"type":3},function(rs){
						alert(rs.trim()=="true"?"申请注销店铺成功":"申请注销店铺失败");
					if(rs=="true"){
						$("#order_id").empty();
						$("#pagging").empty();
						$("#pagging").append("<span>您已申请注销店铺成功，请等待1~2个工作日查看结果</span>");
						}
					})
				}
			});
			
		});
		var current = 1;
		//获取数据
		var ajax_order=function(store_id,p,str){
			$.ajax({
				url:"store_goodsajax",
				type:"get",
				dataType:"JSON",
				data:{"store_id":store_id,
					"p":p,
					"desc":str},
				async:true,
				//这个
				success:function(pgoods){
					
					if(pgoods.list.length>0){
						/*alert(pgoods.list.length)*/
						 $('#pagging').pagination({
							coping:true,
						    pageCount:pgoods.pageCount,
						    current:pgoods.currentPage,
						    homePage:'首页',
						    endPage:'末页',
						    prevContent:'上页',
						    nextContent:'下页',
						    callback:function(index){
								current = index.getCurrent();						    	
						    	$("#goods").empty();
						    	ajax_order(${store_id},index.getCurrent(),$('#sens').val());
						    	
						    }
						}); 
						/*alert(porder.list[0].list.length)*/
						//遍历pageBean对象下的List<Goods> list,goods对象下有一个List<Picture> list
						for(var i=0;i<pgoods.list.length;i++){
							/*alert("i="+i)*/
							/*alert(pgoods.list.length)*/
								var cc="";
								cc+="<table class='order' style='margin-top:5px;border:1px solid orange;'>"+
								"<tr>"+
								"<td  style='width:100px;margin-left:50px'>"+
										"<img src='/imgs/"+pgoods.list[i].list[0].url+"' style='margin-top:3px;margin-left:8px;width: 100px;height:100px;'>"+
								"</td>"+
								"<td   style='width:500px;'>"+
									"<div style='margin-left:10px;padding-top:10px;font-size:14pt;display:inline-block;width:500px;height:45px;'>"+
										"<a href='good.jsp?goods_id="+pgoods.list[i].id+"'>"+pgoods.list[i].name+"</a>"+
									"</div>"+
									"<div style='margin-left:10px;padding-bottom:10px;float:left;color:gray;font-size:12pt;display:inline-block;width:100px;height:20px;''>￥"+
									pgoods.list[i].price+
									"</div><br>"+
									"<div style='margin-left:5px;padding-bottom:0px;clear:left;color:gray;float:left;font-size:12pt;display:inline-block;width:100px;height:20px;'>"+
										"已售：2333"+
									"</div>";
								switch(pgoods.list[i].stock){
								 case 0:
									 cc+="<div style='margin-left:10px;padding-bottom:5px;float:left;color:gray;margin-left:10px;font-size:12pt;display:inline-block;width:100px;height:20px;'>"+
											"状态：已下架"+
											"</div>"+
											"</td>"+
											"</tr>"+
											"</table>"+
											"<table class='order_caozuo' style='margin-top:5px;'>"+
											"<tr>"+"<td class='edit'style='width:300px;text-align: center;border-right:2px deeppink dotted;'><span>已下架</span></td>"+
												"<td class='edit' style='width:300px;text-align: center;'><a href='javascript:void(0);' class='del' >删除宝贝</a><span hidden>"+pgoods.list[i].id+"</span></td>"+
											"</tr>"+
											"</table>";
									break;
								default:
									cc+="<div style='margin-left:10px;padding-bottom:5px;float:left;color:gray;margin-left:10px;font-size:12pt;display:inline-block;width:100px;height:20px;'>库存："+
										pgoods.list[i].stock+
										"</div>"+
											"</td>"+
										"</tr>"+
										"</table>"+
										"<table class='order_caozuo' style='margin-top:5px;'>"+
										"<tr>"+"<td class='edit'style='width:300px;text-align: center;border-right:2px deeppink dotted;'><a href='javascript:void(0);' class='offshelf'>宝贝下架</a><span hidden>"+pgoods.list[i].id+"</span></td>"+
											"<td class='edit' style='width:300px;text-align: center;'><a href='javascript:void(0);' class='del' >宝贝删除</a><span hidden>"+pgoods.list[i].id+"</span></td>"+
										"</tr>"+
										"</table>";
								}
								$("#goods").append(cc);

							}
					//给a加ajax事件
					 $(".offshelf").click(function(){
							
							$.get("store_editgoodsajax",{"type":1,"goods_id":$(this).next().text()},function(rs){
								alert(rs=="true"?"成功":"失败");
								$("#goods").empty();
								ajax_order(${store_id},current,$('#sens').val());
							});
							
						}); 
					 $(".del").click(function(){
						$.get("store_editgoodsajax",{"type":2,"goods_id":$(this).next().text()},function(rs){
							alert(rs=="true"?"删除成功":"删除失败");
							$("#goods").empty();
							ajax_order(${store_id},current,$('#sens').val());
						});
						
					}); 
						
						
					}else{
						$("#pagging").empty();
						$("#pagging").append("<span>本店还没有商品哦~快去添加吧~</span>");
					}
				}
			});
		}




		
		</script>
		<!--尾部-->
		<jsp:include page="index_foot.html" />

	</body>
</html>
