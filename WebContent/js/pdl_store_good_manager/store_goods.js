//一开始就加载
$(function(){
	ajax_order("1","1",'a');
	$("#btn").click(function(){
		$("#goods").empty();
		ajax_order("1","1",$('#sens').val());
	});
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
var ajax_order=function(value,p,str){
	$.ajax({
		url:"store_goodsajax",
		type:"get",
		dataType:"JSON",
		data:{"store_id":value,
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
				    	alert(index.getCurrent())
				    	$("#goods").empty();
				    	ajax_order("1",index.getCurrent());
				    	
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
								"<a href='#'>"+pgoods.list[i].name+"</a>"+
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
									"<tr>"+
										"<td class='edit' style='width:200px;text-align: center;border-right:2px deeppink dotted;'><a href='javascript:;'>编辑宝贝</a></td>"+
										"<td class='edit'style='width:200px;text-align: center;border-right:2px deeppink dotted;'><span>已下架</span></td>"+
										"<td class='edit' style='width:200px;text-align: center;'><a href='javascript:void(0);' class='del' >删除宝贝</a><span hidden>"+pgoods.list[i].id+"</span></td>"+
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
								"<tr>"+
									"<td class='edit' style='width:200px;text-align: center;border-right:2px deeppink dotted;'><a href='javascript:;'>编辑宝贝</a></td>"+
									"<td class='edit'style='width:200px;text-align: center;border-right:2px deeppink dotted;'><a href='javascript:void(0);' class='offshelf'>宝贝下架</a><span hidden>"+pgoods.list[i].id+"</span></td>"+
									"<td class='edit' style='width:200px;text-align: center;'><a href='javascript:void(0);' class='del' >宝贝删除</a><span hidden>"+pgoods.list[i].id+"</span></td>"+
								"</tr>"+
								"</table>";
						}
							
					
					$("#goods").append(cc);
						
				}
				
				//给a加ajax事件
				$(".offshelf").click(function(){
					
					$.get("store_editgoodsajax",{"type":1,"goods_id":$(this).next().text()},function(rs){
						alert(rs=="true"?"下架成功":"下架失败");
						$("#goods").empty();
						ajax_order("1","1",'a');
					});
					
				});
					
				$(".del").click(function(){
					$.get("store_editgoodsajax",{"type":2,"goods_id":$(this).next().text()},function(rs){
						alert(rs=="true"?"删除成功":"删除失败");
						$("#goods").empty();
						ajax_order("1","1",'a');
					});
					
				});
			
					
			
				
				
			}else{
				$("#pagging").append("<span>本店还没有商品哦~快去添加吧~</span>");
			}
		}
	});
}



