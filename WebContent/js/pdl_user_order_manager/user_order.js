//一开始就加载
$(function(){
	//这是店铺id
	ajax_order("1");	
});

var current = 1;
//获取数据
var ajax_order=function(p){
	$.ajax({
		url:"user_order_ajax",
		type:"get",
		dataType:"JSON",
		data:{
			"p":p},
		async:false,
		//这个
		success:function(puorder){
		
			/*alert(puorder);*/
			if(puorder.list.length>0){
				
				//遍历分页结果
				for(var i=0;i<puorder.list.length;i++){
					$(".order_body").append("<li class='order_id' style='margin-top:-6px;font-size:15px;'>订单编号："+puorder.list[i].id+"</li>"+
							"<li class='price' style='margin-top:-6px;font-size:15px;padding-left:200px;'>成交时间："+puorder.list[i].orderTime+"</li><br>");

					for(var j=0;j<puorder.list[i].list.length;j++){
						/*alert()
						alert(puorder.list[0].list[0].pics[0].url)*/
						//判断商品的名称，如果商品已经删除，那么查到的商品名称就改成已删除，然后再通过判断商品名称来选择放进去的内容
						var cc = "";
						cc += "<table style='border:1ps plum solid;margin-left:0px;width:980px;height:100px;background-color: #FAFAD2;'>"+
						"<td width='100px' >"
						//开始判断，选择图片
						switch(puorder.list[i].list[j].good_name){
						case'该商品已删除':
							cc+="<img src='img/pdl_pic/notfound.png' style='width: 100px;height:100px' />";
							break;
						default:
							cc+="<img src='/imgs/"+puorder.list[i].list[j].pics[0].url+"' style='width: 100px;height:100px' />";
						}
						cc+="</td>"+
						"<td width='190px'>"+
						"<div style='font-size:12pt;width:200px;height:40px;'><a href='good.jsp?goods_id="+puorder.list[i].list[j].good_id+"'>"+
						puorder.list[i].list[j].good_name+
						"</a></div>"+
						"<div style='float:left;color:gray;font-size:12px;display:inline-block;width:60px;height:20px;'>"+
							"颜色：黑白"+
						"</div>"+
						"<div style='float:left;margin-left:10px;color:gray;font-size:12px;display:inline-block;width:60px;height:20px;'>"+
							"尺码：大"+
						"</div><br>"+
					"</td>"+
					"<td style='width:100px;text-align: center;'>"+puorder.list[i].list[j].goods_price+"</td>"+
					"<td style='width:60px;text-align: center;'>"+puorder.list[i].list[j].goods_num+"</td>"+
					"<td width='100px'>"+
						"<div style='margin-left:10px;clear:left;float:left;font-size:6px;display:inline-block;width:100px;height:30px;'>"+
							"<img src='img/7.PNG' title='7天无理由退货'/>7天无理由退货"+
						"</div>"+
						"<div style='float:left;margin-left:10px;font-size:6px;display:inline-block;width:100px;height:30px;'>"+
							"<img src='img/48.PNG' title='48小时内发货'/>8小时内发货"+
						"</div>"+
					"</td>"+
					"<td style='width:120px;text-align: center;'>"+puorder.list[i].totalPrice+"</td>";
	
						switch(puorder.list[i].status_id){
						//未支付
						case 1:
							cc += "<td style='width:130px;text-align: center;'>未支付</td>"+
									"<td style='width:110px;text-align: center;'>"+
									"<a href='javascript:void(0);' class='zhifu'><label >支付订单</label></a><span hidden>"+puorder.list[i].id+"</span><br><br>"+
									"<a href='javascript:void(0);' class='del'><label >删除订单</label></a><span hidden>"+puorder.list[i].id+"</span>"+
								"</td>";
							break;
						//已支付
						case 2:
							cc += "<td style='width:130px;text-align: center;'>已支付</td>"+
									"<td style='width:110px;text-align: center;'>"+
									"<a href='javascript:void(0);'><label >已支付</label></a><span hidden>"+puorder.list[i].id+"</span><br><br>"+
									"<a href='javascript:void(0);' class='del'><label >删除订单</label></a><span hidden>"+puorder.list[i].id+"</span>"+
								"</td>";
							break;
						//完成
						case 3:
							cc += "<td style='width:130px;text-align: center;'>已完成</td>"+
									"<td style='width:110px;text-align: center;'>"+
									"<a href='javascript:void(0);' class='tui'><label >申请退货</label></a><span hidden>"+puorder.list[i].id+"</span><br><br>"+
									"<a href='javascript:void(0);' class='del'><label >删除订单</label></a><span hidden>"+puorder.list[i].id+"</span>"+
								"</td>";
							break;
							//申请退货
						case 5:
							cc +="<td style='width:130px;text-align: center;'>申请退货</td>"+
									"<td style='width:110px;text-align: center;'>"+
									"<a href='javascript:void(0);'><label >已申请退货</label></a><span hidden>"+puorder.list[i].id+"</span><br><br>"+
									"<a href='javascript:void(0);' class='del'><label >删除订单</label></a><span hidden>"+puorder.list[i].id+"</span>"+
								"</td>";
							break;
							//正在处理
						case 6:
							cc += "<td style='width:130px;text-align: center;'>已退货</td>"+
									"<td style='width:110px;text-align: center;'>"+
									"<a href='javascript:void(0);'><label >已退货</label></a><span hidden>"+puorder.list[i].id+"</span><br><br>"+
									"<a href='javascript:void(0);' class='del'><label >删除订单</label></a><span hidden>"+puorder.list[i].id+"</span>"+
								"</td>";
							break;
							//已退货
						case 7:
							cc +="<td style='width:130px;text-align: center;'>已发货</td>"+
									"<td style='width:110px;text-align: center;'>"+
									"<a href='javascript:void(0);' class='queren'><label >确认收货</label></a><span hidden>"+puorder.list[i].id+"</span><br><br>"+
									"<a href='javascript:void(0);' class='del'><label >删除订单</label></a><span hidden>"+puorder.list[i].id+"</span>"+
								"</td>";
							break;
						}
						
						cc += "</table>"
	
						$(".order_body").append(cc.toString());
						
					}
					
				}
				$(".order_body").append("<div id='pagging' >"+"</div>")
				$('#pagging').pagination({
					coping:true,
				    pageCount:puorder.pageCount,
				    current:puorder.currentPage,
				    homePage:'首页',
				    endPage:'末页',
				    prevContent:'上页',
				    nextContent:'下页',
				    callback:function(index){
				    	$(".order_body").empty();
				    	ajax_order(index.getCurrent());
				    	current = index.getCurrent();
				    }
				});
				//点击支付链接
				$(".zhifu").click(function(){
					$.get("user_editOrder_ajax",{"type":1,"order_id":$(this).next().text()},function(rs){
						alert(rs=="true"?"支付成功":"支付失败");
						$(".order_body").empty();
						ajax_order(current);	
					});
				});
				//点击确认删除链接
				$(".del").click(function(){
					$.get("user_editOrder_ajax",{"type":2,"order_id":$(this).next().text()},function(rs){
						alert(rs=="true"?"删除成功":"删除失败");
						$(".order_body").empty();
						ajax_order(current);	
					});
				});
				//点击确认收货链接
				$(".queren").click(function(){
					$.get("user_editOrder_ajax",{"type":3,"order_id":$(this).next().text()},function(rs){
						alert(rs=="true"?"确认成功":"确认失败");
						$(".order_body").empty();
						ajax_order(current);	
					});
				});
				$(".tui").click(function(){
					$.get("user_editOrder_ajax",{"type":6,"order_id":$(this).next().text()},function(rs){
						alert(rs=="true"?"已申请":"系统繁忙，请稍后再试");
						$(".order_body").empty();
						ajax_order(current);	
					});
				});
			}else{
				$("#order_body").append("<label style='color:gray;padding-left:500px ;font-size:16pt;'/>目前没有订单哦~~</label>");
			}
			
		}
	});
}
/*var cc="";
cc+="<li class='order_id' style='margin-top:-6px;font-size:15px;'>订单编号：</li>"+
"<li class='price' style='margin-top:-6px;font-size:15px;padding-left:200px;'>成交时间：</li><br>"+
"<table border='1'style='border:1ps plum solid;margin-left:0px;width:980px;height:100px;;'>"+
	"<td width='100px' >"+
		"<img src='img/panda.jpg' style='width: 100px;height:100px' />"+
	"</td>"+
	"<td width='190px'>"+
		"<div style='font-size:8pt;width:200px;height:40px;'>"+
					"【四川卧龙】绝对本地产大熊猫小大熊猫胖胖的熊猫团团啦啦啦"+
		"</div>"+
		"<div style='float:left;color:gray;font-size:12px;display:inline-block;width:60px;height:20px;'>"+
			"颜色：黑白"+
		"</div>"+
		"<div style='float:left;margin-left:10px;color:gray;font-size:12px;display:inline-block;width:60px;height:20px;'>"
			"尺码：大"+
		"</div><br>"+
		
	"</td>"+
	"<td style='width:100px;text-align: center;'>1000</td>"+
	"<td style='width:60px;text-align: center;'>1</td>"+
	"<td width='100px'>"
		"<div style='margin-left:10px;clear:left;float:left;font-size:6px;display:inline-block;width:100px;height:30px;'>"+
			"<img src='img/7.PNG' title='7天无理由退货'/>7天无理由退货"+
		"</div>"+
		"<div style='float:left;margin-left:10px;font-size:6px;display:inline-block;width:100px;height:30px;'>"+
			"<img src='img/48.PNG' title='48小时内发货'/>8小时内发货"+
		"</div>"+
	"</td>"+
	"<td style='width:120px;text-align: center;'>实付款</td>"+
	"<td style='width:130px;text-align: center;'>你猜</td>"+
	"<td style='width:110px;text-align: center;'>操作</td>"+
"</table>"*/