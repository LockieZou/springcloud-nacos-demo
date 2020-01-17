package com.lockie.bootuser.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.lockie.bootuser.model.Results;
import lombok.extern.slf4j.Slf4j;
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
}
