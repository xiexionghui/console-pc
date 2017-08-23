<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="../../../include/head.jsp" %>


<body  style="padding:5px">

<div id="searchbar" style="margin-bottom: 10px;">
<form id="searchFrom">
    手机号码：<input id="mobilePhone" type="text" value="${page.mobilePhone}" /> &nbsp; 
    真实姓名：<input id="realname" value="${page.realname}"  type="text" /> &nbsp; 
    状态： <select id="customerStatus" ltype="select">
     <!-- 账户状态：10-正常; 20-禁用  -->
	    <option value="">请选择</option>
	    <option value="10">正常</option>
	    <option value="20">禁用</option>
   </select> &nbsp; 
    <input id="btnOK" style="width: 60px;background: #e0edff;" type="button" value="查询" onclick="f_search()" /> &nbsp; 
    
    <input id="moreCondition" style="width: 60px;background: #e0edff;" type="button" value="高级" onclick="f_moreCondition()" /> &nbsp; 
    <input id="reset" style="width: 60px;" type="button" value="重置" onclick="f_reset()" /> &nbsp;
    
    <shiro:hasPermission name="finance_customer_asset_export">
        <input id="btnExcel" style="width: 60px;background: #e0edff;" type="button" value="导出" onclick="f_excel()" /> &nbsp;
    </shiro:hasPermission>
    
    <br/>
     <div id="searchMoreCondition" style="display: none;"> 
    手机号码归属地：<input id="mobilePhoneLocation" type="text" /> &nbsp; 
     渠道：<input id="channelName" type="text" /> &nbsp; 
 <br/>
    可用余额：<input id="usableBalanceStart" type="text" style="width:59px;"/> - <input id="usableBalanceEnd" type="text" style="width:58px;"/> &nbsp; 
    冻结金额：<input id="freezeBalanceStart" type="text" style="width:59px;"/> - <input id="freezeBalanceEnd" type="text" style="width:58px;"/> &nbsp; 
    黄金总克重：<input id="totalGramStart" type="text" style="width:59px;"/> - <input id="totalGramEnd" type="text" style="width:58px;"/> &nbsp; 
     </div>
 </form>
</div>

<div id="grid"></div>
    <input type="hidden" id="originalMobilePhone">
</body>

<c:if test="${not empty errorMsg}">
	<script type="text/javascript">
		$.ligerDialog.error('${errorMsg}')
	</script>
</c:if>

<script type="text/javascript">
// 搜索使用
var parms;
var mobilePhone;
var realname;
var customerStatus;
var mobilePhoneLocation;

var openWindow;
var ligerManager;

$(function() {
	initPage();
});

function initPage() {
	ligerManager = $("#grid").ligerGrid({
		columns: [{
			display: "手机号码",
			name: "mobilePhone",
			minWidth:100,
			frozen: true,
			render: function(rowdata, rowindex, value) {
				var h="";
				<shiro:hasPermission name="finance_asset_customer_info">
				h +="<a style='text-decoration:none;color:#0000FF;' href=javascript:detail('"+ rowdata.customerId +"')>" + value + "</a>";
				</shiro:hasPermission>
				<shiro:lacksPermission name="finance_asset_customer_info">
				h += value;
				</shiro:lacksPermission>
				return h; 
			}
		},{
			display: "真实姓名",
			name: "realname",
			minWidth:100
		},{
			display: "资产总额",
			minWidth:150,
			name:"accountSum"
		},{
			display: "可用余额",
			name: "usableBalance",
			minWidth:100
		},{
			display: "冻结金额",
			name: "freezeBalance",
			minWidth:100
		},{
			display: "黄金总克重/g",
			name: "totalGram",
			minWidth:100
		},{
			display: "黄金总现值",
			name: "goldValue",
			minWidth:100
		},{
			display: "黄金冻结资产/g",
			name: "freezeGram",
			minWidth:100
		},{
			display: "待回款收益（保价金）",
			name: "recievedInterest",
			minWidth:120
		},{
			display: "手机归属地",
			name: "mobilePhoneLocation",
			minWidth:150
		},{
			display: "渠道",
			name: "channelName",
			minWidth:120
		},{
			display: "账户状态",
			name: "accountStatus",
			minWidth:100,
			render: function (rowdata){
				return "<a style='text-decoration:none;color:#0000FF;' href=javascript:changeAccountStatus('"+ rowdata.customerId +"','"+ rowdata.accountStatusChName +"')>" + rowdata.accountStatusChName + "</a>";
			}
		}],
		url: "${ctx}/account/findAccountAssetsList.do",
		parms: parms,
		checkbox: true,
		pageSize:20,
		pageSizeOptions: [10,20,30,50,100,200],
		rownumbers: true,
		headerRowHeight: 26,
		rowHeight: 26,
		enabledSort: false,
		selectRowButtonOnly: true // 点复选框才勾选
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
	  var url = "${ctx}/account/findAccountAssetsListExcel?"+"&"+params;
	  $('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
}


//多条件
function f_moreCondition(){
	$("#searchMoreCondition").toggle();
}

//重置
function f_reset(){
	$("#searchFrom").find(":input").not(":button,:submit,:reset,:hidden").val("").removeAttr("checked").removeAttr("selected");
}

//修改账户状态
function changeAccountStatus(customerId, accountStatus){
	if(accountStatus=="未实名"){
		$.ligerDialog.warn("未实名客户无法更改！");
	}
	else{
	 	openWindow=$.ligerDialog.open({
		        url: "${ctx}/pay/account/changeAccountStatusUI?customerId="+customerId, 
		        width: 500, height:260, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
		        name:"changeAccountStatus_1",
		        title: "信息"
/* 		        , buttons: [
	            <shiro:hasPermission name='finance_customer_asset_updateStatus'>
		            {text: '保存', onclick: function (item, dialog) { window.frames["changeAccountStatus_1"].saveAccountStatus(); }}, 
		        </shiro:hasPermission>   
		        {text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
			] */
		});
	}
	
}

// 客户详情
function detail(customerId){
	  openWindow=$.ligerDialog.open({
	            url: "${ctx}/customer/detail.do?customerId="+customerId, 
	            width: 850, height:650, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
	            title: "信息"
/* 	            , buttons: [
	            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
	        ] */
	    });
}



</script>
