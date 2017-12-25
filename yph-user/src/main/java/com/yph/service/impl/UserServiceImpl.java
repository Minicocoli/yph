package com.yph.service.impl;

import com.yph.entity.user.User;
import com.yph.mapper.user.UserMapper;
import com.yph.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 *  用户服务
 * @author : Administrator Hzhan
 * @create ：2017/12/25
 **/
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    /**
     *  保存用户
     * @param user
     * @return
     */
    @Override
    public int save(User user) {
        log.info("[新增用户]");
        return userMapper.insert(user);
    }

    /**
     *  通过条件更新用户
     * @param user
     * @return
     */
    @Override
    public int updateUserByExample(User user) {
        return 0;
    }

    @Override
    public int updateUserById(Long id) {
        return 0;
    }

    /**
     *  通过Id 删除用户
     * @param id
     * @return
     */
    @Override
    public int delUserById(Long id) {
        return 0;
    }

    /**
     *  通过Id 查找用户
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return null;
    }

    /**
     *  通过条件查找用户
     * @param user
     * @return
     */
    @Override
    public User findByExample(User user) {
        return null;
    }

    /**
     *  查找用户 按照条件
     * @param params
     * @return
     */
    @Override
    public List<User> findUserListByParams(HashMap<String, Object> params) {
        return null;
    }

    /**
     *  登录接口 --》
     * @param userName
     * @param password
     * @return
     */
    public User findUserByUserNameAndPassword(String userName,String password){
        HashMap<String, Object> params = new HashMap<>();
        params.put("userName",userName);
        params.put("password",password);
        return userMapper.findUserByUserNameAndPassword(params);
    }

}
