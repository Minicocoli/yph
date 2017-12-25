package com.yph.service;


import com.yph.entity.user.User;

import java.util.HashMap;
import java.util.List;

public interface IUserService {

    /**
     *  保存用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     *  根据条件更新用户信息
     * @param user
     * @return
     */
    int updateUserByExample(User user);

    /**
     *  通过Id 更新用户
     * @param id
     * @return
     */
    int updateUserById(Long id);

    /**
     *  通过Id 删除用户
     * @param id
     * @return
     */
    int delUserById(Long id);

    /**
     *  通过Id 查找用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     *  通过条件查找用户
     * @param user
     * @return
     */
    User findByExample(User user);

    /**
     *  查找用户列表 ，通过条件。
     * @param params
     * @return
     */
    List<User> findUserListByParams(HashMap<String,Object> params);


    User findUserByUserNameAndPassword(String userName , String password);

}

