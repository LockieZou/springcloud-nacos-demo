package com.lockie.clouduser.service.impl;

import com.lockie.clouduser.mapper.UserMapper;
import com.lockie.clouduser.model.User;
import com.lockie.clouduser.service.CloudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: lockie
 * @Date: 2020/9/8 16:33
 * @Description:
 */
@Service
public class CloudUserServiceImpl implements CloudUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 增加账户金额
     * @param userId
     * @param accountMoney
     * @return
     */
    @Override
    public User increaseMoney(Integer userId, BigDecimal accountMoney) {
        if (userId == null || accountMoney == null) {
            return null;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return null;
        }
        BigDecimal money = user.getAccountMoney();
        BigDecimal totoalMoney = money.add(accountMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
        user.setAccountMoney(totoalMoney);
        userMapper.updateByPrimaryKey(user);

        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 减少账户金额
     * @param userId
     * @param accountMoney
     * @return
     */
    @Override
    public User decreaseMoney(Integer userId, BigDecimal accountMoney) throws Exception {
        if (userId == null || accountMoney == null) {
            return null;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return null;
        }
        BigDecimal money = user.getAccountMoney();
        // 判断用户金额是否够本次扣款金额
        if (money.compareTo(accountMoney) < 0) {
            throw new Exception("账户余额不足！");
        }

        BigDecimal totoalMoney = money.subtract(accountMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
        user.setAccountMoney(totoalMoney);
        userMapper.updateByPrimaryKey(user);

        return userMapper.selectByPrimaryKey(userId);
    }
}
