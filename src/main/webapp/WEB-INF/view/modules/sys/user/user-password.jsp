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

    <script type="text/javascript">
        var eee;
        $(function ()
        {
            $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                errorPlacement: function (lable, element)
                {
                    lable.ligerHideTip();
                    lable.remove();
                    
                    if (element.hasClass("l-textarea"))
                    {
                        element.ligerTip({ content: lable.html(), target: element[0] }); 
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
                    }
                    else
                    {
                        lable.appendTo(element.parents("td:first").next("td"));
                    }
                },
                success: function (lable)
                {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function ()
                {
                    $("form .l-text,.l-textarea").ligerHideTip();
                    saveFrom();
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function ()
            {
                alert(v.element($("#txtName")));
            });
        });  
        
        // 保存数据
        function saveFrom(){
        	var newPassword = $("#newPassword").val();
        	var newPassword2 = $("#newPassword2").val();
        	if(newPassword!=newPassword2){
        		$.ligerDialog.warn("新密码两次输入不一致");
        	}
        	else{
        		
            $.post("${ctx}/user/updatePassword.do", $("#form1").serialize(), function (msg) { 
                if(msg=="true"){
                	$.ligerDialog.success("修改密码成功", "", function(){
                		window.parent.outSystem();
               	    });
                }
                else if(msg=="oldPasswordError"){
                	$.ligerDialog.warn("原密码输入错误");
                }
                else if(msg.indexOf("notAuthorizationError")!=-1){
                	$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
                }
                else{
                	$.ligerDialog.error("修改密码失败");
                }
            });
            
        	}
        }
        
        // 验证数据
        function updatePasswordFormSubmit(){
        	$("#form1").submit();
        }
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>

</head>

<body style="padding:10px">

    <form name="form1" method="post" action="" id="form1">
<div>
</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        
            <tr>
                <td align="right" class="l-table-edit-td">原密码:</td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" value="" name="oldPassword" type="password" id="oldPassword" ltype="text" validate="{required:true,maxlength:50}" /></td>
                <td align="left"></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">新密码:</td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" value="" name="newPassword" type="password" id="newPassword" ltype="text" validate="{required:true,maxlength:50}" /></td>
                <td align="left"></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">确认新密码:</td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" value="" type="password" id="newPassword2" ltype="text" validate="{required:true,maxlength:50}" /></td>
                <td align="left"></td>
            </tr>
            
            
        </table>
 
    </form>
    
<input type="button" value="保存" onclick="updatePasswordFormSubmit()" style="background: #e0edff;" class="l-button l-button-submit vertical-center-user-defined" /> 
  
</body>
</html>
