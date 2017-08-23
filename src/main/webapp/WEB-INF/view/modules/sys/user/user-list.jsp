<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="../../../include/head.jsp" %>


<body  style="padding:5px">

<div id="searchbar" style="margin-bottom: 10px;">
    账户名：<input id="loginName" type="text" /> &nbsp; 
   真实姓名：<input id="realName" type="text" /> &nbsp; 
    <input id="btnOK" style="width: 60px;background: #e0edff;" type="button" value="查询" onclick="f_search()" /> &nbsp; 
    <input id="reset" style="width: 60px;" type="button" value="重置" onclick="f_reset()" />
</div>

<div id="t1"></div>

</body>
<script type="text/javascript">

// 搜索使用
var parms;
var loginName;
var realName;

var openWindow;

var col = [
           { display: "账户名", name: "loginName", width:150,frozen: true,
   			 render: function(rowdata, rowindex, value) {
   				 var h = "";
				 return h;
			 }
           },
           { display: "真实姓名", name: "realName"},
           { display: "性别", name: "sex"},
           { display: "手机号码", name: "mobile"},
           { display: "邮箱", name: "email"},
           { display: "部门", name: "organName"},
           { display: "可登录状态", name: "status"},
           { display: "在职状态", name: "dimissionStatus"},
           { display: "平台账号", name: "mobilePhone"},
           { display: "创建时间", name: "createTime"}
          ];


var ligerManager;

 var tools = { items: [
                             { text: "新增账号", icon: "add", click: add },
                             { line: true },
                             
                             { text: "限制登录", icon: "lock", click: astrictLogin },
                             { line: true },
                         
                             { text: "解除限制", icon: "config", click: relieveAstrict },
                             { line: true },
                             
                             { text: "重置密码", icon: "config", click: resetPassword },
                             { line: true },
                         
                             { text: "授权", icon: "role", click: rolePower },
                             /* { line: true }, */
                         
                             /* { text: "删除", icon: "delete", click: del } */ 
                         
	                         { text: "关联平台账户", icon: "add", click: relation }
                             
                     ]
            };

// 限制登录
function astrictLogin(){
    var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择");
    }
    else{
    	var ids=new Array();
		for(i = 0; i<rows.length; i++){
			ids[i]=rows[i].id;
        } 
       $.ligerDialog.confirm("你确定要限制吗？", "询问", function (r) {
            if (r == true) {
                $.post("${ctx}/user/astrictLogin.do", 
                       {ids:ids.toString()},
                       function (msg) {
                    	  if(msg=="true"){
                    		  $.ligerDialog.success("限制成功", "", function(){
                    		      ligerManager.loadData();
                    		  });
                    	  }
                          else if(msg.indexOf("notAuthorizationError")!=-1){
                          	$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
                          }
                    	  else{
                    		  $.ligerDialog.error("限制失败");
                    	  }
                      }
              )}
        })
    }
} 

// 解除限制
function relieveAstrict(){
    var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择");
    }
    else{
    	var ids=new Array();
		for(i = 0; i<rows.length; i++){
			ids[i]=rows[i].id;
        } 
       $.ligerDialog.confirm("你确定要解除限制吗？", "询问", function (r) {
            if (r == true) {
                $.post("${ctx}/user/relieveAstrict.do", 
                       {ids:ids.toString()},
                       function (msg) {
                    	  if(msg=="true"){
                    		  $.ligerDialog.success("解除限制成功", "", function(){
                    		      ligerManager.loadData();
                    		  });
                    	  }
                          else if(msg.indexOf("notAuthorizationError")!=-1){
                          	$.ligerDialog.warn("权限异常，你是否具有此功能权限？");
                          }
                    	  else{
                    		  $.ligerDialog.error("解除限制失败");
                    	  }
                      }
              )}
        })
    }
} 

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
       $.ligerDialog.confirm("你确实给多用户授予相同角色吗？", "询问", function (r) {
            if (r == true) {
            	rolePowerMethod(ids.toString());
            }
       });
    }
    else{
    	rolePowerMethod(rows[0].id);
    }
}
    
// 角色授权调用后台方法
function rolePowerMethod(ids){
	  openWindow=$.ligerDialog.open({
          url: "${ctx}/role/roleUserAccreditUI.do?userIds="+ids, 
          width: 400, height:500, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
          name:"userPowerRoleUI",
          title: "授权角色"
/*           , buttons: [
          { text: '保存', onclick: function(item, dialog) { window.frames["userPowerRoleUI"].saveUserRole();} },
          { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
         ] */
     });
}
            
// 添加用户            
function add() {
  openWindow=$.ligerDialog.open({
            url: '${ctx}/user/addUserUI.do', 
            width: 600, height:500, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"addUI",
            title: "添加用户"
/*             , buttons: [
            { text: '保存', onclick: function(item, dialog) { window.frames["addUI"].saveformSubmit();} },
            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
        ] */
    });
}

// 修改用户
function update(id) {
       openWindow=$.ligerDialog.open({
            url: "${ctx}/user/editUserUI.do?id="+id, 
            width: 600, height:500, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"updateUI",
            title: "修改用户"
/*             , buttons: [
                <shiro:hasPermission name="user_update">                  
            { text: '保存', onclick: function(item, dialog) { window.frames["updateUI"].saveformSubmit();} },
                </shiro:hasPermission>
            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
             ] */
        });
}

//重置密码
function resetPassword () {
    var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择");
    }
    else{
    	var ids=new Array();
		for(i = 0; i<rows.length; i++){
			ids[i]=rows[i].id;
        }
		
	  openWindow=$.ligerDialog.open({
            url: "${ctx}/user/resetPasswordUI.do?ids="+ids.toString(), 
            width: 500, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"resetPasswordUI",
            title: "重置密码"
/*             , buttons: [
                 { text: '保存', onclick: function(item, dialog) { window.frames["resetPasswordUI"].updatePasswordFormSubmit();} },
                 { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
             ] */
      });
    }
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
                $.post("${ctx}/user/delete.do", 
                       {ids:ids.toString()},
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

function relation(){
	var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择");
    }
    else{
    	var ids=new Array();
		for(i = 0; i<rows.length; i++){
			ids[i]=rows[i].id;
        }
		
		if(ids.length > 1){
			$.ligerDialog.warn("不支持批量操作");
			return;
		}
		
	    openWindow=$.ligerDialog.open({
            url: "${ctx}/user/relationUI.do?ids="+ids[0].toString(), 
            width: 500, height:220, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"relationUI",
            title: "关联账户"
       });
    }
}

    // 进入界面加载                      
    $(function () {
        ligerManager = $("#t1").ligerGrid({ 
    	                                columns: col,
                            			url: "${ctx}/user/findList.do",
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
