package com.tony.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tony.community.domain.User;
import com.tony.community.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表
 */

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 新增
     */
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

    /**
     * 删除
     */
    public void delete(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getAccountId, id);
        userMapper.delete(wrapper);
    }

    /**
     * 修改
     */
    public User update(User user){
        userMapper.updateById(user);
        return user;
    }

    /**
     * 根据token获取user
     */
    public User queryByToken(String token) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getToken, token);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据账户Id获取user
     */
    public User queryByAccountId(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getAccountId, id);
        return userMapper.selectOne(wrapper);
    }

}
