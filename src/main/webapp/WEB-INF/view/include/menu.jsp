<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

  <div position="left" title="导航栏" id="left">
  
        <div title="产品管理">
            <ul class="menulist">
                        <li menuid="1" menulink="${ctx}/gold/noviceProduct/updateUI" menuname="新手金"><span>新手金</span></li>
            </ul>
        </div>
        <div title="订单管理">
            <ul class="menulist">
                        <li menuid="106" menulink="${ctx}/bullion/order/findListUI" menuname="购物订单"><span>购物订单</span></li>
            </ul>
        </div>
        <div title="客户管理">
            <ul class="menulist">
                        <li menuid="201" menulink="${ctx}/customer/findListUI" menuname="客户信息"><span>客户信息</span></li>
            </ul>
        </div>
        <div title="信息管理">
            <ul class="menulist">
                    <li menuid="301" menulink="${ctx}/news/newNoticeUI" menuname="平台公告"><span>平台公告</span></li>
                
            </ul>
        </div>    
        <div title="系统设置">
            <ul class="menulist">
                    <li menuid="601" menulink="${ctx}/organ/findListShuUI" menuname="组织架构"><span>组织架构</span></li>
                
                    <li menuid="602" menulink="${ctx}/role/findListUI" menuname="角色授权"><span>角色授权</span></li>
                
                    <li menuid="603" menulink="${ctx}/user/findListUI" menuname="后台账号管理"><span>后台账号管理</span></li>
                
                    <li menuid="604" menulink="${ctx}/apm-druid/" menuname="Druid Monitoring"><span>Druid Monitor</span></li>
            </ul>
        </div>
    </div>


    <div position="center" id="home">
        <%@ include file="home.jsp"%>
    </div>


    <script type="text/javascript">
        var mytab;
        function addtab(id,atext,aurl) {
            if (!mytab){
            	return;
            }else{
                mytab.addTabItem({tabid:id,text:atext,url:aurl})
            }
        }
        
        $(function () {
            $("#main").ligerLayout({ leftWidth: 150 })
            mytab = $("#home").ligerTab({ width: "100%", height: "100%", contextmenu: true });
            //加载导航栏效果
            $("#left").ligerAccordion();
                
            //注册单击事件
            $(".menulist li").live("click", function () {
                    var vli = $(this);
                    var tabid = vli.attr("menuid");
                    var link = vli.attr("menulink");
                    vli.attr("tabid", tabid);
                    if (!link) return;
                        addtab(tabid, vli.attr("menuname"), link);
            }).live("mouseover", function () {
                    $(this).addClass("over")
            }).live("mouseout", function () {
                    $(this).removeClass("over")
            })
            
        });
    
  </script>
