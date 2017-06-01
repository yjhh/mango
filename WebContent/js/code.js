		//生成验证码
		function createcode(){
			var btn = document.getElementById("code");
			var num=4;
			var selectChar = new Array(2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');
			var code="";
			for(var i=0;i<num;i++){
				var index=Math.floor(Math.random()*32);
				code+=selectChar[index];
			}
			btn.value=code;
		}
		//验证验证码
		function validate() {
			var code = document.getElementById("code");
			var code_span = document.getElementById("code_span");
			var inputCode = document.getElementById("code_input").value.toUpperCase();
			code_span.style.width="200px";
			if(inputCode.length <=0) {
				code_span.innerHTML="* 请输入验证码！";
				code_span.style.color="red";
				createcode();
			   return false;
			}
			if(inputCode != code.value ){
			   code_span.innerHTML="* 验证码输入错误！";
			   code_span.style.color="red";
			   createcode();
			   return false;
			}
			
				code_span.innerHTML="";
			   return true;
			
			}
		
		//验证手机号码
		var flag ;
		function telConfirm(){
			var reg=/^1[3578]\d{9}/;
			var tel=document.getElementById("tel").value;
			var tel_span=document.getElementById("tel_span");
			tel_span.style.width="200px";
			if(tel == ""){
				return false;
				}
			if(!reg.test(tel)){
				tel_span.innerHTML="* 手机号码输入有误！";
				tel_span.style.color="red";
				return false;
			}else{
				
				//手机号码格式正确，判断是否
				$.ajax({
					url : "user_reg.html",
					type : "GET",
//					dataType : "JSON",
					data :{"phone" : tel},
					async : true,
					success:function(rs){
						if(rs.trim() == "true"){
							tel_span.innerHTML="* 手机号码已存在！";
							tel_span.style.color="red";
							flag = false;
						}else {
							tel_span.innerHTML="";
							flag = true;
						}
					}
				});
				return flag;
				
//				tel_span.innerHTML="* 正确！";
//				tel_span.innerHTML="";
//				tel_span.style.color="green";
//				return true;
			}
		}

		

		


		
