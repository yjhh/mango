$(function(){
	$(window).scrollTop(0);
/*动态加载图片*/
	var str = new Array();
	//计数
	var i = 0;			
	var k = 1;
	 function loadata()
	 { 
		 var totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop()); 
     if (($("#main_hot_goods").outerHeight()) <= totalheight) {    //滚动到div底部时，开始动态加载数据
    	 for(;i<(k*5>str.length?str.length:k*5);i++){
    			 $("#main_hot_goods").append("<div class='main_hot_goods_thing'>" +
    	 		"<a href='good.jsp?goods_id="+str[i]["id"]+"'><img width='220px' height='320px' src="+str[i]["src"]+" /></a>" +
    	 		"<p title="+str[i]["describe"]+">"+str[i]["name"]+"</p>" +
    	 		"<p><img src='img/index/main_hot_goods/rm.png' style='width: 30px;height: 18px;'/>" +
    	 		"</p><div class='main_hot_goods_info'><label>￥"+str[i]["price"]+"</label>" +
    	 		"</div></div>")
    		 }
    	k++;
	 }
     
 } 

	 
	 $.ajax({
		 url:"getPicture",
		 type : "Post",
		 dataType : "JSON",
		 async : true,
		 success : function(rs){
		 for(var i = 0;i < rs.length;i++ ){
				 str[i] = {id:rs[i].id,name:rs[i].name,price:rs[i].price,describe:rs[i].describe,src:rs[i].list[0].url};
		 }
		 }
	 })
	 
	 //滚动条滚动时执行
 $(window).scroll( function() { 
     loadata();
 }); 
})
	 