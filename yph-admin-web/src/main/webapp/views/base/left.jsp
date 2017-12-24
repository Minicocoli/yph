<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="layui-side layui-bg-black kit-side">
    <div class="layui-side-scroll">
        <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>

            <c:forEach items="${leftMenus}" var="nav">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> ${nav.menus}</span></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" kit-target
                               data-options="{url:'https://www.baidu.com',icon:'&#xe658;',title:'百度一下',id:'5'}"><i
                                    class="layui-icon">&#xe658;</i><span> 百度一下</span></a>
                        </dd>
                    </dl>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
