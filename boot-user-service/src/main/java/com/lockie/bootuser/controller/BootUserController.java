package com.lockie.bootuser.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.lockie.bootuser.service.BootUserService;
import com.lockie.common.controller.BaseController;
import com.lockie.common.model.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lockie
 * @Date: 2019/12/31 16:38
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/bootUser")
public class BootUserController extends BaseController {

    @NacosValue(value = "${nacos.test:123}", autoRefreshed = true)
    String properties;

    @Autowired
    BootUserService bootUserService;

    @RequestMapping("/getProperties")
    public Results getProperties() {
        return succeed(properties);
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
     * 根据用户ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public Results getUserById(Integer id) {
        return succeed(bootUserService.getUserById(id));
    }
}
