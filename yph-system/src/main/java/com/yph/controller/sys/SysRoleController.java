package com.yph.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yph.common.result.CommonResult;
import com.yph.entity.sys.SysRole;
import com.yph.service.sys.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 角色控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/6
 **/
@Slf4j
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;


    /**
     *  保存系统角色
     * @param sysRole
     * @return
     */
    @ResponseBody
    @RequestMapping("saveSysRole")
    public CommonResult saveSysRole(SysRole sysRole){
        int result = sysRoleService.saveSysRole(sysRole);
        if(result<1){
            log.info("[ 系统角色 ] -----> 保存系统角色失败");
            return CommonResult.ERROR("保存系统角色失败");
        }
        return CommonResult.SUCCESS();
    }

    /**
     *  删除系统角色
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delSysRoleById")
    public CommonResult delSysRoleById(Long id){
        int result = sysRoleService.delSysRoleById(id);
        if(result<1){
            log.info("[ 系统角色 ] -----> 删除系统角色失败");
            return CommonResult.ERROR("删除系统角色失败");
        }
        return CommonResult.SUCCESS();
    }

    /**
     *  更新系统角色
     * @param sysRole
     * @return
     */
    @ResponseBody
    @RequestMapping("updateSysRole")
    public CommonResult updateSysRole(SysRole sysRole){
        int result = sysRoleService.updateSysRole(sysRole);
        if(result<1){
            log.info("[ 系统角色 ] -----> 删除系统角色失败");
            return CommonResult.ERROR("删除系统角色失败");
        }
        return CommonResult.SUCCESS();
    }

    /**
     *  通过Id 查询系统角色
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("findSysRoleOne")
    public CommonResult findSysRoleOne(Long id){
      SysRole selectSysRole =   sysRoleService.findSysRoleById(id);
      return CommonResult.SUCCESS(selectSysRole);
    }


    /**
     *  获取系统角色 分页
     * @return
     */
    @ResponseBody
    @RequestMapping("findSysRoleListByPage")
    public CommonResult findSysRoleListByPage(@RequestParam HashMap<String,Object> params,int pageNum,int pageSize){
      PageInfo pageInfo=  sysRoleService.findSysRoleListByPage(params,pageNum,pageSize);
      return CommonResult.SUCCESS(pageInfo);
    }

    /**
     *  获取角色列表
     * @return
     */
    @ResponseBody
    @RequestMapping("findSysRoleList")
    public CommonResult findSysRoleList(@RequestParam HashMap<String ,Object> params){
      List<SysRole> list =  sysRoleService.findSysRoleList(params);
      return CommonResult.SUCCESS(list);
    }


    /**
     *  批量删除
     * @return
     */
    @ResponseBody
    @RequestMapping("batchDelSysRoleByIds")
    public CommonResult batchDelSysRoleByIds(HttpServletRequest request){
        String[] ids = request.getParameterValues("ids");
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(Long.valueOf(ids[i]));
        }
        int result=  sysRoleService.batchDelSysRoleByIds(list);
        return CommonResult.SUCCESS();
    }


}
