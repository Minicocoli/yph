package com.yph.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description:
 * @Author :Hzhan
 * @Date :Create in 10:42 2017/12/24
 */
@Data
public class BaseEntity implements Serializable{
    private String name;
    private String url;
    private Integer type;
}
