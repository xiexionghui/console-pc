<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>

    <%@ include file="../include/head.jsp" %>

    <body style="overflow: hidden;">

        <div id="top">
            <%@ include file="../include/top.jsp"%>
        </div>

        <div id="main">
            <%@ include file="../include/menu.jsp"%>
        </div>

    </body>
    
</html>