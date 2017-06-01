		var xmlhttprequest = false;
		//删除按钮
		function delete_User(btn){
			//实例化XMLHttpRequest对象 ，判断浏览器 （IE 或 非IE）
			if(window.ActiveXObject){ //IE
				xmlhttprequest = new ActiveXObject("Microsoft.XMLHTTP");
			}else{ //非IE
				xmlhttprequest = new XMLHttpRequest();
			}
			//设置open
			xmlhttprequest.open("GET","userajax?value="+btn.value+"&type=1",true);
			xmlhttprequest.send(null);
			//设置回调函数
			xmlhttprequest.onreadystatechange = function(){
				//alert("状态发送改变:"+xmlhttprequest.readyState);
				if(xmlhttprequest.readyState==4){
					if(xmlhttprequest.status == 200){ //HTTP状态
						//取得返回值
						var rs = xmlhttprequest.responseText;
						//判断
						if(rs == 'true'){
							alert("删除成功");
							$(btn.parentNode.parentNode).remove();
						}else{
							alert("删除错误");
						}
					}
				}
			}
			//发送请求
			xmlhttprequest.send(null);
		}
		
		//冻结,解冻
		function update_User(btn){
			//得到用户状态
			var status = btn.parentNode.parentNode.childNodes[8].innerHTML== "正常"?0:1;
			//实例化XMLHttpRequest对象 ，判断浏览器 （IE 或 非IE）
			if(window.ActiveXObject){ //IE
				xmlhttprequest = new ActiveXObject("Microsoft.XMLHTTP");
			}else{ //非IE
				xmlhttprequest = new XMLHttpRequest();
			}
			//设置open 
			xmlhttprequest.open("GET","userajax?value="+btn.value+"&type=2&status="+status,true);
			xmlhttprequest.send(null);
			//设置回调函数
			xmlhttprequest.onreadystatechange = function(){
				//alert("状态发送改变:"+xmlhttprequest.readyState);
				if(xmlhttprequest.readyState==4){
					if(xmlhttprequest.status == 200){ //HTTP状态
						//取得返回值
						var rs = xmlhttprequest.responseText;
						//判断
						if(rs == 'true'){
							ajax_keyup($("#text").val());
							alert("操作成功");
						}else{
							alert("操作失败");
						}
					}
				}
			}
			//发送请求
			xmlhttprequest.send(null);
		}
		
		
		
		//删除店铺按钮
		function delete_store(btn){
			//实例化XMLHttpRequest对象 ，判断浏览器 （IE 或 非IE）
			if(window.ActiveXObject){ //IE
				xmlhttprequest = new ActiveXObject("Microsoft.XMLHTTP");
			}else{ //非IE
				xmlhttprequest = new XMLHttpRequest();
			}
			//设置open
			xmlhttprequest.open("GET","storeAjax?value="+btn.value+"&type=1",true);
			xmlhttprequest.send(null);
			//设置回调函数
			xmlhttprequest.onreadystatechange = function(){
				//alert("状态发送改变:"+xmlhttprequest.readyState);
				if(xmlhttprequest.readyState==4){
					if(xmlhttprequest.status == 200){ //HTTP状态
						//取得返回值
						var rs = xmlhttprequest.responseText;
						//判断
						if(rs == 'true'){
							alert("删除成功");
							$(btn.parentNode.parentNode).remove();
						}else{
							alert("删除错误");
						}
					}
				}
			}
			//发送请求
			xmlhttprequest.send(null);
		}
		
		//冻结,解冻,审核店铺
		function update_store(btn){
			//得到用户状态
			var status = btn.parentNode.parentNode.childNodes[5].innerHTML;
//			alert(status)
			var sta = "";
			if(status=="正常"){
				sta = 0;
			}else if (status == "被冻结") {
				sta = 1;
			}else if (status == "审核中") {
				sta = 2;
			}else if (status == "已注销"){
				sta = 3;
			}else if (status == "申请注销"){
				sta = 4;
			}
			//实例化XMLHttpRequest对象 ，判断浏览器 （IE 或 非IE）
			if(window.ActiveXObject){ //IE
				xmlhttprequest = new ActiveXObject("Microsoft.XMLHTTP");
			}else{ //非IE
				xmlhttprequest = new XMLHttpRequest();
			}
			//设置open 
			xmlhttprequest.open("GET","storeAjax?value="+btn.value+"&type=2&status="+sta,true);
			xmlhttprequest.send(null);
			//设置回调函数
			xmlhttprequest.onreadystatechange = function(){
				//alert("状态发送改变:"+xmlhttprequest.readyState);
				if(xmlhttprequest.readyState==4){
					if(xmlhttprequest.status == 200){ //HTTP状态
						//取得返回值
						var rs = xmlhttprequest.responseText;
						//判断
						if(rs == 'true'){
							store_a($("#text").val());
							alert("操作成功");
						}else{
							alert("操作失败");
						}
					}
				}
			}
			//发送请求
			xmlhttprequest.send(null);
		}
		
		
		
		//分页
		var current=1;
		var page=1;
		$("#page_div").createPage({
	        pageCount:8,
	        current:1,
	        backFn:function(p){
	        	//current=p;
	        }
	    });