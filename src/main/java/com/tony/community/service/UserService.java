package com.tony.community.service;

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

}
