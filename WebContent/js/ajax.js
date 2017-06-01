//页面加载时调用ajax
$(function() {

	ajax_keyup("");
	// 点击事件
	$("#div_body span").click(function() {
		ajax_keyup($("#text").val());
	});

	// 判断是否为enter键
	$(document).keydown(function(event) {
		if (event.keyCode == 13) {
			$("#div_body span").click();
		}
	});

})

// 设置鼠标样式
$("a").mousemove(function() {
	$("a").css("cursor", "pointer");
})

$("#div_body")
		.append(
				"<label id='lab'><input type='radio' name='radio' value=1 checked>用户名查询<input type='radio' name='radio' value=2>用户状态查询<label>")
// ajax获取数据
var ajax_keyup = function(value) {
	page = 1;
	current = 1;
	// 移除b标签
	$("b").remove();
	$
			.ajax({
				url : "userajax",
				type : "POST",
				dataType : "JSON",
				data : {
					"name" : value,
					"choice" : $("input[type='radio']:checked").val(),
					"current" : current
				},
				async : true,

				success : function(rs) {
					$("b").remove();
					if (rs != "" && rs != null) {
						page = rs.pageCount;
						$("#table").remove();
						$("#content_div")
								.append(
										"<table id='table' border=1 rowspadding=0 cellpadding=0 style='border-color: black;'><tr>"
												+ "<th>用户ID</th><th>登录名</th><th>电话号码</th><th>用户名</th><th>邮箱</th><th>性别</th><th>注册时间</th><th>收获地址</th><th>用户状态</th><th>操作</th></tr></table>");
						// 遍历json数组
						for (var i = 0; i < rs.list.length; i++) {
							$("#table")
									.append(
											"<tr><td>"
													+ rs.list[i].id
													+ "</td><td>"
													+ rs.list[i].loginName
													+ "</td><td>"
													+ rs.list[i].telNum
													+ "</td><td>"
													+ rs.list[i].name
													+ "</td><td>"
													+ rs.list[i].email
													+ "</td><td>"
													+ rs.list[i].sex
													+ "</td><td>"
													+ rs.list[i].register_time
													+ "</td>"
													+ "<td>"
													+ rs.list[i].address
													+ "</td><td>"
													+ (rs.list[i].status == 0 ? "正常"
															: "被冻结")
													+ "</td><td><button onclick='delete_User(this)' type='button' value="
													+ rs.list[i].id
													+ ">删除</button><button onclick='update_User(this)' type='button' value="
													+ rs.list[i].id
													+ ">"
													+ (rs.list[i].status == 0 ? "冻结"
															: "解冻")
													+ "</button></td></tr>");
						}
						$('#page_div').pagination({
							pageCount : page,
							current : current,
							coping : false,
							homePage : '首页',
							endPage : '末页',
							prevContent : '上页',
							nextContent : '下页',
							callback : function(index) {
								current = index.getCurrent();
								ajax_keyup($("#text").val());
							}
						});
					} else {
						$("#table").remove();
						$('#page_div').empty();
						$("#page_div").append("<b style = 'color:black;font-size:25px;text-alien:center;'>哦哦哦,还没有数据哦！</b>");
					}
				},
			});
}

// 点击用户管理
$("#user_a")
		.click(
				function() {
					$("#div_body span").remove();
					$("#div_body").append('<span>查询</span>');
					$("#lab").remove();
					$("#div_body")
							.append(
									"<label id='lab'><input type='radio' name='radio' value=1 checked>用户名查询<input type='radio' name='radio' value=2>用户状态查询<label>")
					ajax_keyup("");
					// 点击事件
					$("#div_body span").click(function() {
						$("b").remove();
						ajax_keyup($("#text").val());
					})

				})

// 点击店铺管理事件
$("#store_a")
		.click(
				function() {
					$("#div_body span").remove();
					$("#div_body").append('<span>查询</span>');
					page = 1;
					current = 1;
					$("b").remove();
					$("#lab").remove();
					$("#div_body")
							.append(
									"<label id='lab'><input type='radio' name='radio' value=1 checked>店铺名查询<input type='radio' name='radio' value=2>店铺状态查询<label>")
					// ajax处理函数
					store_a = function(value) {
						/* $("#form").submit(); */
						$
								.ajax({
									url : "storeAjax",
									type : "POST",
									dataType : "JSON",
									data : {
										"name" : value,
										"choice" : $(
												"input[type='radio']:checked")
												.val(),
										"current" : current
									},
									async : true,

									success : function(rs) {
										page = rs.pageCount;
										$("b").remove();
										if (rs != "" && rs != null && rs.list.length != 0) {
											$("#table").remove();
											$("#content_div")
													.append(
															"<table id='table' border=1 rowspadding=0 cellpadding=0 style='border-color: black;'><tr>"
																	+ "<th>店铺ID</th><th>店铺名</th><th>商家地址</th><th>店铺邮箱</th><th>店铺电话</th><th>店铺状态</th><th>商家ID</th><th>操作</th></tr></table>");
											for (var i = 0; i < rs.list.length; i++) {
												var p = "";
												var c = "";
												if(rs.list[i].status==0){
													p="正常";
													c = "冻结";
													}else if (rs.list[i].status==1) {
													p="被冻结";
													c="解冻";
												}else if(rs.list[i].status==2) {
													p="审核中";
													c="审核";
												}else if(rs.list[i].status==3){
													p="已注销";
													c="";
												} else if(rs.list[i].status==4){
													p="申请注销";
													c="注销";
												}
												$("#table")
														.append(
																"<tr><td>"
																		+ rs.list[i].id
																		+ "</td><td>"
																		+ rs.list[i].name
																		+ "</td><td>"
																		+ rs.list[i].address
																		+ "</td><td>"
																		+ rs.list[i].email
																		+ "</td><td>"
																		+ rs.list[i].telNum
																		+ "</td><td>"
																		+p
																		+ "</td><td>"
																		+ rs.list[i].user_id
																		+ "</td><td><button onclick='delete_store(this)' type='button' value="
																		+ rs.list[i].id
																		+ ">删除</button><button onclick='update_store(this)' type='button' value="
																		+ rs.list[i].id
																		+ ">"
																		+ c
																		+ "</button></td></tr>");
											}
											$('#page_div')
													.pagination(
															{
																pageCount : page,
																current : current,
																coping : false,
																homePage : '首页',
																endPage : '末页',
																prevContent : '上页',
																nextContent : '下页',
																callback : function(
																		index) {
																	current = index
																			.getCurrent();
																	store_a($(
																			"#text")
																			.val());
																}
															});
										} else {
											$("#table").remove();
											$('#page_div').empty();
											$("#page_div").append("<b style = 'color:black;font-size:25px;text-alien:center;'>哦哦哦,还没有数据哦！</b>");
										}
									},
								});
					}

					store_a("");

					// 点击事件
					$("#div_body span").click(function() {
						$("b").remove();
						store_a($("#text").val());
					})
					// 判断是否为enter键
					$(document).keydown(function(event) {
						if (event.keyCode == 13) {
							$("#div_body span").click();
						}
					});
				})

// 点击订单管理事件
$("#order_a")
		.click(
				function() {
					$("#div_body span").remove();
					$("#div_body").append('<span>查询</span>');
					page = 1;
					current = 1;
					$("b").remove();
					$("#lab").remove();
					$("#div_body")
							.append(
									"<label id='lab'><input type='radio' name='radio' value=1 checked>订单编号查询<input type='radio' name='radio' value=2>订单状态查询<label>")
					// ajax处理函数
					var order_a = function(value) {
						/* $("#form").submit(); */
						$
								.ajax({
									url : "orderAjax",
									type : "POST",
									dataType : "JSON",
									data : {
										"name" : value,
										"choice" : $(
												"input[type='radio']:checked")
												.val(),
										"current" : current
									},
									async : true,

									success : function(rs) {

										$("b").remove();
										if (rs != "" && rs != null && rs.list.length != 0) {

											page = rs.pageCount;
											$("#table").remove();

											// 添加表头
											$("#content_div")
													.append(
															"<table id='table' border=1 rowspadding=0 cellpadding=0 style='border-color: black;'><tr>"
																	+ "<th>订单id</th><th>商品名</th><th>收货人</th><th>收货地址</th><th>手机号码</th><th>购买id</th><th>店铺编号</th><th>商品价格</th><th>购买数量</th><th>总价</th><th>订单时间</th><th>订单状态</th></tr></table>");
											// 循环添加表格
											for (var i = 0; i < rs.list.length; i++) {
												for (var j = 0; j < rs.list[i].list.length; j++) {
													$("#table")
															.append(
																	"<tr><td>"
																			+ rs.list[i].id
																			+ "</td><td>"
																			+ rs.list[i].list[j].good_name
																			+ "</td><td>"
																			+ rs.list[i].receive_name
																			+ "</td><td>"
																			+ rs.list[i].full_address
																			+ "</td><td>"
																			+ rs.list[i].mobile
																			+ "</td><td>"
																			+ rs.list[i].user_id
																			+ "</td><td>"
																			+ rs.list[i].store_id
																			+ "</td><td>"
																			+ rs.list[i].list[j].goods_price
																			+ "</td><td>"
																			+ rs.list[i].list[j].goods_num
																			+ "</td><td>"
																			+ rs.list[i].totalPrice
																			+ "</td><td>"
																			+ rs.list[i].orderTime
																			+ "</td><td>"
																			+ rs.list[i].status_id
																			+ "</td></tr>");
												}
											}
											$('#page_div')
													.pagination(
															{
																pageCount : page,
																current : current,
																coping : false,
																homePage : '首页',
																endPage : '末页',
																prevContent : '上页',
																nextContent : '下页',
																callback : function(
																		index) {
																	current = index
																			.getCurrent();
																	order_a($(
																			"#text")
																			.val());
																}
															});
										} else {
											$("#table").remove();
											$('#page_div').empty();
											$("#page_div").append("<b style = 'color:black;font-size:25px;text-alien:center;'>哦哦哦,还没有数据哦！</b>");
										}
									},
								});
					}

					order_a("");
					// 点击事件
					$("#div_body span").click(function() {
						$("b").remove();
						order_a($("#text").val());
					})
					// 判断是否为enter键
					$(document).keydown(function(event) {
						if (event.keyCode == 13) {
							$("#div_body span").click();
						}
					});
				})

// 点击类别管理事件
$("#categroy_a")
		.click(
				function() {
					$("#div_body span").remove();
					$("#div_body").append('<span>查询</span>');
					page = 1;
					current = 1;
					$("#lab").remove();
					// ajax处理函数
					var categroy_a = function(value) {
						$
								.ajax({
									url : "categroyAjax",
									type : "POST",
									dataType : "JSON",
									data : {
										"name" : value,
										"current" : current
									},
									async : true,

									success : function(rs) {
										page = rs.pageCount;
										if (rs != "" && rs != null && rs.list.length != 0) {
											$("b").remove();
											$("#table").remove();
											$("#content_div")
													.append(
															"<table id='table' border=1 rowspadding=0 cellpadding=0 style='border-color: black;'><tr>"
																	+ "<th>类别ID</th><th>类别名</th><th>描述</th><th>父类id</th></tr></table>");
											for (var i = 0; i < rs.list.length; i++) {

												$("#table")
														.append(
																"<tr><td>"
																		+ rs.list[i].id
																		+ "</td><td>"
																		+ rs.list[i].name
																		+ "</td><td>"
																		+ rs.list[i].describe
																		+ "</td><td>"
																		+ rs.list[i].parent_id
																		+ "</td></tr>");
											}
											$('#page_div')
													.pagination(
															{
																pageCount : page,
																current : current,
																coping : false,
																homePage : '首页',
																endPage : '末页',
																prevContent : '上页',
																nextContent : '下页',
																callback : function(
																		index) {
																	current = index
																			.getCurrent();
																	categroy_a($(
																			"#text")
																			.val());
																}
															});
										} else {
											$("#table").remove();
											$('#page_div').empty();
											$("#page_div").append("<b style = 'color:black;font-size:25px;text-alien:center;'>哦哦哦,还没有数据哦！</b>");
										}
									},
								});
					}

					categroy_a("");
					// 点击事件
					$("#div_body span").click(function() {
						$("b").remove();
						categroy_a($("#text").val());

					})
					// 判断是否为enter键
					$(document).keydown(function(event) {
						if (event.keyCode == 13) {
							$("#div_body span").click();

						}
					});

				})
