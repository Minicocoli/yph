package com.yph.entity.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * TreeView 封装类
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Data
public class TreeVo implements Serializable{

    private static final long serialVersionUID = -3413513389534144830L;

    /**
     *  id
     */
    private Long id;

    /**
     *  名称
     */
    private String text ;

    /**
     *  节点
     */
    private List<TreeVo> nodes ;

}
