package com.yph.entity.sys.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色VO
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Data
public class SysRoleVo implements Serializable {

    private static final long serialVersionUID = 5428135504234525762L;

    private Long id;

    private String roleName;

    private String remark;

    private Long createUserId;

    private Date createTime;


}
