<!doctype html>
<%@ page contentType="text/html;charset=utf-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />

<html>
<head>
<title>买金呗-黄金后台系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0,initial-scale=1.0,user-scalable=no">
<link href="${ctxStatic }/images/favicon.ico" type="image/x-icon" rel="shortcut icon">
<script src="${ctxStatic }/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/js/jquery.cookie.js"></script> 
<script type="text/javascript" src="${ctxStatic}/js/md5.js"></script>
<script type="text/javascript">if(top.location!==self.location)top.location.href=self.location.href;</script>
<style>
	body{ margin: 0; padding: 0; background:url(${ctxStatic}/images/bg_login.jpg) no-repeat center top; font-family:Microsoft Yahei,Helvetica, Arial,"宋体"; }
	input{font-family:Microsoft Yahei,Helvetica, Arial,"宋体"; }
	.loginBox{ width: 340px; margin: 8% auto;}
	.logo{ height: 48px; background: url(${ctxStatic}/images/title_login.png) no-repeat center top; margin-bottom: 25px;}
	.loginCon{background: #fff;padding: 20px 26px;border-top: 0;color: #666; border-radius: 5px;}
	.loginCon p.error{ font-size: 14px; color: #EC5E00; margin: 0; line-height: 20px; height: 20px; text-align: center; display: none;}
	.loginMsg{text-align: left;padding: 0;font-size: 18px;font-weight:600;margin: 0 0 -1px;line-height: 30px;border-bottom: solid 3px #008df3;display: inline-block;margin-bottom: 15px;}
	
	.inpBox{position: relative;margin-bottom: 15px;}
	.inpBox	input{width: 236px; height: 32px;padding: 6px 6px 6px 44px;background-color: #fff;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 3px 2px rgba(0,0,0,.05); font-size: 14px; transition: all 0.1s ease-in; outline:none;}
	.inpBox	input:focus{ border-color: #008df3;}
	.inpBox label{ position:absolute; left: 1px; top: 1px; height: 44px; width:44px; background:url(${ctxStatic}/images/icon_login.png) no-repeat 0 0;}
	.inpBox.pass label{ background:url(${ctxStatic}/images/icon_login.png) no-repeat 0 -44px;}
	.inpBox.code label{ background:url(${ctxStatic}/images/icon_login.png) no-repeat 0 -88px;}
	.inpBox.code img{ position: absolute; right: 40px; top:10px; border-radius: 3px;}
	.inpBox.code span{ width:22px; height:22px;cursor:pointer;position:absolute; font-size: 12px; color: #666; right: 8px; top:12px; background: url(${ctxStatic}/images/icon_login.png) no-repeat 0 bottom;}

	.checkbox{ font-size: 14px;}
	.checkbox input{ width: 16px; height: 16px; margin: 2px 4px 0; float: left;}
	
	.loginBtn{ margin: 15px 0 10px;}
	.loginBtn input{ background: #008df3; cursor: pointer; text-align: center; font-size: 16px; color: #fff; line-height: 46px; height: 46px; border: none; border-radius: 4px; width: 100%; transition: background 0.1s ease-in; outline:none;}
	.loginBtn input:hover{ background: #0082e0;}
	.loginBtn input:focus{ box-shadow: none;}
	
	.login-ts{ height:30px; text-align:center; color:#f60; font-size:14px; font-family:"微软雅黑"; line-height:30px; margin-top:14px}
	.login-ts p{ display:none;}
</style>
</head>

<body>
	<div class="loginBox">
		<div class="logo"></div>
		<div class="loginCon">
			<p class="loginMsg">账号密码登陆</p>
			<form action="${ctx}/login" id="loginform" method="post">
				<div class="inpBox name">
					<label></label><input id="username" name="username"  onblur="reloadValidateCode();" type="text" placeholder="用户名" tabindex="1" value="${username }"  maxlength="" />
				</div>
				<div class="inpBox pass">
					<label></label><input id="password" type="password" name="password"  placeholder="密码" tabindex="2" maxlength="20" />
				</div>
				<div class="inpBox code">
					<label></label><input type="text" class="validCode" placeholder="验证码" tabindex="3" maxlength="6" name="validateCode" />
					<img id="validateCodeImg" />
					<span title="看不清？" onclick="javascript:reloadValidateCode();"></span>
				</div>
				<div class="checkbox">
					<input type="checkbox" id="rememberUserName" class="regular-checkbox" tabindex="4" />
					<label for="rememberUserName">记住用户名</label>
	    		</div>
				
				<div class="loginBtn"><input id="loginbtn" value="登录" type="button" tabindex="5" onclick="login();"></div>
			</form>
			<div class="login-ts">
    			<p></p>
    		</div>
		</div>
	</div>
	
<script type="text/javascript">
function reloadValidateCode(){
	var username=$("#username").val();
    $("#validateCodeImg").attr("src","${ctx}/servlet/validateCodeServlet?username="+username+"&data=" + new Date().getTime() + Math.floor(Math.random()*24));
}


//检查是否勾选记住账号并刷新cookie
function checkRemember_login() {
	var remember_checked = document.getElementById("rememberUserName").checked;
	if (remember_checked) {
		Set_Cookie("username", $("#username").val(), 10, "/", "", "");
	} else {
		Set_Cookie("username", "", 10, "/", "", "");
	}
	Set_Cookie("rememberUserName", remember_checked, 10, "/", "", "");
}

// 写入cookie
function Set_Cookie(name, value, expires, path, domain, secure) {
	var today = new Date();
	today.setTime(today.getTime());
	if (expires) {
		expires = expires * 1000 * 60 * 60 * 24;
	}
	var expires_date = new Date(today.getTime() + (expires));
	document.cookie = name + "=" + escape(value)
			+ ((expires) ? ";expires=" + expires_date.toGMTString() : "")
			+ ((path) ? ";path=" + path : "")
			+ ((domain) ? ";domain=" + domain : "")
			+ ((secure) ? ";secure" : "");
}

var alertFlag = true;
function login() {
	if($("#username").val()==""||$("#username").val()==null){
		$(".login-ts").html("用户名不能为空");
	}else if($("#password").val()==""||$("#password").val()==null){
		$(".login-ts").html("密码不能为空");
	}else if($(".validCode").val()==""||$(".validCode").val()=="验证码"){
		$(".login-ts").html("验证码不能为空");
	}else{
		checkRemember_login();
		md5pwd();
	    $("#loginform").submit();
	}
}

function md5pwd(){
	var pwd = MD5(MD5($("#password").val())+$.trim($("#username").val()).toLowerCase());
	$("#password").val(pwd)
}

$("body").bind('keyup',function(event) {  
	if(event.keyCode==13){
		if(alertFlag==true){
			login();
		}else{
			layer.closeAll(); 
			alertFlag = true;
			$("#password").focus();
		}
	} 
}); 


$(document).ready(function(){
	// 复选 框及cookie处理
	var remember_checked = $.cookie("rememberUserName");
	if (remember_checked == "true") {
		$("#rememberUserName").attr("checked", remember_checked);
	}
	var cookieValue = $.cookie("username");
	if (remember_checked && cookieValue != null && cookieValue != "") {
		$("#username").val(cookieValue);
		$("#password").focus();
	} else {
		$("#username").focus();
	}
	reloadValidateCode();
});
</script>

<script type="text/javascript">
	$(".login-ts>p").show(100);
	$(".login-ts").find("p").html("${loginerror}");
	$("#password").css("z-index","5").focus();
</script>

</script> 
</body>
</html>