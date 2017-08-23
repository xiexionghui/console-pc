<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="../../../include/head.jsp" %>

<body  style="padding:5px">

<div id="t1"></div>

</body>

<script type="text/javascript">

// 搜索使用
var parms;
var organName;

var openWindow;

var col = [
             { display: '部门名称', name: 'name', id: 'idName', frozen: true, align: 'left',
     			 render: function(rowdata, rowindex, value) {
       				 var h = "";
       				 h +='<shiro:hasPermission name="organ_update or organ_query"><a style="text-decoration:none;color:#0000FF;" href=javascript:update('+ rowdata.id +')></shiro:hasPermission>';
       				 h +=value
       				 h +='<shiro:hasPermission name="organ_update or organ_query"></a></shiro:hasPermission>'
    				 return h;
    			 }
             },
             { display: '部门描述', name: 'description'},
             { display: '创建时间', name: 'createTime'},
          ];
          
var tools = { items: [
                      <shiro:hasPermission name="organ_add">
                          { text: "新增部门", icon: "add", click: add }
                      </shiro:hasPermission>
                      
/*                     { line: true },
                       <shiro:hasPermission name="organ_delete">
                          { text: "删除部门", icon: "delete", click: del },
                          { line: true },
                      </shiro:hasPermission> */
                      
                      /* { text: "展开", click: expandAll },
                      { line: true },
                      { text: "收缩", click: collapseAll } */
                  ]
         };


var ligerManager;

$(function (){
	ligerManager = $("#t1").ligerGrid({
                                           columns:col, 
                                           url: "${ctx}/organ/findListShu.do",
                                           toolbar:tools,
                                           usePager:false,
                                           rownumbers: true,
                               			   enabledSort:false,
                                           tree: { columnId: 'idName' }
       });
});

// 收缩
function collapseAll(){
	ligerManager.collapseAll();
}
// 展开
function expandAll(){
	ligerManager.expandAll();
}

// 添加部门
function add(){
	  openWindow=$.ligerDialog.open({
          url: "${ctx}/organ/addUI.do", 
          width: 500, height:450, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
          name:"addUI",
          title: "添加"
/*           , buttons: [
          { text: '保存', onclick: function(item, dialog) { window.frames["addUI"].saveFormSubmit();} },
          { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
      ] */
  });
}

// 修改
function update(id) {
       openWindow=$.ligerDialog.open({
            url: "${ctx}/organ/editUI.do?id="+id, 
            width: 500, height:450, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"updateUI",
            title: "修改"
/*             , buttons: [
                <shiro:hasPermission name="organ_update">
            { text: '保存', onclick: function(item, dialog) { window.frames["updateUI"].saveFormSubmit();} },
                </shiro:hasPermission>
            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
             ] */
        });
}

//删除
function del() {
    var row = ligerManager.getSelectedRow();
    if(row==null || row.length<=0){
    	$.ligerDialog.warn("请选择");
    }
    else{
    	if(ligerManager.hasChildren(row)){
            $.ligerDialog.confirm("此部门及所有分部门都将会删除，你确定要删除吗？", "询问", function (r) {
                if (r == true) {
            	    deleteDep(row.id);
                }
            });
    	}
    	else{
            $.ligerDialog.confirm("你确定要删除吗？", "询问", function (r) {
                if (r == true) {
            	    deleteDep(row.id);
                }
            });
    	}
    }
}
// 删除
function deleteDep(id){
    $.post("${ctx}/organ/delete.do", 
            {id:id},
            function (msg) {
         	  if(msg=="true"){
         		  $.ligerDialog.success("删除成功", "", function(){
         		      ligerManager.loadData();
         		  });
         	  }
              else if(msg.indexOf("notAuthorizationError")!=-1){
              	$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
              }
         	  else{
         		  $.ligerDialog.error("删除失败");
         	  }
     })
}

</script>