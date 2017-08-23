<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
  <head>
	<style type="text/css">
	#content {
		width: 740px;
		float: left;
	}
	#errorbox {
		background: url('${ctxStatic}/images/error_bg.jpg') no-repeat center 50% transparent;
		width:500px;
		height: 420px;
		margin: 0 auto;
		text-align: center;
		padding-top: 30px;
	}
	#errorinfo {
		width: 100%;
		height: 36px;
		line-height: 36px;
	}
	#errorinfo span {
		font-size: 16px;
		color: #555555;
	}
	</style>
  </head>
  <body>
<div id="content">
	<div id="errorbox">
		<div style="height:120px;width: 100%;">
			<img src="${ctxStatic}/images/warning.gif">
		</div>
		<div style="width: 100%;">
			<img src="${ctxStatic}/images/sorry.png">
		</div>
		<div id="errorinfo">
			<span>
				服务器拒绝了您的访问请求，请联系本站网络管理员<br/>
				建议您稍后再试！
			</span>
		</div>
	</div>
</div>
  </body>
</html>
