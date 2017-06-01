<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
	作者：1210149028@qq.com
	时间：2016-10-12
	描述：退货订单，也是一打开就跳出来
-->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>店铺订单页</title>
		
		<script src="js/jquery-3.1.0.js"></script>
		<!-- <script src="js/pdl_store_order_manager/store_order_needtui.js"></script> -->
		
		
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
			table#order_items{
				width:800px;
				/*border:1px palevioletred solid;*/
				/*background-image: url(img/bg.PNG);*/
				
			}
			/**
			 * 订单项
			 */
			table#order{
				margin-left: 50px;
				width:700px;
				height:130px;
				/*border:1px red solid;*/
				background-color: antiquewhite;
				text-align: left;
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
				margin-left:50px;
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
							<label><a href="store_order_needTui.jsp">申请退货订单</a></label>
							<label><a href="store_order_completed.jsp">已完成订单</a > </label>
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
					订单管理
                -->
				<div id="right_block">
					<!--
						订单菜单栏
                    -->
						<div style="background:#f9f9f9;" >
							<table id="order_items" >
								<thead>
									<tr>
										<td ><a href="store_AllOrders.jsp">显示全部</a></td>
										<td ><a href="store_order_waitfordo.jsp">未处理订单</a></td>
										<td ><a href="store_order_needTui.jsp">申请退货订单</a></td>
										<td ><a href="store_order_completed.jsp">订单</a > </td>
									</tr>
								</thead>
							</table>
						</div>
	   					<div id ="td_name_div" style="width：700px;margin-left='50px';height: 30px;background-color:aliceblue;margin-top:10px;">
					        <ul class="order-title-column" >
					            <li class="goods" style="padding-left:130px;">商品</li>
					            <li class="price" style="padding-left:270px;">价格(元)</li>
					            <li class="quantity"style="padding-left:30px;">数量</li>
					            <li class="status"style="padding-left:25px;">订单状态</li>
					            <li class="other"style="padding-left:40px;">操作</li>
					        </ul> 
	  					</div>
		                <!--
	                    	订单
	                    -->
	                    <!--表格的部分开始-->
	                   <div id="orders" > 
	                   	<!--关于订单编号-->
	                   	<ul id="order_id" style="background-color:white;">
	                   	
	                   </ul>
	                   	<!--关于订单详细信息-->
	                   	<div id="pagging">
	                   		
						</div>
					</div>
					
		
				</div>
			</div>
		</div>
		<script src="js/jquery.pagination.js"></script>
		<script type="text/javascript">
		//一开始就加载
		var current=1;
		$(function(){
			//5是申请退货
			ajax_order("5",${store_id},current);
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
		});
		function operate(bt,type){
			$.get("user_editOrder_ajax",{"type":type,"order_id":bt},function(rs){
				alert(rs=="true"?"操作成功":"操作失败");
				$("#order_id").empty();
				ajax_order("5",${store_id},current);
			});
		};
		//获取数据
		var ajax_order=function(status_id,store_id,p){
			$.ajax({
				url:"order_tuiajax",
				type:"get",
				dataType:"JSON",
				data:{"status_id":status_id,"store_id":store_id,"p":p},
				async:true,
				success:function(porder){

					
					if(porder.list.length>0){
						$('#pagging').pagination({
							coping:true,
						    pageCount:porder.pageCount,
						    current:porder.currentPage,
						    homePage:'首页',
						    endPage:'末页',
						    prevContent:'上页',
						    nextContent:'下页',
						    callback:function(index){
						    	/*alert(index.getCurrent())*/
						    	$("#order_id").empty();
						    	current=index.getCurrent();
						    	ajax_order(${store_id},index.getCurrent());
						    	
						    }
						});
						//遍历分页结果
						for(var i=0;i<porder.list.length;i++){
							$("#order_id").append(
							        "<li class='order_id' style='margin-top:13px;float:left;font-weight:550;text-align:center;margin-left:50px;font-size:14px;'>订单编号："+porder.list[i].id+"</li>"+
							        "<li class='price' style='margin-top:13px;float:left;font-weight:550;text-align:center;margin-left:50px;font-size:14px;padding-left:100px;'>成交时间："+porder.list[i].orderTime+"</li>"+
							        "<li class='quantity'style='margin-top:13px;float:left;font-weight:550;text-align:center;margin-left:50px;font-size:14px;padding-left:100px;'>买家："+porder.list[i].user_logName+"</li><br><br>");


							for(var j=0;j<porder.list[i].list.length;j++){
							/*alert()
							alert(porder.list[0].list[0].pics[0].url)*/
							//判断商品的名称，如果商品已经删除，那么查到的商品名称就改成已删除，然后再通过判断商品名称来选择放进去的内容
							var cc = "";
							cc += "<table id='order' style='margin-top:6px;border:1px solid orange;margin-left:80px'><tbody><tr>"+
							"<td  style='width:100px;'>"
							//开始判断，选择图片
							switch(porder.list[i].list[j].good_name){
							case'该商品已删除':
								cc+="<div style='display:inline-block;'> "+
								"<img src='img/pdl_pic/notfound.png' style='width: 100px;height:100px;'>"+
								"</div>";
								break;
							default:
								cc+="<div style='display:inline-block;'> "+
								"<img src='/imgs/"+porder.list[i].list[j].pics[0].url+"' style='width: 100px;height:100px;'>"+
								"</div>";
							}
							cc+="</td>"+
							"<td  style='width:245px;'>"+
							"<div style='margin-left:5px;font-size:12pt;width:200px;height:40px;'>"+
							porder.list[i].list[j].good_name+
							"</div>"+
							"<div style='margin-left:5px;float:left;color:gray;font-size:12px;display:inline-block;width:60px;height:20px;'>"+
								"颜色：黑白"+
							"</div>"+
							"<div style='float:left;margin-left:10px;color:gray;font-size:12px;display:inline-block;width:60px;height:20px;'>"+
								"尺码：大"+
							"</div><br>"+
							"<div style='margin-left:5px;clear:left;float:left;font-size:6px;display:inline-block;width:16px;height:20px;'>"+
								"<img src='img/7.PNG' title='7天无理由退货'/>"+
							"</div>"+
							"<div style='margin-left:5px;float:left;margin-left:10px;font-size:6px;display:inline-block;width:16px;height:20px;'>"+
								"<img src='img/48.PNG' title='48小时内发货'/>"+
							"</div>"+
							"</td>"+
							"<td style='width:80px;text-align: center;'>"+porder.list[i].list[j].goods_price+"</td>"+
							"<td style='width:60px;text-align: center;'>"+porder.list[i].list[j].goods_num+"</td>";


							switch(porder.list[i].status_id){
							case 1:
								cc += "<td style='width:80px;text-align: center;'>未支付</td>"+
								"<td style='width:80px;text-align: center;'>不可操作</td>"
								"</tr></tbody></table>";
								break;
							case 2:
								cc += "<td style='width:80px;text-align: center;'>已支付</td>"+
								"<td style='width:80px;text-align: center;'><a href='javascript:void(0)' onclick='operate("+porder.list[i].list[j].order_id+",5)' class='edit'>发货</a></td>"
								"</tr></tbody></table>";
								break;
							case 3:
								cc += "<td style='width:80px;text-align: center;'>已收货</td>"+
								"<td style='width:80px;text-align: center;'>不可操作</td>"
								"</tr></tbody></table>";
								break;
							case 4:
								cc += "<td style='width:80px;text-align: center;'>用户已删除</td>"+
								"<td style='width:80px;text-align: center;'>不可操作</td>"
								"</tr></tbody></table>";
								break;
							case 5:
								cc += "<td style='width:80px;text-align: center;'>申请退货</td>"+
								"<td style='width:80px;text-align: center;'><a href='javascript:void(0)' onclick='operate("+porder.list[i].list[j].order_id+",4)' class='edit'>退货</a></td>"
								"</tr></tbody></table>";
								break;
							case 6:
								cc += "<td style='width:80px;text-align: center;'>已退货</td>"+
								"<td style='width:80px;text-align: center;'>不可操作</td>"
								"</tr></tbody></table>";
								break;
							case 7:
								cc += "<td style='width:80px;text-align: center;'>已发货</td>"+
								"<td style='width:80px;text-align: center;'>不可操作</td>"
								"</tr></tbody></table>";
								break;
							}


							$("#order_id").append(cc.toString());

							}
						
						}
						
						
					}else{
						$("#pagging").append("<span style='margin-top:15px;font-size:16pt;color:gray;'>目前没有订单哦~~</span>");
					}
					
				}
			});
				
				
		}


		</script>
		<!--尾部-->
		<jsp:include page="index_foot.html" />

	</body>
</html>
