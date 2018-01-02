package com.yph.entity.sys.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单 vo 类
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
@Data
public class SysMenuVo implements Serializable{

    /**
     *  菜单名称
     */
    private String name ;

    /**
     *  地址连接
     */
    private String url;

    /**
     *  类型
     */
    private Integer type;

    /**
     *  子菜单
     */
    private List<SysMenuVo> list =new ArrayList<>();

}
