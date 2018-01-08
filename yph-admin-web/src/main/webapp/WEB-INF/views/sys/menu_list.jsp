<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html style="height:100%;width:100%;overflow:hidden;">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>菜单编辑</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/font-awesome/css/font-awesome.min.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layex/lay-ex.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/treeview/bootstrap-treeview.min.css"
          media="all">
</head>
<body style="height:100%;width: 100%;padding:0;margin: 0;overflow: hidden">
<input type="text" id="path" hidden="hidden" value="${pageContext.request.contextPath}">

<div id="content" style="width:100%;height: 100%;padding:0;margin: 0;">

    <%--左侧菜单树结构--%>
    <div style="width: 250px;height:90%;float:left;position: absolute;margin-top: 5px;margin-left: 5px;">
        <div id="menuTree" style="overflow: scroll;">
        </div>
    </div>

    <%--右侧内容结构--%>
    <div style="float: left;width: 100%;height:90%;margin-left: 260px;">
        <%--搜索--%>
        <blockquote id="searchQuoto" style="margin-top: 5px;" class="layui-elem-quote elem-quote-ex">
            <form class="layui-form" action="">
                <div class="layui-form-item form-item-ex">
                    <div class="layui-inline ">
                        <label class="layui-form-label form-label-ex-4">菜单名称:</label>
                        <div class="layui-input-inline input-line-ex">
                            <input type="text"  id="searchName" name="name" placeholder="输入菜单名称搜索" autocomplete="off"
                                   class="layui-input input-ex">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label form-label-ex-4">创建时间:</label>
                        <div class="layui-input-inline input-line-ex">
                            <input type="text" id="searchCreateTime" name="searchCreateTime" lay-verify="date"
                                   class="layui-input input-ex">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline input-line-ex line-button-ex">
                            <a class="layui-btn  layui-btn-sm" href="javascript:void(0);" onclick="formSearch()">查询</a>
                            <button type="reset" class="layui-btn layui-btn-primary  layui-btn-sm">重置</button>
                        </div>
                    </div>
                </div>
            </form>
        </blockquote>

        <%--按钮组--%>
        <blockquote id="buttonQuoto" style="margin-top: 5px;" class="layui-elem-quote elem-quote-ex">
            <button style="margin-left: 10px;" class="layui-btn layui-btn-sm" onclick="openCreateMenuWindow()">
                <i class="layui-icon">&#xe608;</i>新增菜单
            </button>
            <button style="margin-left: 10px; " class="layui-btn layui-btn-danger layui-btn-sm" onclick="batchDelMenus()">
                <i class="layui-icon">&#xe640;</i>批量删除</button>
        </blockquote>

        <%--数据表格--%>
        <table class="layui-hide" id="layMenu" lay-data="{id: 'idMenu'}" lay-filter="menuFilter"></table>

        <%--数据分页条--%>
        <div id="pageDiv"></div>
    </div>
</div>

<%--新建菜单 弹出框--%>
<div id="createMenuWindow" hidden="hidden" style="overflow: hidden">
    <blockquote class="layui-elem-quote" style="margin-top: 5px;">
      系统菜单 : 增添系统的功能喔!
    </blockquote>
    <fieldset class="layui-elem-field" style="width: 830px;margin-left: 8px;">
        <legend style="margin: 0px;padding: 0px;">填写菜单信息</legend>
        <div class="layui-field-box">
            <form class="layui-form" action="">
                <input type="text" id="id" name="id" value="" hidden="hidden" class="layui-input">
                <div class="layui-form-item form-item-ex">
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <label class="layui-form-label form-label-ex-4">菜单类型:</label>
                            <div class="layui-input-inline form-input-line-ex">
                                <select lay-filter="menuTypeFilter" id="menuType" name="type">
                                    <option value="" selected="">请选择菜单类型</option>
                                    <option value="0">一级菜单</option>
                                    <option value="1">二级菜单</option>
                                    <option value="2">三级菜单</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <label class="layui-form-label form-label-ex-4">上级菜单:</label>
                            <div class="layui-input-inline form-input-line-ex">
                                <select id="parentMenus" name="parentId" lay-filter="parentMenuFilter" lay-verify="required" lay-search="">
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item form-item-ex">
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <label class="layui-form-label form-label-ex-4">菜单名称:</label>
                            <div class="layui-input-inline form-input-line-ex">
                                <input type="text" id="name" name="name" required autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <label class="layui-form-label form-label-ex-4">排序:</label>
                            <div class="layui-input-inline form-input-line-ex">
                                <input type="number" id="sort" name="sort" required autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item form-item-ex">
                    <div class="layui-row">
                        <div class="layui-col-xs12">
                            <label class="layui-form-label form-label-ex-4">链接地址:</label>
                            <div class="layui-input-block form-input-line-ex">
                                <input type="text" name="url" id="url" placeholder="请输入连接地址" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                </div>

                <hr class="layui-bg-gray" style="margin: 0px">
                <div class="layui-form-item form-item-ex" style="text-align: center;">
                    <div class="layui-inline">
                        <div class="layui-input-inline input-line-ex line-button-ex">
                            <button class="layui-btn  layui-btn-sm" lay-submit="" lay-filter="saveMenu">保存</button>
                            <button type="reset" class="layui-btn layui-btn-primary  layui-btn-sm" style="margin-left: 70px;">重置</button>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </fieldset>
</div>


<%--操作模版--%>
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>

<%--菜单类型模版--%>
<script type="text/html" id="menuBar">
    {{# if(d.type=='0'){   }}
    <span>一级导航菜单</span>
    {{# } else if(d.type=='1'){ }}
    <span>二级导航菜单</span>
    {{# } else if(d.type=='2'){ }}
    <span>三级导航菜单</span>
    {{# } }}
</script>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/css/treeview/bootstrap-treeview.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/sys/menu_list.js"></script>
</body>
</html>
