<%@ page contentType="text/html;charset=UTF-8"%>

<head>
<style type="text/css">
a:link {font-size:12px; color:#000000; text-decoration:none;}
a:visited {font-size:12px; color:#000000; text-decoration:none;}
a:hover {font-size:12px; color:#00CCFF;text-decoration:none;}
.STYLE4 {font-size: 12px}

.top_right {color:#FFF}

.white-link {color:#FFF}
.white-link a:link {font-size:12px; color:#FFF; text-decoration:none}
.white-link a:visited,.white-link a:visited {font-size:12px; color:#000; text-decoration:none;}
</style>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr style="color:#FFF;background:url(${ctxStatic }/images/top_bg.jpg)">
    <td height="31" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="40%" height="31" style="color:#FFF;padding-left:50px;background:url(${ctxStatic }/images/gold_24px.png) 20px 7px no-repeat">买金呗-黄金平台后台管理系统</td> 
        <td width="60%" align="right"> <table width="100%" border="0" cellspacing="0" cellpadding="0" align="right">
          <tr class="white-link">
            <td align="right"><img src="${ctxStatic }/ligerUI/image/uesr.gif" width="14" height="14"> 
            
               <span > 当前登录用户：  </span> &nbsp; 

               <span class="STYLE1"><img src="${ctxStatic }/ligerUI/image/home.gif" width="12" height="13"> </span>
               <span class="STYLE4"><a href="${ctx}/index">回首页</a>  &nbsp;  &nbsp; 
               
               </span><span class="STYLE1"> <img src="${ctxStatic }/ligerUI/image/quit.gif" width="16" height="16"> </span><span class="STYLE4">
               <a href="javascript:outSystem();">退出系统</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
               
               </span><span class="STYLE1"> <img src="${ctxStatic }/ligerUI/lib/ligerUI/skins/icons/modify.gif" width="16" height="16"> </span><span class="STYLE4">
               <a href="javascript:updatePassword();">修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
              </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>

<script type="text/javascript">

var openWindow
// 修改密码
function updatePassword () {
	var openWindow=$.ligerDialog.open({
            url: "${ctx}/user/updatePasswordUI.do", 
            width: 500, height:300, showMax: true, showToggle: true, showMin: true, isResize: true, modal: true, 
            name:"updateUserPasswordUI",
            title: "修改密码"
/*             , buttons: [
                 { text: '保存', onclick: function(item, dialog) { window.frames["updateUserPasswordUI"].updatePasswordFormSubmit();} },
                 { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
             ] */
      });
}

// 退出系统
function outSystem(){
	window.location.href="${ctx}/logout";
}
</script>