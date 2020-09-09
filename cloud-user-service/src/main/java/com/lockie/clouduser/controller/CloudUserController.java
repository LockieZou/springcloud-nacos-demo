package com.lockie.clouduser.controller;

import com.lockie.clouduser.model.Results;
import com.lockie.clouduser.service.CloudUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Results getUserById(@RequestParam("userId") Integer userId) {
        return succeed(cloudUserService.getUserById(userId));
    }
}
