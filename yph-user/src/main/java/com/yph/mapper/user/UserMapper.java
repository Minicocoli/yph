package com.yph.mapper.user;


import com.yph.entity.user.User;

import java.util.HashMap;

/**
 *  @Description : 用户orm 访问接口
 *  @author : Hzhan
 *  @Date : create in 2017-12-26
 *
 **/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByUserNameAndPassword(HashMap<String, Object> params);
}