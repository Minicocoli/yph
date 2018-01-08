<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--头部-->
<div class="layui-header">
    <div class="layui-logo"><span style="font-size: 21px;">后台管理系统</span></div>
    <ul class="layui-nav layui-layout-left kit-nav">
        <c:forEach items="${menuList}" var="nav">
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/${nav.url}">${nav.name}</a></li>
        </c:forEach>
        <%--<li class="layui-nav-item layui-this">--%>
        <%--<a href="javascript:;">客户系统</a>--%>
        <%--<dl class="layui-nav-child">--%>
        <%--<dd><a href="">选项1</a></dd>--%>
        <%--<dd><a href="">选项2</a></dd>--%>
        <%--<dd><a href="">选项3</a></dd>--%>
        <%--</dl>--%>
        <%--</li>--%>
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
        <li class="layui-nav-item"><a href="javascript:;" onclick="loginOut()"><i class="fa fa-sign-out"aria-hidden="true"></i> 注销</a>
        </li>
    </ul>
</div>

<script type="text/javascript">
    var loginOut = function () {
        layer.confirm('确定要退出系统吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layui.jquery.post('${pageContext.request.contextPath}/sys/user/loginOut.htm',function (data) {
               var  retData =  JSON.parse(data);
                if(retData.code ==0){
                    layer.msg('退出成功!即将跳转到登录页面。');
                    setTimeout("toLogin()",3000);
                }
            });
        }, function(){
            layer.closeAll();
        });
    }

    var toLogin =function () {
        window.location.href="toLogin.htm";
    }

</script>