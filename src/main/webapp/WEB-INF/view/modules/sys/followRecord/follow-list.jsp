<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="../../../include/head.jsp" %>


<body  style="padding:5px">

<!-- <div id="searchbar" style="margin-bottom: 10px;">
    ：<input id="bankName" type="text" /> &nbsp; 
    ：  <select id="bankStatus" ltype="select">
	    <option value="">请选择</option>
	    <option value="10">正常</option>
	    <option value="20">停用</option>
   </select> &nbsp; 
    <input id="btnOK" style="width: 60px;background: #e0edff;" type="button" value="查询" onclick="f_search()" /> &nbsp; 
    <input id="reset" style="width: 60px;" type="button" value="重置" onclick="f_reset()" />
</div> -->

<div id="grid"></div>

</body>
<script type="text/javascript">

// 搜索使用
var parms;
var openWindow;
var ligerManager;

$(function() {
	initPage();
});

var tools = { items: [
                          { text: "添加回访记录", icon: "add", click: add }
                     ]
            };

function initPage() {
	ligerManager = $("#grid").ligerGrid({
		columns: [{
			display: "回访人",
			minWidth:150,
			name: "loginName",
			frozen: true
		},{
			display: "回访时间",
			name: "followTime",
			minWidth:130
		},{
			display: "回访备注",
			name: "remark",
			editor: { type: 'text' },
			minWidth:200
		},{
			display: "操作",
			minWidth:50,
			render: function(rowdata, rowindex, value) {
				return "<a style='text-decoration:none;color:#0000FF;' href=javascript:detail('"+ rowdata.id +"')>详情</a>";
			}
		}],
		url: "${ctx}/followRecord/findList.do?customerId=${customerId}",
		parms: parms,
		pageSize: 10,
		pageSizeOptions: [10,20,30,50,100,200],
		toolbar:tools,
		checkbox: true,
		rownumbers: true,
		headerRowHeight: 26,
		rowHeight: 26,
		enabledSort: false,
		enabledEdit:true,
		selectRowButtonOnly: true // 点复选框才勾选
	});
	//ligerManager.loadData();
}

// 添加
function add() {
  openWindow=$.ligerDialog.open({
            url: '${ctx}/followRecord/addUI.do?customerId=${customerId}', 
            width: 550, height:320, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"addUI",
            title: "添加"
/*             , buttons: [
            { text: '保存', onclick: function(item, dialog) { window.frames["addUI"].saveformSubmit();} },
            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
        ] */
    });
}

	// 搜索
    function f_search() {
    	var queryArray=new Array();
   	    $("#searchbar").find("input[type=text],select").each(function(index){
   		     var object = new Object;  
   		     object.name=this.id;
   		     object.value=this.value;
   		     queryArray[index]=object;
   	    });
        parms=queryArray;
        
        ligerManager.set({
    	    parms:parms,
    	    page:1,
    	    newPage:1
        });
        ligerManager.loadData();
    }

	// 重置
	function f_reset(){
		$("#searchbar").find(":input").not(":button,:submit,:reset,:hidden").val("").removeAttr("checked").removeAttr("selected");
	}
	
	// 详情
	function detail(id) {
	  openWindow=$.ligerDialog.open({
	            url: "${ctx}/followRecord/detail?id="+id, 
	            width: 500, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
	            title: "信息"
/* 	            , buttons: [
	            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
	        ] */
	    });
	}
</script>
