package com.yph.entity.sys.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Data
public class SysMenuVo implements Serializable{

    private static final long serialVersionUID = 2688495338266644086L;

    /**
     *  菜单Id
     */
    private Long id;

    /**
     *  菜单名称
     */
    private String name;
    /**
     *  菜单地址链接
     */
    private String url;

    /**
     *  子菜单
     */
    private List<SysMenuVo> menuList =new ArrayList<>();


}
