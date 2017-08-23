<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ include file="../../../include/head.jsp" %>

<body  style="padding:5px">

<!-- <div>
          <a class="l-button button-css-set" onclick="expandAll()">展开</a>
          <a class="l-button button-css-set" onclick="collapseAll()">收缩</a>
</div> -->

 <div class="l-clear"></div>

<div id="tree1"></div>

<input type="button" value="确定" onclick="selectDemp()" style="background: #e0edff;" class="l-button l-button-submit vertical-center-user-defined" /> 

</body>

<script type="text/javascript">

var manager = null;
$(function (){
	var organId=$("#id", window.parent.document).val();
    $("#tree1").ligerTree({url:"${ctx}/organ/organEditShu.do?organId="+organId,
    	                   idFieldName: "id", 
    	                   textFieldName : "name", 
    	                   single:true,
		                   checkbox: true,
		                   isExpand:2
    });
    manager = $("#tree1").ligerGetTreeManager();
}); 

// 展开全部
function expandAll(){
    manager.expandAll();
}

// 关闭全部
function collapseAll(){
    manager.collapseAll();
}

// 选择部门
function selectDemp(){
    var notes = manager.getChecked();
    if(notes.length<1){
    	$.ligerDialog.warn("请选择部门");
    }
    else if($("#checkSelectParentId", window.parent.document).val()==notes[0].data.id){
    	$.ligerDialog.warn("上级部门不能选择自己");
    }
    else{
    	var id=notes[0].data.id;
    	var name=notes[0].data.name;
        $("#organId", window.parent.document).val(id);
        $("#organName", window.parent.document).val(name);
        window.parent.openWindow.close();
    }
}

</script>