/* 代码整理：懒人之家 www.lanrenzhijia.com */
$(function(){
	//商品分类
	$('.all-goods .item').hover(function(){
		$(this).addClass('active').find('s').hide();
		$(this).find('.product-wrap').show();
	},function(){
		$(this).removeClass('active').find('s').show();
		$(this).find('.product-wrap').hide();
	});
							//显示搜索选择 二级菜单
						$('.choice').hover(function() {
							$('.choice_list').show();
						}, function() {
							$('.choice_list').hide();
						});
						
						$('.choice_list').hover(function() {
							$(this).show();
						}, function() {
							$(this).hide();
						});
						
						$('.choice_list ul li').click(function() {
							
							if($(this).text().match("商品") != null){
								$(".choice").html("搜商品");
								
								$("#text").attr("value","1");
//								alert($("#text").val());
							}
							
							if($(this).text().match("店铺") != null){
								$(".choice").html("搜店铺");
								$("#text").attr("value","2");
//								alert($("#text").val());
							}
						});
	
	});
