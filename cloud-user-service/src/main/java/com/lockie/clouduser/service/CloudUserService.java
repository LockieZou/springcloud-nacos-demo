package com.lockie.clouduser.service;


import com.lockie.clouduser.model.User;

import java.math.BigDecimal;

/**
 * @author: lockie
 * @Date: 2020/9/8 16:32
 * @Description:
 */
public interface CloudUserService {

    User getUserById(Integer userId);

    /**
     * 增加用户账户金额
     * @param userId
     * @param accountMoney
     * @return
     */
    User increaseMoney(Integer userId,BigDecimal accountMoney);

    /**
     * 减少用户账户金额
     * @param userId
     * @param accountMoney
     * @return
     * @throws Exception
     */
    User decreaseMoney(Integer userId, BigDecimal accountMoney) throws Exception;
}
