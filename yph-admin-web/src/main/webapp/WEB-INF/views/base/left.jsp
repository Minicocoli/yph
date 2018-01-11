<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="layui-side layui-bg-black kit-side">
    <div class="layui-side-scroll">
        <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <%--一级菜单 ---> 系统设置--%>
        <c:forEach items="${menuList}" var="navMenus"  varStatus="status">
            <ul class="layui-nav layui-nav-tree
            <c:choose><c:when test="${status.count == 1}">menu-left-show</c:when><c:otherwise> menu-left-hide</c:otherwise></c:choose>" id="menu_${navMenus.id}" lay-filter="kitNavbar" kit-navbar>
                    <%--二级菜单 ----> 菜单设置--%>
                <c:forEach items="${navMenus.menuList}" var="navParent">
                    <li class="layui-nav-item">
                        <a class="" href="javascript:;"><i class="fa fa-list-ul"  aria-hidden="true"></i><span> ${navParent.name}</span></a>
                        <dl class="layui-nav-child">
                                <%--三级菜单--%>
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
            </ul>
        </c:forEach>
    </div>
</div>
