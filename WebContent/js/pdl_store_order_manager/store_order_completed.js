//一开始就加载
$(function(){
	//5是申请退货
	ajax_order("1","1");
	$(".zhuxiao").click(function(){
		$.get("store_editgoodsajax",{"type":3},function(rs){
			alert(rs=="true"?"申请注销店铺成功":"申请注销店铺失败");
			$("#order_id").empty();
			$("#pagging").empty
			$("#pagging").append("<span>您已申请注销店铺成功，请等待1~2个工作日查看结果</span>");
		})
	});
});



//获取数据
var ajax_order=function(store_id,p){
	$.ajax({
		url:"order_completeajax",
		type:"get",
		dataType:"JSON",
		data:{"store_id":store_id,"p":p},
		async:true,
		
		success:function(porder){
			
			
			
			$('#pagging').pagination({
				coping:true,
			    pageCount:porder.pageCount,
			    current:porder.currentPage,
			    homePage:'首页',
			    endPage:'末页',
			    prevContent:'上页',
			    nextContent:'下页',
			    callback:function(index){
			    	
			    	$("#order_id").empty();
			    	ajax_order("1",index.getCurrent());
			    	
			    }});
				if(porder.list.length>0){
					//porder.list就是List<Order>,这里是数组
					for(var i=0;i<porder.list.length;i++){
						
						$("#order_id").append(
							            "<li class='order_id' style='margin-top:13px;float:left;font-weight:550;text-align:center;margin-left:50px;font-size:14px;'>订单编号："+porder.list[i].id+"</li>"+
							            "<li class='price' style='margin-top:13px;float:left;font-weight:550;text-align:center;margin-left:50px;font-size:14px;padding-left:100px;'>成交时间："+porder.list[i].orderTime+"</li>"+
							            "<li class='quantity'style='margin-top:13px;float:left;font-weight:550;text-align:center;margin-left:50px;font-size:14px;padding-left:100px;'>买家："+porder.list[i].user_logName+"</li><br>");
					

						//porder.list[i]是一个Order,order有一个List<item> list
						for(var j=0;j<porder.list[i].list.length;j++){
							//判断商品的名称，如果商品已经删除，那么查到的商品名称就改成已删除，然后再通过判断商品名称来选择放进去的内容
							var cc = "";
							cc += "<table id='order' style='margin-top:6px;border:1px solid orange;margin-left:40px'><tbody><tr>"+
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
							"<td  style='width:250px;'>"+
							"<div style='font-size:8pt;width:200px;height:40px;'>"+
							porder.list[i].list[j].good_name+
							"</div>"+
							"<div style='float:left;color:gray;font-size:12px;display:inline-block;width:60px;height:20px;'>"+
								"颜色：黑白"+
							"</div>"+
							"<div style='float:left;margin-left:10px;color:gray;font-size:12px;display:inline-block;width:60px;height:20px;'>"+
								"尺码：大"+
							"</div><br>"+
							"<div style='clear:left;float:left;font-size:6px;display:inline-block;width:16px;height:20px;'>"+
								"<img src='img/7.PNG' title='7天无理由退货'/>"+
							"</div>"+
							"<div style='float:left;margin-left:10px;font-size:6px;display:inline-block;width:16px;height:20px;'>"+
								"<img src='img/48.PNG' title='48小时内发货'/>"+
							"</div>"+
						"</td>"+
						"<td style='width:80px;text-align: center;'>"+porder.list[i].list[j].goods_price+"</td>"+
						"<td style='width:60px;text-align: center;'>"+porder.list[i].list[j].goods_num+"</td>";
							
							
							switch(porder.list[i].status_id){
							case 1:
								cc += "<td style='width:80px;text-align: center;'>未支付</td>";
								break;
							case 2:
								cc += "<td style='width:80px;text-align: center;'>已支付</td>";
								break;
							case 3:
								cc += "<td style='width:80px;text-align: center;'>已完成</td>";
								break;
							case 4:
								cc += "<td style='width:80px;text-align: center;'>用户已删除</td>";
								break;
							case 5:
								cc += "<td style='width:80px;text-align: center;'>申请退货</td>";
								break;
							case 6:
								cc += "<td style='width:80px;text-align: center;'>正在处理</td>";
								break;
							case 7:
								cc += "<td style='width:80px;text-align: center;'>已退货</td>";
								break;
							}
							
							cc += "<td style='width:80px;text-align: center;'><a href='#'>操作</a></td>"
								"</tr></tbody></table>";
							
							
							$("#order_id").append(cc.toString());
						}
						
						
					}
				}else{
					$("#pagging").append("<span style='margin-top:15px;font-size:16pt;color:gray;'>目前没有相关订单哦~~</span>");
				}
				
			
			
		
		}
	});
}

