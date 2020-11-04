package com.lockie.clouduser.controller;

import com.lockie.clouduser.model.User;
import com.lockie.clouduser.service.CloudUserService;
import com.lockie.common.controller.BaseController;
import com.lockie.common.model.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author: lockie
 * @Date: 2020/1/10 16:26
 * @Description:
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/cloudUser")
public class CloudUserController extends BaseController {
    @Value("${nacos.test:132}")
    String nacosTest;

    @Autowired
    CloudUserService cloudUserService;

    @Value("${spring.application.name}")
    String applicationName;

    /**
     * 通用接口
     * @return
     */
    @GetMapping("/helloWord")
    public Results helloWord() {
        return succeed("hello, this is " + applicationName);
    }

    @GetMapping("/getProperties")
    public Results getProperties() {
        return succeed(nacosTest);
    }

    /**
     * 获取用户名
     * @return
     */
    @GetMapping("/getUserName")
    public String getUserName() {
        return "lockie";
    }

    /**
     * 根据ID查询
     * @param userId
     * @return
     */
    @GetMapping("/getUserById")
    public User getUserById(@RequestParam("userId") Integer userId) {
        return cloudUserService.getUserById(userId);
    }

    /**
     * 增加用户账户金额
     * @param userId
     * @param accountMoney
     * @return
     */
    @GetMapping("/increaseMoney")
    public User increaseMoney(@RequestParam("userId") Integer userId, @RequestParam("accountMoney") BigDecimal accountMoney) {
        if (userId == null || accountMoney == null) {
            return null;
        }
        User user = cloudUserService.increaseMoney(userId, accountMoney);
        return user;
    }

    /**
     * 减少用户账户金额
     *
     * @param userId
     * @param accountMoney
     * @return
     * @throws Exception
     */
    @GetMapping("/decreaseMoney")
    public User decreaseMoney(@RequestParam("userId") Integer userId, @RequestParam("accountMoney") BigDecimal accountMoney) throws Exception {
        if (userId == null || accountMoney == null) {
            return null;
        }
        User user = cloudUserService.decreaseMoney(userId, accountMoney);
        return user;
    }
}
