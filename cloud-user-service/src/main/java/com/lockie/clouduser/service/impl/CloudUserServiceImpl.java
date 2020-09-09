package com.lockie.clouduser.service.impl;

import com.lockie.clouduser.mapper.UserMapper;
import com.lockie.clouduser.model.User;
import com.lockie.clouduser.service.CloudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 邹细良
 * @Date: 2020/9/8 16:33
 * @Description:
 */
@Service
public class CloudUserServiceImpl implements CloudUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
