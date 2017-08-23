<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<%@ include file="../../../include/head.jsp"%>
<link href="${ctxStatic}/ligerUI/lib/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>

</head>

<body style="padding:10px">

	<form name="form1" method="post" action="" id="form1">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<!-- 主键 -->
			<input type="hidden" id="id" name="id" value="${id}">
			<input type="hidden" id="customerId" name="customerId"/>

			<tr>
				<td align="right" class="l-table-edit-td">客户手机号:<span
					style="color:red;">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3"><input
					id="mobilePhone" type="text" ltype="text" name="mobilePhone" validate="{required:true}"/></td>
			</tr>

			<tr>
				<td align="right" class="l-table-edit-td">客户姓名:</td>
				<td align="left" class="l-table-edit-td" colspan="3"><span id="realName"></span></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td align="center" class="l-table-edit-td" colspan="4"><input
					type="button" onclick="saveformSubmit()" value="保存" id="Button1"
					class="l-button" style="background: #e0edff;" /></td>
			</tr>
		</table>

	</form>


</body>
</html>
<script type="text/javascript">
	var openWindow;
	var regMobile = "/^0?1[3|4|5|8][0-9]\d{8}$/";
	$(function() {
		$.metadata.setType("attr", "validate");
		var v = $("form").validate({
			errorPlacement : function(lable, element) {
				lable.ligerHideTip();
				lable.remove();
				if (element.hasClass("l-textarea")) {
					element.ligerTip({
						content : lable.html(),
						target : element[0]
					});
				} else if (element.hasClass("l-text-field")) {
					element.parent().ligerTip({
						content : lable.html(),
						target : element[0]
					});
				} else {
					lable.appendTo(element.parents("td:first").next("td"));
				}
			},
			success : function(lable) {
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler : function() {
				$("form .l-text,.l-textarea").ligerHideTip();
				saveFrom();
			}
		});
		$("form").ligerForm();
	});

	// 保存数据
	function saveFrom() {
		
		var customerId = $("#customerId").val();
		if(customerId == ""){
			$.ligerDialog.warn("该客户不存在，请核对！");
			return;
		}
		
		var param = {};
 	    param.customerId = customerId;
 	    param.id = $("#id").val();

		$.post("${ctx}/user/relation.do", param, function(msg) {
			var obj = JSON.parse(msg);
			if (obj.code == "200") {
				$.ligerDialog.success("平台账户关联成功！", "", function() {
					window.parent.ligerManager.loadData();
					window.parent.openWindow.close();
				});
			} else if (msg.indexOf("notAuthorizationError") != -1) {
				$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
			} else {
				$.ligerDialog.error("修改失败，原因：" + obj.msg);
			}
		});

	};
	
	
    $(function(){  
        $('#mobilePhone').bind('input propertychange', function() {  
           var phone = $(this).val();
           if(phone.length == 11){
        	   var param = {};
        	   param.mobilePhone = phone;
        	   $.post("${ctx}/user/queryUser.do", param, function(msg) {
       			var obj = JSON.parse(msg);
       			if (obj.code == "200") {
       				var html = "";
       				if(obj.data.realnameStatus == 20){
       					html = obj.data.realname;
       				}
       				else{
       					html = "未实名";
       				}
       				$("#realName").html(html);
       				$("#customerId").val(obj.data.customerId);
       			} else {
       				$("#realName").html("该客户不存在，请核对！");
       			}
       		});
           }
        });  
    })  
	
	// 验证数据
	function saveformSubmit() {
		$("#form1").submit();
	}
</script>