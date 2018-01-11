<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--头部-->
<div class="layui-header">
    <div class="layui-logo"><span style="font-size: 21px;">后台管理系统</span></div>
    <ul class="layui-nav layui-layout-left kit-nav">
        <c:choose>
            <c:when test="${isMenuSplit}">
                <c:forEach items="${menuList}" var="nav">
                    <li class="layui-nav-item" id="${nav.id}" onclick="changeLeftMenu(this)"><a
                            href="javascript:void(0);">${nav.name}</a></li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach items="${showList}" var="nav">
                    <li class="layui-nav-item" id="${nav.id}" onclick="changeLeftMenu(this)"><a
                            href="javascript:void(0);">${nav.name}</a></li>
                </c:forEach>
                <li class="layui-nav-item layui-this" onclick="changeLeftMenu(this)">
                    <a href="javascript:;">${sysMenuVo.name}</a>
                    <dl class="layui-nav-child">
                        <c:forEach items="${hidenList}" var="nav">
                            <dd id="${nav.id}" onclick="changeLeftMenu(this)"><a
                                    href="javascript:void(0);">${nav.name}</a></dd>
                        </c:forEach>
                    </dl>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>

    <ul class="layui-nav layui-layout-right kit-nav">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img"> ${user.userName}
            </a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;">基本资料</a></dd>
                <dd><a href="javascript:;">安全设置</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;" onclick="loginOut()"><i class="fa fa-sign-out"
                                                                                  aria-hidden="true"></i> 注销</a>
        </li>
    </ul>
</div>

<script type="text/javascript">
    var loginOut = function () {
        layer.confirm('确定要退出系统吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layui.jquery.post('${pageContext.request.contextPath}/sys/user/loginOut.htm', function (data) {
                var retData = JSON.parse(data);
                if (retData.code == 0) {
                    layer.msg('退出成功!即将跳转到登录页面。');
                    setTimeout("toLogin()", 3000);
                }
            });
        }, function () {
            layer.closeAll();
        });
    }


    var toLogin = function () {
        window.location.href = "toLogin.htm";
    }

</script>