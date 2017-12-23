<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/font-awesome/css/font-awesome.min.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <!--头部-->
    <div class="layui-header">
        <div class="layui-logo"><span style="font-size: 21px;">后台管理系统</span></div>
        <ul class="layui-nav layui-layout-left kit-nav">
            <li class="layui-nav-item"><a href="javascript:;">系统设置</a></li>
            <li class="layui-nav-item"><a href="javascript:;">用户管理系统</a></li>
            <li class="layui-nav-item"><a href="javascript:;">销售系统</a></li>
            <li class="layui-nav-item"><a href="javascript:;">营销系统</a></li>
            <li class="layui-nav-item"><a href="javascript:;">订单系统</a></li>
            <li class="layui-nav-item"><a href="javascript:;">财务系统</a></li>
            <li class="layui-nav-item"><a href="javascript:;">日志系统</a></li>
            <li class="layui-nav-item layui-this">
                <a href="javascript:;">客户系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">选项1</a></dd>
                    <dd><a href="">选项2</a></dd>
                    <dd><a href="">选项3</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img"> Van
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">基本资料</a></dd>
                    <dd><a href="javascript:;">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> 基本元素</span></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" kit-target
                               data-options="{url:'https://www.baidu.com',icon:'&#xe658;',title:'百度一下',id:'5'}"><i
                                    class="layui-icon">&#xe658;</i><span> 百度一下</span></a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> 组件</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" kit-target
                               data-options="{url:'navbar.html',icon:'&#xe658;',title:'Navbar',id:'6'}"><i
                                class="layui-icon">&#xe658;</i><span> Navbar</span></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" id="container">
    </div>
    <!--页脚-->
    <div class="layui-footer" style="text-align: center">
        Hokok create in 2017-12-23
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/plugins/layui/lay/modules/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
<script>
    var message;
    layui.config({
        base: 'js/'
    }).use(['app', 'message'], function () {
        var app = layui.app,
            $ = layui.jquery,
            layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;
        //主入口
        app.set({
            type: 'iframe'
        }).init();
    });
</script>
</body>
</html>
