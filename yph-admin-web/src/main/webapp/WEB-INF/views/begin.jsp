<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理系统</title>
    <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/font-awesome/css/font-awesome.min.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" media="all">
</head>
<style>
    .menu-left-show{
        display: block;
    }

    .menu-left-hide{
        display: none;
    }


</style>
<body>

<div class="layui-layout layui-layout-admin kit-layout-admin">
    <jsp:include page="base/top.jsp"/>
    <jsp:include page="base/left.jsp"/>
    <div class="layui-body" id="container">
    </div>
    <jsp:include page="base/footer.jsp"/>

    <%@include file="base/footer.jsp"%>

</div>

<%--<script src="${pageContext.request.contextPath}/js/plugins/layui/lay/modules/jquery.js"></script>--%>
<script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
<script>
    var message;
    var changeLeftMenu;
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



        /**
         *  改变隐藏左侧菜单
         * @param obj
         */
        changeLeftMenu =function (obj) {
            var id =  'menu_'+obj.id;
            $('.menu-left-show').addClass('menu-left-hide');
            $('.menu-left-show').removeClass(' menu-left-show');
            $('#'+id).removeClass('menu-left-hide');
            $('#'+id).addClass('menu-left-show');
        }





    });
</script>

</body>
</html>
