package com.yph.entity.sys.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息Vo
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/3
 **/
@Data
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = 5927605889569220214L;

    private Long id;

    private String userName;

    private String password;

    private String email;

    private String mobile;

    private Byte status;

    private Long createUserId;

    private Date createTime;




}
