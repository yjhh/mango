$(function(){
			//用户名
			$("#userName").focus(function() {
		        //捕获触发事件的对象，并设置为以下语句的默认对象 
		       this.value="";
		    });
		    $("#userName").blur(function() {
		        //捕获触发事件的对象，并设置为以下语句的默认对象 
		       if(this.value==""){
		       	this.value="昵称/邮箱/手机号";
		       	$("#userName").css("color","#999");
		       }else{
		       	$("#userName").css("color","black");
		       }
		    });
		    
		    //密码
		   $("#password").focus(function() {
		        //捕获触发事件的对象，并设置为以下语句的默认对象 
		       this.value="";
		       $("#password").attr("type", "password");
		    });
		    $("#password").blur(function() {
		        //捕获触发事件的对象，并设置为以下语句的默认对象 
		       if(this.value==""){
		       	$("#password").attr("type", "text")
		       	this.value="密码";
		       	$("#password").css("color","#999");
		       }else{
		       	$("#password").css("color","black");
		       }
		    });
		    
		})