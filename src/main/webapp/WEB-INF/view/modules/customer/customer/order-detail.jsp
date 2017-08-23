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
    
</head>

<body style="margin:10px;">
    
    <div>
			<ul id="myTabOrder" class="nav nav-tabs" >
			
			    <shiro:hasPermission name="customer_order_navice">
					<li id="noviceOrder" onclick="selectTabOrderDetail('noviceOrder')">
					    <a href="${ctx}/gold/buy/noviceFindListUI?customerId=${customerId }" target="iframeOrder">新手</a>
					</li>
			    </shiro:hasPermission>
							
				<shiro:hasPermission name="customer_order_current">			
					<li id="currentOrder" onclick="selectTabOrderDetail('currentOrder')">
						<a href="${ctx}/gold/buy/currentFindListUI?customerId=${customerId }" target="iframeOrder">金荷包</a>
					</li>
				</shiro:hasPermission>
					
				<shiro:hasPermission name="customer_order_regular">			
					<li id="regularOrder" onclick="selectTabOrderDetail('regularOrder')">
						<a href="${ctx}/gold/buy/regularFindListUI?customerId=${customerId }" target="iframeOrder">定期</a>
					</li>
				</shiro:hasPermission>
					
				<shiro:hasPermission name="customer_order_pledge">			
					<li id="pledgeOrder" onclick="selectTabOrderDetail('pledgeOrder')">
						<a href="${ctx}/gold/buy/findListUI?customerId=${customerId }" target="iframeOrder">保价</a>
					</li>
				</shiro:hasPermission>
					
				<shiro:hasPermission name="customer_order_risefall">			
					<li id="risefallOrder" onclick="selectTabOrderDetail('risefallOrder')">
						<a href="${ctx}/risefall/order/findListUI?customerId=${customerId }" target="iframeOrder">看涨跌</a>
					</li>
				</shiro:hasPermission>
				
			</ul>	
						
			<div id="myTabContentOrder" >
				<div class="tab-pane fade in active" id="followRecordOrder">
					<iframe id="iframeOrder" name="iframeOrder" width="100%" height="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0"  allowtransparency="yes">
					</iframe>
				</div>
			</div>
			
   </div>
        
  </table>
</body>
</html>

        
<script type="text/javascript">
var selectId="noviceOrder";
function selectTabOrderDetail(id){
	$("#"+selectId).removeClass("active");
	$("#"+id).addClass("active");
	selectId=id;
}

$(function(){
	$("#myTabOrder").children().first().find("a")[0].click();
});
</script>