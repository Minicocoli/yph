package com.yph.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yph.common.annotation.SysLog;
import com.yph.common.result.CommonResult;
import com.yph.entity.sys.SysMenu;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.entity.tree.TreeVo;
import com.yph.entity.tree.ZtreeVo;
import com.yph.service.sys.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 菜单控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Slf4j
@Controller
@RequestMapping("/sys/menu/")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 新增保存菜单
     *
     * @param sysMenu
     * @return
     */
    @ResponseBody
    @RequestMapping("saveSysMenu")
    public CommonResult saveSysMenu(SysMenu sysMenu) {
        Long result = sysMenuService.saveSysMenu(sysMenu);
        if (result < 1) {
            return CommonResult.ERROR("保存菜单失败!");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 通过菜单Id 删除菜单
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delSysMenuById")
    public CommonResult delSysMenuById(Long id) {
        int result = sysMenuService.delSysMenuById(id);
        if (result < 1) {
            return CommonResult.ERROR("删除菜单失败!");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 更新菜单信息
     *
     * @param sysMenu
     * @return
     */
    @SysLog("")
    @ResponseBody
    @RequestMapping("updateSysMenu")
    public CommonResult updateSysMenu(SysMenu sysMenu) {
        int result = sysMenuService.updateSysMenu(sysMenu);
        if (result < 1) {
            return CommonResult.ERROR("更新菜单失败!");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 通过类型获取菜单类别
     *
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping("findSysMenuListByType")
    public CommonResult findSysMenuListByType(Integer type,Long parentId) {
        List<SysMenu> list = sysMenuService.findSysMenuListByType(type,parentId);
        return CommonResult.SUCCESS(list);
    }


    /**
     * 获取所有菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllMenu2TreeList")
    public CommonResult findAllMenu2TreeList() {
        List<TreeVo> treeList = sysMenuService.findAllMenu2TreeList();
        return CommonResult.SUCCESS(treeList);
    }

    /**
     * 获取系统菜单 分页操作
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("findMenuListByPage")
    public CommonResult findMenuListByPage(@RequestParam HashMap<String, Object> params, int pageNum, int pageSize) {
        PageInfo pageInfo = sysMenuService.findMenuListByPage(params, pageNum, pageSize);
        return CommonResult.SUCCESS(pageInfo);
    }


    /**
     * 批量删除
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("batchDelSysMenuByIds")
    public CommonResult batchDelSysMenuByIds(HttpServletRequest request) {
        String[] ids = request.getParameterValues("ids");
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(Long.valueOf(ids[i]));
        }
        int result = sysMenuService.batchDelSysMenuByIds(list);
        if (result < 1) {
            return CommonResult.ERROR("删除失败!");
        }
        return CommonResult.SUCCESS();
    }


    /**
     * 获取菜单列表  ztree
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("findListByZtree")
    public CommonResult findListByZtree(@RequestParam HashMap<String, Object> params) {
        List<ZtreeVo> list = sysMenuService.findListByZtree(params);
        return CommonResult.SUCCESS(list);
    }


}
