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
             { display: '渠道名称', 
               name: 'channelName', 
               id: 'idName', 
               frozen: true, 
               align: 'left',
               minWidth:150,
     		   render: function(rowdata, rowindex, value) {
     				return "<a style='text-decoration:none;color:#0000FF;' href=javascript:update('"+ rowdata.id +"')>" + value + "</a>";
    		   }
             },{
				display: "客户手机号",
				name: "mobilePhone",
				minWidth:120
			},{
				display: "部门名称",
				name: "organName",
				minWidth:150
			},{
				display: "备注",
				name: "remark",
				minWidth:150
			},{
				display: "添加时间",
				name: "createTime",
				minWidth:130
			}
             
          ];
          
var tools = { items: [
                      <shiro:hasPermission name="info_channel_add">
                          { text: "新增渠道", icon: "add", click: add },
                          { line: true },
                      </shiro:hasPermission>
                      
                      <shiro:hasPermission name="info_channel_delete">
                          { text: "删除渠道", icon: "delete", click: del }
                      </shiro:hasPermission>
                          
                /*    { line: true },
                      { text: "展开", click: expandAll },
                      { line: true },
                      { text: "收缩", click: collapseAll } */
                  ]
         };


var ligerManager;

$(function (){
	ligerManager = $("#t1").ligerGrid({
                                           columns:col, 
                                           url: "${ctx}/channel/findListShu.do",
                                           toolbar:tools,
                                           usePager:false,
                                           rownumbers: true,
                               			   enabledSort:false,
                                           tree: { columnId: 'idName' },
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
          url: "${ctx}/channel/addUI.do", 
          width: 600, height:450, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
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
            url: "${ctx}/channel/editUI.do?id="+id, 
            width: 600, height:450, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"updateUI",
            title: "修改"
/*             , buttons: [
            <shiro:hasPermission name="info_channel_update">
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
            $.ligerDialog.confirm("此渠道及所有分渠道都将会删除，你确定要删除吗？", "询问", function (r) {
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
    $.post("${ctx}/channel/delete.do", 
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