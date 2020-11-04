package com.lockie.bootuser.service.impl;

import com.lockie.bootuser.mapper.UserMapper;
import com.lockie.bootuser.model.User;
import com.lockie.bootuser.service.BootUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lockie
 * @Date: 2020/9/8 16:33
 * @Description:
 */
@Service
public class BootUserServiceImpl implements BootUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
