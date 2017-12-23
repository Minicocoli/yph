<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
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
