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
    <link rel="stylesheet" media="screen" href="${ctxStatic }/bootstrap-3.3.1/dist/css/bootstrap.css?version=20170101">
    
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>

</head>

<body style="margin:10px;">
    <table cellpadding="0" cellspacing="0" class="l-table-edit">
    
        <input type="hidden" id="customerId" value="${customer.customerId }">
         
        <tr>
            <td colspan="6">
				<div class="l-group l-group-hasicon">
					<img src="${ctxStatic}/ligerUI/lib/ligerUI/skins/icons/communication.gif"><span style="margin-left:0px;">基本信息</span>
				</div>
            </td>
        </tr>
        
        <tr>
			<td width="11%" align="right" class="l-table-edit-td">手机号码：</td>
			<td width="20%" align="left" class="l-table-edit-td">${customer.mobilePhone }</td>
			<td width="14%" align="right" class="l-table-edit-td">真实姓名：</td>
			<td width="20%" align="left" class="l-table-edit-td">${customer.realname }</td>
			<td width="14%" align="right" class="l-table-edit-td">身份证号：</td>
			<td width="20%" align="left" class="l-table-edit-td">${customer.idcardNo }</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">最近登录IP：</td>
			<td align="left" class="l-table-edit-td">${customer.lastIp }</td>
			<td align="right" class="l-table-edit-td">最后登录时间：</td>
			<td align="left" class="l-table-edit-td">${customer.lastTime }</td>
			<td align="right" class="l-table-edit-td">登录次数：</td>
			<td align="left" class="l-table-edit-td">${customer.loginCount }</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">注册时间：</td>
			<td align="left" class="l-table-edit-td">${customer.registerTime }</td>
			<td align="right" class="l-table-edit-td">首次充值：</td>
			<td align="left" class="l-table-edit-td">${customer.rechargeFirstTime }</td>
			<td align="right" class="l-table-edit-td">首次投资：</td>
			<td align="left" class="l-table-edit-td">${customer.buyFirstTime }</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">状态：</td>
			<td align="left" class="l-table-edit-td">${customer.customerStatus }</td>
			<td align="right" class="l-table-edit-td">是否合时代用户：</td>
			<td align="left" class="l-table-edit-td">${customer.p2pFlag }</td>
			<td align="right" class="l-table-edit-td">合时代用户名：</td>
			<td align="left" class="l-table-edit-td">${customer.p2pUsername }</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">我的推荐码：</td>
			<td align="left" class="l-table-edit-td">${customer.recomCode }</td>
			<td align="right" class="l-table-edit-td">推荐人：</td>
			<td align="left" class="l-table-edit-td">${customer.referrerPhone }</td>
			<td align="right" class="l-table-edit-td">渠道：</td>
			<td align="left" class="l-table-edit-td">${customer.channelName }</td>
        </tr>
    
    </table>
    
    <br />
    <br />
    
    <div>
    
			<ul id="myTab" class="nav nav-tabs" >
			         <shiro:hasPermission name="customer_query_assetPandect">
							<li id="assetPandect" onclick="selectTab('assetPandect')">
								<a href="${ctx}/customer/pandectUI?customerId=${customer.customerId }" target="recodeIframe">资产总览</a>
						    </li>
				     </shiro:hasPermission>
						    
			         <shiro:hasPermission name="customer_query_bankCardInfo">
						    <li  id="bankCardInfo" onclick="selectTab('bankCardInfo')">
								<a href="${ctx}/pay/bankCard/findListUI?customerId=${customer.customerId }" target="recodeIframe">银行卡信息</a>
						    </li>
				     </shiro:hasPermission>
						    
			         <shiro:hasPermission name="customer_query_accountDetail">
						    <li  id="accountInfo" onclick="selectTab('accountInfo')">
								<a href="${ctx}/pay/account/findListUI?customerId=${customer.customerId }" target="recodeIframe">账户明细</a>
						    </li>
				     </shiro:hasPermission>
						    
<%--						
			         <shiro:hasPermission name="customer_query_returnedMoneyRecord">
							<li id="returnedMoneyRecord" onclick="selectTab('returnedMoneyRecord')">
								<a href="${ctx}/gold/hold/findListUI?customerId=${customer.customerId }" target="recodeIframe">回款记录</a>
							</li>
				     </shiro:hasPermission> --%>
				     
 			         <shiro:hasPermission name="customer_current">
							<li id="currentDetail" onclick="selectTab('currentDetail')">
								<a href="${ctx}/gold/account/currentDetailUI?customerId=${customer.customerId }" target="recodeIframe">金荷包明细</a>
							</li>
				     </shiro:hasPermission>
							
 			         <shiro:hasPermission name="customer_query_orderRecord">
							<li id="orderRecord" onclick="selectTab('orderRecord')">
								<a href="${ctx}/customer/orderDetailUI?customerId=${customer.customerId }" target="recodeIframe">订单记录</a>
							</li>
				     </shiro:hasPermission>
							
			         <shiro:hasPermission name="customer_query_returnVisitRecord">
							<li id="returnVisitRecord" onclick="selectTab('returnVisitRecord')">
								<a href="${ctx}/followRecord/findListUI?customerId=${customer.customerId }" target="recodeIframe">回访记录</a>
							</li>
				     </shiro:hasPermission>
			</ul>	
						
    
			<div id="myTabContent" >
				<div class="tab-pane fade in active" id="followrecord">
					<iframe id="recodeIframe" name="recodeIframe" width="100%" height="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0"  allowtransparency="yes">
					</iframe>
				</div>
			</div>
			
        </div>
        


  </table>
</body>
</html>

<script type="text/javascript">
var selectId="assetPandect";
function selectTab(id){
	$("#"+selectId).removeClass("active");
	$("#"+id).addClass("active");
	selectId=id;
}

$(function(){
	$("#myTab").children().first().find("a")[0].click();
});


</script>