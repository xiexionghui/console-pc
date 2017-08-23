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
        var manager;
        var tree;
        $(function (){
        	tree=$("#tree1").ligerTree({ url: "${ctx}/role/roleUserAccredit.do?userIds=${userIds}", ajaxType: 'post',
            		                 idFieldName:"id",
            		                 textFieldName:"name",
            		                 nodeWidth:300,
            		                 parentIcon: null,
            		                 childIcon: null,
            		                 onSuccess: onSelectRow,
          		                     isExpand:2
            		              });
            manager = $("#tree1").ligerGetTreeManager();
        }); 
        
        // 设置选择项
        function onSelectRow(){
        	var data = manager.data;
            var parm = function (data){
                return data.flag=="1";
            };
            tree.selectNode(parm);
        }
        
        // 获取选择的项
        function getChecked() {
        	var ids=new Array();
            var notes = manager.getChecked();
            for (var i = 0; i < notes.length; i++){
            	ids[i]=notes[i].data.id;
            }
            return ids.toString();
        }
        
        // 保存用户角色
        function saveUserRole(){
        	var ids = getChecked();
        	var userIds="${userIds}";
            $.post("${ctx}/role/saveUserRoleAccredit.do", {roleIds:ids, userIds:userIds}, function (msg) { 
                if(msg=="true"){
                	$.ligerDialog.success("保存成功", "", function(){
                		 window.parent.ligerManager.loadData();
               	         window.parent.openWindow.close();
               	    });
                }
                else if(msg.indexOf("notAuthorizationError")!=-1){
                	$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
                }
                else{
                	$.ligerDialog.error("保存失败");
                }
            });
        	
        }
    </script>
    
    
<div id="tree1" style="margin-left: 20px; margin-top: 20px;"></div>

<input type="button" value="保存" onclick="saveUserRole()" style="background: #e0edff;" class="l-button l-button-submit vertical-center-user-defined" /> 
   
  
