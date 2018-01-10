/***
 *  系统角色 v 1.0
 *
 * @type {null}
 */

var openCreateWindow = null;

var batchDel = null;

var formSearch = null;

var saveRoleMenu =null;

layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'element'], function () {

    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , form = layui.form //分页
    layer = layui.layer //弹层
        , table = layui.table //表格
        , element = layui.element; //元素操作


    // 项目路径
    var serverPath = $('#path').val();

    var selectMenusList = [];

    // 设置表格宽高
    var bodyWidth = $('#body')[0].offsetWidth;
    var contentHeight = $('#content')[0].offsetHeight;
    $('#content')[0].style.width = bodyWidth + 'px';
    var tableHeight = contentHeight - 136;


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
                        getTableList();
                    } else {
                        layer.msg('亲! 不要点击这么快啦~~~~ ');
                    }
                }

            }
        });
    }

    /**
     *  获取树形设置
     */
    var getZtreeSetting =function (roleId) {
        // ztree 设置
        var setting = {
            // 异步请求
            async: {
                enable: true,
                url: serverPath + '/sys/menu/findListByZtree.htm?roleId='+roleId,
                autoParam: ["id"],
                // otherParam:["roleId",selectRoleId],
                dataFilter: datasFilter,
                type: 'post'
            },
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "ps", "N": "ps"}
            }
        };
        return setting;
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
                if(childNodes[i].checked=='true'){
                    selectMenusList.push(childNodes.data[i]);
                }
            }
            return childNodes.data;
        } else {
            layer.msg('获取下级菜单失败!');
        }
    }




    /**
     *  初始化表格
     * @param tableData
     */
    var initTable = function (tableData) {
        table.render({
            elem: '#layMenu'
            , id: 'idRole'
            , height: tableHeight
            // , width: tableWidth
            , data: tableData
            , loading: true
            , limit: page.limit
            , skin: 'row'
            , even: true
            , cols: [[ //表头
                {field: 'left', title: '', type: 'checkbox', width: 80, align: 'center'}
                , {field: '', title: '序号', type: 'numbers', width: 80, align: 'center'}
                , {field: 'roleName', title: '角色名称', width: 200, align: 'center'}
                , {field: 'remark', title: '备注', width: 180, align: 'center'}
                , {field: 'createUser', title: '创建人', width: 180, align: 'center'}
                , {field: 'createTime', title: '创建时间', width: 170, align: 'center'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barOption'}
            ]]
        });
    }


    /**
     *  开启创建窗口
     */
    openCreateWindow = function () {
        layer.open({
            type: 1,
            title: ['新建角色', 'font-size:18px;'],
            offset: 'auto',
            anim: 2,
            fixed: false,
            offset: '100px',
            scrollbar: false,
            resize: false,
            area: ['850px', '300px'],
            content: $('#createWindow'),
            end: function () {
                $('#roleName')[0].value = '';
                $('#remark')[0].value = '';
                $('#roleId')[0].value = '';
            }
        });
    }

    /**
     *  初始化日期
     */
    laydate.render({
        elem: '#searchCreateTime'
    });


    //监听工具条
    table.on('tool(roleFilter)', function (obj) {
        if (obj.event === 'edit') {
            openEditWindow(obj.data);
        } else if (obj.event === 'del') {
            layer.confirm('确定要删除所选的?', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                delSysRole(obj.data.id);
            });
        }
    });

    /**
     *  开启编辑窗口
     * @param data
     */
    var openEditWindow = function (data) {

        selectMenusList =[];

        var setting = getZtreeSetting(data.id);
        // 数据回显
        $.fn.zTree.init($("#tree"), setting);

        layer.open({
            type: 1,
            title: ['修改角色', 'font-size:18px;'],
            offset: 'auto',
            anim: 2,
            fixed: false,
            offset: '100px',
            scrollbar: false,
            resize: false,
            area: ['850px', '660px'],
            content: $('#createWindow'),
            end: function () {

            }
        });
    }

    /**
     *  监听 新增菜单的保存按钮操作
     */
    form.on('submit(saveSysRole)', function (data) {
        saveSysRole(data.field);
        return false;
    });




    /**
     *  批量删除
     */
    batchDel = function () {
        layer.confirm('确定要删除所选的？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var selectIds = [];
            var selectObjList = table.checkStatus('idRole').data;
            if (selectObjList.length > 0) {
                for (var i = 0; i < selectObjList.length; i++) {
                    selectIds.push(selectObjList[i].id);
                }
                batchDelSysRoleByIds(selectIds);
            } else {
                layer.msg('请选择要删除的喔!');
            }
        }, function () {
        });
    }

    /**
     *  搜索功能
     */
    formSearch = function () {
        getTableList();
    }

    /**
     *  保存角色权限
     */
    saveRoleMenu =function () {
        // var treeObj = $.fn.zTree.getZTreeObj("tree");
        // var nodes = treeObj.getNodes();

        // 获取当前被勾选的节点集合
        var treeObj1 = $.fn.zTree.getZTreeObj("tree");
        var newSelectMenuList = treeObj1.getCheckedNodes(true);

        // 1、新增的菜单选项
        var addMenuList = [];
        // 2、移除的菜单选项
        var removeMenuList = [];







        //
        // for(var i=0;i<selectMenusList.length;i++){
        //     for(var j=0;j<newSelectMenuList.length;j++){
        //
        //     }
        // }















    }


    /*****************************************  END   【  以上是lay组件初始化   】 *****************************************/

    /*****************************************  START 【 以下是获取数据 操作数据 】 *****************************************/

    /**
     *  页面初始化
     */
    var init = function () {

        initPgae();

        initTable();

        getTableList();

    }


    /**
     *  获取表格数据
     */
    var getTableList = function () {
        $.post(serverPath + "/sys/role/findSysRoleListByPage.htm", {
            pageNum: filter.pageNum,
            pageSize: filter.pageSize,
            roleName: $('#searchName')[0].value,
            createTime: $('#searchCreateTime')[0].value
        }, function (data) {
            var retObj = JSON.parse(data);
            if (retObj.code == '0') {
                // 处理 --
                initTable(retObj.data.list);
                page.count = retObj.data.total;
                initPgae();
                isPaging = false;
                layer.closeAll();
            } else {
                layer.msg('查询错误 : ' + retObj.msg);
            }
        });
    }

    /**
     *  保存系统角色
     */
    var saveSysRole = function (data) {
        var url = '';
        if (data.id != null && data.id != '') {
            url = serverPath + '/sys/role/updateSysRole.htm';
        } else {
            url = serverPath + '/sys/role/saveSysRole.htm';
        }
        $.post(url, data, function (data) {
            var retData = JSON.parse(data);
            if (retData.code == '0') {
                layer.closeAll();
                layer.msg('保存成功!');
                getTableList();
            } else {
                layer.msg(retData.msg);
            }
        });
    }


    /**
     *  删除系统角色
     */
    var delSysRole = function (roleId) {
        $.post(serverPath + '/sys/role/delSysRoleById.htm', {
            id: roleId
        }, function (data) {
            var retData = JSON.parse(data);
            if (retData.code == '0') {
                layer.closeAll();
                layer.msg('保存成功!');
                getTableList();
            } else {
                layer.msg(retData.msg);
            }
        });
    }

    /**
     *   批量删除
     * @param ids
     */
    var batchDelSysRoleByIds = function (ids) {
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
                    getTableList();
                }
            },
            error: function (e) {
                layer.msg('删除失败!');
            }
        });
    }

    /**
     *  获取菜单树形
     */
    var getMenuListTree = function () {
        $.post(serverPath + "/sys/menu/findAllMenu2TreeList.htm", function (data) {
            var retObj = JSON.parse(data);
            if (retObj.code == '0') {
                // initTree(retObj.data);
            } else {
                // do something
            }
        });
    }




    init();
});