package com.lockie.clouduser.service;


import com.lockie.clouduser.model.User;

/**
 * @author: 邹细良
 * @Date: 2020/9/8 16:32
 * @Description:
 */
public interface CloudUserService {

    User getUserById(Integer userId);
}
