package com.yph.entity.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 对ztree封装的视图vo
 * 各种属性不是很完整 按需求进行添加
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/9
 **/
@Data
public class ZtreeVo implements Serializable {

    private static final long serialVersionUID = -556552556449771704L;

    /**
     * 映射到数据库中记录的Id
     */
    private Long id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 复选框  是否展开
     */
    private String open;

    /**
     * 是否被选中
     */
    private String checked;

    /**
     * 上级节点Id
     */
    private Long parentId;

    /**
     * 子节点
     */
    private List<ZtreeVo> children = new ArrayList<>();

    /**
     *  是否是根节点
     */
    private String isParent;

    /**
     * 菜单类型
     */
    private Integer type;


    public String getIsParent() {
       if(type!=2){
           return "true";
       }else{
           return "false";
       }
    }

    public void setIsParent(String isParent) {
        if(type!=2){
            this.isParent="true";
        }else{
            this.isParent = "false";
        }

    }
}
