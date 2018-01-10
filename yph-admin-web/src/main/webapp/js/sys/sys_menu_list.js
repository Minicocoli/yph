/**
 *  系统菜单 --js
 *      create by Hzhan 2018-01-05
 *  @type {*|jQuery}
 */

// 开启创建菜单页面
var openCreateMenuWindow = null;

var batchDelMenus = null;

var formSearch = null;

layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element'], function () {

    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , form = layui.form //分页
    layer = layui.layer //弹层
        , table = layui.table //表格
        , element = layui.element; //元素操作


    // 项目路径
    var serverPath = $('#path').val();


    // 设置表格宽高
    var contentWidth = $('#content')[0].offsetWidth;
    var contentHeight = $('#content')[0].offsetHeight;
    var tableWidth = contentWidth - 265;
    var tableHeight = contentHeight - 180;
    $('#tree')[0].style.height = contentHeight + 'px';
    $('#buttonQuoto')[0].style.width = tableWidth + 'px';

    /**
     *  搜索对象
     * @type {{pageNum: number, pageSize: number, name: null}}
     */
    var filter = {
        createTime: null,
        pageNum: 1,
        pageSize: 20,
        name: null
    };

    /**
     *  初始化日期
     */
    laydate.render({
        elem: '#searchCreateTime'
    });

    /**
     *  初始化表格
     * @param tableData
     */
    var initMenuTable = function (tableData) {
        table.render({
            elem: '#layMenu'
            , id: 'idMenu'
            , height: tableHeight
            , width: tableWidth
            , data: tableData
            , loading: true
            , limit: page.limit
            , skin: 'row'
            , even: true
            , cols: [[ //表头
                {field: 'left', title: '', type: 'checkbox', width: 80, align: 'center'}
                , {field: '', title: '序号', type: 'numbers', width: 80, align: 'center'}
                , {field: 'name', title: '菜单名称', width: 200, align: 'center'}
                , {field: 'type', title: '菜单类型', width: 180, toolbar: '#menuBar', align: 'center'}
                , {field: 'sort', title: '排序', width: 180, align: 'center'}
                , {field: 'parentId', title: '上级id', width: 180, align: 'center'}
                , {field: 'url', title: '链接地址', width: 180, align: 'center'}
                , {field: 'createTime', title: '创建时间', width: 170, align: 'center'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barOption'}
            ]]
        });
    }


    var isPaging = false;

    /**
     *  分页对象
     * @type {{count: null, limit: null, limits: null, curr: null}}
     */
    var page = {
        count: null,
        limit: 20,
        limits: [20, 30, 40, 50],
        curr: 1,
        groups: 5
    }

    /**
     *  初始化分页
     */
    var initPgae = function () {
        laypage.render({
            elem: 'pageDiv'
            , count: page.count
            , limit: page.limit
            , limits: page.limits
            , curr: page.curr
            , groups: page.groups
            , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
            , jump: function (obj, first) {
                //首次不执行
                if (!first) {
                    if (!isPaging) {
                        layer.load(0, {shade: false});
                        isPaging = true;
                        page.curr = obj.curr;
                        page.limit = obj.limit;
                        filter.pageNum = obj.curr;
                        filter.pageSize = obj.limit;
                        getMenuListPage();
                    } else {
                        layer.msg('亲! 不要点击这么快啦~~~~ ');
                    }
                }
            }
        });
    }


    /**
     *  开启新增菜单页面
     */
    openCreateMenuWindow = function () {
        layer.open({
            type: 1,
            title: ['新建菜单', 'font-size:18px;'],
            offset: 'auto',
            anim: 2,
            fixed: false,
            offset: '100px',
            scrollbar: false,
            resize: false,
            area: ['850px', '500px'],
            content: $('#createMenuWindow')
        });
    }

    /**
     *  监听 新增菜单的保存按钮操作
     */
    form.on('submit(saveMenu)', function (data) {
        saveMenu(data.field);
        return false;
    });

    /**
     *  监听菜单级别的选择
     */
    form.on('select(menuTypeFilter)', function (data) {
        var type = data.value;
        if (type != '') {
            if (type == '0') {
                refreshMenuSelect(null);
            } else {
                type = type - 1;
                findMenuByType(type);
            }
        }
    });


    /**
     *  刷新 上级菜单下拉框
     */
    var refreshMenuSelect = function (menuList) {
        if (menuList != null) {
            var htm = '<option value="" selected="">请选择上级菜单</option>';
            for (var i = 0; i < menuList.length; i++) {
                htm = htm + '<option value="' + menuList[i].id + '" >' + menuList[i].name + '</option>';
            }
            $('#parentMenus').html(htm);

            // form.render('select','parentMenuFilter');
            form.render('select');
        }
    }


    //监听工具条
    table.on('tool(menuFilter)', function (obj) {
        if (obj.event === 'edit') {
            // 编辑
            openEditWindow(obj.data);
        } else if (obj.event === 'del') {
            layer.confirm('确定要删除该菜单', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                delMenuById(obj.data.id);
            });
        }
    });


    /**
     *  批量按钮删除
     */
    batchDelMenus = function () {
        layer.confirm('确定要删除所选的？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var selectIds = [];
            var selectObjList = table.checkStatus('idMenu').data;
            if (selectObjList.length > 0) {
                for (var i = 0; i < selectObjList.length; i++) {
                    selectIds.push(selectObjList[i].id);
                }
                console.log(selectIds);
                batchDelMenusByIds(selectIds);
            } else {
                layer.msg('请选择要删除的菜单选项喔!');
            }
        }, function () {
        });
    }

    /**
     *  搜索功能
     */
    formSearch = function () {
        filter.name = $('#searchName')[0].value;
        filter.createTime = $('#searchCreateTime')[0].value;
        getMenuListPage();
    }

    /**
     *   开启编辑窗口
     * @param data  选中的行的数据
     */
    var openEditWindow = function (data) {
        // 数据回显
        $('#id')[0].value = data.id;
        $('#sort')[0].value = data.sort;
        $('#url')[0].value = data.url;
        $('#name')[0].value = data.name;

        // 渲染下拉选择框 ---> 【菜单类型】
        renderSelect(data.type,data.parentId);

        layer.open({
            type: 1,
            title: ['编辑菜单', 'font-size:18px;'],
            offset: 'auto',
            anim: 2,
            fixed: false,
            offset: '100px',
            scrollbar: false,
            resize: false,
            area: ['850px', '500px'],
            content: $('#createMenuWindow'),
            end: function () {
                // 将回显的数据清空
                $('#id')[0].value = '';
                $('#sort')[0].value = '';
                $('#url')[0].value = '';
                $('#name')[0].value = '';
            }
        });
    }

    /**
     *  通过菜单类型回显
     * @param type
     */
    var renderSelect = function (type,parentId) {
        // 回显菜单类型
        var selectMenusType = ['一级菜单', '二级菜单', '三级菜单'];
        var htm = '<option value="">请选择菜单类型</option>';
        for (var i = 0; i < selectMenusType.length; i++) {
            if (type == i) {
                htm = htm + '<option value="' + i + '" selected="" >' + selectMenusType[i] + '</option>';
            } else {
                htm = htm + '<option value="' + i + '" >' +  selectMenusType[i] + '</option>';
            }
        }
        $('#menuType').html(htm);
        form.render('select');
        // 回显上级菜单
        // 先读取缓存
        type =parseInt(type);
        if(type>0){
            type = type -1;
        }
        findMenuByType(type ,true,parentId);
    }

    /**
     *   渲染上级菜单 回显
     * @param list
     */
    var renderParentMenu = function (list,parentId) {
        var parentMenuHtm = '<option value="">请选择上级菜单</option>';
        for (var i = 0; i < list.length; i++) {
            if(list[i].id == parentId){
                parentMenuHtm =parentMenuHtm +'<option value="' + list[i].id + '" selected="" >' + list[i].name + '</option>';
            }else{
                parentMenuHtm =parentMenuHtm +'<option value="' + list[i].id + '" >' + list[i].name + '</option>';
            }
        }

        $('#parentMenus').html(parentMenuHtm);

        form.render('select');
    }


    /*****************************************  END   【  以上是lay组件初始化   】 *****************************************/

    /*****************************************  START 【 以下是获取数据 操作数据 】 *****************************************/

    /**
     *  初始化获取数据
     */
    var init = function () {

        initMenuTable(null);

        initPgae();

        getMenuListPage();

        initTree();

    }

    /**
     *  初始化树
     */
    var initTree =function () {
        // ztree 设置
        var setting = {
            // 异步请求
            async: {
                enable: true,
                url: serverPath + '/sys/menu/findListByZtree.htm',
                autoParam: ["id"],
                // otherParam:["roleId",selectRoleId],
                dataFilter: datasFilter,
                type: 'post'
            }
        };
        $.fn.zTree.init($("#tree"), setting);
    }

    /**
     *   树表返回数据 进行拼装渲染
     * @param treeId
     * @param parentNode
     * @param childNodes
     * @returns {null}
     */
    function datasFilter(treeId, parentNode, childNodes) {
        if (childNodes.code == '0') {
            if (childNodes.data.length < 1) {
                layer.msg('没有下级的菜单啦~~~');
                return null;
            }

            // 将选择了的放进去 数组装起来
            for(var i=0;i<childNodes.data.length;i++){
                if(childNodes.data[i].checked=='true'){
                    selectMenusList.push(childNodes.data[i]);
                }
            }
            return childNodes.data;
        } else {
            layer.msg('获取下级菜单失败!');
        }
    }


    /**
     *  获取菜单数据
     */
    var getMenuListPage = function () {
        $.post(serverPath + "/sys/menu/findMenuListByPage.htm", {
            pageNum: filter.pageNum,
            pageSize: filter.pageSize,
            name: $('#searchName')[0].value,
            createTime: $('#searchCreateTime')[0].value
        }, function (data) {
            var retObj = JSON.parse(data);
            if (retObj.code == '0') {
                // 处理 --
                initMenuTable(retObj.data.list);
                page.count = retObj.data.total;
                initPgae();
                initTree();
                isPaging = false;
                layer.closeAll();
            } else {
            }
        });
    }

    /**
     *   保存系统菜单
     *      通过Id 判断是更新还是新增
     * @param data
     */
    var saveMenu = function (data) {
        var url = '';
        if (data.id != null && data.id != '') {
            url = serverPath + '/sys/menu/updateSysMenu.htm';
        } else {
            url = serverPath + '/sys/menu/saveSysMenu.htm'
        }
        $.post(url, data, function (data) {
            var retData = JSON.parse(data);
            if (retData.code == '0') {
                layer.closeAll();
                layer.msg(retData.msg);
                getMenuListPage();
            } else {
                layer.msg(retData.msg);
            }
        });
    }

    /**
     *   通过Id 删除菜单
     * @param menuId
     */
    var delMenuById = function (menuId) {
        $.post(serverPath + '/sys/menu/delSysMenuById.htm', {
            id: menuId
        }, function (data) {
            var retData = JSON.parse(data);
            if (retData.code == '0') {
                layer.closeAll();
                layer.msg(retData.msg);
                getMenuListPage();
            } else {
                layer.msg(retData.msg);
            }
        });
    }

    /**
     *  通过类别获取菜单数据
     *      PS : 进行了缓存 -->
     * @param type
     */
    var findMenuByType = function (type,isEdit,parentId) {

        var list = sessionStorage.getItem('menu_' + type);
        if (list != null) {
            list = JSON.parse(list);
            //
            if(isEdit){
                renderParentMenu(list,parentId);
            }else{
                refreshMenuSelect(list);
            }
        } else {
            $.post(serverPath + '/sys/menu/findSysMenuListByType.htm', {
                type: type
            }, function (data) {
                var retData = JSON.parse(data);
                if (retData.code == '0') {
                    var menuList = retData.data;
                    sessionStorage.setItem('menu_' + type, JSON.stringify(menuList));
                    //
                    if(isEdit){
                        renderParentMenu(list,parentId);
                    }else{
                        refreshMenuSelect(menuList);
                    }
                } else {
                    layer.msg(retData.msg);
                }
            });
        }
    }

    /**
     *   清除下拉菜单缓存
     */
    var clearAllCache = function () {
    }


    /**
     *  批量删除 通过Id
     * @param ids
     */
    var batchDelMenusByIds = function (ids) {
        $.ajax({
            type: "POST",
            url: serverPath + "/sys/menu/batchDelSysMenuByIds.htm",
            dataType: "json",
            traditional: true,
            data: {
                ids: ids
            },
            async: true,
            success: function (data) {
                if (data.code == '0') {
                    layer.closeAll();
                    layer.msg('删除成功!');
                    getMenuListPage();
                }
            },
            error: function (e) {
                layer.msg('删除失败!');
            }
        });
    }

    init();
});

