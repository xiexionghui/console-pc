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
			<ul id="myTab2" class="nav nav-tabs" >
			
			    <shiro:hasPermission name="customer_current_detail">
				    <li id="currentDetail" onclick="selectTabCurrentDetail('currentDetail')">
				        <a href="${ctx}/gold/account/findGoldFlowListUI?customerId=${customerId }" target="iframeCurrentDetail">明细记录</a>
					</li>
				</shiro:hasPermission>
							
			    <shiro:hasPermission name="customer_current_income">
					<li id="incomeRecord" onclick="selectTabCurrentDetail('incomeRecord')">
					    <a href="${ctx}/gold/income/incomeCurrentUI?customerId=${customerId }" target="iframeCurrentDetail">收益记录</a>
					</li>
				</shiro:hasPermission>
			</ul>	
    
			<div id="myTabContent2" >
				<div class="tab-pane fade in active" id="followrecord2">
					<iframe id="iframeCurrentDetail" name="iframeCurrentDetail" width="100%" height="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0"  allowtransparency="yes">
					</iframe>
				</div>
			</div>
			
   </div>
        
  </table>
</body>
</html>

        
<script type="text/javascript">
var selectId="currentDetail";
function selectTabCurrentDetail(id){
	$("#"+selectId).removeClass("active");
	$("#"+id).addClass("active");
	selectId=id;
}

$(function(){
	$("#myTab2").children().first().find("a")[0].click();
});
</script>