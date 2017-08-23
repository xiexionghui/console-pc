<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<body>

    <table cellpadding="0" cellspacing="0" class="l-table-edit">
    
        <tr>
			<td width="18%" align="right" class="l-table-edit-td">&nbsp;</td>
			<td width="15%" align="left" class="l-table-edit-td">&nbsp;</td>
			<td width="19%" align="right" class="l-table-edit-td">&nbsp;</td>
			<td width="15%" align="left" class="l-table-edit-td">&nbsp;</td>
			<td width="18%" align="right" class="l-table-edit-td">&nbsp;</td>
			<td width="15%" align="left" class="l-table-edit-td">&nbsp;</td>
        </tr>
    
        <tr>
			<td align="right" class="l-table-edit-td">资产总额：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.assetTotal}" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">可用余额：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.usableBalance}" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">冻结金额：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.freezeBalance}" pattern="#,##0.00#"/></td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">黄金总克数：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.totalGram}" pattern="#,##0.000#"/></td>
			<td align="right" class="l-table-edit-td">黄金冻结资产：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.freezeGram}" pattern="#,##0.000#"/></td>
			<td align="right" class="l-table-edit-td"></td>
			<td align="left" class="l-table-edit-td"></td>
        </tr>
        
        <tr>
			<td colspan="6">&nbsp;</td>
        </tr>
        
        
        <tr>
			<td align="right" class="l-table-edit-td">新手金总克重：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.noviceGram}" pattern="#,##0.000#"/></td>
			<td align="right" class="l-table-edit-td">新手金昨日收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.noviceInterestYesterday}" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">新手金累计收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.noviceTotalInterest}" pattern="#,##0.00#"/></td>
        </tr>
        
        <tr>
			<td colspan="6">&nbsp;</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">金荷包总克重：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.currentGram}" pattern="#,##0.000#"/></td>
			<td align="right" class="l-table-edit-td">金荷包昨日收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.currentInterestYesterday}" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">金荷包累计收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.currentInterestTotal}" pattern="#,##0.00#"/></td>
        </tr>
        <tr>
			<td align="right" class="l-table-edit-td">昨日红包加息收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="0" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">今日待发放红包加息收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="0" pattern="#,##0.00#"/></td>
		    <td align="right" class="l-table-edit-td"></td>
			<td align="left" class="l-table-edit-td"></td>
        </tr>
        
        <tr>
			<td colspan="6">&nbsp;</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">保价金总克数：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.pledgeGram}" pattern="#,##0.000#"/></td>
			<td align="right" class="l-table-edit-td">保价金总金额：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.pledgeGoldValue}" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">保价金待收收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.pledgeRecievedInterest}" pattern="#,##0.00#"/></td>
        </tr>
        <tr>
			<td align="right" class="l-table-edit-td">保价金红包加息收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="0" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td"></td>
			<td align="left" class="l-table-edit-td"></td>
			<td align="right" class="l-table-edit-td">保价金累计收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.pledgeInterestTotal}" pattern="#,##0.00#"/></td>
        </tr>
        
        <tr>
			<td colspan="6">&nbsp;</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">定期金总克重：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.regularGram - assetPandect.noviceGram}" pattern="#,##0.000#"/></td>
			<td align="right" class="l-table-edit-td">定期金昨日收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.regularInterestYesterday-assetPandect.noviceInterestYesterday}" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">定期金累计收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.regularInterestTotal-assetPandect.noviceTotalInterest}" pattern="#,##0.00#"/></td>
        </tr>
        <tr>
			<td align="right" class="l-table-edit-td">定期金红包加息收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="0" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td"></td>
			<td align="left" class="l-table-edit-td"></td>
		    <td align="right" class="l-table-edit-td"></td>
			<td align="left" class="l-table-edit-td"></td>
        </tr>
        
        <tr>
			<td colspan="6">&nbsp;</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">看涨跌总金额：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.risefallTotal}" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">看涨跌待收最小收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.risefallMinIncome}" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td">看涨跌待收最大收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.risefallMaxIncome}" pattern="#,##0.00#"/></td>
        </tr>
        <tr>
			<td align="right" class="l-table-edit-td">看涨跌红包加息收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="0" pattern="#,##0.00#"/></td>
			<td align="right" class="l-table-edit-td"></td>
			<td align="left" class="l-table-edit-td"></td>
			<td align="right" class="l-table-edit-td">看涨跌累计收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.risefallTotalIncome}" pattern="#,##0.00#"/></td>
        </tr>        

        <tr>
			<td colspan="6">&nbsp;</td>
        </tr>
        
        <tr>
			<td align="right" class="l-table-edit-td">&nbsp;</td>
			<td align="left" class="l-table-edit-td">&nbsp;</td>
			<td align="right" class="l-table-edit-td">&nbsp;</td>
			<td align="left" class="l-table-edit-td">&nbsp;</td>
			<td align="right" class="l-table-edit-td">累计收益：</td>
			<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${assetPandect.interestTotal + assetPandect.risefallTotalIncome}" pattern="#,##0.00#"/></td>
        </tr>
    
    </table>
    
  </table>
</body>
</html>
