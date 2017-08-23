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
            $.post("${ctx}/role/roleAdd.do", $("#form1").serialize(), function (msg) { 
                if(msg=="true"){
                	$.ligerDialog.success("保存成功", "", function(){
                	     window.parent.ligerManager.loadData();
               	         window.parent.openWindow.close();
               	    });
                }
                else if(msg=="nameAlreadyExist"){
                	$.ligerDialog.warn("此角色名称已存在，请更改角色名称");
                }
                else if(msg.indexOf("notAuthorizationError")!=-1){
                	$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
                }
                else{
                	$.ligerDialog.error("保存失败");
                }
            });
        }
        
        // 验证数据
        function saveFormSubmit(){
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
                <td align="right" class="l-table-edit-td">角色名称:</td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" name="name" type="text" id="name" ltype="text" validate="{required:true,maxlength:100}" /></td>
                <td align="left"></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">角色描述:</td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" name="description" type="text" id="description" ltype="text" validate="{required:true,maxlength:200}" /></td>
                <td align="left"></td>
            </tr>
            
            
        </table>

    </form>
    
    <input type="button" value="保存" onclick="saveFormSubmit()" style="background: #e0edff;" class="l-button l-button-submit vertical-center-user-defined" /> 

</body>
</html>
