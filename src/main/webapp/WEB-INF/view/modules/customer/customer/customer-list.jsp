<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="../../../include/head.jsp" %>


<body  style="padding:5px">

<div id="searchbar" style="margin-bottom: 10px;">

    手机号码：<input id="mobilePhone" type="text" /> &nbsp; 
    真实姓名：<input id="realname" type="text" /> &nbsp; 
    账户状态：<select id="customerStatus" ltype="select">
     <!-- 账户状态：10-正常; 20-禁用  -->
	    <option value="">请选择</option>
	    <option value="10">正常</option>
	    <option value="20">禁用</option>
   </select> &nbsp; 
    实名认证：<select id="realnameStatus" ltype="select">
     <!-- 实名状态：10-未实名；20-已实名  -->
	    <option value="">请选择</option>
	    <option value="10">未实名</option>
	    <option value="20">已实名</option>
       </select> &nbsp; 
<input id="btnOK" style="width: 60px;background: #e0edff;" type="button" value="查询" onclick="f_search()" /> &nbsp; 
<input id="moreCondition" style="width: 60px;background: #e0edff;" type="button" value="高级" onclick="f_moreCondition()" /> &nbsp; 
<input id="reset" style="width: 60px;" type="button" value="重置" onclick="f_reset()" /> &nbsp;

<shiro:hasPermission name="customer_query_export">
    <input id="btnExcel" style="width: 60px;background: #e0edff;" type="button" value="导出" onclick="f_excel()" /> &nbsp;
</shiro:hasPermission>

 <br />
 
 <div id="searchMoreCondition" style="display: none;">
   手机归属：<input id="mobilePhoneLocation" type="text" /> &nbsp; 
    身份证号：<input id="idcardNo" type="text" /> &nbsp; 
    性 &nbsp; 别 &nbsp;&nbsp; ：<select id="gender" ltype="select">
     <!-- 性别：10-男；20-女  -->
	    <option value="">请选择</option>
	    <option value="10">男</option>
	    <option value="20">女</option>
   </select> &nbsp; 
    投资状态：<select id="investStatus" ltype="select">
     <!-- 投资状态：0=未投资，1=投资  -->
	    <option value="">请选择</option>
	    <option value="0">未投资</option>
	    <option value="1">已投资</option>
   </select> &nbsp; 
 <br />
   注册时间：<input id="registerTimeStart" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'registerTimeEnd\')||\'2020-10-01\'}'})"/> - 
       <input id="registerTimeEnd" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'registerTimeStart\')}',maxDate:'2020-10-01'})"/> &nbsp;
 <span style="margin-left: 22px;">是否合时代用户：</span><select id="p2pFlag" ltype="select">
     <!-- 是否合时代用户：10-是;20-不是  -->
	    <option value="">请选择</option>
	    <option value="10">是</option>
	    <option value="20">否</option>
   </select> &nbsp;
   
   年龄：<input id="ageStart" type="text" style="width: 59px;"/> - <input id="ageEnd" type="text" style="width: 58px;"/> &nbsp;
 <br />
 
   首次充值时间：<input id="firstRechargeTimeStart" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'firstRechargeTimeEnd\')||\'2020-10-01\'}'})"/> - 
       <input id="firstRechargeTimeEnd" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'firstRechargeTimeStart\')}',maxDate:'2020-10-01'})"/> &nbsp;
   &nbsp; &nbsp;合时代用户名：<input id="p2pUsername" type="text" /> &nbsp; 
 <br />
 
   首次投资时间：<input id="firstInvestTimeStart" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'firstInvestTimeEnd\')||\'2020-10-01\'}'})"/> - 
       <input id="firstInvestTimeEnd" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'firstInvestTimeStart\')}',maxDate:'2020-10-01'})"/> &nbsp;
 <br />
 
    我的推荐码：<input id="recomCode" type="text" /> &nbsp; 
    推荐人：<input id="referrerPhone" type="text" /> &nbsp; 
    渠道：<input id="channelName" type="text" /> &nbsp; 
  <br />
  
    <span style="margin-left: 22px;">注册来源：</span><select id="registerDataFrom" ltype="select">
     <!-- 是否合时代用户：10-是;20-不是  -->
	    <option value="">请选择</option>
	    <option value="10">本站</option>
	    <option value="20">P2P</option>
   </select> &nbsp;
   
   <span style="margin-left: 22px;">客户端：</span><select id="registerClientType" ltype="select">
     <!-- 是否合时代用户：10-是;20-不是  -->
	    <option value="">请选择</option>
	    <option value="10">PC</option>
	    <option value="20">微信</option>
	    <option value="30">WAP</option>
	    <option value="40">APP</option>
	    <option value="41">Android</option>
	    <option value="42">IOS</option>
   </select> &nbsp;
</div>
 
</div>

<div id="grid"></div>

    <input type="hidden" id="originalMobilePhone">

<c:if test="${not empty errorMsg}">
	<script type="text/javascript">
		$.ligerDialog.error('${errorMsg}')
	</script>
</c:if>

</body>
<script type="text/javascript">

// 搜索使用
var parms;
var mobilePhone;
var realname;
var customerStatus;

var openWindow;
var ligerManager;

$(function() {
	initPage();
});

var tools = { items: [
                       <shiro:hasPermission name="customer_updateMobilePhone">
                          { text: "修改手机号码", icon: "edit", click: updateMobilePhone },
                          { line: true },
                       </shiro:hasPermission>
                          
                       <shiro:hasPermission name="customer_astrictLogin">
                          { text: "限制登录", icon: "lock", click: astrictLogin },
                          { line: true },
                       </shiro:hasPermission>
                      
                       <shiro:hasPermission name="customer_relieveAstrict">
                          { text: "解除限制", icon: "config", click: relieveAstrict }
                       </shiro:hasPermission>
                          
                  ]
         };

function initPage() {
	ligerManager = $("#grid").ligerGrid({
		columns: [{
			display: "手机号码",
			name: "mobilePhone",
			minWidth:100,
			frozen: true,
			render: function(rowdata, rowindex, value) {
				return "<a style='text-decoration:none;color:#0000FF;' href=javascript:customerDetail('"+ rowdata.customerId +"')>" + value + "</a>";
			}
		},{
			display: "真实姓名",
			name: "realname",
			minWidth:100
		},{
			display: "性别",
			name: "gender",
			minWidth:80
		},{
			display: "年龄",
			name: "age",
			minWidth:80
		},{
			display: "生日",
			name: "birthday",
			minWidth:100
		},{
			display: "手机归属地",
			name: "mobilePhoneLocation",
			minWidth:120
		},{
			display: "实名认证",
			name: "realnameStatus",
			minWidth:80
		},{
			display: "身份证号",
			name: "idcardNo",
			minWidth:150
		},{
			display: "我的推荐码",
			name: "recomCode",
			minWidth:100
		},{
			display: "推荐人",
			name: "referrerPhone",
			minWidth:100
		},{
			display: "渠道",
			name: "channelName",
			minWidth:120
		},{
			display: "是否投资",
			name: "investStatus",
			minWidth:100
		}/* ,{
			display: "最近回款日期",
			name: "recentlyReturnMoneyDate",
			width:150
		} */,{
			display: "注册时间",
			name: "registerTime",
			minWidth:130
		},{
			display: "首次充值时间",
			name: "rechargeFirstTime",
			minWidth:130
		},{
			display: "首次投资时间",
			name: "buyFirstTime",
			minWidth:130
		},{
			display: "状态",
			name: "customerStatus",
			minWidth:100
		},{
			display: "注册来源",
			name: "registerDataFrom",
			minWidth:100
		},{
			display: "客户端",
			name: "registerClientType",
			minWidth:100
		}],
		url: "${ctx}/customer/findList.do",
		parms: parms,
		pageSize:20,
		pageSizeOptions: [10,20,30,50,100,200],
		toolbar:tools,
		checkbox: true,
		rownumbers: true,
		headerRowHeight: 26,
		rowHeight: 26,
		enabledSort: false,
		selectRowButtonOnly: true // 点复选框才勾选
	});
}

//限制登录
function astrictLogin(){
    var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择需要限制登录的客户");
    }
    else{
    	var ids=new Array();
		for(i = 0; i<rows.length; i++){
			ids[i]=rows[i].customerId;
        } 
       $.ligerDialog.confirm("确定要限制选择的客户登录吗？", "", function (r) {
            if (r == true) {
                $.post("${ctx}/customer/astrictLogin.do", 
                       {customerIds:ids.toString()},
                       function (msg) {
                    	  var obj = JSON.parse(msg); 
                    	  if(obj.code=="200"){
                    		  $.ligerDialog.success("限制登录成功", "", function(){
                    		      ligerManager.loadData();
                    		  });
                    	  }
                          else if(msg.indexOf("notAuthorizationError")!=-1){
                          	  $.ligerDialog.warn("权限异常，你是否具有此功能权限？");
                          }
                    	  else{
                    		  $.ligerDialog.error("限制登录失败，原因："+obj.msg);
                    	  }
                      }
              )}
        })
    }
} 

// 解除限制登录
function relieveAstrict(){
    var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择需要解除登录限制的客户");
    }
    else{
    	var ids=new Array();
		for(i = 0; i<rows.length; i++){
			ids[i]=rows[i].customerId;
        } 
       $.ligerDialog.confirm("确定要解除选择客户的登录限制吗？", "", function (r) {
            if (r == true) {
                $.post("${ctx}/customer/relieveAstrict.do", 
                       {customerIds:ids.toString()},
                       function (msg) {
                    	  var obj = JSON.parse(msg); 
                    	  if(obj.code=="200"){
                    		  $.ligerDialog.success("解除限制成功", "", function(){
                    		      ligerManager.loadData();
                    		  });
                    	  }
                          else if(msg.indexOf("notAuthorizationError")!=-1){
                          	  $.ligerDialog.warn("权限异常，你是否具有此功能权限？");
                          }
                    	  else{
                    		  $.ligerDialog.error("解除限制失败，原因："+obj.msg);
                    	  }
                      }
              )}
        })
    }
} 

// 修改手机号
function updateMobilePhone(){
    var rows = ligerManager.getCheckedRows();
    if(rows.length<=0){
    	$.ligerDialog.warn("请选择");
    }
    else if(rows.length>1){
    	$.ligerDialog.warn("请选择一条");
    }
    else{
      $("#originalMobilePhone").val(rows[0].mobilePhone);
  	  openWindow=$.ligerDialog.open({
              url: "${ctx}/customer/updateMobilePhoneUI.do?customerId="+rows[0].customerId, 
              width: 450, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
              name:"updatePhoneUI",
              title: "客户手机号码"
/*               , buttons: [
              { text: '保存', onclick: function(item, dialog) { window.frames["updatePhoneUI"].saveformSubmit();} },
              { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
             ] */
         });
    	
    }
} 

// 详情
function customerDetail(id) {
  openWindow=$.ligerDialog.open({
            url: "${ctx}/customer/detail.do?customerId="+id, 
            width: 850, height:650, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            title: "信息"
/*             , buttons: [
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
	
	// 多条件
	function f_moreCondition(){
		$("#searchMoreCondition").toggle();
	}
	
	// 导出
	function f_excel(){
		var queryArray=new Array();
		    $("#searchbar").find("input[type=text],select").each(function(index){
			     var object = new Object;  
			     object.name=this.id;
			     object.value=this.value;
			     queryArray[index]=object;
		    });
	    parms=queryArray;
		
	  var params = $.param(parms);
	  var url = "${ctx}/customer/findListExcel?"+"&"+params;
	  $('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	}
	
</script>
