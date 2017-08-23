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

<body style="padding:10px">

    <form name="form1" method="post" action="" id="form1">
<div>
</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        
            <input type="hidden" name="id" id="id" value="${channel.id }">
            <input type="hidden" id="checkSelectParentId" value="${channel.id }">
        
        
            <tr>
                <td align="right" class="l-table-edit-td">上级渠道:&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td">
                    <input id="parentId" type="hidden" name="parentId" value="${channel.parentId }">
                    <input id="parentName" type="text" ltype="text" autocomplete="off" value="${channel.parentName }" onclick="selectChannel()"/>
                </td>
                <td align="right" class="l-table-edit-td">渠道名称:&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" name="channelName" type="text" id="channelName" ltype="text" value="${channel.channelName }" validate="{required:true,maxlength:100}"/></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">部门名称:&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td">
                    <input type="hidden" id="organId" name="organId" value="${channel.organId }">
                    <input id="organName" type="text" ltype="text" onclick="selectDepartment()" value="${channel.organName }"/>
                </td>
                <td align="right" class="l-table-edit-td">备注:&nbsp;<span style="color:red;"></span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" name="remark" type="text" id="remark" ltype="text" value="${channel.remark }" validate="{required:false,maxlength:100}" /></td>
            </tr>
        
            <tr>
                <td align="right" class="l-table-edit-td">客户手机号:&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" name="mobilePhone" type="text" id="mobilePhone" value="${channel.mobilePhone }" ltype="text" validate="{required:true,isMobile:true}" /></td>
                <td align="right" class="l-table-edit-td"></td>
                <td align="left" class="l-table-edit-td"></td>
            </tr>
        </table>
 
    </form>

<shiro:hasPermission name="info_channel_update">
    <input type="button" value="保存" onclick="saveFormSubmit()" style="background: #e0edff;" class="l-button l-button-submit vertical-center-user-defined" /> 
</shiro:hasPermission>

</body>
</html>
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
        
        // 保存编辑数据
        function saveFrom(){
            $.post("${ctx}/channel/edit.do", $("#form1").serialize(), function (msg) { 
                if(msg=="true"){
                	$.ligerDialog.success("保存成功", "", function(){
                	     window.parent.ligerManager.loadData();
               	         window.parent.openWindow.close();
               	    });
                }
                else if(msg=="nameAlreadyExist"){
                	$.ligerDialog.warn("此渠道名称已经存在");
                }
                else if(msg=="customerNotExist"){
                	$.ligerDialog.warn("此客户手机号不存在");
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
        
        // 选择渠道
        function selectChannel(){
        	openWindow=$.ligerDialog.open({
                  url: "${ctx}/channel/channelEditShuUI.do", 
                  width: 300, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
                  name:"selectDemp",
                  title: "请选择上级渠道"
/*                   , buttons: [
                  { text: '确定', onclick: function(item, dialog) { window.frames["selectDemp"].selectDemp();} },
                  { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
              ] */
          });
        }
        
        // 选择部门
        function selectDepartment(){
        	openWindow=$.ligerDialog.open({
                  url: "${ctx}/organ/organUserAddUI.do", 
                  width: 300, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
                  name:"selectDemp",
                  title: "请选择部门"
/*                   , buttons: [
                  { text: '确定', onclick: function(item, dialog) { window.frames["selectDemp"].selectDemp();} },
                  { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
              ] */
          });
        }
    </script>