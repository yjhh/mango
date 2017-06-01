//一开始就加载
$(function(){
	$("#btn").click(function(){
		$("#goods").empty();
		ajax_order("1","1",$('#sens').val());
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
		
			$(".firstMsg").remove();
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
						"<td  style='width:420px;'>"+
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
								"<td width='80px'>"+
								"<span>下架成功</span>"+
							"</td>"+
							"</table>"
							break;
						default:
							cc+="<div style='margin-left:10px;padding-bottom:5px;float:left;color:gray;margin-left:10px;font-size:12pt;display:inline-block;width:100px;height:20px;'>库存："+
									pgoods.list[i].stock+
									"</div>"+
								"</td>"+
								"<td width='80px'>"+
								"<a href='javascript:void(0);' class='offshelf'>宝贝下架</a><span hidden>"+pgoods.list[i].id+"</span>"+
								"</td>"+
								"</table>";
						}
					
					$("#goods").append(cc);
						
				}
				
				//给a加ajax事件
				$(".offshelf").click(function(){
					
					$.get("store_editgoodsajax",{"type":1,"goods_id":$(this).next().text()},function(rs){
						alert(rs=="true"?"成功":"失败");
						$("#goods").empty();
						ajax_order("1","1",$('#sens').val());
					});
					
				});
				
			
					
			
				
				
			}else{
				$("#pagging").append("<span>您要下架的商品不存在哦~您可以继续查找哦~~</span>");
			}
		}
	});
}
"<table class='order' style='margin-top:5px;border:1px solid orange;'>"+
"<tr>"+
"<td  style='width:100px;margin-left:50px'>"+
		"<img src='/imgs/"+pgoods.list[i].list[0].url+"' style='margin-top:3px;margin-left:8px;width: 100px;height:100px;'>"+
"</td>"+
"<td  style='width:420px;'>"+
	"<div style='margin-left:10px;padding-top:10px;font-size:14pt;display:inline-block;width:500px;height:45px;'>"+
		"<a href='#'>"+pgoods.list[i].name+"</a>"+
	"</div>"+
	"<div style='margin-left:10px;padding-bottom:10px;float:left;color:gray;font-size:12pt;display:inline-block;width:100px;height:20px;''>"+
	pgoods.list[i].price+
	"</div><br>"+
	"<div style='margin-left:5px;padding-bottom:0px;clear:left;color:gray;float:left;font-size:12pt;display:inline-block;width:100px;height:20px;'>"+
		"已售：2333"+
	"</div>"+
	"<div style='margin-left:10px;padding-bottom:5px;float:left;color:gray;margin-left:10px;font-size:12pt;display:inline-block;width:100px;height:20px;'>库存："+
	pgoods.list[i].stock+
	"</div>"+
"</td>"+
"<td width='80px'>"+
"<a href='javascript:void(0);' class='offshelf'>宝贝下架</a><span hidden>"+pgoods.list[i].id+"</span>"+
"</td>"+
"</table>";