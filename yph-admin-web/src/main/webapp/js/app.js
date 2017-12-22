var tab;
layui.define(['element', 'nprogress', 'form', 'table', 'loader', 'tab', 'navbar', 'onelevel', 'laytpl', 'spa'], function(exports) {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        _win = $(window),
        _doc = $(document),
        _body = $('.kit-body'),
        form = layui.form,
        table = layui.table,
        loader = layui.loader,
        navbar = layui.navbar,
        _componentPath = 'components/',
        spa = layui.spa;
    tab = layui.tab
    var app = {
        hello: function(str) {
            layer.alert('Hello ' + (str || 'test'));
        },
        config: {
            type: 'iframe'
        },
        set: function(options) {
            var that = this;
            $.extend(true, that.config, options);
            return that;
        },
        init: function() {
            var that = this,
                _config = that.config;
            if (_config.type === 'spa') {
                navbar.bind(function(data) {
                    spa.set({
                        // openWait: true
                    }).render(data.url, function() {
                        console.log('渲染完成..');
                    });
                });
            }
            if (_config.type === 'page') {
                tab.set({
                    renderType: 'page',
                    mainUrl: 'table.html',
                    elem: '#container',
                    onSwitch: function(data) { //选项卡切换时触发
                    },
                    closeBefore: function(data) { //关闭选项卡之前触发
                        return true; //返回true则关闭
                    }
                }).render();
                //navbar加载方式一，直接绑定已有的dom元素事件                
                navbar.bind(function(data) {
                    tab.tabAdd(data);
                });
            }
            if (_config.type === 'iframe') {
                tab.set({
                    //renderType: 'iframe',
                    //mainUrl: 'table.html',
                    //openWait: false,
                    elem: '#container',
                    onSwitch: function(data) { //选项卡切换时触发
                    },
                    closeBefore: function(data) { //关闭选项卡之前触发
                        return true; //返回true则关闭
                    }
                }).render();
                //navbar加载方式一，直接绑定已有的dom元素事件                
                navbar.bind(function(data) {
                    tab.tabAdd(data);
                });

                //处理顶部一级菜单
                var onelevel = layui.onelevel;
                if (onelevel.hasElem()) {
                    onelevel.set({
                        remote: {
                            url: '/datas/onelevel1.json' //远程地址
                        },
                        onClicked: function(id) {
                            switch (id) {
                                case 1:
                                    navbar.set({
                                        remote: {
                                            url: '/datas/navbar1.json'
                                        }
                                    }).render(function(data) {
                                        tab.tabAdd(data);
                                    });
                                    break;
                                case 2:
                                    navbar.set({
                                        remote: {
                                            url: '/datas/navbar2.json'
                                        }
                                    }).render(function(data) {
                                        tab.tabAdd(data);
                                    });
                                    break;
                                default:
                                    navbar.set({
                                        data: [{
                                            id: "1",
                                            title: "基本元素",
                                            icon: "fa-cubes",
                                            spread: true,
                                            children: [{
                                                id: "7",
                                                title: "表格",
                                                icon: "&#xe6c6;",
                                                url: "test.html"
                                            }, {
                                                id: "8",
                                                title: "表单",
                                                icon: "&#xe63c;",
                                                url: "form.html"
                                            }]
                                        }, {
                                            id: "5",
                                            title: "这是一级导航",
                                            icon: "fa-stop-circle",
                                            url: "https://www.baidu.com",
                                            spread: false
                                        }]
                                    }).render(function(data) {
                                        tab.tabAdd(data);
                                    });
                                    break;
                            }
                        },
                        renderAfter: function(elem) {
                            elem.find('li').eq(0).click(); //模拟点击第一个
                        }
                    }).render();
                }
            }

            // ripple start
            var addRippleEffect = function(e) {
                // console.log(e);
                layui.stope(e)
                var target = e.target;
                if (target.localName !== 'button' && target.localName !== 'a') return false;
                var rect = target.getBoundingClientRect();
                var ripple = target.querySelector('.ripple');
                if (!ripple) {
                    ripple = document.createElement('span');
                    ripple.className = 'ripple'
                    ripple.style.height = ripple.style.width = Math.max(rect.width, rect.height) + 'px'
                    target.appendChild(ripple);
                }
                ripple.classList.remove('show');
                var top = e.pageY - rect.top - ripple.offsetHeight / 2 - document.body.scrollTop;
                var left = e.pageX - rect.left - ripple.offsetWidth / 2 - document.body.scrollLeft;
                ripple.style.top = top + 'px'
                ripple.style.left = left + 'px'
                ripple.classList.add('show');
                return false;
            }
            document.addEventListener('click', addRippleEffect, false);
            // ripple end

            return that;
        }
    };

    //输出test接口
    exports('app', app);
});