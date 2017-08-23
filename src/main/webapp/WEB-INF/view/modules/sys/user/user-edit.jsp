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
        
            <input type="hidden" name="id" value="${sysUser.id }">
            
        
        <tr>
            <td colspan="4">
				<div class="l-group l-group-hasicon">
					<img src="${ctxStatic}/ligerUI/lib/ligerUI/skins/icons/communication.gif"><span style="margin-left:0px;">用户基本信息</span>
				</div>
            </td>
        </tr>
        
            <tr>
                <td align="right" class="l-table-edit-td">后台账号:&nbsp;&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off" name="loginName" type="text" id="loginName" value="${sysUser.loginName }" ltype="text" validate="{required:true,maxlength:50,stringCheck:true}" /></td>
                <td align="right" class="l-table-edit-td">用户姓名:&nbsp;&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off"  name="realName" type="text" id="realName" value="${sysUser.realName }" ltype="text" validate="{required:true,maxlength:50}" /></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">用户状态:&nbsp;&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td">
                    <input id="status_0" type="radio" name="status" value="0" <c:if test="${sysUser.status=='正常'}">checked="checked"</c:if> /><label for="rbtnl_0">正常</label> 
                    <input id="status_1" type="radio" name="status" value="1" <c:if test="${sysUser.status=='限制'}">checked="checked"</c:if> /><label for="rbtnl_1">限制</label>
                </td>
                <td align="right" class="l-table-edit-td">用户性别:&nbsp;&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td">
                    <input id="sex_0" type="radio" name="sex" value="0" <c:if test="${sysUser.sex=='男'}">checked="checked"</c:if> /><label for="rbtnl_0">男</label> &nbsp; 
                    <input id="sex_1" type="radio" name="sex" value="1" <c:if test="${sysUser.sex=='女'}">checked="checked"</c:if> /><label for="rbtnl_1">女</label>
                </td>
            </tr>
            
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
            
        <tr>
            <td colspan="4">
				<div class="l-group l-group-hasicon">
					<img src="${ctxStatic}/ligerUI/lib/ligerUI/skins/icons/communication.gif"><span style="margin-left:0px;">用户详细信息</span>
				</div>
            </td>
        </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">手机号码:&nbsp;&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off"  name="mobile" type="text" id="mobile" value="${sysUser.mobile }" ltype="text" validate="{required:true,isMobile:true}" /></td>
                <td align="right" class="l-table-edit-td">用户邮箱:&nbsp;&nbsp;<span style="color:red;"></span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off"  name="email" type="text" id="email" value="${sysUser.email}" ltype="text" validate="email" /></td>
            </tr>
            
            
            <tr>
                <td align="right" class="l-table-edit-td">部门名称:&nbsp;&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td">
                    <input type="hidden" id="organId" name="organId" value="${sysUser.organId }">
                    <input id="organName" type="text" ltype="text" onclick="selectDepartment()" value="${sysUser.organName }" validate="{required:true}"/>
                </td>
                <td align="right" class="l-table-edit-td">生日:&nbsp;&nbsp;<span style="color:red;"></span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off"  name="birthday" type="text" id="birthday" ltype="text" value="${sysUser.birthday}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">办公电话:&nbsp;&nbsp;<span style="color:red;"></span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off"  name="officeTel" type="text" value="${sysUser.officeTel}" id="officeTel" ltype="text" validate="{required:false,isTelephone:true}" /></td>
                <td align="right" class="l-table-edit-td">家庭电话:&nbsp;&nbsp;<span style="color:red;"></span></td>
                <td align="left" class="l-table-edit-td"><input autocomplete="off"  name="homeTel" type="text" id="homeTel" value="${sysUser.homeTel}" ltype="text" validate="{required:false,isTelephone:true}" /></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">在职状态:&nbsp;&nbsp;<span style="color:red;">*</span></td>
                <td align="left" class="l-table-edit-td">
                    <input id="dimissionStatus_0" type="radio" name="dimissionStatus" value="0" <c:if test="${sysUser.dimissionStatus=='在职'}">checked="checked"</c:if> /><label for="rbtnl_0">在职</label> &nbsp; 
                    <input id="dimissionStatus_1" type="radio" name="dimissionStatus" value="1" <c:if test="${sysUser.dimissionStatus=='离职'}">checked="checked"</c:if> /><label for="rbtnl_1">离职</label>
                </td>
                <td align="right" class="l-table-edit-td">离职时间:&nbsp;&nbsp;<span style="color:red;"></span></td>
                <td align="left" class="l-table-edit-td">
                    <input autocomplete="off"  name="dimissionTime" type="text" id="dimissionTime" ltype="text" value="${sysUser.dimissionTime}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                </td>
            </tr>
            
        </table>
    </form>
    
<shiro:hasPermission name="user_update">    
    <input type="button" value="保存" onclick="saveFormSubmit()" style="background: #e0edff;" class="l-button l-button-submit vertical-center-user-defined" /> 
</shiro:hasPermission>
    
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
                updateFrom();
            }
        });
        $("form").ligerForm();
    });   
        
        // 保存数据
        function updateFrom(){
        	if($("#organName").val().length<=0){
            	$.ligerDialog.warn("请选择部门");
    	    }else{
    	    	
            $.post("${ctx}/user/editUse.do", $("#form1").serialize(), function (msg) { 
                if(msg=="true"){
                	$.ligerDialog.success("保存成功", "", function(){
                	     window.parent.ligerManager.loadData();
               	         window.parent.openWindow.close();
               	    });
                }
                else if(msg=="loginNameExists"){
                	$.ligerDialog.warn("此登录名已存在");
                }
                else if(msg.indexOf("notAuthorizationError")!=-1){
                	$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
                }
                else{
                	$.ligerDialog.error("保存失败");
                }
            });
            
    	    }
            
        }
        
        // 验证数据
        function saveFormSubmit(){
        	$("#form1").submit();
        }
        
        // 选择部门
        function selectDepartment(){
        	openWindow=$.ligerDialog.open({
                  url: "${ctx}/organ/organUserAddUI.do", 
                  width: 300, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
                  name:"selectDemp",
                  title: "请选择部门"
 /*                  , buttons: [
                  { text: '确定', onclick: function(item, dialog) { window.frames["selectDemp"].selectDemp();} },
                  { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
              ] */
          });
        }
    </script>
    