<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/25
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"  %>
<html>
<head>
    <title>错误页面</title>
    <style>
        .circle {
            width: 200px;
            height: 200px;
            border-radius: 200px;
            border: 15px solid #B22727;
        }

        .circle > div {
            color: #B22727;
            font: bolder 50px arial;
            transform: matrix(0.642788,-0.766044,0.766044,0.642788,0,95);
            -ms-transform: matrix(0.642788,-0.766044,0.766044,0.642788,0,95);
            -moz-transform: matrix(0.642788,-0.766044,0.766044,0.642788,0,95);
            -o-transform: matrix(0.642788,-0.766044,0.766044,0.642788,0,95);
            position: absolute;
            top: 0;
            left: 0;
        }

        .block {
            width: 60px;
            display: inline-block;
            height: 15px;
            background-color: #B22727;
            margin: 12px 10px;
        }
    </style>
</head>

<body>


<div>
    <div class="circle" >
        <div>
            <span class="block"></span><span>404</span><span class="block"></span>
        </div>
    </div>
</div>

<br/><br/><br/><br/>
<h2 style="text-align: center">
    林哥 ! 找不到页面啦 .................
</h2>
</body>
</html>
