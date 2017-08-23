//项目前缀
var urlPrefix = $("#urlPrefix").val();

/**
 * 通讯组件
 */
var req = {
		// 发送请求
		send:function(url ,params, successHandler, errorHandler) {
			$.ajax({
				type: 'POST',
				url: url,
				data: params,
				//dataType: "json",
				success:function(result){
					if(successHandler!=undefined){
						successHandler(result);
					} else if(errorHandler!=undefined){
						errorHandler(result);
					}else{
						//alert('111'+result.message);
						console.log(result.message);
					}
				},
				error:function(xhr){
					console.log(xhr.status);
				}  	  
			});
			
		},
		//url字典
		dictionary:{
			"notGoodsRefund": urlPrefix + "/bullion/order/notGoodsRefund.do", // 金饰订单：无货退款
			"sendGoods": urlPrefix + "/bullion/order/sendGoods.do", // 金饰订单：发货
			"addRemark": urlPrefix + "/bullion/order/addRemark.do", // 金饰订单：备注
			"closeOrder": urlPrefix + "/bullion/order/closeOrder.do", // 金饰订单：关闭订单
			"acceptance": urlPrefix + "/bullion/order/acceptance.do", // 金饰订单：受理
			"refundrefuse": urlPrefix + "/bullion/order/refundrefuse.do", // 金饰订单：退货拒绝
			"refundPass": urlPrefix + "/bullion/order/refundPass.do", // 金饰订单：退货通过
			"fixedProductOffline": urlPrefix + "/gold/fixedProduct/fixedProductOffline.do", // 定期产品下线操作
			"feedbackAddRemark": urlPrefix + "/feedback/addRemark.do"// 反馈信息添加备注
		},
		
		invoke:function(method,params){
			var url=this.dictionary[method];
			this.send(url, params, successHandler, errorHandler);
		},
		invokeChildren:function(method,params){
			var url=this.dictionary[method];
			this.send(url, params, successHandlerChildren, errorHandler);
		}
}

function errorHandler(msg){
	
}

// 后台服务成功结果
function successHandler(msg){
	if(msg.indexOf("notAuthorizationError")!=-1){
        $.ligerDialog.warn("权限异常，你是否具有此功能权限？");
	}
	else{
	    var obj=JSON.parse(msg);
        if (obj.code == 200) {
    	    $.ligerDialog.success("保存成功", "", function(){
    	        ligerManager.loadData();
   	        });
        }
        else{
            $.ligerDialog.error("保存失败，原因："+obj.msg);
        }
	}
}

// 后台服务成功结果
function successHandlerChildren(msg){
	if(msg.indexOf("notAuthorizationError")!=-1){
		$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
	}
	else{
		var obj=JSON.parse(msg);
		if (obj.code == 200) {
			$.ligerDialog.success("保存成功", "", function(){
       	        window.parent.ligerManager.loadData();
   	            window.parent.openWindow.close();
			});
		}
		else{
			$.ligerDialog.error("保存失败，原因："+obj.msg);
		}
	}
}

function formatNumber(num, pattern) {
	var strarr = num ? num.toString().split('.') : [ '0' ];
	var fmtarr = pattern ? pattern.split('.') : [ '' ];
	var retstr = '';

	// 整数部分
	var str = strarr[0];
	var fmt = fmtarr[0];
	var i = str.length - 1;
	var comma = false;
	for ( var f = fmt.length - 1; f >= 0; f--) {
		switch (fmt.substr(f, 1)) {
		case '#':
			if (i >= 0)
				retstr = str.substr(i--, 1) + retstr;
			break;
		case '0':
			if (i >= 0)
				retstr = str.substr(i--, 1) + retstr;
			else
				retstr = '0' + retstr;
			break;
		case ',':
			comma = true;
			retstr = ',' + retstr;
			break;
		}
	}
	if (i >= 0) {
		if (comma) {
			var l = str.length;
			for (; i >= 0; i--) {
				retstr = str.substr(i, 1) + retstr;
				if (i > 0 && ((l - i) % 3) == 0)
					retstr = ',' + retstr;
			}
		} else
			retstr = str.substr(0, i + 1) + retstr;
	}

	retstr = retstr + '.';
	// 处理小数部分
	str = strarr.length > 1 ? strarr[1] : '';
	fmt = fmtarr.length > 1 ? fmtarr[1] : '';
	i = 0;
	for ( var f = 0; f < fmt.length; f++) {
		switch (fmt.substr(f, 1)) {
		case '#':
			if (i < str.length)
				retstr += str.substr(i++, 1);
			break;
		case '0':
			if (i < str.length)
				retstr += str.substr(i++, 1);
			else
				retstr += '0';
			break;
		}
	}
	return retstr.replace(/^,+/, '').replace(/\.$/, '');
}

$.ligerDefaults.Grid.formatters['amount'] = function(num, column) {
	return formatNumber(num, "#,##0.00");
};

