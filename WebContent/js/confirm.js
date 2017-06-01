
$(function(){
	var $pwd = $("#pwd");
	var $pwd_span = $("#pwd_span");
	var $submit = $("#submit");
	//按钮点击事件
	$submit.click(function(){
		var pwd_return=p();
		var tel_return=telConfirm();
		var code_return=validate();
		if(!(pwd_return && tel_return && code_return)){
			createcode();
			return false;
		}
	});
	//密码框失去焦点事件
	$pwd.blur(function(){
		p();
	});
	
	//测试密码强度函数
	var p = function(){
		var pwd1 = /[a-zA-Z]+/;		//字母
		var pwd2 = /\d+/;			//数字
		var pwd3 = /\W+/;			//特殊符号
		var count = 0;
		if($pwd.val().length<6){
			$pwd_span.html("* 请输入至少六位密码");
			$pwd_span.css({"color":"red","width":"200px","font-family":"幼圆" });
			return false;
		}
		if(pwd1.test($pwd.val())){
			count++;
		}
		if(pwd2.test($pwd.val())){
			count++;
		}
		if(pwd3.test($pwd.val())){
			count++;
		}
		switch (count){
				case 1:
					$pwd_span.html("* 密码强度太弱");
					$pwd_span.css({"color":"red","font-family": '黑体',"font-size": "16px" });
					return false;
				case 2:
					$pwd_span.html("* 密码强度中");
//					$submit.attr("type","submit");
					$pwd_span.css({"color":"#33FF00","font-family": '黑体',"font-size": "16px" });
					return true;
				case 3:
					$pwd_span.html("* 密码强度强");
//					$submit.attr("type","submit");
					$pwd_span.css({"color":"green","font-family": '黑体',"font-size": "16px" });
					return true;
		}
		
	};
	
});
