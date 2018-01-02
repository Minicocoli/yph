package com.yph.service.sys.impl;

import com.yph.entity.sys.SysMenu;
import com.yph.entity.sys.vo.SysMenuVo;
import com.yph.mapper.sys.SysMenuMapper;
import com.yph.service.sys.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 菜单Service 实现类
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
@Slf4j
@Service
@Transactional
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    /**
     *  保存系统菜单
     * @param sysMenu
     * @return
     */
    @Override
    public int saveSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }

    /**
     *  删除系统菜单
     * @param sysMenu
     * @return
     */
    @Override
    public int delSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.updateByPrimaryKey(sysMenu);
    }

    /**
     * 更新系统菜单
     * @param sysMenu
     * @return
     */
    @Override
    public int updateSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.updateByPrimaryKey(sysMenu);
    }

    /**
     *  根据参数查询菜单
     * @param params
     * @return
     */
    @Override
    public List<SysMenu> findSysMenusList(Map<String, Object> params) {
        return null;
    }


    /**
     *  用户Id
     * @param userId
     * @return
     */
    public List<SysMenuVo> findSysMenusByRole(Long userId){



        return null;
    };

}
