$(document).ready(function() {

	//下边框移动
	$('.btn-switch-cart').mouseenter(function(event) {
		$('.btn-switch-cart').removeClass('selectColumn');
		$(this).addClass('selectColumn');
	}).mouseleave(function(event) {
		$('.btn-switch-cart').removeClass('selectColumn');
		$('.switch-cart-0').addClass('selectColumn');
	});

	//卖家促销下拉列表
	$('body').on('mouseenter mouseleave','.promotion',function(event){
		if (event.type==='mouseenter') {
			$(this).siblings('.proSlidedown').stop().show('fast');
		} else {
			$(this).siblings('.proSlidedown').stop().hide('fast');
		}
	});

	
	

	//商品库存
	function stock(that){
		var $stock = parseInt(that.parent('.item-amount').siblings('.stock').text());
		var thisNum = that.parent().find('input').val();
		var thisOutNum = that.parent().siblings('.outNum');
		var thisStockNum = thisOutNum.find('.stockNum');
		if (parseInt(thisNum) > $stock) {
			thisOutNum.show('fast');
		} else {
			thisOutNum.hide('fast');
		}
		thisStockNum.text($stock);
	}



	//商品数量的输入框
	$('body').on('keypress keyup blur','.item-amount input',function(event){
		if (event.type==='keypress') {
			var thisParent = $(this).parents('.td-amount');
			var thisInput = $(this).parent('.item-amount');
			var $text = thisParent.siblings('.td-price').find('span').text();
			var tdSum = thisParent.find('.td-sum').children('span');
			var keyCode = event.keyCode ? event.keyCode : event.charCode ;
			if (keyCode !== 0 && (keyCode <48 || keyCode >57) && keyCode!==8 && keyCode !==37 && keyCode !==39 && keyCode !==46) {
				return false;
			} else {
				return true;
			};
		} else if(event.type ==='keyup'){
			stock($(this));
			var $stock = parseInt($(this).parent('.item-amount').siblings('.stock').text());
			var thisParent = $(this).parents('.td-amount');
			var thisInput = $(this).parent('.item-amount');
			var $text = thisParent.siblings('.td-price').find('span').text();
			var tdSum = thisParent.siblings('.td-sum').find('span');
			var keyCode = event.keyCode ? event.keyCode : event.charCode ;
			if (keyCode !== 8) {
				var num = parseInt($(this).val()) || 0;
				if (num < 1) {
					num = 1;
				} else if(num > $stock){
					num = $stock;
				} else {
					num = num;
				}
				var num = $(this).val();
				tdSum.text($text * num + '.00');
			};
			var anNum = $(this).val();
			tdSum.text($text * anNum +'.00');
			getCount();
		} else {
			stock($(this));
			var $stock = parseInt($(this).parent('.item-amount').siblings('.stock').text());
			var thisParent = $(this).parents('.td-amount');
			var thisInput = $(this).parent('.item-amount');
			var $text = thisParent.siblings('.td-price').find('span').text();
			var tdSum = thisParent.siblings('.td-sum').find('span');
			var keyCode = event.keyCode ? event.keyCode : event.keyCode;
			var num = parseInt($(this).val()) || 0;
			if (num > $stock) {
				num = $stock;
			} else if(num < 1){
				num = 1;
			} else {
				num = num;
			}
			$(this).val(num);
			var anNum = $(this).val();
			tdSum.text($text * anNum +'.00');
			getCount();
		}
	});

	//商品数量增加
	$('body').on('click','.amount-right',function(event){
		var $stock = parseInt($(this).parent('.item-amount').siblings('.stock').text());
		var thisParent = $(this).parents('.td-amount');
		var thisInput = $(this).parent('.item-amount');
		var $text = thisParent.siblings('.td-price').find('span').text();
		var tdSum = thisParent.siblings('.td-sum').find('span');
		var num = thisInput.find('input').val();
		if (num < $stock) {
			num++;
			thisInput.find('input').val(num);
		} else {
			$(this).parent().siblings('.outNum').show('fast');
		}
		$('.amount-left').css({
			'cursor':'pointer',
			'color':'#444'
		})
		tdSum.text($text * num + '.00');
		getCount();
		// stock($(this));
		return false;
	});

	//商品数量减少
	$('body').on('click','.amount-left',function(event){
		var $stock = parseInt($(this).parent('.item-amount').siblings('.stock').text());
		var thisParent = $(this).parents('.td-amount');
		var thisInput = $(this).parent('.item-amount');
		var $text = thisParent.siblings('.td-price').find('span').text();
		var tdSum = thisParent.siblings('.td-sum').find('span');
		var num = parseInt(thisInput.find('input').val());
		if (num > 1 ) {
			num-1;
			thisInput.find('input').val(num);
		}
		tdSum.text($text * num +'.00');
		getCount();
		stock($(this));
		return false;
	});

	//全选商品金额相加
	function sumTotal(){
		var $tdSum = $('.td-sum').find('span').text().split('.00');
		var $total=0;
		for (var i = 0; i < $tdSum.length-1; i++) {
			$total+=parseInt($tdSum[i]);
		}
		return $total;
	}


	//获得已选中商品和商品价格总额
	function getCount(){
		var counts = 0;
		var sum = 0;
		$('.td-inner input').each(function(index, el) {
			if ($(this).prop('checked')) {
				for (var i = 0; i < $(this).length; i++) {   //找元素
					counts += parseInt($(this).parents('.td-chk').siblings('.td-sum').find('span').text());
					sum += 1;
				}
			}
		});
		$('.totalSum').text(sum);
		$('.total-sum').html((counts).toFixed(2));
		$('.total-symbol').html((counts).toFixed(2));
	};

	// 删除批量 --获取以选中的商品
	$(".delete-all").click(function(){
		var checkeds = false;
		
		$('.td-inner input').each(function(index, el) {
			if ($(this).prop('checked')) {
				checkeds = true;
			}
		});
		
		//判断是否有被选中
		if(checkeds){
			var choice2 = confirm("确定删除？");
			if(choice2){
				//遍历删除
				$('.td-inner input').each(function(index, el) {
					if ($(this).prop('checked')) {
						for (var i = 0; i < $(this).length; i++) {
							var thisInfo = $(this).parents('.mainCommodity');
							
							//获取购物项编号 cart编号
							//alert(thisInfo.find(".delete span").text());
							//调用ajax
							$.post("CartServlet.php",{"choice":2,"cart_id":thisInfo.find(".delete span").text()},function(rs){
								alert(rs=="true"?"删除成功！":"删除成功！");
							});
							
							thisInfo.detach();
						}
					}
				});
			}else{
				return false;
			}
		}else{
			alert("请先选中商品！");
			return false;
		}
	});
	
	//商品全选
	$('.selectAll').on('click', '.allSelected1', function(event) {
		if ($(this).prop('checked')) {
			$(':checkbox').prop('checked',true);
			$('.commodityInfo').css({
				'background-color':'#FFF8E1'
			});
			$('.submit-btn').css({
				'background-color':'#f40',
				'cursor':'pointer'
			});
			$('#btn-sum').css({
				'background-color':'#f40',
				'cursor':'pointer'
			});
		} else {
			$(':checkbox').prop('checked',false);
			$('.commodityInfo').css({
				'background-color':'#fcfcfc'
			});
			$('.submit-btn').css({
				'background-color':'#aaa',
				'cursor':'not-allowed'
			})
			$('#btn-sum').css({
				'background-color':'#aaa',
				'cursor':'not-allowed'
			})
		}
		getCount();
	});

	//未选中商品时禁止点击
	$('.submit-btn').click(function(event) {
		return false;
	});
	$('#btn-sum').click(function(event) {
		return false;
	});


	//fixed中的全选按钮
	$('.all-selected').on('click', '.allSelected2', function(event) {
		if ($(this).prop('checked')) {
			$(':checkbox').prop('checked',true);
			$('.commodityInfo').css({
				'background-color':'#FFF8E1'
			});
			$('.submit-btn').css({
				'background-color':'#f40',
				'cursor':'pointer'
			})
			$('#btn-sum').css({
				'background-color':'#f40',
				'cursor':'pointer'
			})
		} else {
			$(':checkbox').prop('checked',false);
			$('.commodityInfo').css({
				'background-color':'#fcfcfc'
			});
			$('.submit-btn').css({
				'background-color':'#aaa',
				'cursor':'not-allowed'
			})
			$('#btn-sum').css({
				'background-color':'#aaa',
				'cursor':'not-allowed'
			})
		}
		getCount();
	});




	//取消全选
	function cancelSelect(){
		if ($('.td-inner input').length === $('.td-inner input:checked').length) {
			$('.allSelected1').prop('checked',true);
			$('.allSelected2').prop('checked',true);
		} else {
			$('.allSelected1').prop('checked',false);
			$('.allSelected2').prop('checked',false);
		}
	}

	//如果有商品未选中，则取消全选。
	function cancelCalculator(){
		if ($('.td-inner input:checked').length === 0) {
			$('#btn-sum').css({
				'background-color':'#aaa',
				'cursor':'not-allowed'
			});
			$('.submit-btn').css({
				'background-color':'#aaa',
				'cursor':'not-allowed'
			});
		} else {
			$('#btn-sum').css({
				'background-color':'#f40',
				'cursor':'pointer'
			});
			$('.submit-btn').css({
				'background-color':'#f40',
				'cursor':'pointer'
			})
		}
	}


	//点击某商品时选中
	$('body').on('click','.td-inner input',function(event){
		if ($(this).prop('checked')) {
			$(this).parents('.commodityInfo').siblings('.shopInfo').find('input').prop('checked',true);
			$(this).parents('.commodityInfo').css({
				'background-color':'#fff8e1'
			});
		} else {
			$(this).parents('.commodityInfo').siblings('.shopInfo').find('input').prop('checked',false);
			$(this).parents('.commodityInfo').css({
				'background-color':'#fcfcfc'
			});
		}
		cancelCalculator();
		cancelSelect();
		getCount();
	});

	//点击某商品时选中
	$('body').on('click','.shopInfo input',function(event){
		if ($(this).prop('checked')) {
			$(this).parents('.shopInfo').siblings('.commodityInfo').find('.td-inner input').prop('checked',true);
			$(this).parents('.shopInfo').siblings('.commodityInfo').css({
				'background-color':'#fff8e1'
			});
		} else {
			$(this).parents('.shopInfo').siblings('.commodityInfo').find('.td-inner input').prop('checked',false);
			$(this).parents('.shopInfo').siblings('.commodityInfo').css({
				'background-color':'#fcfcfc'
			});
		}
		cancelCalculator();
		cancelSelect();
		getCount();
	});
















/********************MOBILE***********************/

	//mobile中fixed全选
	$('.all-selected1').on('click','.allSelected2',function(event){
		if ($(this).prop('checked')) {
			$(':checkbox').prop('checked',true);
			$('.commodityInfo').css({
				'background-color':'#FFF8E1'
			});
		} else {
			$(':checkbox').prop('checked',false);
			$('.commodityInfo').css({
				'background-color':'#fcfcfc'
			});
		}
		getCountMobile();
	})

	//mobile中点击某商品时选中
	$('body').on('click','.td-inner1 input',function(event){
		if ($(this).prop('checked')) {
			$(this).parents('.commodityInfo').siblings('.shopInfo1').find('input').prop('checked',true);
			$(this).parents('.commodityInfo').css({
				'background-color':'#fff8e1'
			});
		} else {
			$(this).parents('.commodityInfo').siblings('.shopInfo1').find('input').prop('checked',false);
			$(this).parents('.commodityInfo').css({
				'background-color':'#fcfcfc'
			});
		}
		cancelSelectMobile();
		getCountMobile();
	});

	//mobile中点击某商品时选中
	$('body').on('click','.shopInfo1 input',function(event){
		if ($(this).prop('checked')) {
			$(this).parents('.shopInfo1').siblings('.commodityInfo').find('.td-inner1 input').prop('checked',true);
			$(this).parents('.shopInfo1').siblings('.commodityInfo').css({
				'background-color':'#fff8e1'
			});
		} else {
			$(this).parents('.shopInfo1').siblings('.commodityInfo').find('.td-inner1 input').prop('checked',false);
			$(this).parents('.shopInfo1').siblings('.commodityInfo').css({
				'background-color':'#fcfcfc'
			});
		}
		cancelSelectMobile();
		getCountMobile();
	});

	//商品数量的输入框
	$('body').on('keypress keyup blur','.changeCount input',function(event){
		if (event.type==='keypress') {
			var thisParent = $(this).parents('.td-amount');
			var thisInput = $(this).parent('.item-amount');
			var $text = thisParent.siblings('.td-price').find('span').text();
			var tdSum = thisParent.find('.td-sum').children('span');
			var keyCode = event.keyCode ? event.keyCode : event.charCode ;
			if (keyCode !== 0 && (keyCode <48 || keyCode >57) && keyCode!==8 && keyCode !==37 && keyCode !==39 && keyCode !==46) {
				return false;
			} else {
				return true;
			};
		} else if(event.type ==='keyup'){
			var thisParent = $(this).parents('.td-amount');
			var thisInput = $(this).parent('.item-amount');
			var $text = thisParent.siblings('.td-price').find('span').text();
			var tdSum = thisParent.siblings('.td-sum').find('span');
			var keyCode = event.keyCode ? event.keyCode : event.charCode ;
			if (keyCode !== 8) {
				var num = parseInt($(this).val()) || 0;
				num = num < 1 ? 1 : num;
				var num = $(this).val();
				tdSum.text($text * num + '.00');
			};
			var anNum = $(this).val();
			tdSum.text($text * anNum +'.00');
			getCount();
		} else {
			var thisParent = $(this).parents('.td-amount');
			var thisInput = $(this).parent('.item-amount');
			var $text = thisParent.siblings('.td-price').find('span').text();
			var tdSum = thisParent.siblings('.td-sum').find('span');
			var keyCode = event.keyCode ? event.keyCode : event.keyCode;
			var num = parseInt($(this).val()) || 0;
			num = num < 1 ? 1 : num ;
			$(this).val(num);
			var anNum = $(this).val();
			tdSum.text($text * anNum +'.00');
			getCount();
		}
	});




	//Mobile商品数量增加
	$('body').on('click','.plusCount',function(event){
		var thisParentS = $(this).parents('.shopInfo1').siblings('.commodityInfo');
		var unitPrice = thisParentS.find('.unitPrice').text();
		var thisInput = $(this).parent('.changeCount');
		var $sum = thisParentS.find('.discount');
		var $text = thisParentS.find('.discount').text();
		var num = thisInput.find('input').val();
		num++;
		thisInput.find('input').val(num);
		$sum.text(unitPrice * num + '.00');
		getCountMobile();
		return false;
	});

	//Mobile商品数量减少
	$('body').on('click','.substrCount',function(event){
		var thisParentS = $(this).parents('.shopInfo1').siblings('.commodityInfo');
		var unitPrice = thisParentS.find('.unitPrice').text();
		var thisInput = $(this).parent('.changeCount');
		var $sum = thisParentS.find('.discount');
		var $text = thisParentS.find('.discount').text();
		var num = thisInput.find('input').val();
		if (num>1) {
			num--;
			thisInput.find('input').val(num);
		}
		$sum.text(unitPrice * num + '.00');
		getCountMobile();
		return false;
	});

	//Mobile商品数量减少
	$('body').on('click','.amount-left',function(event){
		var thisParent = $(this).parents('.td-amount');
		var thisInput = $(this).parent('.item-amount');
		var $text = thisParent.siblings('.td-price').find('span').text();
		var tdSum = thisParent.siblings('.td-sum').find('span');
		var num = thisInput.find('input').val();
		if (num > 1 ) {
			num--;
			thisInput.find('input').val(num);
		}
		tdSum.text($text * num +'.00');
		getCount();
		return false;
	});

	//mobile中取消全选
	function cancelSelectMobile(){
		if ($('.td-inner1 input').length === $('.td-inner1 input:checked').length) {
			$('.allSelected2').prop('checked',true);
		} else {
			$('.allSelected2').prop('checked',false);
		}
	}


	//mobile中获得已选中商品和商品价格总额
	function getCountMobile(){
		var counts = 0;
		var sum = 0;
		$('.td-inner1 input').each(function(index, el) {
			if ($(this).prop('checked')) {
				for (var i = 0; i < $(this).length; i++) {
					counts += parseInt($(this).parents('.td-chk').siblings('.td-info1').find('.discount').text());
					sum += 1;
				}
			}
		});
		$('.totalSum').text(sum);
		$('.total-sum').html((counts).toFixed(2));
	};




});
