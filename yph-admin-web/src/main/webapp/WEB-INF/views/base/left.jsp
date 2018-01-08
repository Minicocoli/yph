<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="layui-side layui-bg-black kit-side">
    <div class="layui-side-scroll">
        <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
            <c:forEach items="${menuList}" var="navMenus">
                <c:forEach items="${navMenus.menuList}" var="navParent">
                    <li class="layui-nav-item">
                        <a class="" href="javascript:;"><i class="fa fa-plug"
                                                           aria-hidden="true"></i><span> ${navParent.name}</span></a>
                        <dl class="layui-nav-child">
                            <c:forEach items="${navParent.menuList}" var="navChild">
                                <dd>
                                    <a href="javascript:;" kit-target
                                       data-options="{url:'${pageContext.request.contextPath}/${navChild.url}',icon:'&#xe658;',title:'${navChild.name}',id:'${navChild.id}'}"><i
                                            class="layui-icon">&#xe658;</i><span> ${navChild.name}</span></a>
                                </dd>
                            </c:forEach>
                        </dl>
                    </li>
                </c:forEach>
            </c:forEach>
        </ul>
    </div>
</div>
