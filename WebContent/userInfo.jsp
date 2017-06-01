<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%
			String n = request.getParameter("n");
			if(n!=null){
		%>
		<meta http-equiv="refresh"/>
		<%
		}
		%>
		<title>个人信息</title>
		<!-- <script src="js/user_info.js" type="text/javascript" charset="utf-8"></script> -->
		<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
		<link href="css/userInfo.css" rel="stylesheet" type="text/css" />
		
		<style>
			input[type=text]{
				padding-left:2px;
			}
			textarea{
				font-size:16px;
				padding-left:2px;
				font-family:'微软雅黑';
			}
			
		</style>
		<script type="text/javascript">
			$.ajax({
				type:"get",
				url:"userInfo",
				dataType:"json",
				async:true,
				success:function(rs){
					$("#loginName").val(rs.loginName),
					/* $("#radio").checked(rs.sex),  */
					$("#phone").val(rs.telNum),
					$("#bornAdress").val(rs.address),
					$("#userInfo").val(rs.info);
				}
			});
		</script>
	</head>
	<body>
	<jsp:include page="index_head.jsp" />
		<div id="body_wrap">
			<div id="body_main">
				
				<div id="user_info_left">
					<div style="width: 100%;height: 160px;background:white;">
						<img src="/imgs/user/${User.icon}" style="width:150px;height:150px">
					</div>
					<div id="myInfoType">
					</div>
					<form id="updateHead" enctype="multipart/form-data">
						<input type="file" name="head" id="file" style="display: none;" accept="image/jpg,image/jpeg,image/png" >
						<label for="file">&nbsp;&nbsp;&nbsp;<img style="cursor:pointer ;" alt="修改头像" src="image/tx.png"/></label>
					</form>
					<div id="myInfoType">	
					</div>
					<div id="myInfoType">
					</div>
					<div id="myInfoType">
					</div>
					<div id="myInfoType">
					</div>
				</div>
				<form action="userInfo" method="post">
				<div id="user_info_right">
					<span>基本资料</span><br />
					<label>昵称：</label> 
					<input type="text" name="loginName" id="loginName"/><br /><br />
					
					<label>性别：</label>
					<input type="radio" name="sex" id="sex" value="女" 
					<c:if test="${sessionScope.User.sex == '女'}">
					checked="checked"
					</c:if>
					/>女
					<input type="radio" name="sex" id="sex" value="男"
					<c:if test="${sessionScope.User.sex == '男'}">
					checked="checked"
					</c:if>
					 />男<br /><br />
					<label>电话：</label> 
					<input type="text" name="phone" id="phone"/><br /><br />
					
					
					<span>其他信息</span><br />
					<label>个人标签：</label>
					<input type="text" name="infoType" id="infoType"/>
					<br /><br />
					<label>自我介绍：</label>
					<textarea name="userInfo" id="userInfo" rows="10px" cols="50px" ></textarea>
					<br />
					<input type="submit" value="确认修改" 
						style="align-content: center;color: white;margin: 20px 0 20px 100px;
						background: #FFB608;border-radius: 2px;border: none;width: 80px;height: 30px;
						cursor: pointer;"/>
				</div>
				</form>
			</div>
		</div>
		<script>
		$(function(){
			<%
			String type = request.getParameter("n");
			if("1".equals(type)){
				%>
				
				 alert("修改成功!"); 
				 window.location.replace("userInfo.jsp");
				<%
			}else if(type != null){
				%>	
				
				alert("修改失败!"); 
				window.location.replace("userInfo.jsp");
				<%}
			%>
			
		})
		$("#file").change(function(){
			var form = document.getElementById("updateHead");
			var formData = new FormData(form);
			$.ajax({
				url:"head.php",
				data: (formData),
				type : "post",
				async: true,  
				processData: false,  // 告诉jQuery不要去处理发送的数据
				contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
				success : function(rs){
					if(rs.trim() == "false"){
						alert("系统繁忙,稍后再试");
					}else{
					alert("头像修改成功");
					window.location.reload();
					}
				},
				error : function(){
					alert("系统繁忙,请稍后再试");
				}
			});
		})
		
		
		</script>
		
		
	</body>
</html>
<jsp:include page="login_foot.html" />

