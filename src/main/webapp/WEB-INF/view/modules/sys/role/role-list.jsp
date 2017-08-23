<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<%@ include file="../../../include/head.jsp" %>

<body  style="padding:5px">

<div id="searchbar" style="margin-bottom: 10px;">
    角色名：<input id="roleName" type="text" /> &nbsp; 
    <input id="btnOK" style="width: 60px;background: #e0edff;" type="button" value="查询" onclick="f_search()" /> &nbsp; 
    <input id="reset" style="width: 60px;" type="button" value="重置" onclick="f_reset()" />
</div>

<div id="t1"></div>

</body>

<script type="text/javascript">

// 搜索使用
var parms;
var roleName;
var realName;

var openWindow;

var col = [
           {display: "角色名称", name: "name", frozen: true,
     			 render: function(rowdata, rowindex, value) {
       				 var h = "";
       				 h +='<shiro:hasPermission name="role_update or role_query"><a style="text-decoration:none;color:#0000FF;" href=javascript:update('+ rowdata.id +')></shiro:hasPermission>';
       				 h +=value
       				 h +='<shiro:hasPermission name="role_update or role_query"></a></shiro:hasPermission>'
    				 return h;
    			 }
           },
           {display: "角色描述", name: "description"},
           {display: "创建时间", name: "createTime"}
          ];


var ligerManager;

            var tools = { items: [
                                  <shiro:hasPermission name="role_add">
                                      { text: "新增角色", icon: "add", click: add },
                                      { line: true },
                                  </shiro:hasPermission>
                                  
                                  <shiro:hasPermission name="role_delete">
                                      { text: "删除角色", icon: "delete", click: del },
                                      { line: true },
                                  </shiro:hasPermission>
                                  
                                  <shiro:hasPermission name="role_accredit">
                                      { text: "授权", icon: "role", click: rolePower }
                                  </shiro:hasPermission>
                              ]
                     };
            

//授权
function rolePower(){
    var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择");
    }
    else if(rows.length>1){
    	var ids=new Array();
		for(i = 0; i<rows.length; i++){
			ids[i]=rows[i].id;
        } 
       $.ligerDialog.confirm("你确实给多角色授予相同权限吗？", "询问", function (r) {
            if (r == true) {
            	rolePowerUser(ids.toString());
            }
       });
    }
    else{
    	rolePowerUser(rows[0].id)
    }
}

// 授权
function rolePowerUser(ids){
	openWindow=$.ligerDialog.open({
          url: "${ctx}/menu/roleAccreditUI.do?roleIds="+ids, 
          width: 400, height:600, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
          name:"rolePermissionPowerUI",
          title: "角色授权"
/*           , buttons: [
          { text: '保存', onclick: function(item, dialog) { window.frames["rolePermissionPowerUI"].saveFormSubmit();} },
          { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
        ] */
    });
}

// 添加用户            
function add() {
  openWindow=$.ligerDialog.open({
            url: '${ctx}/role/roleAddUI.do', 
            width: 500, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"addUI",
            title: "添加角色"
/*             , buttons: [
            { text: '保存', onclick: function(item, dialog) { window.frames["addUI"].saveFormSubmit();} },
            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
        ] */
    });
}

// 修改用户
function update(id) {
       openWindow=$.ligerDialog.open({
            url: "${ctx}/role/roleEditUI.do?roleId="+id, 
            width: 500, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"updateUI",
            title: "修改角色"
/*             , buttons: [
                <shiro:hasPermission name="role_update">
            { text: '保存', onclick: function(item, dialog) { window.frames["updateUI"].saveFormSubmit();} },
                </shiro:hasPermission>
            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
             ] */
        });
	
}

//删除用户
function del() {
    var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择");
    }
    else{
    	var ids=new Array();
		for(i = 0; i<rows.length; i++){
			ids[i]=rows[i].id;
        } 
       $.ligerDialog.confirm("你确实要删除吗？", "询问", function (r) {
            if (r == true) {
                $.post("${ctx}/role/roleDelete.do", 
                       {roleIds:ids.toString()},
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
                      }
              )}
        })
    }
}

    // 进入界面加载                      
    $(function () {
        ligerManager = $("#t1").ligerGrid({ 
    	                                columns: col,
                            			url: "${ctx}/role/findList.do",
                            			rownumbers: true,
                            			checkbox:true,
                            			usePager: true,
                            			pageSize: 20,
                            			pageSizeOptions: [10,20,30,50,100,200],
                            			toolbar:tools,
                            			parms:parms,
                            			enabledSort:false,
                            			selectRowButtonOnly:true // 点复选框才勾选
                                      });
        ligerManager.loadData();
   })

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

  //重置
    function f_reset(){
    	$("#searchbar").find(":input").not(":button,:submit,:reset,:hidden").val("").removeAttr("checked").removeAttr("selected");
    }
</script>