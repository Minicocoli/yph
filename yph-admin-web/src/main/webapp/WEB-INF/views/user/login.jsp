<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>登录页面</title>
    <script>
        if (window != window.top) top.location.href = self.location.href;
    </script>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/plugins/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/slide.css" rel="stylesheet"/>
</head>
<body class="login" style="background:#ffffff;">
    <div>
        <div class="wrapper header clearfix" style="height:100px;">
            <div class="fl logo"><img src="${pageContext.request.contextPath}/img/logo.png" /></div>
            <div class="fr tip" ><i class="tel-icon"><img height="37" src="${pageContext.request.contextPath}/img/tel.png" /></i><span class="tel">服务热线：020-89859636</span></div>
        </div>
        <div class="bg  slideBox container clearfix">
            <div class="fl" style="height:100%;width:60%;">
                <div class="hd">
                    <ul><li></li><li></li></ul>
                </div>
                <div class="wrapper" style="padding:20px 0">
                    <div class="slide">
                        <div  id="slideBox" class="slideBox">
                            <div class="bd">
                                <ul class="clearfix">
                                    <li class="banner01"><img src="${pageContext.request.contextPath}/img/banner1.png" /></li>
                                    <li class="banner02"><img src="${pageContext.request.contextPath}/img/banner2.png" /></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="large-header" class="large-header fr" style="width: 400px;">
                <div class="kit-login-box">
                    <header>
                        <h1>用户登录</h1>
                    </header>
                    <div class="kit-login-main">
                        <form id="formId" action="/" class="layui-form" method="post">
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe612;</i>
                                </label>
                                <input type="text" name="userName" lay-verify="required" autocomplete="off"
                                       placeholder="这里输入用户名." class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe642;</i>
                                </label>
                                <input type="password" name="password" lay-verify="required" autocomplete="off"
                                       placeholder="这里输入密码." class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe642;</i>
                                </label>
                                <input type="text" id="validCode" name="validCode" autocomplete="off" placeholder="输入验证码"
                                       class="layui-input">
                                <span class="form-code" id="changeCode" style="position:absolute;right:2px; top:2px;">
                                        <img width="100" height="36" src="${pageContext.request.contextPath}/sys/user/captcha.htm"
                                             id="refImg" style="cursor:pointer;" title="点击刷新"/>
                                    </span>
                            </div>
                            <div class="layui-form-item">
                                <div class="kit-pull-left kit-login-remember">
                                    <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked
                                           title="记住帐号?">
                                </div>
                                <div class="kit-pull-right">
                                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
                                        <i class="fa fa-sign-in" aria-hidden="true"></i> 登录
                                    </button>
                                </div>
                                <div class="kit-clear"></div>
                            </div>
                        </form>
                    </div>
                    <footer>
                        <p>云品汇科技有限公司</p>
                    </footer>
                </div>
            </div>
        </div>
    </div>
<!-- /container -->

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.SuperSlide.2.1.1.js"></script>
<script>
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        $('#changeCode').on('click', function () {
            $("#validCode")[0].value = '';
            $('#changeCode > img')[0].src = '${pageContext.request.contextPath}/sys/user/captcha.htm';
        });

        //清理左侧菜单缓存
        var index = layer.load(2, {
            shade: [0.3, '#333']
        });
        $(window).on('load', function () {
            layer.close(index);
            form.on('submit(login)', function (formData) {
                $.post('${pageContext.request.contextPath}/sys/user/login.htm', $('#formId').serialize(),function (data) {
                    var retData = JSON.parse(data);
                    console.info(retData);
                    if(retData.code ==0 ){ // 登录成功
                        window.location.href="index.htm";
                    }else{
                        layer.msg(retData.msg);
                    }
                });
                return false;
            });
        }());

    });
    //焦点图
    $(function(){
        jQuery(".slideBox").slide({mainCell:".bd ul",autoPlay:true});
    }) ;
</script>
</body>

</html>