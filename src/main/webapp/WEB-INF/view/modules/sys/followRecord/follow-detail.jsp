<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>

    <%@ include file="../../../include/head.jsp" %>
    
    <link href="${ctxStatic}/ligerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>

</head>

<body style="padding:5px">

    <form name="form1" method="post" action="" id="form1">
<div>
</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        
            <tr>
                <td colspan="2">
				    <div class="l-group l-group-hasicon">
					    <img src="${ctxStatic}/ligerUI/lib/ligerUI/skins/icons/communication.gif">
					    <span style="margin-left:0px; width:100px;">回访记录详情</span>
				    </div>
                </td>
            </tr>
        
            <tr>
                <td align="right" class="l-table-edit-td">回访人:</td>
                <td align="left" class="l-table-edit-td">${followRecord.loginName }</td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">回访时间:</td>
                <td align="left" class="l-table-edit-td">${followRecord.followTime }</td>
            </tr>
            
			<tr>
				<td align="right" class="l-table-edit-td">回访备注:&nbsp;</td>
				<td align="left" class="l-table-edit-td">
			        <textarea readonly style="border:none;" cols="50" rows="3" name="remark"  id="remark" class="l-textarea">${followRecord.remark }</textarea>
				</td>
			</tr>
			
        </table>
 
    </form>
    
    
</body>
</html>

    <script type="text/javascript">
        var openWindow;
        $(function (){
            $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                errorPlacement: function (lable, element){
                    lable.ligerHideTip();
                    lable.remove();
                    if (element.hasClass("l-textarea")){
                        element.ligerTip({ content: lable.html(), target: element[0] }); 
                    }
                    else if (element.hasClass("l-text-field")){
                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
                    }
                    else{
                        lable.appendTo(element.parents("td:first").next("td"));
                    }
                },
                success: function (lable){
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function (){
                    $("form .l-text,.l-textarea").ligerHideTip();
                    saveFrom();
                }
            });
            $("form").ligerForm();
        });  
        
        // 保存数据
        function saveFrom(){
        	var limitHtml = CKEDITOR.instances.limitHtml.getData();
        	if(limitHtml.length<=0){
        		$.ligerDialog.warn("请填写限额Html片段");
        	}
        	else{
        		    $("#limitHtml").val(limitHtml);
		            $.post("${ctx}/pay/bank/add.do", $("#form1").serialize(), function (msg) { 
		            	var obj = JSON.parse(msg); 
		                if(obj.code=="200"){
		                	$.ligerDialog.success("保存成功", "", function(){
		                	     window.parent.ligerManager.loadData();
		               	         window.parent.openWindow.close();
		               	    });
		                }
		                else if(msg.indexOf("notAuthorizationError")!=-1){
		                	$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
		                }
		                else{
		                	$.ligerDialog.error("保存失败，原因："+obj.msg);
		                }
		            });
        	}
            
        };
        
        // 验证数据
        function saveformSubmit(){
        	$("#form1").submit();
        }
    </script>
    
    <script src="${ctxStatic }/plugins/ckeditor/ckeditor.js" type="text/javascript">
	    jQuery.noConflict(); 
	</script>
	
	